$(document).ready(function () {
    $('.createoder_here').click(function (event) {
        // Collect data from the form
        let fullName = $('input[name="fullName"]').val().trim();
        let number = $('input[name="number"]').val().trim();
        let emailuser = $('input[name="emailuser"]').val().trim();
        let address = $('input[name="address"]').val().trim();
        let paymentMethod = $('#inlineFormCustomSelectPref').val().trim();
        let notice = $('textarea[name="notice"]').val().trim();

        let totalprices = $('.text-red').data("total");

        // Collect product data
        const products = [];
        document.querySelectorAll('.image-product').forEach((element) => {
            products.push({
                productID: element.dataset.productid,
                productSIZE: element.dataset.productsize,
                productCOLOR: element.dataset.productcolor,
                productQT: element.dataset.productqt,
                productPRICE: element.dataset.productprice
            });
        });

        // Prepare the form data
        const formData = {
            fullName: fullName,
            number: number,
            emailuser: emailuser,
            address: address,
            paymentMethod: paymentMethod,
            notice: notice
        };

        // Validation checks
        if (!fullName) {
            alert("Họ và tên không được để trống");
            return;
        }
        if (!number) {
            alert("Số điện thoại không được để trống");
            return;
        }
        if (!emailuser) {
            alert("Email không được để trống");
            return;
        }
        if (!address) {
            alert("Địa chỉ không được để trống");
            return;
        }

        if (products.length === 0) {
            alert("Không có sản phẩm nào trong giỏ hàng. Vui lòng thêm sản phẩm trước khi đặt hàng.");
            return;
        }
        if (paymentMethod === "1") {
            // Send POST request for payment method 1
            $.ajax({
                url: '/user/thanksuser', // Your Spring Boot API
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    formData: formData,
                    products: products,
                    amount: totalprices,
                    orderInfo: 132,
                    selectedPayment: paymentMethod
                }),
                success: function (response) {
                    console.log(response);
                    alert("Đơn hàng đã được gửi thành công!");
                },
                error: function (xhr, status, error) {
                    console.error(xhr.responseText);
                    alert("Đã xảy ra lỗi khi gửi đơn hàng.");
                }
            });
        } else {
            // Send POST request for other payment methods
            fetch('/submitOrder', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    formData: formData,
                    products: products,
                    orderTotal: totalprices,
                    orderInfo: 132,
                    selectedPayment: paymentMethod
                })
            })
            .then(response => response.json())
            .then(data => {
                if (data.redirectUrl) {
                    window.location.href = data.redirectUrl;
                } else {
                    console.error("Không có URL chuyển hướng.");
                }
            })
            .catch(error => console.error('Lỗi:', error));
        }
    });
});



$(document).ready(function(){
    $('.goahead').click(function (event) {
        event.preventDefault(); 
        $('html, body').animate({ scrollTop: 0 }, 'slow'); // Cuộn lên đầu trang với hiệu ứng.
    })
});


// Lấy tất cả các nút giảm và tăng
document.querySelectorAll('.cartMinutes').forEach(button => {
    button.addEventListener('click', (e) => {
        const quantityInput = e.target.closest('.control').querySelector('.number-quantity');
        let currentValue = parseInt(quantityInput.value) || 0;

        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
        }

        updateCartItem(
            quantityInput.dataset.productId,  // Lấy ID sản phẩm từ data attribute
            quantityInput.closest('.image-product').dataset.productColor,  // Lấy màu sản phẩm
            quantityInput.value  // Số lượng mới
        );
    });
});

document.querySelectorAll('.cartPlus').forEach(button => {
    button.addEventListener('click', (e) => {
        const quantityInput = e.target.closest('.control').querySelector('.number-quantity');
        let currentValue = parseInt(quantityInput.value) || 0;
        quantityInput.value = currentValue + 1;

        updateCartItem(
            quantityInput.dataset.productId,
            quantityInput.closest('.image-product').dataset.productColor,
            quantityInput.value
        );
    });
});

// Đảm bảo chỉ nhận giá trị số trong input
document.querySelectorAll('.number-quantity').forEach(input => {
    input.addEventListener('input', (e) => {
        if (e.target.value < 1) {
            e.target.value = 1;
        }
    });
});

// Hàm gửi yêu cầu sửa đổi thông tin sản phẩm trong giỏ hàng
function updateCartItem(productId, color, newQuantity) {
    const requestBody = {
        IDP: productId,
        quantity: newQuantity,
        Color: color
    };
    const currentUrl = window.location.href;
    $.ajax({
        url: currentUrl,  // URL của endpoint update
        method: 'PUT',  // Hoặc 'PATCH' nếu muốn sửa một phần
        contentType: 'application/json',
        data: JSON.stringify(requestBody),
        success: function(response) {
            console.log('Sản phẩm đã được cập nhật', response);
            // Cập nhật giao diện nếu cần
        },
        error: function(error) {
            console.log('Có lỗi xảy ra khi cập nhật sản phẩm', error);
        }
    });
}

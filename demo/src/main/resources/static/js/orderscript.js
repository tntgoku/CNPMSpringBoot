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

        if (paymentMethod === "1") {
            // Send POST request for payment method 1
            $.ajax({
                url: '/gotoalert', // Your Spring Boot API
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
})
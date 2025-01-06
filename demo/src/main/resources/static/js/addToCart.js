// Lấy tất cả các phần tử có class "item-option btn-active"
const items = document.querySelectorAll('#option-version .item-option.btn-active');
const colors= document.querySelectorAll('#option-color .item-option.btn-active')
// Get the selected version (Storage Size)


// Output the selected data
// Gắn sự kiện click vào từng phần tử

items.forEach(item => {
    item.addEventListener('click', () => {
        // Loại bỏ class "selected" khỏi tất cả các phần tử
        items.forEach(i => i.classList.remove('selected'));
        
        const loaisanpham= $('#Name-Pro').data('cate');
        const tensanpham =$('#Name-Pro').data('namep');
        item.classList.add('selected');
        const sku = $('.item-option.selected').data('sku');
        const name = $('#option-color .item-option.selected').data('name');
        const storage= $(item).find('span').text().trim();

    
    });
});

colors.forEach(item => {
    item.addEventListener('click', () => {
        // Loại bỏ class "selected" khỏi tất cả các phần tử
        colors.forEach(i => i.classList.remove('selected'));
        
        item.classList.add('selected');
        const sku = $('.item-option.selected').data('sku');
        const name = $('#option-color .item-option.selected').data('name');
        const skua = $('#option-color .item-option.selected').data('skua');
        const price=$('#option-color .item-option.selected p ').text().trim();

        const storage= $('#option-version .item-option.btn-active.selected').find('span').text().trim();
        console.log("SKU:", sku,"\nNAME: ",name,'\nData:',price,"Storage:",storage,"Data-SKUA:", skua);
       
    });
});


$(document).ready(function () {
    $('.add-to-cart-btn').click(function (event) {
        event.preventDefault(); // Ngăn không tải lại trang

        // Lấy dữ liệu từ form
        let quantity = 1;
        let name = $("#Name-Pro").text().trim();
        let name_product=$("#Name-Pro").data('name-p');
        const currentUrl = window.location.href;
        //Dung luong
        const sku = $('.item-option.selected').data('sku');
        const prices=$('#option-color .item-option.selected ').data('pricenote');
        //Mau 
        const color = $('#option-color .item-option.selected').data('name');
        //ID
        const ID = $('#option-color .item-option.selected').data('skua');
        
        // let cartList = JSON.parse(localStorage.getItem("listCart")) || []; // Nếu chưa có giỏ, khởi tạo mảng trống
        
        //  // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        //  let itemExists = false;
        //  for (let i = 0; i < cartList.length; i++) {
        //      if (cartList[i].IDP === ID) {
        //          // Nếu sản phẩm đã tồn tại, tăng số lượng lên
        //          cartList[i].quantity += parseInt(quantity);
        //          itemExists = true;
        //          break;
        //      }
        //  }
 
        //  // Nếu sản phẩm chưa có trong giỏ, thêm mới
        //  if (!itemExists) {
        //      cartList.push({ IDP: ID,color:color ,quantity: parseInt(quantity) });
        //  }
 
         // Lưu lại giỏ hàng vào localStorage
        //  localStorage.setItem("listCart", JSON.stringify(cartList));
        // Gửi yêu cầu AJAX để thêm vào giỏ
        $.ajax({
            url: currentUrl,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                IDP: ID,
                name_pro: name_product,
                Color: color,
                quantity: parseInt(quantity),
                price:prices
            }),
            success: function(response) {
                alert("Sản phẩm đã được thêm vào giỏ hàng!"); // Hiển thị thông báo phản hồi từ server
                console.log(response); // In thông báo phản hồi từ server
                updateTotalQuantity();
                
            },
            error: function(xhr, status, error) {
                alert("Đã xảy ra lỗi khi thêm vào giỏ: " + error);
            }
        });
    });
});



function updateTotalQuantity() {
    // Lấy danh sách giỏ hàng từ localStorage
    let cartList = JSON.parse(localStorage.getItem("listCart")) || [];

    // Tính tổng số lượng
    let totalQuantity = cartList.reduce((total, item) => total + item.quantity, 0);

    // Hiển thị tổng số lượng vào phần tử có lớp 'count_item_pr hidden-count'
    let countElement = document.querySelector('.count_item_pr.hidden-count');
    if (countElement) {
        countElement.textContent = totalQuantity;  // Cập nhật số lượng vào phần tử
    } else {
        console.error("Không tìm thấy phần tử để hiển thị tổng số lượng!");
    }

    return totalQuantity;
}

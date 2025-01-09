$(document).ready(function () {
    $('.btn-deleted-u').click(function () {
        // Get the product ID from the button's data-id attribute
        const productId = $(this).data('id');
        console.log("Product ID:", productId);

        // Send AJAX POST request to the backend to delete the product
        $.ajax({
            url: '/mainProduct', // Your endpoint for deletion
            type: 'POST',
            data: { masanpham: productId }, // Send product ID as 'masanpham'
            success: function (response) {
                alert("Product deleted successfully!");
                console.log(response);
                location.reload(); // Reload the page to reflect the changes
            },
            error: function (xhr, status, error) {
                alert("Error deleting product: " + error);
            }
        });
    });
});


document.addEventListener("DOMContentLoaded", function() {
    const deleteButtons = document.querySelectorAll(".btn-deleted-produt");

    deleteButtons.forEach(button => {
        button.addEventListener("click", function() {
            const productId = button.getAttribute("data-id");

            if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?")) {
                fetch(`/admin/productphone/${productId}`, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        alert("Xóa thành công sản phẩm: " + productId);
                        window.location.reload(); // Refresh trang sau khi xóa
                    } else {
                        alert("Có lỗi xảy ra khi xóa sản phẩm.");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("Không thể kết nối tới server.");
                });
            }
        });
    });
});

// $(document).ready(function() {
//     $('#searchForm').on('submit', function(event) {
//         event.preventDefault();  // Ngừng hành động mặc định của form (không tải lại trang)

//         var searchQuery = $('#searchInput').val();  // Lấy giá trị tìm kiếm từ input

//         // Gửi yêu cầu AJAX đến server
//         $.ajax({
//             url: '/admin/productphone',  // Địa chỉ URL của controller xử lý tìm kiếm
//             method: 'GET',        // Phương thức HTTP là GET
//             data: { search: searchQuery },  // Truyền tham số search qua URL
//             success: function(response) {
//                 // // Khi nhận được kết quả từ server, cập nhật phần #productList
//                 // $('#productList').html(response);  // Thay thế nội dung của #productList bằng kết quả trả về
//             },
//             error: function(xhr, status, error) {
//                 console.log('Có lỗi xảy ra: ' + error);
//             }
//         });
//     });
// });

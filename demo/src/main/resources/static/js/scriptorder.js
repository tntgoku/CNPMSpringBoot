document.getElementById("submitOrderBtn").addEventListener("click", function () {
    // Lấy dữ liệu từ input
    const amount = document.getElementById("amount").value;
  
    // Kiểm tra dữ liệu
    if (!amount || amount <= 0) {
      alert("Vui lòng nhập số tiền hợp lệ.");
      return;
    }
  
    // Gửi dữ liệu tới server
    fetch('/submitOrder', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            orderTotal: amount,
            orderInfo: 132,
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
  });
  
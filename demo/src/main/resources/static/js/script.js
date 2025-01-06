


let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";  
        slides[i].classList.remove("fade"); // Loại bỏ lớp "fade" nếu có
}
for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
    
}
slides[slideIndex-1].style.display = "flex";
for (i = 0; i < slideIndex - 1; i++) {
    slides[i].classList.add("fade");
  }
  dots[slideIndex-1].className += " active";
}

function cartChange(productCode) {
  var input = document.getElementById('quantityInput');
  var value = input.value;
  
  // Kiểm tra nếu giá trị chứa ký tự không phải số
  if (!/^\d+$/.test(value)) {
      alert("Chỉ được nhập các chữ số từ 0 đến 9.");
      input.value = value.replace(/\D/g, ''); // Xóa ký tự không phải số
  }

  // Thực hiện các hành động khác với giá trị hợp lệ
  console.log("Giá trị thay đổi cho sản phẩm", productCode, "với số lượng:", input.value);
}







// 1. what is API
// 2. How do I call API
// 3. Explain code


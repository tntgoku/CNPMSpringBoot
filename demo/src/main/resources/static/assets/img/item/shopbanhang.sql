-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 31, 2024 lúc 04:38 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shopbanhang`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `id` int(12) NOT NULL,
  `name` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `total` int(12) NOT NULL,
  `pttt` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`id`, `name`, `address`, `tel`, `email`, `total`, `pttt`) VALUES
(20, 'bac11', 'hải dương', '2131345612', 'nghia@gmail.com', 924, 0),
(23, 'bac11', 'hai duong', '2131345612', 'nghia@gmail.com', 357, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `id` int(12) NOT NULL,
  `tensp` varchar(100) NOT NULL,
  `img` varchar(100) NOT NULL,
  `dongia` int(12) NOT NULL DEFAULT 0,
  `soluong` int(3) NOT NULL DEFAULT 0,
  `thanhtien` int(12) NOT NULL DEFAULT 0,
  `idbill` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`id`, `tensp`, `img`, `dongia`, `soluong`, `thanhtien`, `idbill`) VALUES
(34, 'Cơm sốt chanh bò ', 'shop-15.jpg', 123, 1, 123000, 18),
(38, 'Tai Nghe Chụp Tai ', '13.png', 222, 1, 222000, 20),
(39, 'Camera 360 KTS ', '5.png', 234, 3, 702000, 20),
(42, 'Sam sung Utra', '11.png', 111, 1, 111000, 23),
(43, 'Vivo A67s', '2.png', 123, 2, 246000, 23);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lienhe`
--

CREATE TABLE `lienhe` (
  `hoten` varchar(200) NOT NULL,
  `sdt` int(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `noidung` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `lienhe`
--

INSERT INTO `lienhe` (`hoten`, `sdt`, `email`, `noidung`) VALUES
('BẮC VŨ', 367456697, 'staff@gmail.com', 'sfgsg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham1`
--

CREATE TABLE `sanpham1` (
  `masp` varchar(100) NOT NULL,
  `nhom_id` int(11) NOT NULL,
  `tensp` varchar(200) NOT NULL,
  `mota` text DEFAULT NULL,
  `dongiacu` int(11) NOT NULL,
  `dongiamoi` int(11) NOT NULL,
  `img1` varchar(200) NOT NULL,
  `img2` varchar(200) DEFAULT NULL,
  `img3` varchar(200) DEFAULT NULL,
  `img4` varchar(200) DEFAULT NULL,
  `ghichu` text DEFAULT NULL,
  `diemnoibat` text DEFAULT NULL,
  `cachchamsoc` text DEFAULT NULL,
  `xuatxu` varchar(255) DEFAULT NULL,
  `ngaydang` varchar(50) DEFAULT NULL,
  `kichthuoc` varchar(255) DEFAULT NULL,
  `nguoidang` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham1`
--

INSERT INTO `sanpham1` (`masp`, `nhom_id`, `tensp`, `mota`, `dongiacu`, `dongiamoi`, `img1`, `img2`, `img3`, `img4`, `ghichu`, `diemnoibat`, `cachchamsoc`, `xuatxu`, `ngaydang`, `kichthuoc`, `nguoidang`) VALUES
('1', 123, 'Sam sung A12', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt  ', 444, 333, '1.png', '11.png', '4.png', '12.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt  ', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt  ', 'tuyên quang         ', '12/12/20023         ', '3 kg         ', 'Vũ Thị Bắc           '),
('2', 987, 'Vivo A67s', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 234, 123, '2.png', '11.png', '4.png', '13.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Hải Dương       ', '12/12/20023     ', '10- 30 cm      ', 'ghf     '),
('12', 123, 'Sam sung A50', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 242, 222, '3.png', '15.png', '5.png', '11.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Tiên Quang     ', '2/2/2022     ', '300g     ', 'Vũ Thị Bắc     '),
('3', 123, 'Sam sung A44', 'Món ăn đặc trưng của Ấn Độ, thường được làm từ thịt, rau hoặc đậu, nấu trong nước sốt gia vị với sữa dừa. Có nhiều loại curry khác nhau tùy theo vùng miền.\r\n8. Caesar Salad     ', 322, 234, '10.png', '12.png', '13.png', '4.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Tiên Quang     ', '12/12/20023     ', '20g     ', 'Nguyễn Minh Thiện     '),
('4', 987, 'ViVO H4', 'Món salad nổi tiếng với rau xà lách Romaine, bánh mỳ nướng, phô mai Parmesan, và nước sốt Caesar làm từ trứng, tỏi, và nước cốt chanh.\r\n9. Pad Thai       ', 333, 234, '5.png', '4.png', '6.png', '5.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'tuyên quang       ', '11/2/2023       ', '10- 30 cm        ', 'Nguyễn Minh Thiện       '),
('7', 123, 'Sam sung Utra', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt    ', 222, 111, '11.png', '10.png', '3.png', '11.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt    ', 'Món salad nổi tiếng với rau xà lách Romaine, bánh mỳ nướng, phô mai Parmesan, và nước sốt Caesar làm từ trứng, tỏi, và nước cốt chanh.      ', 'ha noi      ', '2/11/2023      ', '2 đĩa      ', 'Vũ Thị Bắc      '),
('777', 7567, 'Iphone 13promax', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 234, 222, '13.png', '10.png', '12.png', '13.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Đồng Thấp     ', '2/11/2023     ', '10cm-30cm     ', 'Vũ Thị Bắc       '),
('32', 7567, 'Iphone 16Pro', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 234, 123, '10.png', '15.png', '4.png', '13.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt   ', 'Tiên Quang     ', '2/2/2022     ', '10cm-30cm     ', 'Vũ Thị Bắc     '),
('142', 7567, 'Iphone Xs', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt    ', 234, 222, '14.png', '3.png', '12.png', '2.png', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt    ', 'Linh kiện điện tử là những thành phần cơ bản trong các mạch điện, đóng vai trò quan trọng trong việc điều khiển và xử lý tín hiệu. Chúng bao gồm nhiều loại, như điện trở, tụ điện, diode và transistor, mỗi loại có chức năng riêng biệt    ', 'Hải Dương      ', '2/2/2022    ', 'hi    ', 'Vũ Thị Bắc      ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham_nhom`
--

CREATE TABLE `sanpham_nhom` (
  `id` int(11) NOT NULL,
  `tennhom` varchar(100) NOT NULL,
  `ghichu` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham_nhom`
--

INSERT INTO `sanpham_nhom` (`id`, `tennhom`, `ghichu`) VALUES
(7567, 'Iphone', '657       '),
(123, 'Samsung', 'đẹp      '),
(987, 'ViVo', '12      ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `tendangnhap` varchar(50) NOT NULL,
  `matkhau` varchar(50) NOT NULL,
  `hoten` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`tendangnhap`, `matkhau`, `hoten`, `email`, `enable`) VALUES
('nghia', '123', 'nghĩa', 'nghia@gmail.com', 1),
('bac11', '123 ', 'bac ', 'vuthibac@gmail.com  ', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

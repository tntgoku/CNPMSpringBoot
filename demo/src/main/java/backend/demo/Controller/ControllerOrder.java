package backend.demo.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import backend.demo.DTO.orderDTO;
import backend.demo.DTO.productDTO;
import backend.demo.DTO.userDTO;
import backend.demo.Model.OrderInfo;
import backend.demo.Model.cartItem;
import backend.demo.Model.cartList;
import backend.demo.Model.iPhone;
import backend.demo.Model.paymentMethod;
import backend.demo.Model.uSer;
import backend.demo.Service.VNPayService;
import backend.demo.Service.orderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ControllerOrder {
    @Autowired
    private VNPayService vnPayService;
    @Autowired
    private userDTO userDTO;
    @Autowired
    private productDTO productO;
    @Autowired
    private orderDTO orderDTO;
    
    @GetMapping("/order/orderfail")
    public String paymentFailling(){
        return "/order/orderfail";
    }


    @GetMapping("/order/ordersuccess")
    public String getMethodName(HttpServletRequest request,Model model,HttpSession session) {
        model.addAttribute("model","Temp");

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n"+session.getAttribute("listCart").toString());
        return "/order/ordersuccess";
    }
    
    // Chuyển hướng người dùng đến cổng thanh toán VNPAY
    @PostMapping("/submitOrder")
    public ResponseEntity<?> submitOrder(@RequestBody Map<String, Object> payload, HttpServletRequest request,HttpSession session) {
        try {
            // Lấy dữ liệu từ payload
            Map<String, Object> formData = (Map<String, Object>) payload.get("formData");
            Map<String, String> formData1 = (Map<String, String>) payload.get("formData");
            List<Map<String, Object>> products = (List<Map<String, Object>>) payload.get("products");
            Object value = payload.get("orderTotal");

            session.setAttribute("formDataa", formData1);
            // Ensure proper casting to Long, with handling for Integer or other numeric types
            Long orderTotal = (value instanceof Number) ? ((Number) value).longValue() : Long.parseLong(value.toString());
            products.add(formData);
            String orderInfo = payload.get("orderInfo").toString()+products.toString();
            // Tạo URL cơ bản
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            
            uSer user= (uSer)session.getAttribute("customer");
            String id;
            if(user==null){
                    id="123456789";
            }else{
                id=String.valueOf(user.getUserID());
            }
            // Tạo URL tới VNPay
            System.out.println("OrderTotal: "+orderTotal);
            String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl,products,formData,id);
            System.out.println("URL tạo đơn hàng VNPay:\n" + vnpayUrl);
            
            // Trả về URL VNPay cho client để chuyển hướng
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(Map.of("redirectUrl", vnpayUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Đã xảy ra lỗi khi xử lý đơn hàng.");
        }
    }
    
  
    // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
    @GetMapping("/vnpay-payment-return")
    public String thanhtoanthanhcong(HttpServletRequest request,  
                                    Model model,HttpSession session){
        int paymentStatus =vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");


        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);
         int i=Integer.parseInt(transactionId);
         System.out.println("\n\n\n\n\n");
        return paymentStatus == 1 ? "/order/ordersuccess" : "/cart";
    }
    public static String reverseInputString(String myString) {

		if (myString == null)
			return myString;

		String reverseString = "";

		for (int i = myString.length() - 1; i >= 0; i--) {

			reverseString = reverseString + myString.charAt(i);
		}
		return reverseString;
	}
}
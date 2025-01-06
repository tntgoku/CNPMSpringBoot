package backend.demo.Controller;

import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
@Controller
@RequestMapping("/gotoorder")
public class PaymentController {

    // @GetMapping("/success")
    // public String paymentSuccess(
    //         @RequestParam(name = "vnp_Amount", required = false) String amount,
    //         @RequestParam(name = "vnp_BankCode", required = false) String bankCode,
    //         @RequestParam(name = "vnp_BankTranNo", required = false) String bankTranNo,
    //         @RequestParam(name = "vnp_CardType", required = false) String cardType,
    //         @RequestParam(name = "vnp_OrderInfo", required = false) String orderInfo,
    //         @RequestParam(name = "vnp_PayDate", required = false) String payDate,
    //         @RequestParam(name = "vnp_ResponseCode", required = false) String responseCode,
    //         @RequestParam(name = "vnp_TmnCode", required = false) String tmnCode,
    //         @RequestParam(name = "vnp_TransactionNo", required = false) String transactionNo,
    //         @RequestParam(name = "vnp_TransactionStatus", required = false) String transactionStatus,
    //         @RequestParam(name = "vnp_TxnRef", required = false) String txnRef,
    //         @RequestParam(name = "vnp_SecureHash", required = false) String secureHash,
    //         Model model,
    //         jakarta.servlet.http.HttpSession session
    // ) {
    //     // Gắn thông tin giao dịch vào model
    //     model.addAttribute("amount", amount);
    //     model.addAttribute("bankCode", bankCode);
    //     model.addAttribute("bankTranNo", bankTranNo);
    //     model.addAttribute("cardType", cardType);
    //     model.addAttribute("orderInfo", orderInfo);
    //     model.addAttribute("payDate", payDate);
    //     model.addAttribute("responseCode", responseCode);
    //     model.addAttribute("tmnCode", tmnCode);
    //     model.addAttribute("transactionNo", transactionNo);
    //     model.addAttribute("transactionStatus", transactionStatus);
    //     model.addAttribute("txnRef", txnRef);
    //     model.addAttribute("secureHash", secureHash);

    //     // Lấy danh sách sản phẩm từ session
    //     List<Map<String, String>> products = (List<Map<String, String>>) session.getAttribute("products");
    //     model.addAttribute("products", products);

    //     // Lấy thông tin formData từ session
    //     Map<String, String> formData = (Map<String, String>) session.getAttribute("formDataa");
    //     model.addAttribute("formData", formData);

    //     return "order/ordersuccess"; // Trả về template Thymeleaf
    // }

}

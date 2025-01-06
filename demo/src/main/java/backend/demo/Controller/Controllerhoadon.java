package backend.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.demo.DTO.orderDTO;
import backend.demo.Model.OrderDetails;
import backend.demo.Model.orDers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Controllerhoadon {
    @Autowired
    private orderDTO orDto;


    @GetMapping("/admin/hoadon")
    public String getgopagehoadon(Model model,@RequestParam(name = "search", required = false) String search) {
            List<orDers> list=new ArrayList<>();
        if (search != null && !search.isEmpty()) {
            list = orDto.getllOrderSearchName(search);
        } else {
            // Nếu không có tìm kiếm, lấy tất cả sản phẩm
            list=  orDto.getllOrder();
        }

            List<OrderDetails> listdeDetails=new ArrayList<>();
            List<Map<Integer,String>> tempa=new ArrayList<>();
            List<Map<Integer,String>> tempb=new ArrayList<>();
            tempa=orDto.getlistStatus();
            tempb=orDto.getlistPayment();
        for(int i=0;i<list.size();i++){
            String id=list.get(i).getOrderID();
            list.get(i).setOrderDetails(orDto.findDetails(id));
        }
        for (orDers product : list) {
            int status = product.getStatus();
            String statusText = tempa.stream()
                                          .filter(map -> map.containsKey(status))
                                          .map(map -> map.get(status))
                                          .findFirst()
                                          .orElse("Unknown");
            product.setStatusText(statusText); // Giả sử bạn thêm thuộc tính `statusText` vào Product
        }
        for (orDers product : list) {
            int status = product.getPayment();
            String statusText = tempa.stream()
                                          .filter(map -> map.containsKey(status))
                                          .map(map -> map.get(status))
                                          .findFirst()
                                          .orElse("Unknown");
            product.setpaymentText(statusText); // Giả sử bạn thêm thuộc tính `statusText` vào Product
        }
        model.addAttribute("liststatus", orDto.getlistStatus());
        model.addAttribute("listpayment", orDto.getlistPayment());
        model.addAttribute("products", list);

        return "/admin/mainhoadon/hoadon";
    }

    @GetMapping("/admin/hoadon/UpdatePro/{masanpham}")
    public String getOrderPage(@PathVariable("masanpham") String id,Model model){
        List<OrderDetails> listdeDetails=new ArrayList<>();
        List<Map<Integer,String>> tempa=new ArrayList<>();
        List<Map<Integer,String>> tempb=new ArrayList<>();
        orDers oDerstemp = orDto.findOrDersbyID(id);
        listdeDetails=orDto.findDetailsImg(oDerstemp.getOrderID());
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        String formattedNumber = formatter.format(oDerstemp.getTotalAmount());
        String total= formattedNumber;
        oDerstemp.setOrderDetails(listdeDetails);
        tempa=orDto.getlistStatus();
        tempb=orDto.getlistPayment();
        Map<Integer, String> status = new HashMap<>();
        status.put(0, "Chờ xác nhận");
        status.put(1, "Chờ lấy hàng");
        status.put(2, "Chờ giao hàng");
        status.put(3, "Giao hàng thành công");
        Map<Integer, String> status1 = new HashMap<>();
        status1.put(0, "COD");
        status1.put(1, "VNPAY");
        status1.put(2, "MOMO");
        model.addAttribute("liststatus", status);
        model.addAttribute("producta", oDerstemp);
        model.addAttribute("productss",listdeDetails);
        model.addAttribute("listpayment", status1);
        model.addAttribute("total", total);
        return "/admin/mainhoadon/Update";
    }
    
}

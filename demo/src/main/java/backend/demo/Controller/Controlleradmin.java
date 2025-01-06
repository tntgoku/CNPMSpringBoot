package backend.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import backend.demo.DTO.productDTO;
import backend.demo.DTO.userDTO;
import backend.demo.Model.iPhone;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class Controlleradmin {

    @Autowired
    productDTO productDTO;
    @Autowired
    userDTO userDTO;
    @GetMapping("/admin")
    public String homepageadmin() {
        return "admin/dashboard"; // Không cần .html vì Spring sẽ tự xử lý
    }

    @GetMapping("admin/productphone")
    public String getPageProduct(@RequestParam(name = "search", required = false) String search, Model model) {
        List<iPhone> products;
        System.out.println("Gia tri search:  \n\n\n\n\n"+search);
        if (search != null && !search.isEmpty()) {
            products = productDTO.searchPhones(search);
        } else {
            // Nếu không có tìm kiếm, lấy tất cả sản phẩm
            products = productDTO.GetallPhone();
        }
        for (iPhone iPhone : products) {
                if (iPhone.getStorage()==null || iPhone.getStorage().isEmpty()) {
                    iPhone.setStorage("NULL");
                }
        }
        model.addAttribute("products", products);
        return "admin/mainProduct";
    }
    

    @GetMapping("admin/productphone/UpdatePro/{masanpham}/{namepro}/{color}/{storage}/{price}")
    public String PostProductPAge(@PathVariable("masanpham") String id,
                                  @PathVariable("namepro")String name,
                                  @PathVariable("color")String color,
                                  @PathVariable("storage") String storage,
                                  @PathVariable("price") String price,
                                  Model model) {
        //TODO: process POST request
        model.addAttribute("colors", color);
        System.err.println("\n\n\n\n\n\n"+storage);
        if(storage.isEmpty()|| storage.equals("N/A")){
            model.addAttribute("storages",null);
        }else{
            model.addAttribute("storages", storage);
        }
        model.addAttribute("pricePro", price);
        iPhone s=productDTO.getProductById(Integer.parseInt(id));
         List<Map<String, Object>> listCategory=productDTO.getList("select * from Categories");
        model.addAttribute("product", s);
        model.addAttribute("listCate", listCategory);
        return "admin/mainProducts/Update";
    }

    @PostMapping("/login/register")
    public String postMethodName(@RequestParam("NameC") String i,
                                 @RequestParam("email") String i1,
                                 @RequestParam("address") String i2,
                                 @RequestParam("phonenumber") String i3,
                                 @RequestParam("password") String i4,
                                 @RequestParam("password1") String i5,Model model) {
        //TODO: process POST request

        if(!userDTO.isEmailExist(i1)){
            userDTO.addUser(i, i1, i4, i3, i2, 0);
            model.addAttribute("alertMessage", "Đăng ký thành công");
            model.addAttribute("alertClass", "alert-success");  // Thêm class cho alert thành công
        }else{
            model.addAttribute("alertMessage", "Email này đã tồn tại");
            model.addAttribute("alertClass", "alert-danger");  // Thêm class cho alert lỗi
        }
        return "/login/login";
    }
    
    
    
}

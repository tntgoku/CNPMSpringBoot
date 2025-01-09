package backend.demo.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/admin/addPro")
    public String addProduct(Model model) {
        List<Map<String, Object>> listCategory=productDTO.getList("select * from Categories");
        model.addAttribute("listCate", listCategory);
        return "/admin/mainProducts/AddPro";
    }

    @PostMapping("/admin/addPro")
    public String postAddName(@RequestParam("nameP") String entity,
                                 @RequestParam("type_id") String entity1,
                                 @RequestParam("cost") String entity2,
                                 @RequestParam("Quantity") String entity4,
                                 @RequestParam("discount") String entity5,
                                 @RequestParam(value = "img", required = false) MultipartFile img,
                                 @RequestParam("storage") String entity8,
                                 @RequestParam("desc") String entity6,
                                 @RequestParam("Color") String entity7,Model model) throws IllegalStateException, IOException{
        String filepath;
        if(!img.isEmpty()){
            String uploadDir = Paths.get("src/main/resources/static/assets/img").toAbsolutePath().toString();
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Lưu file
            filepath = Paths.get(uploadDir, img.getOriginalFilename()).toString();
            int a=filepath.indexOf("C:/Users/YourProject/src/main/resources/static");
            img.transferTo(new File(filepath));
            String b="/assets/img/"+img.getOriginalFilename().toString();
            System.out.println("\n\n\n\n\n\n Update thanh cong");
            int cate=Integer.parseInt(entity1);
            int isnew=0;
            //day la hang moi
            if(cate==1 || cate==3){
                isnew=1;
            }else{
                isnew=0;
                //hang cu
            }
            model.addAttribute("successfully", "Thêm thành công sản phẩm này");
            List<Map<String, Object>> listCategory=productDTO.getList("select * from Categories");
            model.addAttribute("listCate", listCategory);
            productDTO.addProduct(entity, Integer.parseInt(entity1),
            
                                            isnew,entity8, entity7,
                                        Integer.parseInt(entity2),
                                        Integer.parseInt(entity4),
                                         b,Integer.parseInt(entity5),
                                         entity6);
        }else{
            System.out.println("\n\n\n\n\n\n Update that bai");
           }
        
        return "/admin/mainProducts/addPro";
    }
    
    



    @PostMapping("admin/productphone/UpdatePro/{masanpham}/{namepro}/{color}/{storage}/{price}")
    public String UpdateProductPAge(@PathVariable("masanpham") String id,
                                  @PathVariable("namepro")String name,
                                  @PathVariable("color")String color,
                                  @PathVariable("storage") String storage,
                                  @PathVariable("price") String price,
                                  @RequestParam("id_product") String i,
                                  @RequestParam("name") String i1,
                                 @RequestParam(value = "img", required = false) MultipartFile img,
                                  @RequestParam("type_id") String i3,
                                  @RequestParam("color") String i4,
                                  @RequestParam("cost") String i6,
                                  @RequestParam("amount") String i7,
                                  @RequestParam("discount") String i8,
                                  @RequestParam("description") String i9,
                                  Model model) throws IllegalStateException, IOException {
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
        String filepath;
        if(!img.isEmpty()){
            String uploadDir = Paths.get("src/main/resources/static/assets/img").toAbsolutePath().toString();
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Lưu file
            filepath = Paths.get(uploadDir, img.getOriginalFilename()).toString();
            int a=filepath.indexOf("C:/Users/YourProject/src/main/resources/static");
            img.transferTo(new File(filepath));
            String b="/assets/img/"+img.getOriginalFilename().toString();
            s.setProductName(i1);
            s.setCategoryID(Integer.parseInt(i3));
            s.setColor(i4);
            s.setImage(b);
            if(Integer.parseInt(i3)==1){
                s.setIsNew(1);
            }else{  
                s.setIsNew(0);
            }
            s.setPrice(Integer.parseInt(i6));
            s.setQuantity(Integer.parseInt(i7));
            s.setDiscount(Integer.parseInt(i8));
            s.setDescription(i9);
            System.out.println(i9+"\n"+i8+"\n"+i7+"\n"+i6+"\n"+"\n"+i4+"\nLoai san pham:"+i3+"\n"+img.toString()+"\nChuoi B:"+b);
           if(productDTO.UpdateDTO(s)!=-1){
            System.out.println("\n\n\n\n\n\n Update thanh cong");
            model.addAttribute("successfully", "Update thanh cong");
           }else{
            System.out.println("\n\n\n\n\n\n Update that bai");
           }
        }
        
        return "admin/mainProducts/Update";
    }


     @DeleteMapping("admin/productphone/{masanpham}")
    public ResponseEntity<String> deleteProduct(@PathVariable("masanpham") String i ) {
            if(productDTO.deleteProduct(Integer.parseInt(i))!=-1){
                return ResponseEntity.ok("Xoa thanh cong san pham nay!!."+i);
            }else{
                return ResponseEntity.ok("Co loi say ra");
            }
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

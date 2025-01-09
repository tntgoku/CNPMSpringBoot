package backend.demo.Controller;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import backend.demo.DTO.productDTO;
import backend.demo.DTO.userDTO;
import backend.demo.Model.cartItem;
import backend.demo.Model.cartList;
import backend.demo.Model.iPhone;
import backend.demo.Model.paymentMethod;
import backend.demo.Model.uSer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;



/*
 * Database đc dùng = sql server 
 * 
 * 
 */

@Controller
public class ControllerSystemp {
    @Autowired
    private productDTO productO;
    @Autowired
    private userDTO userO;
    @GetMapping("/")
    public String homePage(Model model){
        List<iPhone> lists=productO.GetallPhone();
        for (iPhone iPhone : lists) {
            if(iPhone.getCategoryID()==3){
                iPhone.setStorage("NULL");
            }
        }
            model.addAttribute("products", lists);
        return "index";
    }

    @GetMapping("login")
    public String loginpage() {
        return "login/login";
    }
    @ModelAttribute("customer")
    public uSer getLoggedInCustomer(HttpSession session) {
        return (uSer) session.getAttribute("customer");
    }
    @ModelAttribute("TotalCartItem")
    public String getLoggedInTotalCart(HttpSession session) {
        Object cart = session.getAttribute("TotalCartItem");
    if (cart == null) {
        // Handle the case where the cart is null, e.g., return a default value
        return "0";
    }
    return cart.toString(); // Proceed with safe usage
    }

    @ModelAttribute("listCart")
    public List<cartItem> getListcart(HttpSession session){
        return (List<cartItem>)session.getAttribute("listCart");
    }
	@PostMapping("login")
	public String checklogin(@RequestParam ("emails") String entity,
                                 @RequestParam ("pwd") String entiy1,
                                 HttpSession session,Model model) {
		//TODO: process POST request
		String email=entity;
        String password=entiy1;
        boolean hasError = false;

        if (email.isEmpty()) {
            model.addAttribute("errorEmail", "Email không được để trống!");
        }
        if (password.isEmpty()) {
            model.addAttribute("errorPassword", "Mật khẩu không được để trống!");
        }
        if (!userO.checkUser(email, password)) {
            uSer user= userO.getUser(email, password);
            userO.updateUser(user);
            session.setAttribute("customer", user);
            session.setAttribute("nameuser", user.getFullName());
            System.out.println("Dang nhap thanh cong");
                if(userO.isAdmin(user.getRoles())){
                    return "admin/dashboard";
                }else{
                    return "redirect:/";
                }
        } else {
            model.addAttribute("errorMessage", "Mật khẩu khong chinh xac");
            return "login/login"; 
        }
	}


    @GetMapping("/details/{loaisanpham}/{tensanpham}/{storage}")
    public String getProductDetails(
                                    @PathVariable("loaisanpham") String categoryID,
                                    @PathVariable("tensanpham") String productID,
                                    @PathVariable("storage") String storage,Model model) {
        // Giả sử bạn có một service để truy vấn sản phẩm theo ID
        List<iPhone> listprPhones=new ArrayList<>();
        iPhone product ;
        if(!storage.equals("NULL")){
             listprPhones = productO.GetallPhone(productID, storage);
             product= listprPhones.get(0);
        }else{
                product=productO.getProductByName(productID);
        }
        List<iPhone> liststorage=new ArrayList();
        if(Integer.parseInt(categoryID)<=2){
            String name=product.getProductName();
            List<String> lists=new ArrayList<>();
            System.out.println("\n"+product.getProductName());
            //Mau
            System.out.print("\n\n\n\n\n"+product.toString()+"\n\n\n\n\n");
            //Dung luong
            lists=productO.getPhoneStorage(name);
            // Thêm sản phẩm vào model để truyền cho view
            model.addAttribute("liststorage",lists);
            System.out.println(product.getProductName()+'\n'+product.getStorage()+"\nGia tri:\n\n\n\n\n"+liststorage.toString());
        
            
        }
        liststorage =productO.getPhoneColor(product.getProductName(),product.getStorage());
        model.addAttribute("product", product);
        model.addAttribute("listcolorproduct", liststorage);
        return "details";  // Trả về trang "details.html"
    }
    
   
    @PostMapping("/details/{loaisanpham}/{tensanpham}/{storage}")
    public  ResponseEntity<String> postMethodName(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        boolean checkID=false;
        String notice="A";
        int total=0;

         // Lấy giỏ hàng từ session, nếu chưa có thì khởi tạo mới
        List<cartItem> cartList=(List<cartItem>)session.getAttribute("listCart");
        int id=Integer.parseInt(requestBody.get("IDP").toString());
        int quantity =Integer.parseInt(requestBody.get("quantity").toString());
        int price= Integer.parseInt(requestBody.get("price").toString());
        String color=requestBody.get("Color").toString();
        cartItem temp=new cartItem(id,quantity,color,price);
        
        // Nếu giỏ hàng chưa tồn tại trong session, tạo mới
        if (cartList == null) {
            cartList = new ArrayList<>();
        }
        if(cartList.isEmpty()){
         
                cartList.add(temp);
                notice="Cart rong~";
        }else{
            for (cartItem cartItem : cartList) {
                if(cartItem.getIDP()==temp.getIDP()){
                    cartItem.setQuantity(cartItem.getQuantity()+temp.getQuantity());
                    cartItem.setPrices(price+cartItem.getPrices()); 
                    notice="Update thanh cong";       
                    checkID=true;
                }
            }
            if (!checkID) {
                cartList.add(temp);
                notice="Them thanh cong san pham";
            }

        }

        for (cartItem cartItem : cartList) {
           total+= cartItem.getQuantity();
        }
        session.setAttribute("TotalCartItem", total);
        session.setAttribute("listCart", cartList);

        return ResponseEntity.ok("Gia tri:  "+requestBody.toString()+"\n"+notice+"\nSession: "+session.getAttribute("listCart").toString());
    }
    


    @GetMapping("/logout")
        public String logout(HttpSession session) {
            session.invalidate(); // Xóa toàn bộ session

            return "redirect:/";
    }
    private String converNumber(int i){
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);

        String formattedNumber = formatter.format(i);
        return formattedNumber;
   }
    @GetMapping("/cart")
    public String getcartpage(HttpSession session,Model model, HttpServletRequest request ) {

        List<paymentMethod>listpayment=new ArrayList<>();
        listpayment.add(new paymentMethod("Thanh toán khi nhận hàng",1));
        listpayment.add(new paymentMethod("Thanh toán VNPAY",2));
        listpayment.add(new paymentMethod("Thanh toán MOMO",3));
        Object listCartAttr = session.getAttribute("listCart");
        if (listCartAttr == null) {
            System.out.println("\n\n\nGiỏ hàng: null");
        } else {
            System.out.println("\n\n\nGiỏ hàng: " + listCartAttr.toString());
        }
        List<cartItem> lItems = (List<cartItem>)session.getAttribute("listCart");

        List<cartList> listitem=new ArrayList<>();
        if (lItems==null ) {
            lItems= new ArrayList<>();
        }
        if (lItems.isEmpty()) {
            model.addAttribute("notfication","Giỏ hàng rỗng!");
        }else{
            for (cartItem cartList : lItems) {
                iPhone s=productO.getProductById(cartList.getIDP());
                if(s!=null){
                    listitem.add(new cartList(s,cartList.getQuantity(),cartList.getPrices()));
                }
            }
        }
        int sumer=0;
        for (cartList cartList : listitem) {
            sumer+=cartList.getTotalPrice();
        }
        model.addAttribute("listproduct", listitem);
        model.addAttribute("totalpricess",converNumber(sumer).toString());
        model.addAttribute("totalpricess1",sumer);
        model.addAttribute("listpayment", listpayment);
        System.out.println("\n\n\n\n\n\n\n\n\nHTTPREQUEST"+request.toString());
        return "cart";
    }
    

    @PostMapping("/cart/{masanpham}")
    public String getcartpage(
    @RequestParam("masanpham")String id,    
    HttpSession session,Model model, HttpServletRequest request ) {

        List<paymentMethod>listpayment=new ArrayList<>();
        listpayment.add(new paymentMethod("Thanh toán khi nhận hàng",1));
        listpayment.add(new paymentMethod("Thanh toán VNPAY",2));
        listpayment.add(new paymentMethod("Thanh toán MOMO",3));
        Object listCartAttr = session.getAttribute("listCart");
        if (listCartAttr == null) {
            System.out.println("\n\n\nGiỏ hàng: null");
        } else {
            System.out.println("\n\n\nGiỏ hàng: " + listCartAttr.toString());
        }
        List<cartItem> lItems = (List<cartItem>)session.getAttribute("listCart");

        List<cartList> listitem=new ArrayList<>();
        if (lItems==null ) {
            lItems= new ArrayList<>();
        }
        if (lItems.isEmpty()) {
            model.addAttribute("notfication","Giỏ hàng rỗng!");
        }else{
            for (cartItem cartList : lItems) {
                iPhone s=productO.getProductById(cartList.getIDP());
                if(s!=null){
                    listitem.add(new cartList(s,cartList.getQuantity(),cartList.getPrices()));
                }
            }
        }
        int sumer=0;
        for (cartList cartList : listitem) {
            sumer+=cartList.getTotalPrice();
        }
        model.addAttribute("listproduct", listitem);
        model.addAttribute("totalpricess",converNumber(sumer).toString());
        model.addAttribute("totalpricess1",sumer);
        model.addAttribute("listpayment", listpayment);
        System.out.println("\n\n\n\n\n\n\n\n\nHTTPREQUEST"+request.toString());
        return "cart";
    }
    
 
    @PutMapping("/cart")
    public ResponseEntity<String> updateCartItem(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        boolean checkID = false;
        String notice = "Cập nhật sản phẩm thành công!";
        int total = 0;

        // Lấy giỏ hàng từ session, nếu chưa có thì khởi tạo mới
        List<cartItem> cartList = (List<cartItem>) session.getAttribute("listCart");
        if (cartList == null) {
            cartList = new ArrayList<>();
        }

        // Lấy thông tin sản phẩm từ requestBody
        int id = Integer.parseInt(requestBody.get("IDP").toString());
        int quantity = Integer.parseInt(requestBody.get("quantity").toString());
        String color = requestBody.get("Color").toString();

        // Duyệt qua giỏ hàng để tìm sản phẩm và cập nhật
        for (cartItem item : cartList) {
            if (item.getIDP() == id && item.getColor().equals(color)) {
                item.setQuantity(quantity);  // Cập nhật số lượng sản phẩm
                checkID = true;
                break;
            }
        }

        // Nếu không tìm thấy sản phẩm, trả về thông báo lỗi
        if (!checkID) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Sản phẩm không tồn tại trong giỏ hàng.");
        }

        // Cập nhật lại giỏ hàng vào session
        session.setAttribute("listCart", cartList);

        // Tính tổng số lượng sản phẩm trong giỏ hàng
        for (cartItem item : cartList) {
            total += item.getQuantity();
        }

        // Trả về thông báo
        return ResponseEntity.ok(notice + " Tổng sản phẩm trong giỏ: " + total);
    }


}



    // @GetMapping("/details/{loaisanpham}/{masanpham}/{storage}")
    // public String getProductDetails(@PathVariable("masanpham") String productID,
    // @PathVariable("loaisanpham") String categoryID,
    // @PathVariable("storage") String Storage, Model model) {
    //     // Giả sử bạn có một service để truy vấn sản phẩm theo ID
    //     if(Integer.parseInt(categoryID)==1){
    //         iPhone product = productO.getProductById(Integer.parseInt(productID));

    //         //Dung luong
    //         String storage=product.getProductName();
    //         System.out.println(storage.trim()+"%");
    //         List<iPhone> liststorage=new ArrayList();
    //         List<String> lists=new ArrayList<>();
    //        liststorage =productO.getStorageiPhone(product.getProductName(),storage);
    //        lists=productO.getPhoneStorage(Storage);
    //         // Thêm sản phẩm vào model để truyền cho view
    //         model.addAttribute("product", product);
    //         model.addAttribute("listcolor", liststorage);
    //         model.addAttribute("liststorage",lists);
    //         System.out.println("Gia tri:\n\n\n\n\n"+liststorage.toString());
    //         return "details";  // Trả về trang "details.html"
    //     }else{
    //         return "erros";
    //     }
    // }


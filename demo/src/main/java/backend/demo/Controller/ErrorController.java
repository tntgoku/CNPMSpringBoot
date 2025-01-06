package backend.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    
    @RequestMapping("/error")
    public String handleError() {
        // Xử lý lỗi tại đây và trả về trang lỗi tùy chỉnh
        return "errorPage"; // trang lỗi tùy chỉnh
    }

  
}
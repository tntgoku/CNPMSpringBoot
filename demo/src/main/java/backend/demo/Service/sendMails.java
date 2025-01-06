package backend.demo.Service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
public class sendMails {
    private final JavaMailSender mailSender;
    private String user;
    public sendMails(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }
    public void SendMailToUser(){
        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom("trunghieuhsdd1@gmail.com");
            message.setFrom("trunghieuhsdd@gmail.com");
            message.setSubject("Tieu de ");
            message.setText("Day la noi dung can gui: ");
            mailSender.send(message);
            System.out.println("\n\n\n\n-------------------------Send Mail Thanh Cong-------------------------\n\n\n\n");       
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
}

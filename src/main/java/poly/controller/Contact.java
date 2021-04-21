package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.internet.MimeMessage;

@Controller
public class Contact {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    JavaMailSenderImpl mailer;

    @GetMapping("contact")
    public String index(ModelMap model){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            return "contact";
        }else {
            return "contact";
        }
    }
    @PostMapping("send")
    public String send(ModelMap model,
                       @RequestParam("from")String from,
                       @RequestParam("subject")String subject,
                       @RequestParam("body")String body){
            if (SaveLogged.authenticated()){
                model.addAttribute("login",SaveLogged.USER);
                model.addAttribute("role",SaveLogged.USER.getRole());
                model.addAttribute("name",SaveLogged.USER.getName());
            }

            try{

                MimeMessage mail =mailer.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(mail);
                helper.setFrom(from, from);
                helper.setTo("materkaman576@gmail.com");
                helper.setReplyTo(from, from);
                helper.setSubject(subject);
                helper.setText(body, true);
                mailer.send(mail);
                model.addAttribute("success", "Send email successfully !");
                return "contact";
            }
            catch(Exception ex){
                model.addAttribute("success", "Send email fail !");
                return "contact";
            }
    }
}

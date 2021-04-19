package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Contact {


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
}

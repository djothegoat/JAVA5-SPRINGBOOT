package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.controller.SaveLogged;
import poly.entity.Users;
import poly.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class ListUsers {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserService userService;

    @GetMapping("/users/list")
    public String list(ModelMap model){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(SaveLogged.USER.getRole() == true){
                List<Users> users = userService.findAll();
                model.addAttribute("users",users);
                return "/admin/users/list";
            }else{
                model.addAttribute("message","You can not access this page");
                return "error";
            }
        }else {
            return "login";
        }

    }
}

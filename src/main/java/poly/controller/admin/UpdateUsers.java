package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.controller.SaveLogged;
import poly.entity.Users;
import poly.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/")
public class UpdateUsers {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserService userService;

    @GetMapping("/users/update/{id}")
    public String update(ModelMap model, @PathVariable(name = "id") Integer id){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(SaveLogged.USER.getRole() == true){
                Optional<Users> users = userService.findById(id);
                model.addAttribute("users",users.get());
                return "admin/users/update";
            }else{
                model.addAttribute("message","You can not access this page");
                return "error";
            }
        }else {
            return "login";
        }



    }

    @PostMapping("/users/update")
    public String postUpdate(ModelMap model, Users users, RedirectAttributes redirect){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(SaveLogged.USER.getRole() == true){
                userService.save(users);
                model.addAttribute("users",new Users());
                redirect.addFlashAttribute("success", "Update users successfully!");
                return "redirect:/admin/users/list";
            }else{
                model.addAttribute("message","You can not access this page");
                return "error";
            }
        }else {
            return "login";
        }


    }
}

package poly.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import poly.entity.Users;
import poly.service.UserService;

@Controller
public class Register {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String registerUser(ModelMap model){
        model.addAttribute("users",new Users());
        return "/login";
    }

    @PostMapping("/users/save")
    public String add(ModelMap model, Users users){
        userService.save(users);
        model.addAttribute("users",new Users());
        return "/login";
    }

}

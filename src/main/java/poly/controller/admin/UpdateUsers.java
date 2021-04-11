package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
        Optional<Users> users = userService.findById(id);
        model.addAttribute("users",users.get());
        return "admin/users/update";
    }

    @PostMapping("/users/update")
    public String postUpdate(ModelMap model, Users users){
        userService.save(users);
        model.addAttribute("users",new Users());
        return "redirect:/admin/users/list";
    }
}

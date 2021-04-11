package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.service.UserService;

@Controller
@RequestMapping("/admin/")
public class DeleteUsers {
    @Autowired
    UserService userService;

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable(name = "id") Integer id){
        userService.deleteById(id);
        return "redirect:/admin/users/list";
    }
}

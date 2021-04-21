package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.entity.Orders;
import poly.entity.Users;
import poly.service.OrderService;
import poly.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class MyAccount {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @GetMapping("myaccount")
    public String index(ModelMap model) {
        if (SaveLogged.authenticated()) {
            model.addAttribute("login", SaveLogged.USER);
            model.addAttribute("role", SaveLogged.USER.getRole());
            model.addAttribute("name", SaveLogged.USER.getName());
            List<Orders> orders = orderService.findByUserId(SaveLogged.USER.getId());
            model.addAttribute("orders", orders);
            Optional<Users> users = userService.findById(SaveLogged.USER.getId());
            model.addAttribute("users", users.get());
            return "myaccount";
        } else {
            return "login";
        }
    }

    @PostMapping("edit/account")
    public String edit(ModelMap model, Users users,RedirectAttributes redirect) {
        if (SaveLogged.authenticated()) {
            model.addAttribute("login", SaveLogged.USER);
            model.addAttribute("role", SaveLogged.USER.getRole());
            model.addAttribute("name", SaveLogged.USER.getName());
            userService.save(users);
            model.addAttribute("users", new Users());
            redirect.addFlashAttribute("success","Update information successfully");
            return "redirect:/myaccount";
        } else {
            return "login";
        }
    }

    @PostMapping("change/password")
    public String changePass(ModelMap model, Users users, Orders orders,
                             @RequestParam("currentPass") String currentPass,
                             @RequestParam("confirmPass") String confirmPass,
                             @RequestParam("newPass") String newPass) {
        if (SaveLogged.authenticated()) {
            model.addAttribute("login", SaveLogged.USER);
            model.addAttribute("role", SaveLogged.USER.getRole());
            model.addAttribute("name", SaveLogged.USER.getName());
            if (currentPass.equals(SaveLogged.USER.getPassword())) {
                if (confirmPass.equals(newPass)) {
                    users.setPassword(newPass);
                    userService.save(users);
                    model.addAttribute("users", new Users());
                    model.addAttribute("success","Change password successfully");
                    return "myaccount";
                } else {
                    model.addAttribute("message", "Confirm password is not match");
                    return "myaccount";
                }
            } else {
                model.addAttribute("message", "Current password is incorect");
                return "myaccount";
            }

        } else {
            return "login";
        }
    }
}

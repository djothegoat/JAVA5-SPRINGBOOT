package poly.controller.admin;

import netscape.security.UserTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.controller.SaveLogged;
import poly.entity.Orders;
import poly.service.OrderService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/")
public class UpdateOrder {
    @Autowired
    OrderService orderService;

    @GetMapping("order/update/{id}")
    public String update(ModelMap model, @PathVariable(name = "id")Integer id){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(SaveLogged.USER.getRole() == true){
                Optional<Orders> orders = orderService.findById(id);
                model.addAttribute("orders",orders.get());
                return "admin/order/update";
            }else{
                model.addAttribute("message","You can not access this page");
                return "error";
            }
        }else {
            return "login";
        }
    }


    @PostMapping("order/update")
    public String save(ModelMap model, Orders orders,
                       RedirectAttributes redirect) {
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(SaveLogged.USER.getRole() == true){
                orderService.save(orders);
                model.addAttribute("orders",new Orders());
                redirect.addFlashAttribute("success","Update order successfully");
                return "redirect:/admin/order/list";
            }else{
                model.addAttribute("message","You can not access this page");
                return "error";
            }
        }else {
            return "login";
        }

    }
}

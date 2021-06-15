package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import poly.entity.*;
import poly.service.OrderService;
import poly.service.ProductService;
import poly.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class Cart {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping("/cart")
    public String cart(ModelMap model, HttpSession session){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            model.addAttribute("total", total(session));
            return "cart";
        }else {
            return "login";
        }
    }

    @GetMapping(value = "cart/{id}")
    public String buy(ModelMap model, @PathVariable("id") Integer id,
                      HttpSession session){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(session.getAttribute("cart")==null){
                List<Item> cart = new ArrayList<>();
                cart.add(new Item(productService.findById(id).get(),1));
                session.setAttribute("cart",cart);
            }else{
                List<Item> cart = (List<Item>) session.getAttribute("cart");
                int index = isExist(id,cart);
                if(index == -1){
                    cart.add(new Item(productService.findById(id).get(),1));
                }else {
                    int quantity = cart.get(index).getQuantity() + 1;
                    cart.get(index).setQuantity(quantity);
                }
                session.setAttribute("cart",cart);
            }
            model.addAttribute("total", total(session));
            return "/cart";
        }else {
            return "login";
        }

    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, ModelMap model){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("phone",SaveLogged.USER.getPhone());
            model.addAttribute("address",SaveLogged.USER.getAddress());
            model.addAttribute("userId",SaveLogged.USER.getId());
            model.addAttribute("name",SaveLogged.USER.getName());
            model.addAttribute("total", total(session));
            model.addAttribute("order",new Orders());
            return "/checkout";
        }else {
            return "login";
        }
    }

    @PostMapping("/thankyou")
    public String thankyou(ModelMap model, Orders order, HttpSession session){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());

            //add order

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            order.setDate(formatter.format(date));
            orderService.save(order);
            // remove cart
            session.removeAttribute("cart");
            model.addAttribute("order",new Orders());
            return "thankyou";
        }else {
            return "login";
        }



    }

    @PostMapping("cart/update")
    public String update(HttpSession session, HttpServletRequest request){
        String [] quantities = request.getParameterValues("quantity");
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        for(int i = 0; i< cart.size(); i++){
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
            session.setAttribute("cart",cart);
        }
        return "redirect:/cart";
    }
    
    @GetMapping("cart/remove/{id}")
    public String remove(@PathVariable("id")Integer id, HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExist(id,cart);
        cart.remove(index);
        return "redirect:/cart";
    }

    private int isExist(int id, List<Item> cart){
        for(int i = 0; i< cart.size(); i++){
            if(cart.get(i).getProduct().getId() == id){
                return i;
            }
        }
        return -1;
    }
    // get total price
    private int total(HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int s = 0;
        if(cart != null){
            for (Item item: cart){
                s += item.getQuantity() * item.getProduct().getPrice().intValue();
            }
        }
        return s;
    }
}

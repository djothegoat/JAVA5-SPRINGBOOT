package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.entity.*;
import poly.service.OrderService;
import poly.service.ProductService;
import poly.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("total", total(session));
        return "cart";
    }

    @GetMapping(value = "cart/{id}")
    public String buy(ModelMap model, @PathVariable("id") Integer id,
                      HttpSession session){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
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
        model.addAttribute("phone",SaveLogged.USER.getPhone());
        model.addAttribute("address",SaveLogged.USER.getAddress());
        model.addAttribute("userId",SaveLogged.USER.getId());
        model.addAttribute("total", total(session));
        model.addAttribute("order",new Order());

//        Order order = new Order();
//        order.setNotice("1111");
//        order.setAddress(SaveLogged.USER.getAddress());
//        order.setPhone(SaveLogged.USER.getPhone());
//        order.setStatus(1);
//        order.setTotal(total(session));
//        order.setUsersByUserId(SaveLogged.USER);
//        orderService.save(order);
        return "/checkout";
    }

    @PostMapping("/thankyou")
    public String thankyou(ModelMap model, Order order, HttpSession session){
        //add order
        orderService.save(order);
        //add order detail
//        List<Item> cart = (List<Item>) session.getAttribute("cart");
//        for(Item item: cart){
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setQuanlity(1);
//            orderDetail.setDiscount(0);
//
//        }

        // remove cart

        session.removeAttribute("cart");
        model.addAttribute("order",new Order());
        return "redirect:/checkout";
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

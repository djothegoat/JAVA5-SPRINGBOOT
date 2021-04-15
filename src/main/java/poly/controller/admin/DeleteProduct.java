package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.controller.SaveLogged;
import poly.service.CategoryService;
import poly.service.ProductService;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/admin/")
public class DeleteProduct {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/product/delete/{id}")
    public String delete(ModelMap model, @PathVariable(name = "id") Integer id, RedirectAttributes redirect){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            if(SaveLogged.USER.getRole() == null){
                productService.deleteById(id);
                redirect.addFlashAttribute("success", "Deleted product successfully!");
                return "redirect:/admin/product/list";
            }else{
                model.addAttribute("message","You can not access this page");
                return "error";
            }
        }else {
            return "login";
        }

    }
}

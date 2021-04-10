package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import poly.entity.Product;
import poly.service.CategoryService;
import poly.service.ProductService;

import java.util.List;

@Controller
public class Products {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public String list(ModelMap model){
        List<Product> product = productService.findAll();
        model.addAttribute("product",product);
        return "/products";
    }
}

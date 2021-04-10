package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import poly.entity.Category;
import poly.entity.Product;
import poly.service.CategoryService;
import poly.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
public class Detail {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/product/{id}")
    public String detail(ModelMap model, @PathVariable(name = "id") Integer id){
        List<Category> category = categoryService.findAll();
        model.addAttribute("category",category);
        Optional<Product> opt = productService.findById(id);
        model.addAttribute("product", opt.get());
        return "detail";
    }
}

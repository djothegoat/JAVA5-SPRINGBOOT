package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.entity.Category;
import poly.entity.Product;
import poly.service.CategoryService;
import poly.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AddProduct {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("product/add")
    public String addProduct(ModelMap model){
        model.addAttribute("category",new Category());
        return "admin/product/add";
    }

    @PostMapping("/product/save")
    public String save(ModelMap model, Category category){
        categoryService.save(category);
        model.addAttribute("category",new Category());
        return "admin/product/add";
    }




}

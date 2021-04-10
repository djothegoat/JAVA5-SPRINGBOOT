package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.entity.Category;
import poly.entity.Product;
import poly.service.CategoryService;
import poly.service.ProductService;

import java.util.List;


@Controller
@RequestMapping("/admin/")
public class ListProduct {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("product/list")
    public String list(ModelMap model){
        List<Product> product = productService.findAll();
        model.addAttribute("product",product);
        return "admin/product/list";
    }

}

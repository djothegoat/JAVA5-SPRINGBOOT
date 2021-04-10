package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String delete(ModelMap model, @PathVariable(name = "id") Integer id){
        productService.deleteById(id);
        return "redirect:/admin/product/list";
    }
}

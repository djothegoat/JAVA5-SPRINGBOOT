package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import poly.controller.SaveLogged;
import poly.entity.Category;
import poly.entity.Product;
import poly.service.CategoryService;
import poly.service.ProductService;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/")
public class UpdateProduct {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/product/update/{id}")
    public String edit(ModelMap model, @PathVariable(name = "id") Integer id){
        List<Category> category = categoryService.findAll();
        model.addAttribute("category",category);
        Optional<Product> opt = productService.findById(id);
        model.addAttribute("product", opt.get());
        return "admin/product/update";
    }

    @PostMapping("product/update")
    public String update(ModelMap model,Product product, @RequestParam("imagesfile") MultipartFile photo, RedirectAttributes redirect) {
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            if(SaveLogged.USER.getRole() == true){
                try {
                    String productImage =photo.getOriginalFilename();
                    if(!photo.isEmpty()){
                        File pro = new File("C:\\Users\\1Gucci\\IdeaProjects\\JAVA5-SPRINGBOOT\\src\\main\\resources\\static\\img\\" + productImage);
                        if(!pro.exists()){
                            photo.transferTo(pro);
                        }
                        product.setImages(productImage);
                    }
                    productService.save(product);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("message", "Lỗi lưu file !");
                }
                redirect.addFlashAttribute("success", "update product successfully!");
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

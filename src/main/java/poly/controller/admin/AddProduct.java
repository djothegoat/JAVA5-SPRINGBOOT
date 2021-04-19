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

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AddProduct {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;

    @Autowired
    ServletContext context;


    @Autowired
    CategoryService categoryService;

    @GetMapping("product/add")
    public String addProduct(ModelMap model){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(SaveLogged.USER.getRole() == true){
                List<Category> category = categoryService.findAll();
                model.addAttribute("category",category);
                model.addAttribute("product",new Product());
                return "admin/product/add";
            }else{
                model.addAttribute("message","You can not access this page");
                return "error";
            }
        }else {
            return "login";
        }


    }

    @PostMapping("product/save")
    public String save(ModelMap model,Product product, @RequestParam("imagesfile")MultipartFile photo, RedirectAttributes redirect) {
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            if(SaveLogged.USER.getRole()==true){
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
                redirect.addFlashAttribute("success", "Add product successfully!");
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

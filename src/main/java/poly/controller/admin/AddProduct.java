package poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        List<Category> category = categoryService.findAll();
        model.addAttribute("category",category);
        model.addAttribute("product",new Product());
        return "admin/product/add";
    }

    @PostMapping("product/save")
    public String save(ModelMap model,Product product, @RequestParam("imagesfile")MultipartFile photo) {
        if(photo.isEmpty()){
            model.addAttribute("message", "Vui lòng chọn file !");
        }
        else{
            try {
                String productImage =photo.getOriginalFilename();
                product.setImages(productImage);
                productService.save(product);
                File pro = new File("C:\\Users\\1Gucci\\IdeaProjects\\JAVA5-SPRINGBOOT\\src\\main\\resources\\static\\img\\" + productImage);
                if(!pro.exists()){
                    photo.transferTo(pro);
                }

                return "admin/product/add";
            }
            catch (Exception e) {
                model.addAttribute("message", "Lỗi lưu file !");
            }
        }


        model.addAttribute("category",new Category());
        return "admin/product/add";
    }




}

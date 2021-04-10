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
    public String update(ModelMap model,Product product, @RequestParam("imagesfile") MultipartFile photo) {
        String productImage =photo.getOriginalFilename();
        if(photo.isEmpty()){
            model.addAttribute("message", "Vui lòng chọn file !");
        }
        else{
            try {
                product.setImages(productImage);
                productService.save(product);
                File pro = new File("C:\\Users\\1Gucci\\IdeaProjects\\JAVA5-SPRINGBOOT\\src\\main\\resources\\static\\img\\" + productImage);
                if(!pro.exists()){
                    photo.transferTo(pro);
                }
            }

            catch (Exception e) {
                model.addAttribute("message", "Lỗi lưu file !");
            }
        }


        productService.save(product);
        return "redirect:/admin/product/list";
    }
}

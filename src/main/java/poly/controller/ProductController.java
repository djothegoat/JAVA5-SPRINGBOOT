package poly.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.entity.Category;
import poly.entity.Product;
import poly.service.CategoryService;
import poly.service.ProductService;


import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;





//    @GetMapping("/add")
//    public String addProduct(ModelMap model){
//        List<Size> size = sizeService.findAll();
//        model.addAttribute("size",size);
//        model.addAttribute("product",new Product());
//        return "product/add";
//    }
//
//    @PostMapping("/addorupdate")
//    public String addOrUpdate(ModelMap model, Product product){
//        productService.saveProduct(product);
//        model.addAttribute("product",new Product());
//        return "product/add";
//    }

//
//    @GetMapping("/products")
//    public String listProduct(ModelMap model){
//        List<Product> product = productService.findAllProduct();
//        model.addAttribute("product",product);
//            return "admin/products";
//        }


//    @GetMapping("/edit/{id}")
//    public String edit(ModelMap model, @PathVariable(name = "id") Integer id){
//        List<Size> size = sizeService.findAll();
//        model.addAttribute("size",size);
//        Optional<Product> opt = productService.findById(id);
//        model.addAttribute("product", opt.get());
//        return "product/add";
//
//    }
//    @GetMapping("/detail/{id}")
//    public String detail(ModelMap model, @PathVariable(name = "id") Integer id){
//        List<Size> size = sizeService.findAll();
//        model.addAttribute("size",size);
//        Optional<Product> opt = productService.findById(id);
//        model.addAttribute("product", opt.get());
//        return "product/detail";
//    }



//    @GetMapping("/delete/{id}")
//    public String delete(ModelMap model, @PathVariable(name = "id") Integer id){
//        productService.deleteById(id);
//        return listProduct(model);
//    }
}

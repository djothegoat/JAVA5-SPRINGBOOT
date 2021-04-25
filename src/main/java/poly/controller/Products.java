package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import poly.entity.Product;
import poly.service.CategoryService;
import poly.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Products {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/products")
    public String list(ModelMap model, HttpServletRequest request){
        request.getSession().setAttribute("productlist",null);
//        List<Product> product = productService.findAll();
//        model.addAttribute("product",product);
        return "redirect:/products/page/1";
    }

    @GetMapping("/products/page/{pageNumber}")
    public String showProductPage(HttpServletRequest request, @PathVariable int pageNumber,
                                  ModelMap model, @RequestParam(value = "name",defaultValue = "")String name){
        if(SaveLogged.authenticated()){
            model.addAttribute("login",SaveLogged.USER);
            model.addAttribute("role",SaveLogged.USER.getRole());
            model.addAttribute("name",SaveLogged.USER.getName());
            PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
            int pagesize = 9;
            List<Product> list = productService.findByName(name);
            if(pages == null){
                pages = new PagedListHolder<>(list);
                pages.setPageSize(pagesize);
            }else{
                pages = new PagedListHolder<>(list);
                final int goToPage = pageNumber - 1;
                if(goToPage <= pages.getPageCount() && goToPage >= 0){
                    pages.setPageSize(pagesize);
                    pages.setPage(goToPage);
                }
            }
            request.getSession().setAttribute("productlist",pages);
            int current = pages.getPage() + 1;
            int begin = Math.max(1,current-list.size());
            int end = Math.min(begin + 20, pages.getPageCount());
            int totalPageCount = pages.getPageCount();

            System.out.println(totalPageCount);
            String baseUrl = "/products/page/";
            model.addAttribute("beginIndex",begin);
            model.addAttribute("endIndex",end);
            model.addAttribute("currentIndex",current);
            model.addAttribute("totalPageCount",totalPageCount);
            model.addAttribute("baseUrl",baseUrl);
            model.addAttribute("category",categoryService.findAll());
            model.addAttribute("product",pages);
            return "/products";
        }else {
            PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("productlist");
            int pagesize = 9;
            List<Product> list = productService.findByName(name);
            if(pages == null){
                pages = new PagedListHolder<>(list);
                pages.setPageSize(pagesize);
            }else{
                pages = new PagedListHolder<>(list);
                final int goToPage = pageNumber - 1;
                if(goToPage <= pages.getPageCount() && goToPage >= 0){
                    pages.setPageSize(pagesize);
                    pages.setPage(goToPage);
                }
            }
            request.getSession().setAttribute("productlist",pages);
            int current = pages.getPage() + 1;
            int begin = Math.max(1,current-list.size());
            int end = Math.min(begin + 20, pages.getPageCount());
            int totalPageCount = pages.getPageCount();

            System.out.println(totalPageCount);
            String baseUrl = "/products/page/";
            model.addAttribute("beginIndex",begin);
            model.addAttribute("endIndex",end);
            model.addAttribute("currentIndex",current);
            model.addAttribute("totalPageCount",totalPageCount);
            model.addAttribute("baseUrl",baseUrl);
            model.addAttribute("category",categoryService.findAll());
            model.addAttribute("product",pages);
            model.addAttribute("search",name);
            return "/products";
        }


    }
}

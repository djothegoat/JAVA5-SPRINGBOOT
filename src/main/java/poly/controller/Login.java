package poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import poly.entity.Users;
import poly.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class Login {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(@CookieValue(value = "setUser", defaultValue = "")String setUser,ModelMap model){
        SaveLogged.USER = new Users();
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        model.addAttribute("login",null);
        model.addAttribute("user",SaveLogged.USER);
        model.addAttribute("role",null);
        return "/login";
    }

    @PostMapping("checkLogin")
    public String checkLogin(@ModelAttribute("user") Users user, @CookieValue(value = "setUser",defaultValue = "") String setUser,
                             @RequestParam("username")String username, @RequestParam("password")String password,
                             HttpServletResponse response, HttpServletRequest request, ModelMap model){

        try{
                user = userService.findByUsername(username);
                if(user == null){
                    model.addAttribute("message","username is incorect");
                    return "/login";

                }
                if(user.getPassword().equals(password)){
                        setUser = user.getEmail();
                    // create cookie and set it in response
                    Cookie cookie = new Cookie("setUser",setUser);
                    cookie.setMaxAge(24*60*60);
                    response.addCookie(cookie);
                    //get all cookies
                    Cookie[] cookies = request.getCookies();
                    //iterate each cookie
                    for (Cookie ck : cookies) {
                        //display only the cookie with the name 'setUser'
                        if (ck.getName().equals("setUser")) {
                            model.addAttribute("cookieValue", ck);
                            break;
                        } else {
                            ck.setValue("");
                            model.addAttribute("cookieValue", ck);
                            break;
                        }
                    }
                    model.addAttribute("logged","login success");
                    model.addAttribute("user",setUser);

                    //save login's information
                    SaveLogged.USER =  user;
                    boolean role = user.getRole();

                    model.addAttribute("login",SaveLogged.USER);
                    model.addAttribute("role",role);

                    return "home";

                }else {
                    user.setEmail("");
                    Cookie cookie =new Cookie("setUser",setUser);
                    model.addAttribute("cookieValue", cookie);
                    model.addAttribute("message", "password is incorrect");
                    return "/login";
                }

        } catch (Exception e){
            user.setEmail("");
            Cookie cookie =new Cookie("setUser",setUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("message", "username is incorrect");
            return "/login";
        }



    }

    @GetMapping("logout")
    public String logOUt(){
        return "/login";
    }

}

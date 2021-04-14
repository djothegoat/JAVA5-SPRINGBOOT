package poly.controller;

import org.springframework.stereotype.Controller;
import poly.entity.Users;

@Controller
public class SaveLogged {
    //save USER's information after login successfully
    public static Users USER = null;

    //remove User's information after log out
    public static void logoff() {
        SaveLogged.USER = null;
    }

    //Check isLogged or isNotLogged
    public static boolean authenticated() {
        return SaveLogged.USER != null;
    }
}


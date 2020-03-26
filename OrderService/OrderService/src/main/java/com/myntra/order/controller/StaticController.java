package com.myntra.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

    @GetMapping("/")
    public String defaaultPage(){
        return "index";
    }

    @GetMapping("/index.html")
    public String homePage(){
        return "index";
    }

    @GetMapping("/menu.html")
    public String menuPage(){
        return "menu";
    }

    @GetMapping("/about.html")
    public String aboutPage(){
        return "about";
    }

    @GetMapping("/contact.html")
    public String contactPage(){
        return "contact";
    }

    @GetMapping("/blog.html")
    public String blogPage(){
        return "blog";
    }

    @GetMapping("/blog-details.html")
    public String blogDetails(){
        return "blog-details";
    }

    @GetMapping("/gallery.html")
    public String galleryPage(){
        return "gallery";
    }

    @GetMapping("/reservation.html")
    public String reservationDetails(){
        return "reservation";
    }

    @GetMapping("/stuff.html")
    public String stuffPage(){
        return "stuff";
    }

    @GetMapping("/login.html")
    public String loginPage(){
        return "login";
    }
}

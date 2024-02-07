package com.demo.testing.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {


    @GetMapping()
    public String index() {
        return "pages/main";
    }

    @GetMapping("/main")
    public String main() {
        return "pages/main";
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

    @GetMapping("/tutorials")
    public String tutorials() {
        return "pages/tutorials";
    }

    @GetMapping("/homepage")
    public ModelAndView homepage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/pages/homepage");
        modelAndView.getModel().put("username", "Hi Jaeguk!");

        return modelAndView;
    }
}

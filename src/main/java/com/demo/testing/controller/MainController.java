package com.demo.testing.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String index() {
        return "pages/main";
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }
}

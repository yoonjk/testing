package com.demo.testing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class HelloController {




    @GetMapping("/hello")
    public String Hello(){

        String st = "testing";
        System.out.println("$$$$$$$$$$$$" + st);
        return "pages/hello";
    }
}

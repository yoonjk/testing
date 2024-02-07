package com.demo.testing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class HelloController {
    @GetMapping("/hello")
    public ModelAndView Hello(){
        ModelAndView modelAndView = new ModelAndView();

        String st = "testing";
        System.out.println("$$$$$$$$$$$$" + st);
        modelAndView.getModel().put("username", "testing");
        modelAndView.setViewName("/pages/hello");
        return modelAndView;
    }
}

package com.demo.testing.controller;

import com.demo.testing.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public String getAll(Model model, @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "6") int size,
                         @RequestParam(defaultValue = "id,asc") String[] sort) {
        return "/user/list";

    }

    @GetMapping("/register")
    public String showForm(Model model) {
        UserDto userDto = UserDto.builder().build();
        model.addAttribute("user", userDto);

        List<String> listProfession = Arrays.asList("Developer", "Tester", "Architect");
        model.addAttribute("listProfession", listProfession);

        return "/user/register_form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") UserDto user) {
        System.out.println(user);
        return "/user/register_success";
    }
}

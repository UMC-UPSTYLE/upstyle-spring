package com.upstyle.upstyle.web.controller;


import com.upstyle.upstyle.service.UserService.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final UserCommandService userCommandService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
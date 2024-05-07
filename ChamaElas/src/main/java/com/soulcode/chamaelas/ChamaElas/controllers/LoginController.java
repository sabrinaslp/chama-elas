package com.soulcode.chamaelas.ChamaElas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {
    @GetMapping
    public String showLoginForm(){
        return login;
    }
}

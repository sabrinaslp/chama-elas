package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(){
        return "login-usuario";
    }

    @GetMapping("/pagina-autenticacao")
    public String showPaginaAutenticaco() {
        return "pagina-autenticacao";
    }

}

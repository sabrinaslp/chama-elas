package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "Email ou senha inválidos, por favor verifique novamente.");
        }
        return "login-usuario";
    }




    @GetMapping("/pagina-autenticacao")
    public String showPaginaAutenticaco() {
        return "pagina-autenticacao";
    }

}

package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TecnicoController {

    @GetMapping("/pagina-tecnico")
    public String paginaTecnico() {
        return "dashboard-tecnico";
    }

    



}

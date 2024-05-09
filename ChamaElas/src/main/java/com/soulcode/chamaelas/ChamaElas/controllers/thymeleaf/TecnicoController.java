package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TecnicoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/pagina-tecnico")
    public String paginaTecnico(Model model, Authentication authentication) {
        String emailUsuario = authentication.getName();

        // Obtendo o nome do usuário a partir do e-mail
        String nomeUsuario = emailUsuario.split("@")[0];
        nomeUsuario = nomeUsuario.substring(0, 1).toUpperCase() + nomeUsuario.substring(1);

        model.addAttribute("nomeUsuario", nomeUsuario);

        List<ChamadoModel> chamadosEmAberto = chamadoService.getChamadosEmAberto();
        model.addAttribute("chamadosEmAberto", chamadosEmAberto);

        return "tecnico-chamados";
    }
}

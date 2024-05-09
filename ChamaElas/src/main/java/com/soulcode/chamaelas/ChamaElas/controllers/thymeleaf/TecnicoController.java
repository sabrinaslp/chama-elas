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
        // Recuperando o nome do usuário autenticado
        String nomeUsuario = authentication.getName();

        // Adicionando o nome do usuário ao modelo
        model.addAttribute("nomeUsuario", nomeUsuario);

        // Recuperando os chamados em aberto
        List<ChamadoModel> chamados = chamadoService.getChamadosEmAberto();

        // Adicionando os chamados ao modelo
        model.addAttribute("chamados", chamados);

        // Retornando a página
        return "tecnico-chamados";
    }











}

package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import com.soulcode.chamaelas.ChamaElas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/abertura-chamado")
    public String mostrarPaginaCadastroChamado() {
        return "abertura-chamado";
    }

    @PostMapping("/abertura-chamado")
    public String cadastrarNovoChamado(@RequestParam("nome") String nome,
                                       @RequestParam("email") String email,
                                       @RequestParam("descricao") String descricao,
                                       @RequestParam("prioridade") String prioridade,
                                       Model model) {
        try {
             long id =1;
            chamadoService.criarChamado(id, descricao, prioridade, model);
            return "abertura-chamado";
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "abertura-chamado";
        } catch (Exception e) {
            model.addAttribute("error", "Ocorreu um erro ao processar a abertura chamado.");
            return "abertura-chamado";
        }
    }
}

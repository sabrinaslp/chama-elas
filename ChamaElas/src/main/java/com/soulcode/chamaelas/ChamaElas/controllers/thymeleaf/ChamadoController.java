package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ClienteRepository;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import com.soulcode.chamaelas.ChamaElas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/abertura-chamado")
    public String mostrarPaginaCadastroChamado(Model model, Authentication authentication) {

        var cliente1 = clienteRepository.getClienteByEmail(authentication.getName());
        model.addAttribute("nomeUsuario", cliente1.getNome());

        return "abertura-chamado";
    }

    @PostMapping("/grava-chamado")
    public String cadastrarNovoChamado(
                                       @RequestParam("titulo") String titulo,
                                       @RequestParam("descricao") String descricao,
                                       Model model, Authentication authentication) {
        try {

            ClienteModel cliente = clienteRepository.getClienteByEmail(authentication.getName());

            chamadoService.criarChamado(titulo, descricao, (ClienteModel) cliente);
            model.addAttribute("authentication", authentication);
            return "redirect:/pagina-cliente";
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "abertura-chamado";
        } catch (Exception e) {
            model.addAttribute("error", "Ocorreu um erro ao processar a abertura chamado.");
            return "abertura-chamado";
        }
    }
}

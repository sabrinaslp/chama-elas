package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ClienteRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

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

    @GetMapping("/excluir-chamado")
    public String excluirChamado(@RequestParam Long chamadoId, Model model, Authentication authentication) {
        try {
            ClienteModel cliente = clienteRepository.getClienteByEmail(authentication.getName());
            model.addAttribute("authentication", authentication);

            Optional<ChamadoModel> chamadoOptional = chamadoService.findById(chamadoId);
            if (chamadoOptional.isPresent()) {
                ChamadoModel chamado = chamadoOptional.get();
                if (chamado.getStatus() == ChamadoModel.TicketStatus.ABERTO) {
                    chamadoService.deleteById(chamadoId);
                    model.addAttribute("authentication", authentication);

                } else {
                    // Caso o chamado não esteja aberto, retornar uma mensagem de erro
                    model.addAttribute("error", "Não é possível excluir chamados que não estão abertos.");

                }
            }
            return "redirect:/pagina-cliente";
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
           return "redirect:/pagina-cliente";
        } catch (Exception e) {
            model.addAttribute("error", "Ocorreu um erro ao processar a exclusão do chamado.");
           return "redirect:/pagina-cliente";
        }
    }

    @GetMapping("/excluir-chamadoAdmin")
    public String excluirChamadoAdmin(@RequestParam Long chamadoId, Model model, Authentication authentication) {
        try {
            ClienteModel cliente = clienteRepository.getClienteByEmail(authentication.getName());
            model.addAttribute("authentication", authentication);

            Optional<ChamadoModel> chamadoOptional = chamadoService.findById(chamadoId);
            if (chamadoOptional.isPresent()) {
                ChamadoModel chamado = chamadoOptional.get();
                if (chamado.getStatus() == ChamadoModel.TicketStatus.ABERTO) {
                    chamadoService.deleteById(chamadoId);
                    model.addAttribute("authentication", authentication);

                } else {
                    // Caso o chamado não esteja aberto, retornar uma mensagem de erro
                    model.addAttribute("error", "Não é possível excluir chamados que não estão abertos.");

                }
            }

            return "redirect:/administrador-chamado";
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/administrador-chamado";
        } catch (Exception e) {
            model.addAttribute("error", "Ocorreu um erro ao processar a exclusão do chamado.");
            return "redirect:/administrador-chamado";
        }
    }

    @GetMapping("/alterar-chamadoAdmin")
    public String mostrarPaginaAlterarChamado(@RequestParam Long chamadoId,Model model, Authentication authentication) {
        long id =1;
        var cliente1 = usuarioRepository.getReferenceById(id);
        model.addAttribute("nomeUsuario", cliente1.getNome());
        Optional<ChamadoModel> chamado = chamadoService.findById(chamadoId);
        if (chamado.isPresent()) {
            chamado.ifPresent(c -> model.addAttribute("chamado", c));
            return "alterar-chamado";
        }
        else
        {
            model.addAttribute("error", "Ocorreu um erro ao procurar o chamado a ser alterado.");
            return "redirect:/administrador-chamado";
        }

    }

    @PostMapping("/alterar-chamado")
    public String alterarNovoChamado(
            @RequestParam("titulo") String titulo,
            @RequestParam("descricao") String descricao,
            @RequestParam("setor") String setor,
            @RequestParam("chamadoId") Long id,
            Model model, Authentication authentication) {
        try {

            ClienteModel cliente = clienteRepository.getClienteByEmail(authentication.getName());

            chamadoService.alterarChamado(id, titulo, descricao, setor, (ClienteModel) cliente);
            model.addAttribute("authentication", authentication);
            return "redirect:/administrador-chamado";
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "alterar-chamado";
        } catch (Exception e) {
            model.addAttribute("error", "Ocorreu um erro ao processar a alteração chamado.");
            return "alterar-chamado";
        }
    }
}

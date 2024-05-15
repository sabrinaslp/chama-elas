package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.services.AdminService;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/administrador-chamado")
    public String mostrarPaginaCadastroUsuario(Model model, Authentication authentication) {
        // Obtendo o usuário autenticado
        String email = authentication.getName();
        String[] parts = email.split("@");
        String nomeUsuario = parts[0]; // Aqui está o nome de usuário obtido do email
        nomeUsuario = nomeUsuario.replaceFirst("(?i)\\b\\w", nomeUsuario.substring(0, 1).toUpperCase());

        List<ChamadoModel> listaChamados = chamadoService.listarTodosChamadosAdmin();
        int qtdChamadosEmAberto = adminService.getQuantidadeChamadosPorStatus(ChamadoModel.TicketStatus.ABERTO);
        int qtdChamadosEmAndamento = adminService.getQuantidadeChamadosPorStatus(ChamadoModel.TicketStatus.EM_ANDAMENTO);
        int qtdChamadosFinalizados = adminService.getQuantidadeChamadosPorStatus(ChamadoModel.TicketStatus.FECHADO);

        model.addAttribute("listaChamados", listaChamados);
        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("quantidadeAbertos", qtdChamadosEmAberto);
        model.addAttribute("quantidadeEmAndamento", qtdChamadosEmAndamento);
        model.addAttribute("quantidadeFinalizados", qtdChamadosFinalizados);

        return "administrador-chamado";
    }

    @GetMapping("/gerenciador-usuarios")
    public String mostrarGerenciadorUsuarios(Model model, Authentication authentication) {
        // Obtendo o usuário autenticado
        String email = authentication.getName();
        String[] parts = email.split("@");
        String nomeUsuario = parts[0]; // Aqui está o nome de usuário obtido do email
        nomeUsuario = nomeUsuario.replaceFirst("(?i)\\b\\w", nomeUsuario.substring(0, 1).toUpperCase());

        List<UsuarioModel> listaUsuariosRegistrados = adminService.getUsuariosRegistrados();


        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("listaUsuariosRegistrados", listaUsuariosRegistrados);

        return "gerenciador-usuarios";
    }

    @PostMapping("/admin-desativar-usuario")
    public String desativarUsuario(@RequestParam("usuarioId") Long usuarioId,
                                   @RequestParam("ativo") boolean ativo) {
        if (ativo) {
            adminService.inativarUsuarioPorID(usuarioId);
        } else {
            adminService.ativarUsuarioPorID(usuarioId);
        }

        return "redirect:/gerenciador-usuarios";
    }


}
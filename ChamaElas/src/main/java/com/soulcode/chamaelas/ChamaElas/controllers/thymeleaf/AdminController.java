package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.AdminRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.ClienteRepository;
import com.soulcode.chamaelas.ChamaElas.services.AdminService;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @GetMapping("/administrador-chamado")
    public String mostrarPaginaCadastroUsuario(Model model, Authentication authentication) {
        // Obtendo o usuário autenticado
        String email = authentication.getName();
        String[] parts = email.split("@");
        String nomeUsuario = parts[0]; // Aqui está o nome de usuário obtido do email

        List<ChamadoModel> listaChamados = chamadoService.listarTodosChamadosAdmin();
        int qtdChamadosEmAberto = chamadoRepository.findByStatus(ChamadoModel.TicketStatus.ABERTO).size();
        int qtdChamadosEmAndamento = chamadoRepository.findByStatus(ChamadoModel.TicketStatus.EM_ANDAMENTO).size();
        int qtdChamadosFinalizados = chamadoRepository.findByStatus(ChamadoModel.TicketStatus.FECHADO).size();

        model.addAttribute("listaChamados", listaChamados);
        model.addAttribute("nomeUsuario", nomeUsuario);
        model.addAttribute("quantidadeAbertos", qtdChamadosEmAberto);
        model.addAttribute("quantidadeEmAndamento", qtdChamadosEmAndamento);
        model.addAttribute("quantidadeFinalizados", qtdChamadosFinalizados);

        return "administrador-chamado";
    }



}
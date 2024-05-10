package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ClienteRepository;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ChamadoService chamadoService;

    @GetMapping("/pagina-cliente")
    public String paginaUsuario(Model model, Authentication authentication) {
        var cliente1 = clienteRepository.getClienteByEmail(authentication.getName());
        model.addAttribute("nomeUsuario", cliente1.getNome());


        ClienteModel cliente = clienteRepository.getClienteByEmail(authentication.getName());
        List<ChamadoModel> chamadosEmAberto = chamadoService.listarChamadosCliente(cliente);

        model.addAttribute("chamadosEmAberto", chamadosEmAberto);

        return "usuario-chamados";
    }




}

package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.AdminDTO;
import com.soulcode.chamaelas.ChamaElas.models.dto.ChamadoDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.ClienteRepository;
import com.soulcode.chamaelas.ChamaElas.services.AdminService;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import com.soulcode.chamaelas.ChamaElas.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ChamadoService chamadoService;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdminService adminService;

    @GetMapping("/administrador-chamado")
    public String mostrarPaginaCadastroUsuario(Model model , Authentication authentication) {
        List<ChamadoModel> listaChamados = chamadoService.listarTodosChamadosAdmin();
        model.addAttribute("listaChamados", listaChamados);

      //  var cliente1 = clienteRepository.getClienteByEmail(authentication.getName());
       // model.addAttribute("nomeUsuario", cliente1.getNome());

        return "administrador-chamado";

    }



}
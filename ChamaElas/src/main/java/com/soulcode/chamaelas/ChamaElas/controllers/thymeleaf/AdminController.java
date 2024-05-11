package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.AdminRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.ClienteRepository;
import com.soulcode.chamaelas.ChamaElas.services.AdminService;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String mostrarPaginaCadastroUsuario(Model model, Authentication authentication) {
        // Obtendo o usuário autenticado
        String email = authentication.getName();
        String[] parts = email.split("@");
        String nomeUsuario = parts[0]; // Aqui está o nome de usuário obtido do email
        List<ChamadoModel> listaChamados = chamadoService.listarTodosChamadosAdmin();
        model.addAttribute("listaChamados", listaChamados);
        model.addAttribute("nomeUsuario", nomeUsuario);
        return "administrador-chamado";
    }
}
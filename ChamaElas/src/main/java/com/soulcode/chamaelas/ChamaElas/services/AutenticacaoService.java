package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.RoleModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.RoleDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Verificação se o email já existe na base de dados
    public String verifiqueSeOEmailJaFoiCadastrado(String email, Model model) {
        UsuarioModel usuarioExistente = usuarioRepository.findByEmail(email);
        if (usuarioExistente != null) {
            model.addAttribute("error", "Este e-mail já está em uso. Por favor, escolha outro.");
        }
        return "cadastro-usuario";
    }

    // Verificação para confirmar a senha
    public String verifiqueSeAsSenhasSaoIguais(String senha, String confirmSenha, Model model) {
        if (!senha.equals(confirmSenha)) {
            model.addAttribute("error", "As senhas não correspondem.");
        }
        return "cadastro-usuario"; // ALTERAR PARA A PÁGINA CORRESPONDENTE
    }

    // Verificação da função (role) do usuário cadastrado
    public RoleModel atribuiFuncaoAoUsuario(String role, Model model) {
        RoleDTO roleDTO = null;

        if (role.equals("tecnico")) {
            roleDTO = new RoleDTO(1L, "Técnico");
        } else {
            roleDTO = new RoleDTO(2L, "Cliente");
        }

        return RoleDTO.toModel(roleDTO);
    }

}
package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.RoleModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.RoleDTO;
import com.soulcode.chamaelas.ChamaElas.models.dto.UsuarioDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.Instant;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    /////////////////////////////////////////////////////////////////////////////////
    // Cadastro de novo usuário /////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    public String cadastrarNovoUsuario(String nome, String email, String password, String confirmSenha, String role, Model model) {

        autenticacaoService.verifiqueSeOEmailJaFoiCadastrado(email, model);
        autenticacaoService.verifiqueSeAsSenhasSaoIguais(password, confirmSenha, model);
        RoleModel roleModel = autenticacaoService.atribuiFuncaoAoUsuario(role, model);

        UsuarioDTO usuarioDTO = new UsuarioDTO(null, nome, email, password, true, roleModel, Instant.now());
        UsuarioModel usuarioModel = UsuarioDTO.toModel(usuarioDTO);
        usuarioRepository.save(usuarioModel);

        return "login-usuario"; // ALTERAR PARA A PÁGINA CORRESPONDENTE
    }




}

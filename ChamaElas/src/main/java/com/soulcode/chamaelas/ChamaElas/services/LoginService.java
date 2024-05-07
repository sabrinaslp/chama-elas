package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.FuncaoModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.UsuarioDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.time.Instant;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    // Cadastro de novo usuário
    public String cadastrarNovoUsuario(String nome, String email, String password, String confirmSenha, String role, Model model) {

        autenticacaoService.verifiqueSeOEmailJaFoiCadastrado(email, model);
        autenticacaoService.verifiqueSeAsSenhasSaoIguais(password, confirmSenha, model);
        FuncaoModel funcaoModel = autenticacaoService.atribuiFuncaoAoUsuario(role);

        UsuarioDTO usuarioDTO = new UsuarioDTO(null, nome, email, password, true, funcaoModel, Instant.now());
        UsuarioModel usuarioModel = UsuarioDTO.toModel(usuarioDTO);
        usuarioRepository.save(usuarioModel);

        return "pagina-login"; // ALTERAR PARA A PÁGINA CORRESPONDENTE
    }

    // Login do usuário
    // Login do usuário
    public String loginUsuario(String email, String senha, Model model) {
        try {
            if (email == null || senha == null || email.isEmpty() || senha.isEmpty()) {
                throw new IllegalArgumentException("E-mail e/ou senha não podem ser vazios");
            }

            Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByEmail(email);

            if (usuarioOptional.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado");
            }

            UsuarioModel usuario = usuarioOptional.get();

            if (!usuario.getSenha().equals(senha)) {
                throw new RuntimeException("Senha incorreta");
            }

            Long tipoUsuario = usuario.getFuncao().getFuncaoId();
            String paginaRedirecionamento = obterPaginaRedirecionamento(tipoUsuario, usuario.getNome());
            return "redirect:" + paginaRedirecionamento;

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "pagina-login";
        }
    }

    // Método auxiliar para redirectionar o usuário para a página especifica
    private String obterPaginaRedirecionamento(Long tipoUsuario, String nomeUsuario) {
        return switch (tipoUsuario.intValue()) {
            case 1 -> "/pagina-tecnico?nome=" + nomeUsuario;
            case 2 -> "/pagina-usuario?nome=" + nomeUsuario;
            default -> "/pagina-admin?nome=" + nomeUsuario;
        };
    }

}

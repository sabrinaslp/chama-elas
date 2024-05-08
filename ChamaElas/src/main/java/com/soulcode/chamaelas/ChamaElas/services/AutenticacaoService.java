package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Verificação se o email já existe na base de dados
    public void verifiqueSeOEmailJaFoiCadastrado(String email) throws DataIntegrityViolationException {
        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findByEmail(email);
        if (usuarioExistente.isPresent()) {
            throw new DataIntegrityViolationException("O email '" + email + "' já está cadastrado.");
        }
    }

    // Verificação para confirmar a senha
    public void verifiqueSeAsSenhasSaoIguais(String senha, String confirmacaoSenha) {
        if (!senha.equals(confirmacaoSenha)) {
            throw new IllegalArgumentException("As senhas não correspondem.");
        }
    }

    public void verificarCadastroUsuario(String nome, String email, String senha, String confirmacaoSenha) {
        verifiqueSeOEmailJaFoiCadastrado(email);
        verifiqueSeAsSenhasSaoIguais(senha, confirmacaoSenha);
    }
}

package com.soulcode.chamaelas.ChamaElas.controllers;

import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.UsuarioDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @RequestMapping(value = "/criausuario", method = RequestMethod.POST)
    public UsuarioModel criarUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public UsuarioModel buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    public UsuarioModel atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuarioAtualizado) {
        Optional<UsuarioModel> usuarioDTOOptional = usuarioRepository.findById(id);
        if (usuarioDTOOptional.isPresent()) {
            UsuarioModel usuario = usuarioDTOOptional.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuario.getSenha());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}

package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.RoleModel;
import com.soulcode.chamaelas.ChamaElas.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private RoleRepository  roleRepository;

    public void cadastrarNovoUsuario(String nome, String email, String senha, int tipoId) {
        RoleModel usuario = new RoleModel();
        roleRepository.save(usuario);
    }

    public boolean verificarSeOEmailJaExiste(String email) {
        RoleModel usuarioExistente = roleRepository.findByEmail(email);
        return usuarioExistente != null;
    }

    public boolean confirmarSenha(String senha, String confirmSenha) {
        return senha.equals(confirmSenha);
    }

    public RoleModel findByEmail(String email) {
        return roleRepository.findByEmail(email);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

}
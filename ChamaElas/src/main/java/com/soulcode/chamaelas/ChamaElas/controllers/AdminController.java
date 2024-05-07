package com.soulcode.chamaelas.ChamaElas.controllers;


import com.soulcode.chamaelas.ChamaElas.models.AdminModel;
import com.soulcode.chamaelas.ChamaElas.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @RequestMapping(value = "/criar-admin", method = RequestMethod.POST)
    public AdminModel criarAdmin(@RequestBody AdminModel admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/buscar-admin/{id}")
    public AdminModel buscarAdminPorId(@PathVariable Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @PutMapping("/atualizar-admin/{id}")
    public AdminModel atualizarAdmin(@PathVariable Long id, @RequestBody AdminModel adminAtualizado) {
        Optional<AdminModel> adminModelOptional = adminRepository.findById(id);
        if (adminModelOptional.isPresent()) {
            AdminModel admin = adminModelOptional.get();
            admin.setNome(adminAtualizado.getNome());
            admin.setEmail(adminAtualizado.getEmail());
            admin.setSenha(adminAtualizado.getSenha());
            return adminRepository.save(admin);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    @DeleteMapping("/excluir-admin/{id}")
    public void excluirAdmin(@PathVariable Long id) {
        adminRepository.deleteById(id);
    }
}

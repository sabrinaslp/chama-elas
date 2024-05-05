package com.soulcode.chamaelas.ChamaElas.services;


import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    // Listar chamados abertos pelo próprio usuário

    // Mostra detalhes do chamado na pagina de usuário (não tem alterar o status)

    // Listar o chamados na pagina do usuário (chamados abertos e chamados criados anteriormente)

}

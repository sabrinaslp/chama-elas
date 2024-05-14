package com.soulcode.chamaelas.ChamaElas.config;
import com.soulcode.chamaelas.ChamaElas.models.FuncaoModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.repositories.FuncaoRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class AdminConfig implements CommandLineRunner {

    @Autowired
    private FuncaoRepository funcaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ChamadoService chamadoService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        Optional<UsuarioModel> userAdminOptional = usuarioRepository.findByEmail("admin@chamaelas.com");
        if (userAdminOptional.isPresent()) {
            UsuarioModel user = userAdminOptional.get();
            System.out.println("Adm já existente");
        } else {
            var funcaoTecnico = funcaoRepository.save(new FuncaoModel(1L, "Técnico"));
            var funcaoCliente = funcaoRepository.save(new FuncaoModel(2L, "Cliente"));
            var funcaoAdmin = funcaoRepository.save(new FuncaoModel(3L, "Admin"));

            var user = new UsuarioModel();

            user.setNome("Admin");
            user.setEmail("admin@chamaelas.com");
            user.setSenha(passwordEncoder.encode("1234"));
            user.setEstaAtivo(true);
            user.setFuncao(funcaoAdmin);
            usuarioRepository.save(user);
        }
    }
}
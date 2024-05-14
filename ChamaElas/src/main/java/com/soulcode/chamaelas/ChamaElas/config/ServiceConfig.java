package com.soulcode.chamaelas.ChamaElas.config;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
public class ServiceConfig implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByEmail(username);
        UsuarioModel usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + username));

//        if (!usuario.isEstaAtivo()) {
//            throw new UsernameNotFoundException("Seu período de teste gratuito expirou.");
//        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getFuncao().getNome());
        System.out.println(usuario.getFuncao().getNome());
        return new User(usuario.getEmail(), usuario.getPassword(), Collections.singleton(authority));
    }
}

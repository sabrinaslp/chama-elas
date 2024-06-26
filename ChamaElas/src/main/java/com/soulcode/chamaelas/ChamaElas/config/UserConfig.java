package com.soulcode.chamaelas.ChamaElas.config;

import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserConfig implements UserDetails {

    private final UsuarioModel usuarioModel;
    private final boolean estaAtivo;

    public UserConfig(UsuarioModel usuarioModel, boolean estaAtivo) {
        this.usuarioModel = usuarioModel;
        this.estaAtivo = estaAtivo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (usuarioModel.getFuncao() != null) {
            authorities.add(new SimpleGrantedAuthority(usuarioModel.getFuncao().getNome()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return usuarioModel.getPassword();
    }

    @Override
    public String getUsername() {
        return usuarioModel.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return estaAtivo;
    }
}

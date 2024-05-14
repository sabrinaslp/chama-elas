package com.soulcode.chamaelas.ChamaElas.config;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;


@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.stream().findFirst().map(GrantedAuthority::getAuthority).orElse("");

        var authourities = authentication.getAuthorities();
        var roles = authourities.stream().map(GrantedAuthority::getAuthority).findFirst();

        if (role.equals("Cliente")){
            response.sendRedirect("/pagina-cliente");
        } else if (role.equals("Técnico")){
            response.sendRedirect("/pagina-tecnico");
        } else if (role.equals("Admin")){
            response.sendRedirect("/administrador-chamado");
        }
    }
}
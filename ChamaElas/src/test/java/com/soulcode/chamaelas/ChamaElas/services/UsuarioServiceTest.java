package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private ChamadoRepository chamadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste se retorna lista de chamados do usuário")
    void listarChamadosUsuarioShouldReturnListOfChamadoModel() {
        // arrange
        ClienteModel cliente = new ClienteModel();
        when(chamadoRepository.findByCliente(cliente)).thenReturn(null);

        // act
        var result = usuarioService.listarChamadosUsuario(cliente);

        // assert
        assertNull(result);
    }

    @Test
    @DisplayName("Teste se retorna detalhes do chamado por ID")
    void obterDetalhesDoChamadoPorIdShouldReturnOptionalChamadoModel() {
        // arrange
        Long id = 1L;
        when(chamadoRepository.findById(id)).thenReturn(Optional.empty());

        // act
        var result = usuarioService.obterDetalhesDoChamadoPorId(id);

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Teste se lança ResponseStatusException quando o usuário não for encontrado pelo email")
    void obterUsuarioPorEmailShouldThrowResponseStatusExceptionWhenUsuarioNotFound() {
        // arrange
        String email = "example@example.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ResponseStatusException.class, () -> usuarioService.obterUsuarioPorEmail(email));
    }

    @Test
    @DisplayName("Teste se retorna false quando o token não existe")
    void verificarTokenShouldReturnFalseWhenTokenDoesNotExist() {
        // arrange
        String token = "123456";
        when(usuarioRepository.findByToken(token)).thenReturn(Optional.empty());

        // act
        var result = usuarioService.verificarToken(token);

        // assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Teste se retorna null quando a autenticação é nula")
    void getClienteLogadoShouldReturnNullWhenAuthenticationIsNull() {
        // arrange
        SecurityContextHolder.clearContext();

        // act
        var result = usuarioService.getClienteLogado();

        // assert
        assertNull(result);
    }

    @Test
    @DisplayName("Teste se retorna ClienteModel quando a autenticação não é nula e o principal é ClienteModel")
    void getClienteLogadoShouldReturnClienteModelWhenAuthenticationIsNotNullAndPrincipalIsClienteModel() {
        // arrange
        var clienteModel = new ClienteModel();
        var authentication = mock(Authentication.class);
        var securityContext = mock(SecurityContext.class);
        when(authentication.getPrincipal()).thenReturn(clienteModel);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // act
        var result = usuarioService.getClienteLogado();

        // assert
        assertEquals(clienteModel, result);
    }

    @Test
    @DisplayName("Teste se retorna null quando a autenticação não é nula e o principal não é ClienteModel")
    void getClienteLogadoShouldReturnNullWhenAuthenticationIsNotNullAndPrincipalIsNotClienteModel() {
        // arrange
        var authentication = mock(Authentication.class);
        var securityContext = mock(SecurityContext.class);
        when(authentication.getPrincipal()).thenReturn("Not a ClienteModel");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // act
        var result = usuarioService.getClienteLogado();

        // assert
        assertNull(result);
    }
}

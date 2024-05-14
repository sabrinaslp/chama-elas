package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutenticacaoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    @Test
    @DisplayName("Testa verificação se o email já foi cadastrado")
    public void testVerifiqueSeOEmailJaFoiCadastrado() {
        // arrange
        String email = "test@chamaelas.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(new UsuarioModel()));

        // act & assert
        var exception = assertThrows(DataIntegrityViolationException.class, () -> {
            autenticacaoService.verifiqueSeOEmailJaFoiCadastrado(email);
        });
        assertEquals("O email 'test@chamaelas.com' já está cadastrado.", exception.getMessage());
        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Testa verificação se as senhas são iguais")
    public void testVerifiqueSeAsSenhasSaoIguais() {
        // Arrange
        String senha = "senha123";

        assertDoesNotThrow(() -> {
            autenticacaoService.verifiqueSeAsSenhasSaoIguais(senha, "senha123");
        });

        var confirmacaoSenhaDiferente = "senha456";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            autenticacaoService.verifiqueSeAsSenhasSaoIguais(senha, confirmacaoSenhaDiferente);
        });
        assertEquals("As senhas não correspondem.", exception.getMessage());
    }

}
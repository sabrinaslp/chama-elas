package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private ChamadoRepository chamadoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    @DisplayName("Testa quantidade de chamados por status")
    public void testGetQuantidadeChamadosPorStatus() {
        // arrange
        ChamadoModel.TicketStatus status = ChamadoModel.TicketStatus.ABERTO;
        List<ChamadoModel> chamados = new ArrayList<>();
        chamados.add(new ChamadoModel());
        chamados.add(new ChamadoModel());

        when(chamadoRepository.findByStatus(status)).thenReturn(chamados);

        // act
        var quantidade = adminService.getQuantidadeChamadosPorStatus(status);

        // assert
        assertEquals(2, quantidade);
        verify(chamadoRepository, times(1)).findByStatus(status);
    }

    @Test
    @DisplayName("Testa listagem de chamados por prioridade")
    public void testGetChamadosPorPrioridade() {
        // arrange
        ChamadoModel.Prioridade prioridade = ChamadoModel.Prioridade.ALTA;
        List<ChamadoModel> chamados = new ArrayList<>();
        chamados.add(new ChamadoModel());
        chamados.add(new ChamadoModel());

        when(chamadoRepository.findByPrioridade(prioridade)).thenReturn(chamados);

        // act
        List<ChamadoModel> resultado = adminService.getChamadosPorPrioridade(prioridade);

        // assert
        assertEquals(2, resultado.size());
        verify(chamadoRepository, times(1)).findByPrioridade(prioridade);
    }

    @Test
    @DisplayName("Testa listagem de chamados por setor")
    public void testGetChamadosPorSetor() {
        // arrange
        String setor = "TI";
        List<ChamadoModel> chamados = new ArrayList<>();
        chamados.add(new ChamadoModel());
        chamados.add(new ChamadoModel());

        when(chamadoRepository.findBySetor(setor)).thenReturn(chamados);

        // act
        List<ChamadoModel> resultado = adminService.getChamadosPorSetor(setor);

        // assert
        assertEquals(2, resultado.size());
        verify(chamadoRepository, times(1)).findBySetor(setor);
    }

    @Test
    @DisplayName("Testa inativação de usuário por ID")
    public void testInativarUsuarioPorID() {
        // arrange
        Long id = 1L;
        UsuarioModel usuario = new UsuarioModel();
        usuario.setEstaAtivo(true);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // act
        adminService.inativarUsuarioPorID(id);

        // assert
        assertFalse(usuario.isEstaAtivo());
        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(usuario);
    }
}

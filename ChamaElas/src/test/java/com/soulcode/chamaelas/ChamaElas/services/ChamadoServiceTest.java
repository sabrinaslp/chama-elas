package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChamadoServiceTest {

    @Mock
    private ChamadoRepository chamadoRepository;

    @InjectMocks
    private ChamadoService chamadoService;

    @Test
    @DisplayName("Testa criação de um novo chamado")
    public void testCriarChamado() {
        // arrange
        String titulo = "Problemas ao entrar no sistema interno";
        String descricao = "Não consigo acessar pois está dando que não sou autorizada";
        ClienteModel clienteModel = new ClienteModel();

        ChamadoModel chamadoSalvo = new ChamadoModel();
        chamadoSalvo.setTicketId(1L);
        chamadoSalvo.setTitulo(titulo);
        chamadoSalvo.setDescricao(descricao);
        chamadoSalvo.setStatus(ChamadoModel.TicketStatus.ABERTO);
        chamadoSalvo.setCliente(clienteModel);

        when(chamadoRepository.save(any(ChamadoModel.class))).thenReturn(chamadoSalvo);

        // act
        ChamadoModel novoChamado = chamadoService.criarChamado(titulo, descricao, clienteModel);

        // assert
        assertNotNull(novoChamado);
        assertEquals(1L, novoChamado.getTicketId());
        assertEquals(titulo, novoChamado.getTitulo());
        assertEquals(descricao, novoChamado.getDescricao());
        assertEquals(ChamadoModel.TicketStatus.ABERTO, novoChamado.getStatus());
        assertEquals(clienteModel, novoChamado.getCliente());
        verify(chamadoRepository, times(1)).save(any(ChamadoModel.class));
    }

    @Test
    @DisplayName("Testa alteração de status e prioridade para um chamado existente")
    public void testAlteraStatusEPrioridadeDoChamado_Existente() {
        // arrange
        Long chamadoId = 1L;
        ChamadoModel chamadoExistente = new ChamadoModel();
        chamadoExistente.setTicketId(chamadoId);

        // act
        when(chamadoRepository.findById(chamadoId)).thenReturn(Optional.of(chamadoExistente));
        when(chamadoRepository.save(chamadoExistente)).thenReturn(chamadoExistente);

        chamadoService.alteraStatusEPrioridadeDoChamado(chamadoId, ChamadoModel.Prioridade.ALTA);

        // assert
        assertEquals(ChamadoModel.TicketStatus.EM_ANDAMENTO, chamadoExistente.getStatus());
        assertEquals(ChamadoModel.Prioridade.ALTA, chamadoExistente.getPrioridade());
        verify(chamadoRepository, times(1)).findById(chamadoId);
        verify(chamadoRepository, times(1)).save(chamadoExistente);
    }

    @Test
    @DisplayName("Testa alteração de status e prioridade para um chamado não existente")
    public void testAlteraStatusEPrioridadeDoChamado_NaoExistente() {
        // arrange
        Long chamadoId = 1L;

        // act
        when(chamadoRepository.findById(chamadoId)).thenReturn(Optional.empty());

        // assert
        assertThrows(RuntimeException.class, () -> chamadoService.alteraStatusEPrioridadeDoChamado(chamadoId, ChamadoModel.Prioridade.ALTA));
        verify(chamadoRepository, times(1)).findById(chamadoId);
        verify(chamadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Testa associação de técnico a um chamado existente")
    public void testAssociarTecnicoAoChamado_Existente() {
        // arrange
        Long chamadoId = 1L;
        TecnicoModel tecnico = new TecnicoModel();
        tecnico.setSetor("Administrativo");

        ChamadoModel chamadoExistente = new ChamadoModel();
        chamadoExistente.setTicketId(chamadoId);

        // act
        when(chamadoRepository.findById(chamadoId)).thenReturn(Optional.of(chamadoExistente));
        when(chamadoRepository.save(chamadoExistente)).thenReturn(chamadoExistente);

        chamadoService.associarTecnicoAoChamado(chamadoId, tecnico);

        // assert
        assertEquals(tecnico, chamadoExistente.getTecnico());
        assertEquals("Administrativo", chamadoExistente.getSetor());
        verify(chamadoRepository, times(1)).findById(chamadoId);
        verify(chamadoRepository, times(1)).save(chamadoExistente);
    }

    @Test
    @DisplayName("Testa desassociação de dados atribuídos a um chamado")
    public void testDesassociarDadosAtribuidosAoChamado() {
        // arrange
        Long chamadoId = 1L;
        ChamadoModel chamadoExistente = new ChamadoModel();
        chamadoExistente.setTicketId(chamadoId);
        chamadoExistente.setTecnico(new TecnicoModel());
        chamadoExistente.setStatus(ChamadoModel.TicketStatus.FECHADO);

        // act
        when(chamadoRepository.findById(chamadoId)).thenReturn(Optional.of(chamadoExistente));
        when(chamadoRepository.save(chamadoExistente)).thenReturn(chamadoExistente);

        chamadoService.desassociarDadosAtribuidosAoChamado(chamadoId, ChamadoModel.TicketStatus.ABERTO);

        // assert
        assertNull(chamadoExistente.getTecnico());
        assertNull(chamadoExistente.getMotivoEncerramento());
        assertEquals(ChamadoModel.TicketStatus.ABERTO, chamadoExistente.getStatus());
        verify(chamadoRepository, times(1)).findById(chamadoId);
        verify(chamadoRepository, times(1)).save(chamadoExistente);
    }

    @Test
    @DisplayName("Testa definição de motivo de encerramento para um chamado existente")
    public void testDefinirMotivoEncerramento_Existente() {
        // arrange
        Long chamadoId = 1L;
        String motivoEncerramento = "Problema resolvido";

        ChamadoModel chamadoExistente = new ChamadoModel();
        chamadoExistente.setTicketId(chamadoId);

        // act
        when(chamadoRepository.findById(chamadoId)).thenReturn(Optional.of(chamadoExistente));
        when(chamadoRepository.save(chamadoExistente)).thenReturn(chamadoExistente);

        chamadoService.definirMotivoEncerramento(chamadoId, ChamadoModel.TicketStatus.FECHADO, motivoEncerramento);

        // assert
        assertEquals("Problema resolvido", chamadoExistente.getMotivoEncerramento());
        assertEquals(ChamadoModel.TicketStatus.FECHADO, chamadoExistente.getStatus());
        verify(chamadoRepository, times(1)).findById(chamadoId);
        verify(chamadoRepository, times(1)).save(chamadoExistente);
    }

    @Test
    @DisplayName("Testa lançamento de exceção ao definir motivo de encerramento para um chamado não existente")
    public void testDefinirMotivoEncerramento_NaoExistente() {
        // arrange
        Long chamadoId = 1L;

        // act
        when(chamadoRepository.findById(chamadoId)).thenReturn(Optional.empty());

        // assert
        assertThrows(RuntimeException.class, () -> chamadoService.definirMotivoEncerramento(chamadoId, ChamadoModel.TicketStatus.FECHADO, "Problema resolvido"));
        verify(chamadoRepository, times(1)).findById(chamadoId);
        verify(chamadoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Testa atualização de chamado para usuário existente")
    public void testAtualizarChamadoUsuario_Existente() {
        // arrange
        Long ticketId = 1L;
        ChamadoModel chamadoExistente = new ChamadoModel();
        chamadoExistente.setTicketId(ticketId);
        chamadoExistente.setDescricao("Descrição antiga");

        ChamadoModel chamadoAtualizado = new ChamadoModel();
        chamadoAtualizado.setDescricao("Nova descrição");

        when(chamadoRepository.findById(ticketId)).thenReturn(Optional.of(chamadoExistente));
        when(chamadoRepository.save(chamadoExistente)).thenReturn(chamadoExistente);

        // act
        ChamadoModel resultado = chamadoService.atualizarChamadoUsuario(ticketId, chamadoAtualizado);

        // assert
        assertNotNull(resultado);
        assertEquals(chamadoAtualizado.getDescricao(), resultado.getDescricao());
        verify(chamadoRepository, times(1)).findById(ticketId);
        verify(chamadoRepository, times(1)).save(chamadoExistente);
    }

    @Test
    @DisplayName("Testa atualização de chamado para usuário não existente")
    public void testAtualizarChamadoUsuario_NaoExistente() {
        // arrange
        Long ticketId = 1L;
        ChamadoModel chamadoAtualizado = new ChamadoModel();
        chamadoAtualizado.setDescricao("Nova descrição");

        when(chamadoRepository.findById(ticketId)).thenReturn(Optional.empty());

        // act
        ChamadoModel resultado = chamadoService.atualizarChamadoUsuario(ticketId, chamadoAtualizado);

        // assert
        assertNull(resultado);
        verify(chamadoRepository, times(1)).findById(ticketId);
        verify(chamadoRepository, never()).save(any());
    }

}

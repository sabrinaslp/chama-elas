package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.TecnicoRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Getter
    @Setter
    private String nomeCliente;

    // Outros métodos


    // Salva o chamado no banco
    public ChamadoModel save(ChamadoModel chamado) {
        return chamadoRepository.save(chamado);
    }

    // Lista chamados disponíveis - sem atribuição de técnico
    public List<ChamadoModel> getChamadosEmAberto() {
        return chamadoRepository.findByStatus(ChamadoModel.TicketStatus.ABERTO);
    }

    // Lista chamados atribuídos ao técnico logado
    public List<ChamadoModel> getChamadosAtribuidos(TecnicoModel tecnico) {
        return chamadoRepository.findByTecnico(tecnico);
    }

    // Lista todos os chamados disponíveis (atribuídos ou não)
    public List<ChamadoModel> findAll() {
        return chamadoRepository.findAll();
    }

    // Procura um chamado pelo ID
    public Optional<ChamadoModel> findById(Long id) {
        return chamadoRepository.findById(id);
    }

    // Procura um chamado pelo cliente
    public List<ChamadoModel> findByClient(ClienteModel cliente) {
        return chamadoRepository.findByCliente(cliente);
    }

    // Deleta um chamado pelo ID
    public void deleteById(Long id) {
        chamadoRepository.deleteById(id);
    }

    // Cria um novo chamado
    public ChamadoModel criarChamado(String titulo, String descricao, ClienteModel model) {
        //   ChamadoDTO novoChamado = new ChamadoDTO(id,"", descricao ,"" ,"0",prioridade);

        ChamadoModel novoChamado = new ChamadoModel();
        novoChamado.setTitulo(titulo);
        novoChamado.setDescricao(descricao);
        //novoChamado.setSetor(prioridade);
        novoChamado.setStatus(ChamadoModel.TicketStatus.ABERTO);
        novoChamado.setCliente(model);

        return chamadoRepository.save(novoChamado);
    }

    public ChamadoModel criarChamado() {
        ChamadoModel novoChamado = new ChamadoModel();
        return chamadoRepository.save(novoChamado);
    }

    // Lista chamados relacionados ao usuário
    public List<ChamadoModel> listarChamadosCliente(ClienteModel cliente) {
        return usuarioService.listarChamadosUsuario(cliente);
    }

    // Edita um chamado do usuário
    public ChamadoModel editarChamadoUsuario(Long ticketId) {
        return chamadoRepository.findById(ticketId).orElse(null);
    }

    // Edita um chamado do técnico
    public ChamadoModel editarChamadoTecnico(Long ticketId) {
        return chamadoRepository.findById(ticketId).orElse(null);
    }

    // Atualiza um chamado do usuário
    public ChamadoModel atualizarChamadoUsuario(Long ticketId, ChamadoModel chamadoAtualizado) {
        Optional<ChamadoModel> optionalChamado = chamadoRepository.findById(ticketId);
        if (optionalChamado.isPresent()) {
            ChamadoModel chamadoExistente = optionalChamado.get();
            chamadoExistente.setDescricao(chamadoAtualizado.getDescricao());
            return chamadoRepository.save(chamadoExistente);
        } else {
            return null;
        }
    }

    // Atualiza um chamado do técnico
    public ChamadoModel atualizarChamadoTecnico(Long ticketId, ChamadoModel chamadoAtualizado) {
        Optional<ChamadoModel> optionalChamado = chamadoRepository.findById(ticketId);
        if (optionalChamado.isPresent()) {
            ChamadoModel chamadoExistente = optionalChamado.get();
            chamadoExistente.setDescricao(chamadoAtualizado.getDescricao());
            return chamadoRepository.save(chamadoExistente);
        } else {
            return null;
        }
    }

    // Lista todos os chamados para o administrador
    public List<ChamadoModel> listarTodosChamadosAdmin() {
        return chamadoRepository.findAll();
    }

    // Listar todos os chamados atribuidos do Tecnico Logado
    public List<ChamadoModel> getChamadosAtribuidosAoTecnicoLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailTecnico = authentication.getName();

        TecnicoModel tecnicoLogado = tecnicoRepository.getTecnicoByEmail(emailTecnico);
        if (tecnicoLogado != null) {
            return chamadoRepository.findByTecnico(tecnicoLogado);
        } else {
            return null;
        }
    }

    // Altera o status e a prioridade do chamado na página do técnico
    public void alteraStatusEPrioridadeDoChamado(Long chamadoId, ChamadoModel.Prioridade prioridade) {
        ChamadoModel chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.setStatus(ChamadoModel.TicketStatus.EM_ANDAMENTO);
        chamado.setPrioridade(prioridade);

        chamadoRepository.save(chamado);
    }

    public void alterarStatusChamado(Long chamadoId, ChamadoModel.TicketStatus status) {
        Optional<ChamadoModel> optionalChamado = chamadoRepository.findById(chamadoId);
        if (optionalChamado.isPresent()) {
            ChamadoModel chamado = optionalChamado.get();
            chamado.setStatus(status);
            chamadoRepository.save(chamado);
        } else {
            throw new RuntimeException("Chamado não encontrado");
        }
    }

    // Cria chamados ficticios para serem realizados testes
    public void criaChamadosFicticios() {
        ChamadoModel chamado1 = new ChamadoModel();
        chamado1.setTitulo("Chamado Teste 1");
        chamado1.setDescricao("Problema na impressora");
        chamado1.setSetor("Administrativo");
        chamado1.setStatus(ChamadoModel.TicketStatus.ABERTO);
        chamado1.setPrioridade(null);
        chamadoRepository.save(chamado1);

        ChamadoModel chamado2 = new ChamadoModel();
        chamado2.setTitulo("Chamado Teste 2");
        chamado2.setDescricao("Problema no teclado");
        chamado2.setSetor("TI");
        chamado2.setStatus(ChamadoModel.TicketStatus.ABERTO);
        chamado2.setPrioridade(null);
        chamadoRepository.save(chamado2);
    }

    public void associarTecnicoAoChamado(Long chamadoId, TecnicoModel tecnico) {
        ChamadoModel chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));

        chamado.setTecnico(tecnico);
        chamado.setSetor(tecnico.getSetor());
        chamadoRepository.save(chamado);
    }

    public void desassociarDadosAtribuidosAoChamado(Long chamadoId, ChamadoModel.TicketStatus novoStatus) {
        ChamadoModel chamado = chamadoRepository.findById(chamadoId)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
        if (novoStatus == ChamadoModel.TicketStatus.ABERTO) {
            chamado.setTecnico(null);
            if (chamado.getStatus() == ChamadoModel.TicketStatus.FECHADO) {
                chamado.setMotivoEncerramento(null);
                chamado.setStatus(ChamadoModel.TicketStatus.ABERTO);
            }
            chamadoRepository.save(chamado);
        }
    }

    public void definirMotivoEncerramento(Long chamadoId, ChamadoModel.TicketStatus novoStatus, String motivoEncerramento) {
        Optional<ChamadoModel> optionalChamado = chamadoRepository.findById(chamadoId);
        if (optionalChamado.isPresent()) {
            ChamadoModel chamado = optionalChamado.get();
            chamado.setStatus(novoStatus);
            chamado.setMotivoEncerramento(motivoEncerramento);
            chamadoRepository.save(chamado);
        } else {
            throw new RuntimeException("Chamado não encontrado com o ID: " + chamadoId);
        }
    }

    public ChamadoModel alterarChamado(Long id , String titulo, String descricao,String setor, ClienteModel model) {

        ChamadoModel novoChamado = chamadoRepository.getReferenceById(id);

        novoChamado.setTitulo(titulo);
        novoChamado.setDescricao(descricao);
        novoChamado.setSetor(setor);

        return chamadoRepository.save(novoChamado);
    }
}
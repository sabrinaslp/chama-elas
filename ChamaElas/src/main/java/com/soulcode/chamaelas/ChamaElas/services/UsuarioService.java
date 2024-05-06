package com.soulcode.chamaelas.ChamaElas.services;


import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    // Listar todos os chamados criados pelo usuário
    public List<ChamadoModel> listarTodosOsChamadosDoUsuario(ClienteModel usuario) {
        return chamadoRepository.findByCliente(usuario);
    }

    // Listar todos os chamados criados pelo usuário que ainda estão em aberto
    public List<ChamadoModel> listarChamadosEmAbertoDoUsuario(ClienteModel usuario) {
        return chamadoRepository.findByClienteAndStatus(usuario, ChamadoModel.TicketStatus.ABERTO);
    }

    // Mostra detalhes do chamado na pagina de usuário (não deve ter alterar o status na pagina)
    public Optional<ChamadoModel> obterDetalhesDoChamadoPorId(Long id) {
        return chamadoRepository.findById(id);
    }

}

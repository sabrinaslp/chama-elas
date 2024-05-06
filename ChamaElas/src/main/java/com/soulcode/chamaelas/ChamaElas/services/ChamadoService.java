package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    // Salva o chamado no bacno
    public ChamadoModel save( ) {
        ChamadoModel novoChamado = new ChamadoModel();
        return chamadoRepository.save(novoChamado);
    }

    // Lista chamados disponiveis - sem atribuição de técnico
    public List<ChamadoModel> getChamadosEmAberto() {
        return chamadoRepository.findByStatus(ChamadoModel.TicketStatus.ABERTO);
    }

    // Lista chamados atribuidos ao técnico logado
    public List<ChamadoModel> getChamadosAtribuidos(TecnicoModel tecnico) {
        return chamadoRepository.findByTecnico(tecnico);
    }

    // Lista todos os chamados disponíveis (atribuidos ou não)
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
}

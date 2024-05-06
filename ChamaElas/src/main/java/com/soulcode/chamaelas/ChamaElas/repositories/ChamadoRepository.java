package com.soulcode.chamaelas.ChamaElas.repositories;


import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<ChamadoModel, Long> {
    List<ChamadoModel> findByCliente(ClienteModel cliente);

    List<ChamadoModel> findByTecnico(TecnicoModel tecnico);

    List<ChamadoModel> findByStatus(ChamadoModel.TicketStatus status);

    List<ChamadoModel> findByPrioridade(ChamadoModel.Prioridade prioridade);

    List<ChamadoModel> findBySetor(String setor);

    List<ChamadoModel> findByClienteAndStatus(ClienteModel usuario, ChamadoModel.TicketStatus ticketStatus);
}

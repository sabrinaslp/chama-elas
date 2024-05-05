package com.soulcode.chamaelas.ChamaElas.repositories;


import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<ChamadoModel, Long> {
    List<ChamadoModel> findByStatusId(int statusId);

    List<ChamadoModel> findByClient(ClienteModel cliente);

    List<ChamadoModel> findByTecnico(TecnicoModel tecnico);

    List<ChamadoModel> findByStatus(ChamadoModel.TicketStatus status);

    List<ChamadoModel> findByPriority(ChamadoModel.Priority priority);

    List<ChamadoModel> findByDepartament(String department);

    List<ChamadoModel> findByClientAndStatus(ClienteModel usuario, ChamadoModel.TicketStatus ticketStatus);
}

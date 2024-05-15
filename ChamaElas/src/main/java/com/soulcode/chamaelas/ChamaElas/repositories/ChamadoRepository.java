package com.soulcode.chamaelas.ChamaElas.repositories;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoModel, Long> {

    List<ChamadoModel> findByCliente(ClienteModel cliente);

    List<ChamadoModel> findByTecnico(TecnicoModel tecnico);

    List<ChamadoModel> findByStatus(ChamadoModel.TicketStatus status);

    List<ChamadoModel> findByPrioridade(ChamadoModel.Prioridade prioridade);

    List<ChamadoModel> findBySetor(String setor);

    List<ChamadoModel> findByClienteAndStatus(ClienteModel cliente, ChamadoModel.TicketStatus status);
}

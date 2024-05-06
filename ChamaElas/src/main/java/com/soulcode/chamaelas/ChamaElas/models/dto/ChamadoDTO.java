package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel.Prioridade;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel.TicketStatus;

public record ChamadoDTO(
        Long ticketId,
        String descricao,
        String departamento,
        String status,
        String prioridade
) {

    public static ChamadoDTO fromModel(ChamadoModel chamadoModel) {
        return new ChamadoDTO(
                chamadoModel.getTicketId(),
                chamadoModel.getDescricao(),
                chamadoModel.getSetor(),
                chamadoModel.getStatus().getDescricao(),
                chamadoModel.getPrioridade().name()
        );
    }

    public static ChamadoModel toModel(ChamadoDTO chamadoDTO) {
        ChamadoModel chamadoModel = new ChamadoModel();
        chamadoModel.setTicketId(chamadoDTO.ticketId());
        chamadoModel.setDescricao(chamadoDTO.descricao());
        chamadoModel.setSetor(chamadoDTO.departamento());
        chamadoModel.setStatus(getTicketStatus(chamadoDTO.status()));
        chamadoModel.setPrioridade(getPrioridade(chamadoDTO.prioridade()));
        return chamadoModel;
    }

    public static TicketStatus getTicketStatus(String status) {
        for (TicketStatus ticketStatus : TicketStatus.values()) {
            if (ticketStatus.getDescricao().equalsIgnoreCase(status)) {
                return ticketStatus;
            }
        }

        throw new IllegalArgumentException("Chamado inválido status: " + status);
    }

    public static Prioridade getPrioridade(String prioridade) {
        for (Prioridade prio : Prioridade.values()) {
            if (prio.name().equalsIgnoreCase(prioridade)) {
                return prio;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida: " + prioridade);
    }
}

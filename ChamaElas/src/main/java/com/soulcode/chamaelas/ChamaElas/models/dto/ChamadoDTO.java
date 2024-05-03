package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel.Priority;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel.TicketStatus;

public record ChamadoDTO(
        Long ticketId,
        String description,
        String department,
        String status,
        String priority
) {

    public static ChamadoDTO fromModel(ChamadoModel chamadoModel) {
        return new ChamadoDTO(
                chamadoModel.getTicketId(),
                chamadoModel.getDescription(),
                chamadoModel.getDepartment(),
                chamadoModel.getStatus().getDescription(),
                chamadoModel.getPriority().name()
        );
    }

    public static ChamadoModel toModel(ChamadoDTO chamadoDTO) {
        ChamadoModel chamadoModel = new ChamadoModel();
        chamadoModel.setTicketId(chamadoDTO.ticketId());
        chamadoModel.setDescription(chamadoDTO.description());
        chamadoModel.setDepartment(chamadoDTO.department());
        chamadoModel.setStatus(getTicketStatus(chamadoDTO.status()));
        chamadoModel.setPriority(getPriority(chamadoDTO.priority()));
        return chamadoModel;
    }

    public static TicketStatus getTicketStatus(String status) {
        for (TicketStatus ticketStatus : TicketStatus.values()) {
            if (ticketStatus.getDescription().equalsIgnoreCase(status)) {
                return ticketStatus;
            }
        }

        throw new IllegalArgumentException("Chamado inválido status: " + status);
    }

    public static Priority getPriority(String priority) {
        for (Priority prio : Priority.values()) {
            if (prio.name().equalsIgnoreCase(priority)) {
                return prio;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida: " + priority);
    }
}

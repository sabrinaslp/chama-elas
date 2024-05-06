package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.AdminModel;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import java.util.List;
import java.util.stream.Collectors;

public record AdminDTO(
        List<ChamadoDTO> ticketsAbertos,
        List<ClienteDTO> clientesRegistrados
) {
    public static AdminDTO fromModel(AdminModel adminModel) {
        return new AdminDTO(
                adminModel.getTicketsAbertos().stream()
                        .map(ChamadoDTO::fromModel)
                        .collect(Collectors.toList()),
                adminModel.getClientesRegistrados().stream()
                        .map(ClienteDTO::fromModel)
                        .collect(Collectors.toList())
        );
    }

    public static AdminModel toModel(AdminDTO adminDTO) {
        AdminModel adminModel = new AdminModel();
        List<ChamadoModel> chamadoModels = adminDTO.ticketsAbertos().stream()
                .map(ChamadoDTO::toModel)
                .collect(Collectors.toList());
        adminModel.setTicketsAbertos(chamadoModels);
        List<ClienteModel> clienteModels = adminDTO.clientesRegistrados().stream()
                .map(ClienteDTO::toModel)
                .collect(Collectors.toList());
        adminModel.setClientesRegistrados(clienteModels);

        return adminModel;
    }
}

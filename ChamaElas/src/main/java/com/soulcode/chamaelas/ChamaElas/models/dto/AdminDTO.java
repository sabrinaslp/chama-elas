package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.AdminModel;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import java.util.List;
import java.util.stream.Collectors;

public record AdminDTO(
        List<ChamadoDTO> openTickets,
        List<ClienteDTO> registeredClients
) {
    public static AdminDTO fromModel(AdminModel adminModel) {
        return new AdminDTO(
                adminModel.getOpenTickets().stream()
                        .map(ChamadoDTO::fromModel)
                        .collect(Collectors.toList()),
                adminModel.getRegisteredClients().stream()
                        .map(ClienteDTO::fromModel)
                        .collect(Collectors.toList())
        );
    }

    public static AdminModel toModel(AdminDTO adminDTO) {
        AdminModel adminModel = new AdminModel();
        List<ChamadoModel> chamadoModels = adminDTO.openTickets().stream()
                .map(ChamadoDTO::toModel)
                .collect(Collectors.toList());
        adminModel.setOpenTickets(chamadoModels);
        List<ClienteModel> clienteModels = adminDTO.registeredClients().stream()
                .map(ClienteDTO::toModel)
                .collect(Collectors.toList());
        adminModel.setRegisteredClients(clienteModels);

        return adminModel;
    }
}

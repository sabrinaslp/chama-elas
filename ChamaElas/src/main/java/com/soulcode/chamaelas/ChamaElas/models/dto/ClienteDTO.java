package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;

public record ClienteDTO(
        Long id,
        String name,
        String email,
        String endereco
) {
    public static ClienteDTO fromModel(ClienteModel clienteModel) {
        return new ClienteDTO(
                clienteModel.getUserId(),
                clienteModel.getName(),
                clienteModel.getEmail(),
                clienteModel.getEndereco()
        );
    }

    public static ClienteModel toModel(ClienteDTO clienteDTO) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setUserId(clienteDTO.id());
        clienteModel.setName(clienteDTO.name());
        clienteModel.setEmail(clienteDTO.email());
        clienteModel.setEndereco(clienteDTO.endereco());
        return clienteModel;
    }
}

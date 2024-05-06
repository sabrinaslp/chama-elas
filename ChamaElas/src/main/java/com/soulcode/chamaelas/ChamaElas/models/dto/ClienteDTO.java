package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;

public record ClienteDTO(
        Long id,
        String nome,
        String email,
        String endereco
) {
    public static ClienteDTO fromModel(ClienteModel clienteModel) {
        return new ClienteDTO(
                clienteModel.getUsuarioId(),
                clienteModel.getNome(),
                clienteModel.getEmail(),
                clienteModel.getEndereco()
        );
    }

    public static ClienteModel toModel(ClienteDTO clienteDTO) {
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setUsuarioId(clienteDTO.id());
        clienteModel.setNome(clienteDTO.nome());
        clienteModel.setEmail(clienteDTO.email());
        clienteModel.setEndereco(clienteDTO.endereco());
        return clienteModel;
    }
}

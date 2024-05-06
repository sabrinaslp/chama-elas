package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;

public record TecnicoDTO(
        Long userId,
        String nome,
        String email,
        boolean estaAtivo,
        String setor,
        String telefone
) {
    public static TecnicoDTO fromModel(TecnicoModel tecnicoModel) {
        return new TecnicoDTO(
                tecnicoModel.getUsuarioId(),
                tecnicoModel.getNome(),
                tecnicoModel.getEmail(),
                tecnicoModel.isEstaAtivo(),
                tecnicoModel.getSetor(),
                tecnicoModel.getTelefone()
        );
    }

    public static TecnicoModel toModel(TecnicoDTO tecnicoDTO) {
        TecnicoModel tecnicoModel = new TecnicoModel();
        tecnicoModel.setUsuarioId(tecnicoDTO.userId());
        tecnicoModel.setNome(tecnicoDTO.nome());
        tecnicoModel.setEmail(tecnicoDTO.email());
        tecnicoModel.setEstaAtivo(tecnicoDTO.estaAtivo());
        tecnicoModel.setSetor(tecnicoDTO.setor());
        tecnicoModel.setTelefone(tecnicoDTO.telefone());
        return tecnicoModel;
    }
}

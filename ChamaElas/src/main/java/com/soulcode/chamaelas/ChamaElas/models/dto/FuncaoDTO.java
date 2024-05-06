package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.FuncaoModel;

public record FuncaoDTO(
        Long funcaoId,
        String nome
) {

    public static FuncaoDTO fromModel(FuncaoModel funcaoModel) {
        return new FuncaoDTO(
                funcaoModel.getFuncaoId(),
                funcaoModel.getNome()
        );
    }

    public static FuncaoModel toModel(FuncaoDTO funcaoDTO) {
        FuncaoModel funcaoModel = new FuncaoModel();
        funcaoModel.setFuncaoId(funcaoDTO.funcaoId());
        funcaoModel.setNome(funcaoDTO.nome());
        return funcaoModel;
    }
}

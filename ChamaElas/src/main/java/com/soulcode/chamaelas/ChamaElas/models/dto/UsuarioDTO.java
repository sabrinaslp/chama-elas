package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.FuncaoModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import java.time.Instant;

public record UsuarioDTO(
        Long usuarioId,
        String nome,
        String email,
        String senha,
        boolean estaAtivo,
        FuncaoModel funcao,
        Instant dataRegistro
) {
    public static UsuarioDTO fromModel(UsuarioModel usuarioModel) {
        return new UsuarioDTO(
                usuarioModel.getUsuarioId(),
                usuarioModel.getNome(),
                usuarioModel.getEmail(),
                usuarioModel.getSenha(),
                usuarioModel.isEstaAtivo(),
                usuarioModel.getFuncao(),
                usuarioModel.getDataRegistro()
        );
    }

    public static UsuarioModel toModel(UsuarioDTO usuarioDTO) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUsuarioId(usuarioDTO.usuarioId());
        usuarioModel.setNome(usuarioDTO.nome());
        usuarioModel.setEmail(usuarioDTO.email());
        usuarioModel.setSenha(usuarioDTO.senha());
        usuarioModel.setEstaAtivo(usuarioDTO.estaAtivo());
        usuarioModel.setFuncao(usuarioDTO.funcao());
        usuarioModel.setDataRegistro(usuarioDTO.dataRegistro());

        return usuarioModel;
    }
}

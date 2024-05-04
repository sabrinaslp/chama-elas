package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.RoleModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import java.time.Instant;

public record UsuarioDTO(
        Long userId,
        String name,
        String email,
        String password,
        boolean isActive,
        RoleModel role,
        Instant creationTimeStamp
) {
    public static UsuarioDTO fromModel(UsuarioModel usuarioModel) {
        return new UsuarioDTO(
                usuarioModel.getUserId(),
                usuarioModel.getName(),
                usuarioModel.getEmail(),
                usuarioModel.getPassword(),
                usuarioModel.isActive(),
                usuarioModel.getRole(),
                usuarioModel.getCreationTimeStamp()
        );
    }

    public static UsuarioModel toModel(UsuarioDTO usuarioDTO) {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setUserId(usuarioDTO.userId());
        usuarioModel.setName(usuarioDTO.name());
        usuarioModel.setEmail(usuarioDTO.email());
        usuarioModel.setPassword(usuarioDTO.password());
        usuarioModel.setIsActive(usuarioDTO.isActive());
        usuarioModel.setRole(usuarioDTO.role());
        usuarioModel.setCreationTimeStamp(usuarioDTO.creationTimeStamp());

        return usuarioModel;
    }
}

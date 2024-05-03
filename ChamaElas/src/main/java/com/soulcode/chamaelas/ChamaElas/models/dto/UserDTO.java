package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.RoleModel;
import com.soulcode.chamaelas.ChamaElas.models.UserModel;
import java.time.Instant;

public record UserDTO(
        Long userId,
        String name,
        String email,
        String password,
        boolean isActive,
        RoleModel role,
        Instant creationTimeStamp
) {
    public static UserDTO fromModel(UserModel userModel) {
        return new UserDTO(
                userModel.getUserId(),
                userModel.getName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.isActive(),
                userModel.getRole(),
                userModel.getCreationTimeStamp()
        );
    }

    public static UserModel toModel(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setUserId(userDTO.userId());
        userModel.setName(userDTO.name());
        userModel.setEmail(userDTO.email());
        userModel.setPassword(userDTO.password());
        userModel.setIsActive(userDTO.isActive());
        userModel.setRole(userDTO.role());
        userModel.setCreationTimeStamp(userDTO.creationTimeStamp());
        return userModel;
    }
}

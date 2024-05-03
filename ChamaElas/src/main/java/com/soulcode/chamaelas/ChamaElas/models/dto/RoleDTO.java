package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.RoleModel;

public record RoleDTO(
        Long roleId,
        String name
) {
    public static RoleDTO fromModel(RoleModel roleModel) {
        return new RoleDTO(
                roleModel.getRoleId(),
                roleModel.getName()
        );
    }

    public static RoleModel toModel(RoleDTO roleDTO) {
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleId(roleDTO.roleId());
        roleModel.setName(roleDTO.name());
        return roleModel;
    }
}

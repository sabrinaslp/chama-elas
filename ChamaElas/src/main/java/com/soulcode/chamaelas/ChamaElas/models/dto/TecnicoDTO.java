package com.soulcode.chamaelas.ChamaElas.models.dto;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;

public record TecnicoDTO(
        Long userId,
        String name,
        String email,
        boolean isActive,
        String department,
        String phone
) {
    public static TecnicoDTO fromModel(TecnicoModel tecnicoModel) {
        return new TecnicoDTO(
                tecnicoModel.getUserId(),
                tecnicoModel.getName(),
                tecnicoModel.getEmail(),
                tecnicoModel.isActive(),
                tecnicoModel.getDepartment(),
                tecnicoModel.getPhone()
        );
    }

    public static TecnicoModel toModel(TecnicoDTO tecnicoDTO) {
        TecnicoModel tecnicoModel = new TecnicoModel();
        tecnicoModel.setUserId(tecnicoDTO.userId());
        tecnicoModel.setName(tecnicoDTO.name());
        tecnicoModel.setEmail(tecnicoDTO.email());
        tecnicoModel.setIsActive(tecnicoDTO.isActive());
        tecnicoModel.setDepartment(tecnicoDTO.department());
        tecnicoModel.setPhone(tecnicoDTO.phone());
        return tecnicoModel;
    }
}

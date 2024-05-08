package com.soulcode.chamaelas.ChamaElas.models.dto;

import com.soulcode.chamaelas.ChamaElas.models.SetorModel;

public record SetorDTO(Long id, String nomeSetor) {

    public SetorDTO(SetorModel setor) {
        this(setor.getId(), setor.getNomeSetor());
    }

    public static SetorDTO fromModel(SetorModel setor) {
        return new SetorDTO(setor);
    }

    public static SetorModel toModel(SetorDTO setorDTO) {
        SetorModel setor = new SetorModel();
        setor.setId(setorDTO.id());
        setor.setNameSector(setorDTO.nomeSetor());
        return setor;
    }

    public static SetorModel convert(SetorDTO setorDTO){
        SetorModel sector = new SetorModel();
        sector.setId(setorDTO.getId());
        sector.setNameSector(setorDTO.getNomeSetor());
        return sector;
    }

    public Long getId() {
        return id;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }
}

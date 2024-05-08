package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_setores")
public class SetorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeSetor;

    public SetorModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSector() {
        return nomeSetor;
    }

    public void setNameSector(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
}
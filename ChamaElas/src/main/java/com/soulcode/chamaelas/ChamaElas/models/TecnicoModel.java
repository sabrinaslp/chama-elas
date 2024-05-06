package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DiscriminatorValue("tech")
public class TecnicoModel extends UsuarioModel {

    private String setor;
    private String telefone;

}

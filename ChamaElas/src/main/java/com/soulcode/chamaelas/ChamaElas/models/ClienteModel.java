package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DiscriminatorValue("cliente")
public class ClienteModel extends UsuarioModel {

    private String endereco;

    @ManyToOne
    @JoinColumn(name = "admin_atribuido_id")
    private AdminModel adminAtribuido;

}

package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("admin")
public class AdminModel extends UsuarioModel {

    @OneToMany(mappedBy = "adminAtribuido", fetch = FetchType.LAZY)
    private List<ChamadoModel> ticketsAbertos;

    @OneToMany(mappedBy = "adminAtribuido", fetch = FetchType.LAZY)
    private List<ClienteModel> clientesRegistrados;


}

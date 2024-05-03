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
public class AdminModel extends UserModel {

    @OneToMany(mappedBy = "assignedAdmin", fetch = FetchType.LAZY)
    private List<ChamadoModel> openTickets;

    @OneToMany(mappedBy = "assignedAdmin", fetch = FetchType.LAZY)
    private List<ClienteModel> registeredClients;

}

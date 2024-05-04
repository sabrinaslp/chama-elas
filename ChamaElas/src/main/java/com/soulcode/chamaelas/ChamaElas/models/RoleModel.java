package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_roles")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    public enum roleValues {
        TECNICO(1L),
        CLIENTE(2L),
        ADMIN(3L);

        long roleId;

        roleValues(long roleId) {
            this.roleId = roleId;
        }
    }

}

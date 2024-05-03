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

    public enum Values {
        TECH(1L),
        CLIENT(2L),
        ADMIN(3L);

        long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }
    }

}

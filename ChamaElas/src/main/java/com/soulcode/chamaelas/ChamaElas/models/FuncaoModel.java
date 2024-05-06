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
@Table(name="tb_funcao")
public class FuncaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcao_id")
    private Long funcaoId;

    private String nome;

    public enum Values {
        TECNICO(1L),
        CLIENTE(2L),
        ADMIN(3L);

        long funcaoId;

        Values(long funcaoId) {
            this.funcaoId = this.funcaoId;
        }
    }

}

package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
public class UsuarioModel implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long usuarioId;

        private String nome;

        @Column(unique = true)
        private String email;

        private String senha;

        @Getter
        private boolean estaAtivo;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinColumn(name = "funcao_id")
        private FuncaoModel funcao;

        @CreationTimestamp
        private Instant dataRegistro;

        public void setEstaAtivo(boolean estaAtivo) {

        }

        public void desativarUsuario() {
                this.estaAtivo = false;
        }

        public void ativarUsuario() {
                this.estaAtivo = true;
        }

}

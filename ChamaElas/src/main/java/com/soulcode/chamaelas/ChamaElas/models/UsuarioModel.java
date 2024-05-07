package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
@Table(name="tb_usuario")
public class UsuarioModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive;

    @Getter
    private boolean estaAtivo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "funcao_id")
    private FuncaoModel funcao;

    @CreationTimestamp
    private Instant dataRegistro;


    public void desativarUsuario() {
        this.estaAtivo = false;
    }

    public void ativarUsuario() {
        this.estaAtivo = true;
    }

    public void setPassword(String encode) {
    }

    public void setRole(FuncaoModel roleAdmin) {

    }

    public String getPassword() {
        return this.senha;
    }

}

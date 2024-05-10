package com.soulcode.chamaelas.ChamaElas.models;

import com.soulcode.chamaelas.ChamaElas.models.FuncaoModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

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

    public LocalDate dataExpiracaoTeste;

    @Column(name = "esta_ativo", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean estaAtivo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "funcao_id")
    private FuncaoModel funcao;

    @CreationTimestamp
    private LocalDate dataRegistro;

    private String token;


    public void desativarUsuario() {
        this.estaAtivo = false;
    }

    public void ativarUsuario() {
        this.estaAtivo = true;
    }

    public void setPassword(String encode) {
    }

    public String getPassword() {
        return this.senha;
    }

    public void setDataExpiracaoTeste(LocalDate dataExpiracaoTeste) {
        this.dataExpiracaoTeste = dataExpiracaoTeste;
    }

    // Método para gerar um token aleatório
    public String gerarToken() {
        int min = 100000;
        int max = 999999;
        int tokenInt = (int) (Math.random() * (max - min + 1) + min);
        // Converter o número para uma string
        return String.valueOf(tokenInt);
    }

    public boolean verificarToken(String tokenRecebido, String tokenOriginal) {
        return tokenRecebido.equals(tokenOriginal);
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.soulcode.chamaelas.ChamaElas.models;

import com.soulcode.chamaelas.ChamaElas.models.FuncaoModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

    @Column(name = "data_expiracao_teste")
    public LocalDate dataExpiracaoTeste;

    @Column(name = "esta_ativo", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean estaAtivo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "funcao_id")
    private FuncaoModel funcao;

    @CreationTimestamp
    private LocalDate dataRegistro;

    private String token;

    // Método para definir a data de expiração para 7 dias após o registro
    @PrePersist
    public void definirDataExpiracaoTeste() {
        if (this.dataRegistro == null) {
            this.dataRegistro = LocalDate.now();
        }
        this.dataExpiracaoTeste = this.dataRegistro.plusDays(7);
    }

    // Método para verificar se a conta expirou
    public boolean contaExpirada() {
        return LocalDate.now().isAfter(this.dataExpiracaoTeste);
    }

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

    // Método para obter as autoridades do usuário
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Obter as permissões do usuário a partir da sua função
        if (this.funcao != null) {
            String role = this.funcao.getNome(); // Supondo que a função contenha o nome da permissão
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return authorities;
    }

}

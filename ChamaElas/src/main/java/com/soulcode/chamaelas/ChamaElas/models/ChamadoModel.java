package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_chamados")
public class ChamadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="chamado_id")
    private Long ticketId;

    @Column(columnDefinition = "TEXT")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String setor;

    @CreationTimestamp
    private Instant dataRegistro;

    @UpdateTimestamp
    private Instant dataRegistroAtualizada;

    @ManyToOne
    private ClienteModel cliente;

    @ManyToOne
    private TecnicoModel tecnico;

    private TicketStatus status;

    private Prioridade prioridade;

    @ManyToOne
    private AdminModel adminAtribuido;

    @Column(columnDefinition = "TEXT")
    private String motivoEncerramento;

    public void setNomeCliente(String nome) {
    }

    @Getter
    public enum Prioridade {
        AGUARDANDO("Aguardando técnico"),
        BAIXA("Baixa"),
        MEDIA("Média"),
        ALTA("Alta");

        private final String descricao;

        Prioridade(String descricao) {
            this.descricao = descricao;
        }
    }

    @Getter
    public enum TicketStatus {
        ABERTO("Aguardando técnico"),
        EM_ANDAMENTO("Em atendimento"),
        ENCAMINHADO("Escalado para outro setor"),
        FECHADO("Finalizado");

        private final String descricao;

        TicketStatus(String descricao) {
            this.descricao = descricao;
        }

    }
}

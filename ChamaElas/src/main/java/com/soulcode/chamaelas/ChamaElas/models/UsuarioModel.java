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
        private Long userId;

        private String name;

        @Column(unique = true)
        private String email;

        private String password;

        private boolean isActive;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinColumn(name = "role_id")
        private RoleModel role;

        @CreationTimestamp
        private Instant creationTimeStamp;

        public void setIsActive(boolean isActive) {

        }

        public void desativarUsuario() {
                this.isActive = false;
        }

        public void ativarUsuario() {
                this.isActive = true;
        }

        public boolean isActive() {
                return isActive;
        }
}

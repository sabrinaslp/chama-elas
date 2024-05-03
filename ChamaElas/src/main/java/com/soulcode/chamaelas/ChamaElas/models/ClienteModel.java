package com.soulcode.chamaelas.ChamaElas.models;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@DiscriminatorValue("client")
public class ClienteModel extends UserModel{

    private String endereco;
}

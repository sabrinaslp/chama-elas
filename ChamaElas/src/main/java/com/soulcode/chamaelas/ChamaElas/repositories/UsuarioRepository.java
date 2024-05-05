package com.soulcode.chamaelas.ChamaElas.repositories;

import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    UsuarioModel findByEmail(String email);

}

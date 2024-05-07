package com.soulcode.chamaelas.ChamaElas.repositories;

import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    UsuarioDTO findByEmail(String email);

}

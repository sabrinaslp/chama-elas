package com.soulcode.chamaelas.ChamaElas.repositories;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmail(String email);

    Optional<UsuarioModel> findByToken(String token);

    List<UsuarioModel> findByDataExpiracaoTesteBefore(LocalDate data);

}

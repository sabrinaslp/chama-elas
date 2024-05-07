package com.soulcode.chamaelas.ChamaElas.repositories;

import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<TecnicoModel ,Long> {
    TecnicoModel findByNome(String nome);
}

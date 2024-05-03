package com.soulcode.chamaelas.ChamaElas.repositories;

import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}

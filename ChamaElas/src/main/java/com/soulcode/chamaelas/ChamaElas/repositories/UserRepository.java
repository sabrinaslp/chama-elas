package com.soulcode.chamaelas.ChamaElas.repositories;

import com.soulcode.chamaelas.ChamaElas.models.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDTO, Long> {
}

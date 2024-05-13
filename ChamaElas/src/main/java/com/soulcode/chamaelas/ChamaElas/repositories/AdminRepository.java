package com.soulcode.chamaelas.ChamaElas.repositories;
import com.soulcode.chamaelas.ChamaElas.models.AdminModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long>  {

    AdminModel getAdminByEmail(String email);
}

package com.soulcode.chamaelas.ChamaElas.repositories;
import com.soulcode.chamaelas.ChamaElas.models.SetorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<SetorModel, Long> {

    static Object updateById() {
        return null;
    }

}

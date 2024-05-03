package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ChamadoService {
    @Autowired
    ChamadoRepository chamadoRepository;
    public ChamadoModel save( ) {

        ChamadoModel c = new ChamadoModel();

        return chamadoRepository.save(c);
    }

    public List<ChamadoModel> findAll() {
        return chamadoRepository.findAll();
    }

    public Optional<ChamadoModel> findById(Long id) {
        return chamadoRepository.findById(id);
    }
    public List<ChamadoModel> findByClient(ClienteModel cliente) {
        return chamadoRepository.findByClient(cliente);
    }

    public List<ChamadoModel> findByTecnico(TecnicoModel tecnico) {
        return chamadoRepository.findByTecnico(tecnico);
    }
    public void deleteById(Long id) {
        chamadoRepository.deleteById(id);
    }

}

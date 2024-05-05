package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    // Atualiza o status do chamado por ID
    public void alterarStatusChamado(Long id, ChamadoModel.TicketStatus novoStatus) {
        Optional<ChamadoModel> optionalChamado = chamadoRepository.findById(id);

        optionalChamado.ifPresent(chamado -> {
            if (novoStatus != null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String nomeTecnico = authentication.getName();
                TecnicoModel tecnico = tecnicoRepository.findByName(nomeTecnico);

                if (tecnico != null) {
                    chamado.setStatus(novoStatus);
                    chamado.setTechnician(tecnico);
                    chamadoRepository.save(chamado);
                }
            }
        });
    }

}

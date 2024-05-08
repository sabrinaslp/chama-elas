package com.soulcode.chamaelas.ChamaElas.services;
import com.soulcode.chamaelas.ChamaElas.models.SetorModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.SetorDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public SetorDTO save(SetorDTO dto){
        SetorModel setor = SetorDTO.convert(dto);
        setor = this.setorRepository.save(setor);
        return new SetorDTO(setor);
    }

    public SetorDTO updateById(SetorDTO dto, Long id){
        this.findById(id);
        SetorModel setor = SetorDTO.convert(dto);
        setor.setId(id);
        setor = this.setorRepository.save(setor);
        return new SetorDTO(setor);
    }

    public SetorDTO deleteById(Long id){
        SetorDTO dto = this.findById(id);
        this.setorRepository.deleteById(id);
        return dto;
    }

    public List<SetorDTO> findAll(){
        List<SetorModel> sectors = this.setorRepository.findAll();
        return sectors.stream().map(SetorDTO::new).collect(Collectors.toList());
    }

    public SetorDTO findById(Long id){
        Optional<SetorModel> resultado = this.setorRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new RuntimeException("Setor não encontrado");
        } else {
            return new SetorDTO(resultado.get());
        }
    }
}

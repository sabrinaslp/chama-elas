package com.soulcode.chamaelas.ChamaElas.controllers;

import com.soulcode.chamaelas.ChamaElas.models.TecnicoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TecnicoController {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @RequestMapping(value = "/criar-tecnico", method = RequestMethod.POST)
    public TecnicoModel criarTecnico(@RequestBody TecnicoModel tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    @GetMapping("/buscar-tecnico/{id}")
    public TecnicoModel buscarTecnicoPorId(@PathVariable Long id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @PutMapping("/atualizar-tecnico/{id}")
    public TecnicoModel atualizarTecnico(@PathVariable Long id, @RequestBody TecnicoModel tecnicoAtualizado) {
        Optional<TecnicoModel> tecnicoModelOptional = tecnicoRepository.findById(id);
        if (tecnicoModelOptional.isPresent()) {
            TecnicoModel tecnico = tecnicoModelOptional.get();
            tecnico.setNome(tecnicoAtualizado.getNome());
            tecnico.setEmail(tecnicoAtualizado.getEmail());
            tecnico.setSenha(tecnico.getSenha());
            return tecnicoRepository.save(tecnico);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    @DeleteMapping("/excluir-tecnico/{id}")
    public void excluirTecnico(@PathVariable Long id) {
        tecnicoRepository.deleteById(id);
    }
}

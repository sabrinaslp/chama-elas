package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.SetorModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.SetorDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.SetorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SetorServiceTest {

    @Mock
    private SetorRepository setorRepository;

    @InjectMocks
    private SetorService setorService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Teste se retorna o DTO do setor salvo")
    void saveDeveRetornarSetorSalvoDTO() {
        // arrange
        SetorDTO dto = new SetorDTO(1L, "Nome do Setor");
        SetorModel setorModel = new SetorModel();
        when(setorRepository.save(any())).thenReturn(setorModel);

        // act
        SetorDTO savedDTO = setorService.save(dto);

        // assert
        assertNotNull(savedDTO);
    }

    @Test
    @DisplayName("Teste se retornar o DTO do setor deletado")
    void deleteByIdDeveRetornarSetorDeletadoDTO() {
        // arrange
        Long id = 1L;
        SetorDTO dto = new SetorDTO(1L, "Nome do Setor");
        SetorModel setorModel = new SetorModel();
        when(setorRepository.findById(id)).thenReturn(Optional.of(setorModel));

        // act
        SetorDTO deletedDTO = setorService.deleteById(id);

        // assert
        assertNotNull(deletedDTO);
    }

    @Test
    @DisplayName("Teste se retorna uma lista de DTOs de setores")
    void findAllDeveRetornarListaDeSetoresDTO() {
        // arrange
        List<SetorModel> setorModels = Collections.singletonList(new SetorModel());
        when(setorRepository.findAll()).thenReturn(setorModels);

        // act
        List<SetorDTO> dtos = setorService.findAll();

        // assert
        assertFalse(dtos.isEmpty());
    }

    @Test
    @DisplayName("Teste se retorna o DTO do setor quando o ID existe")
    void findByIdDeveRetornarSetorDTOQuandoIdExiste() {
        // arrange
        Long id = 1L;
        SetorModel setorModel = new SetorModel();
        when(setorRepository.findById(id)).thenReturn(Optional.of(setorModel));

        // act
        SetorDTO dto = setorService.findById(id);

        // assert
        assertNotNull(dto);
    }

    @Test
    @DisplayName("Teste se lança a RuntimeException quando o ID não existe")
    void findByIdDeveLancarRuntimeExceptionQuandoIdNaoExiste() {
        // arrange
        Long id = 1L;
        when(setorRepository.findById(id)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(RuntimeException.class, () -> setorService.findById(id));
    }
}

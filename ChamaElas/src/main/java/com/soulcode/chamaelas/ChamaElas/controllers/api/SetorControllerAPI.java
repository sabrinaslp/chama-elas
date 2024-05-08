package com.soulcode.chamaelas.ChamaElas.controllers.api;
import com.soulcode.chamaelas.ChamaElas.models.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import com.soulcode.chamaelas.ChamaElas.models.dto.SetorDTO;
import com.soulcode.chamaelas.ChamaElas.services.SetorService;
import com.soulcode.chamaelas.ChamaElas.services.UsuarioService;

import java.util.List;

@RestController
public class SetorControllerAPI {

    @Autowired
    private SetorService setorService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar-setor")
    public String save(@RequestBody SetorDTO dto) {
        this.setorService.save(dto);
        return "/dashboard-admin";
    }

    @GetMapping("/registrar-setor")
    public String save(@AuthenticationPrincipal UserDetails userDetails, Model model){
        UsuarioDTO usuario = usuarioService.UsuarioPorEmail(userDetails.getUsername());
        model.addAttribute("name", usuario.getNome());
        return "setor/registrado-setor";
    }

    @PutMapping(value = "/editar-setor/{id}")
    public SetorDTO updateById(@PathVariable Long id, @RequestBody SetorDTO dto) {
        return this.setorService.updateById(dto, id);
    }

    @DeleteMapping(value = "/deletar-setor/{id}")
    public SetorDTO deleteById(@PathVariable Long id) {
        return setorService.deleteById(id);
    }

    @GetMapping(value = "/listar-setor")
    public List<SetorDTO> findAll() {
        return this.setorService.findAll();
    }

    @GetMapping(value = "/visualizar-setor/{id}")
    public SetorDTO findById(@PathVariable Long id) {
        return this.setorService.findById(id);
    }

}

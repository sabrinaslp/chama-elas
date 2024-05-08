package com.soulcode.chamaelas.ChamaElas.controllers.api;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import com.soulcode.chamaelas.ChamaElas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class ChamadoControllerAPI {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar-chamado")
    public ChamadoModel criarChamado() {
        return chamadoService.criarChamado();
    }

    @GetMapping("/dashboard-usuario")
    public List<ChamadoModel> dashboardUsuario() {
        // Obtém o cliente logado a partir do contexto de segurança
        ClienteModel clienteLogado = usuarioService.getClienteLogado();

        // Verifica se o cliente Logado é nulo (por exemplo, se não houver cliente autenticado)
        if (clienteLogado == null) {
            return Collections.emptyList();
        }
        // Lista os chamados do cliente logado
        return chamadoService.listarChamadosUsuario(clienteLogado);
    }


    @GetMapping("/dashboard-tecnico")
    public List<ChamadoModel> dashboardTecnico() {
        return chamadoService.getChamadosAtribuidosAoTecnicoLogado();
    }

    @GetMapping("/dashboard-tecnico/meus-chamados")
    public List<ChamadoModel> meusChamadosTecnico() {
        return chamadoService.getChamadosAtribuidosAoTecnicoLogado();
    }

    @GetMapping("/usuario/editar-chamado/{ticketId}")
    public ChamadoModel editarChamadoUsuario(@PathVariable Long ticketId) {
        return chamadoService.editarChamadoUsuario(ticketId);
    }

    @PutMapping("/usuario/editar-chamado/{ticketId}")
    public ChamadoModel atualizarChamadoUsuario(@PathVariable Long ticketId, @RequestBody ChamadoModel chamadoAtualizado) {
        return chamadoService.atualizarChamadoUsuario(ticketId, chamadoAtualizado);
    }

    @GetMapping("/tecnico/editar-chamado/{ticketId}")
    public ChamadoModel editarChamadoTecnico(@PathVariable Long ticketId) {
        return chamadoService.editarChamadoTecnico(ticketId);
    }

    @PutMapping("/tecnico/editar-chamado/{ticketId}")
    public ChamadoModel atualizarChamadoTecnico(@PathVariable Long ticketId, @RequestBody ChamadoModel chamadoAtualizado) {
        return chamadoService.atualizarChamadoTecnico(ticketId, chamadoAtualizado);
    }

    @GetMapping("/admin/todos-chamados")
    public List<ChamadoModel> todosChamadosAdmin() {
        return chamadoService.listarTodosChamadosAdmin();
    }
}

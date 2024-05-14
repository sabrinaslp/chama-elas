package com.soulcode.chamaelas.ChamaElas.controllers.api;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import com.soulcode.chamaelas.ChamaElas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

        // Verifica se o clienteLogado é nulo (por exemplo, se não houver cliente autenticado)
        if (clienteLogado == null) {
            return Collections.emptyList();
        }
        // Lista os chamados do cliente logado
        return chamadoService.listarChamadosCliente(clienteLogado);
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

    public class ExclusaoChamadoResponse {
        private boolean excluido;
        private String mensagem;

        public String getMensagem() {
            return mensagem;
        }

        public void setExcluido(boolean excluido) {
            this.excluido = excluido;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
        // Construtor, getters e setters
    }
    @GetMapping("/excluir-chamados/{ticketId}")
    public ExclusaoChamadoResponse excluirChamado(@PathVariable Long ticketId) {
        Optional<ChamadoModel> chamadoOptional = chamadoService.findById(ticketId);
        ExclusaoChamadoResponse response = new ExclusaoChamadoResponse();

        if (chamadoOptional.isPresent()) {
            ChamadoModel chamado = chamadoOptional.get();
            if (chamado.getStatus() == ChamadoModel.TicketStatus.ABERTO) {
                chamadoService.deleteById(ticketId);
                response.setExcluido(true);
                response.setMensagem("Chamado excluído com sucesso.");
            } else {
                response.setExcluido(false);
                response.setMensagem("Não é possível excluir chamados "+chamado.getStatus()+".");
            }
        } else {
            response.setExcluido(false);
            response.setMensagem("Chamado não encontrado.");
        }

        return response;
    }
    }

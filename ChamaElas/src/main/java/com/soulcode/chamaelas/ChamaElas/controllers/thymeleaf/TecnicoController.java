package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.repositories.TecnicoRepository;
import com.soulcode.chamaelas.ChamaElas.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TecnicoController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @GetMapping("/pagina-tecnico")
    public String paginaTecnico(Model model, Authentication authentication) {
        var tecnico = tecnicoRepository.getTecnicoByEmail(authentication.getName());
        model.addAttribute("nome", tecnico.getNome());

        List<ChamadoModel> chamadosEmAberto = chamadoService.getChamadosEmAberto();
        List<ChamadoModel> chamadosAtribuidos = chamadoService.getChamadosAtribuidosAoTecnicoLogado();

        model.addAttribute("chamadosEmAberto", chamadosEmAberto);
        model.addAttribute("chamadosAtribuidos", chamadosAtribuidos);

        return "tecnico-chamados";
    }

    @PostMapping("/atender-chamado")
    public String atenderChamado(@RequestParam("chamadoId") Long chamadoId,
                                 @RequestParam("prioridade") ChamadoModel.Prioridade prioridade,
                                 Authentication authentication) {
        var tecnico = tecnicoRepository.getTecnicoByEmail(authentication.getName());

        chamadoService.associarTecnicoAoChamado(chamadoId, tecnico);
        chamadoService.alteraStatusEPrioridadeDoChamado(chamadoId, prioridade);

        return "redirect:/pagina-tecnico";
    }

    @GetMapping("/detalhes-chamado")
    public String detalhesChamado(@RequestParam("chamadoId") Long chamadoId, Model model) {
        var chamado = chamadoService.findById(chamadoId).orElseThrow();
        model.addAttribute("chamado", chamado);
        return "detalhes-chamado-tecnico";
    }



    /* PENDENTES:
        - (OK) Implementar a lógica para aparecer o nome do usuário e os chamados em aberto
        - (OK) Implementar a lógica para atribuir prioridade ao chamado, através do formulário
        - (OK) Implementar a lógica para atribuir o técnico logado no chamado (atribuir chamado ao técnico)
        - (OK) Implementar a lógica para aparecer os chamados atribuido ao tecnico logado na parte debaixo
        - Implementar a lógica para aparecer os detalhes do chamado
        - Implementar lógica apra mudança de status na página de detalhes do chamado.
        - Implementar a lógica para atribuir novo status e nova prioridade ao chamado atribuido , através do formulário
        - Implementar a lógica para reformatar a data
    */

}

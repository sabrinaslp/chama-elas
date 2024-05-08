package com.soulcode.chamaelas.ChamaElas.services;
import com.soulcode.chamaelas.ChamaElas.models.ChamadoModel;
import com.soulcode.chamaelas.ChamaElas.models.ClienteModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.models.dto.UsuarioDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.ChamadoRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // Listar todos os chamados criados pelo usuário
    public List<ChamadoModel> listarChamadosUsuario(ClienteModel cliente) {
        return chamadoRepository.findByCliente(cliente);
    }
    // Listar todos os chamados criados pelo usuário que ainda estão em aberto
    public List<ChamadoModel> listarChamadosEmAbertoDoUsuario(ClienteModel usuario) {
        return chamadoRepository.findByClienteAndStatus(usuario, ChamadoModel.TicketStatus.ABERTO);
    }

    // Mostra detalhes do chamado na página do usuário (não deve alterar o status na página)
    public Optional<ChamadoModel> obterDetalhesDoChamadoPorId(Long id) {
        return chamadoRepository.findById(id);
    }

    public UsuarioDTO UsuarioPorEmail(String email){
        Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(email);
        if(usuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Usuário não encontrado");
        }
        return UsuarioDTO.fromModel(usuario.get());
    }


    public ClienteModel getClienteLogado() {
        // Obtém o contexto de autenticação do Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Verifica se a autenticação não é nula e se o principal (usuário) é do tipo ClienteModel
        if (authentication != null && authentication.getPrincipal() instanceof ClienteModel) {
            // Retorna o cliente autenticado
            return (ClienteModel) authentication.getPrincipal();
        } else {
            // Se não houver cliente autenticado, retorna null
            return null;
        }
    }
}


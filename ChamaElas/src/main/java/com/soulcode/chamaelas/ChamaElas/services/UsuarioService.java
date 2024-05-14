package com.soulcode.chamaelas.ChamaElas.services;

import com.soulcode.chamaelas.ChamaElas.models.*;
import com.soulcode.chamaelas.ChamaElas.models.dto.ClienteDTO;
import com.soulcode.chamaelas.ChamaElas.models.dto.TecnicoDTO;
import com.soulcode.chamaelas.ChamaElas.models.dto.UsuarioDTO;
import com.soulcode.chamaelas.ChamaElas.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncaoRepository funcaoRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private EmailService emailService;


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

    public UsuarioModel obterUsuarioPorEmail(String email) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByEmail(email);
        return usuarioOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public void cadastrarNovoUsuario(String nome, String email, String senha, String confirmacaoSenha, String funcao, String endereco, String telefone, String setor, String tokenRecebido, Model model) {
        autenticacaoService.verificarCadastroUsuario(nome, email, senha, confirmacaoSenha);
        FuncaoModel funcaoNovoUsuario = atribuirFuncaoAoUsuario(funcao);

        if (funcaoNovoUsuario.getNome().equals(FuncaoModel.Values.CLIENTE.getNome())) {
            cadastrarNovoCliente(nome, email, senha, funcaoNovoUsuario, endereco);
        } else if (funcaoNovoUsuario.getNome().equals(FuncaoModel.Values.TECNICO.getNome())) {
            cadastrarNovoTecnico(nome, email, senha, funcaoNovoUsuario, telefone, setor);
        } else {
            throw new IllegalArgumentException("Função de usuário inválida: " + funcaoNovoUsuario);
        }

        // Obter o usuário recém-criado
        UsuarioModel usuario = obterUsuarioPorEmail(email);

        // Gerar o token no usuário
        String token = usuario.gerarToken();
        usuario.setToken(token);
        usuarioRepository.save(usuario);

        // Envio de e-mail de boas-vindas com o token
        emailService.enviarEmailBoasVindas(email, nome, token);

        // Verificar se o token recebido é igual ao token gerado
        boolean tokenValido = usuario.verificarToken(tokenRecebido, token);

        if (tokenValido) {
            model.addAttribute("successMessage", "Usuário cadastrado com sucesso! Faça o login para acessar sua conta.");
        } else {
            model.addAttribute("errorMessage", "O token de confirmação é inválido. Por favor, verifique o token recebido por e-mail.");
        }
    }


    private void cadastrarNovoCliente(String nome, String email, String senha, FuncaoModel funcao, String endereco) {
        ClienteDTO clienteDTO = new ClienteDTO(null, nome, email, endereco);
        ClienteModel clienteModel = ClienteDTO.toModel(clienteDTO);
        clienteModel.setFuncao(funcao);
        clienteModel.setEstaAtivo(true);
        String senhaCodificada = criptografarSenha(senha);
        clienteModel.setSenha(senhaCodificada);
        clienteRepository.save(clienteModel);
    }

    private void cadastrarNovoTecnico(String nome, String email, String senha, FuncaoModel funcao, String telefone, String setor) {
        TecnicoDTO tecnicoDTO = new TecnicoDTO(null, nome, email, true, setor, telefone);
        TecnicoModel tecnicoModel = TecnicoDTO.toModel(tecnicoDTO);
        tecnicoModel.setFuncao(funcao);
        tecnicoModel.setEstaAtivo(true);
        String senhaCodificada = criptografarSenha(senha);
        tecnicoModel.setSenha(senhaCodificada);
        tecnicoRepository.save(tecnicoModel);
    }

    private String criptografarSenha(String senha) {
        return bCryptPasswordEncoder.encode(senha);
    }

    private FuncaoModel atribuirFuncaoAoUsuario(String funcao) {
        return switch (funcao) {
            case "Cliente" -> funcaoRepository.findByNome(FuncaoModel.Values.CLIENTE.getNome());
            case "Tecnico" -> funcaoRepository.findByNome(FuncaoModel.Values.TECNICO.getNome());
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + funcao);
        };
    }

    // Listar todos os chamados criados pelo usuário
    public List<ChamadoModel> listarTodosOsChamadosDoUsuario(ClienteModel usuario) {
        return chamadoRepository.findByCliente(usuario);
    }

    public UsuarioDTO UsuarioPorEmail(String email) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
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

    // Método para gerar um token de 6 dígitos
    public String gerarToken() {
        // Define o tamanho do token
        int tamanhoToken = 6;
        // Cria um StringBuilder para construir o token
        StringBuilder tokenBuilder = new StringBuilder();
        // Cria um objeto Random
        Random random = new Random();
        // Adiciona dígitos aleatórios ao token até atingir o tamanho desejado
        for (int i = 0; i < tamanhoToken; i++) {
            // Gera um dígito aleatório de 0 a 9
            int digito = random.nextInt(10);
            // Adiciona o dígito ao token
            tokenBuilder.append(digito);
        }
        // Retorna o token como uma string
        return tokenBuilder.toString();
    }

    public boolean verificarToken(String token) {
        // Lógica para verificar se o token existe no banco de dados
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findByToken(token);

        // Se o token existir no banco de dados
        if (usuarioOptional.isPresent()) {
            UsuarioModel usuario = usuarioOptional.get();

            // Retorna o resultado da verificação de token na classe UsuarioModel
            return usuario.verificarToken(token, usuario.getToken());
        } else {
            // Se o token não existir no banco de dados, retorna false
            return false;
        }
    }

    // Método para verificar e excluir contas expiradas
    @Scheduled(fixedRate = 86400000) // Executa a cada 24 horas
    public void verificarEExcluirContasExpiradas() {
        List<UsuarioModel> usuariosExpirados = usuarioRepository.findByDataExpiracaoTesteBefore(LocalDate.now());
        for (UsuarioModel usuario : usuariosExpirados) {
            usuario.setEstaAtivo(false);
            usuarioRepository.save(usuario); // Atualiza o estado do usuário para inativo
        }
    }

}



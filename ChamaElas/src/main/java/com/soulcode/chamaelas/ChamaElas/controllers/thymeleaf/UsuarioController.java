package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import com.soulcode.chamaelas.ChamaElas.models.FuncaoModel;
import com.soulcode.chamaelas.ChamaElas.models.UsuarioModel;
import com.soulcode.chamaelas.ChamaElas.repositories.FuncaoRepository;
import com.soulcode.chamaelas.ChamaElas.repositories.UsuarioRepository;
import com.soulcode.chamaelas.ChamaElas.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncaoRepository funcaoRepository;

    @GetMapping("/cadastro-usuario")
    public String mostrarPaginaCadastroUsuario() {
        return "cadastro-usuario";
    }

    @PostMapping("/cadastro-usuario")
    public String cadastrarNovoUsuario(@RequestParam("nome") String nome,
                                       @RequestParam("email") String email,
                                       @RequestParam("senha") String senha,
                                       @RequestParam("confirmaSenha") String confirmacaoSenha,
                                       @RequestParam("funcao") String funcao,
                                       Model model) {
        try {
            System.out.println("Entrou no cadastro de usuário");
            autenticacaoService.verifiqueSeOEmailJaFoiCadastrado(email);
            autenticacaoService.verifiqueSeAsSenhasSaoIguais(senha, confirmacaoSenha);

            FuncaoModel funcaoModel = null;

            if (funcao.equals("Cliente")) {
                funcaoModel = funcaoRepository.findByNome(FuncaoModel.Values.CLIENTE.name());
                if (funcaoModel == null) {
                    funcaoModel = new FuncaoModel(1L, FuncaoModel.Values.CLIENTE.name());
                    funcaoModel = funcaoRepository.save(funcaoModel);
                }
            } else if (funcao.equals("Tecnico")) {
                funcaoModel = funcaoRepository.findByNome(FuncaoModel.Values.TECNICO.name());
                if (funcaoModel == null) {
                    funcaoModel = new FuncaoModel(2L, FuncaoModel.Values.TECNICO.name());
                    funcaoModel = funcaoRepository.save(funcaoModel);
                }
            } else {
                throw new IllegalArgumentException("Tipo de usuário inválido: " + funcao);
            }

            System.out.println("Passou das verificações");

            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setNome(nome);
            usuarioModel.setEmail(email);
            usuarioModel.setSenha(senha);
            usuarioModel.setFuncao(funcaoModel);
            System.out.println("Definiu os atributos");
            System.out.println("Nome: " + usuarioModel.getNome() + " Função: " + usuarioModel.getFuncao().getNome());

            usuarioRepository.save(usuarioModel);
            System.out.println("Registrou no banco");
            model.addAttribute("successMessage", "Usuário cadastrado com sucesso! Faça o login para acessar sua conta.");
            return "login-usuario";
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "cadastro-usuario";
        } catch (Exception e) {
            model.addAttribute("error", "Ocorreu um erro ao processar o cadastro do usuário.");
            return "cadastro-usuario";
        }
    }
}

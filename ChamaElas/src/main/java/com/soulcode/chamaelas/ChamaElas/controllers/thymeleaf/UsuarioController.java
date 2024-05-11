package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;

import com.soulcode.chamaelas.ChamaElas.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private UsuarioService usuarioService;

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
                                       @RequestParam(value = "endereco", required = false) String endereco,
                                       @RequestParam(value = "telefone", required = false) String telefone,
                                       @RequestParam(value = "setor", required = false) String setor,
                                       Model model) {
        try {
            // Gera um token
            String token = usuarioService.gerarToken();

            // Adiciona o token ao modelo
            model.addAttribute("tokenRecebido", token);

            // Chama o serviço para cadastrar o novo usuário
            usuarioService.cadastrarNovoUsuario(nome, email, senha, confirmacaoSenha, funcao, endereco, telefone, setor, token, model);

            // Redireciona para a página de validação do token
            return "redirect:/pagina-autenticacao";
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "cadastro-usuario";

        } catch (Exception e) {
            model.addAttribute("error", "Ocorreu um erro ao processar o cadastro do usuário.");
            return "cadastro-usuario";
        }
    }

    @PostMapping("/verificar-token")
    public String verificarToken(@RequestParam("authToken") String authToken, HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean tokenValido = usuarioService.verificarToken(authToken);

        if (tokenValido) {
            // Invalida a sessão do usuário
            request.getSession().invalidate();
            // Redireciona para a página de login
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Token inválido. Por favor, verifique novamente.");
            return "pagina-autenticacao";
        }
    }
}
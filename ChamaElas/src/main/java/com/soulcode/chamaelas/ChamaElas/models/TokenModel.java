package com.soulcode.chamaelas.ChamaElas.models;
import org.springframework.stereotype.Component;

@Component
public class TokenModel {

    // Método para gerar um token aleatório
    public String gerarToken() {
        int min = 100000;
        int max = 999999;
        int tokenInt = (int) (Math.random() * (max - min + 1) + min);
        // Converter o número para uma string
        return String.valueOf(tokenInt);
    }

    // Método para verificar se o token recebido é igual ao token original
    public boolean verificarToken(String tokenRecebido, String tokenOriginal) {
        return tokenRecebido.equals(tokenOriginal);
    }
}

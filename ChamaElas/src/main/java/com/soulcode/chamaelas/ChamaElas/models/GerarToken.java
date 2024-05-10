package com.soulcode.chamaelas.ChamaElas.models;
import java.util.Random;

public class GerarToken {

    public String gerarToken() {
        Random random = new Random();
        int tokenInt = random.nextInt(900000) + 100000;
        // Converter o número para uma string
        return String.valueOf(tokenInt);
    }
}

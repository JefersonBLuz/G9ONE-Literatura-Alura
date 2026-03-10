package com.JefersonBLuz.literalura.utils;

import org.jspecify.annotations.NonNull;

public class Utilidades {
    public void exibirBarra(Integer maxLetter) {
        String barra = "";
        for (int i = 0; i < maxLetter; i++) {
            barra = barra.concat("=");
        }
        System.out.println(barra);
    }

    public void CentralizadorMenssagem(@NonNull String message, Integer maxLetter) {
        var lettersMessage = message.length();
        if (lettersMessage > maxLetter){
            System.out.println(message);
            return;
        }
        var adicionar = (maxLetter/2)-(lettersMessage/2);
        String espacos = "";
        for (int i=0; i<adicionar; i++){
            espacos = espacos.concat(" ");
        }
        System.out.println(espacos.concat(message));
    }
}

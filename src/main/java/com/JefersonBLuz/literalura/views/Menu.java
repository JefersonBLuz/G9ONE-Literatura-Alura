package com.JefersonBLuz.literalura.views;

import com.JefersonBLuz.literalura.utils.Utilidades;

public class Menu {
    private final Utilidades uteis = new Utilidades();
    private static final int LARGURA_BARRA = 50;

    public void iniciar() {
        uteis.exibirBarra(LARGURA_BARRA);
        uteis.CentralizadorMenssagem("Bem vindo ao LiterAlura", LARGURA_BARRA);
        uteis.exibirBarra(LARGURA_BARRA);
    }
}

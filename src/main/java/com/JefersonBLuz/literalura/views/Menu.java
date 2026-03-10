package com.JefersonBLuz.literalura.views;

import com.JefersonBLuz.literalura.utils.Utilidades;
import com.JefersonBLuz.literalura.views.metodos.BuscaLivroPorTituloView;

import java.util.Scanner;

public class Menu {
    private final Utilidades uteis = new Utilidades();
    private final BuscaLivroPorTituloView buscaLivroPorTituloView = new BuscaLivroPorTituloView();
    private static final int LARGURA_BARRA = 50;

    public void iniciar() {
        Scanner leitura = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            uteis.exibirBarra(LARGURA_BARRA);
            uteis.CentralizadorMenssagem("Bem vindo ao LiterAlura", LARGURA_BARRA);
            uteis.exibirBarra(LARGURA_BARRA);
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            String entrada = leitura.nextLine();
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    buscaLivroPorTituloView.executar(leitura);
                    break;
                case 0:
                    System.out.println("Saindo do LiterAlura...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }
}

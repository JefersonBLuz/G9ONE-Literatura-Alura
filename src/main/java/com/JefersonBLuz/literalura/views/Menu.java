package com.JefersonBLuz.literalura.views;

import com.JefersonBLuz.literalura.utils.Utilidades;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final Utilidades uteis = new Utilidades();
    private final MetodoListagemView metodoListagemView;
    private static final int LARGURA_BARRA = 50;

    public Menu(MetodoListagemView metodoListagemView) {
        this.metodoListagemView = metodoListagemView;
    }

    public void iniciar() {
        Scanner leitura = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            uteis.exibirBarra(LARGURA_BARRA);
            uteis.CentralizadorMenssagem("Bem vindo ao LiterAlura", LARGURA_BARRA);
            uteis.exibirBarra(LARGURA_BARRA);
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listagem de todos os livros");
            System.out.println("3 - Listar livros por idioma");
            System.out.println("4 - Listar autores");
            System.out.println("5 - Listar autores vivos em um ano");
            System.out.println("6 - Estatísticas de livros por idioma (en/pt)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            String entrada = leitura.nextLine();
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> metodoListagemView.buscarLivroPorTitulo(leitura);
                case 2 -> metodoListagemView.listarTodosLivros();
                case 3 -> metodoListagemView.listarLivrosPorIdioma(leitura);
                case 4 -> metodoListagemView.listarAutores();
                case 5 -> metodoListagemView.listarAutoresVivosNoAno(leitura);
                case 6 -> metodoListagemView.exibirEstatisticasPorIdioma();
                case 0 -> System.out.println("Saindo do LiterAlura...");
                default -> System.out.println("Opcao invalida.");
            }
        }
    }
}

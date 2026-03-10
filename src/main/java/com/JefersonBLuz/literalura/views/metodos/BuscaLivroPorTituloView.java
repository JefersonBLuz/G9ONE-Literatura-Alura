package com.JefersonBLuz.literalura.views.metodos;

import com.JefersonBLuz.literalura.service.GutendexService;

import java.util.Scanner;

public class BuscaLivroPorTituloView {

    private final GutendexService gutendexService = new GutendexService();

    public void executar(Scanner leitura) {
        System.out.print("Digite o titulo para busca: ");
        String titulo = leitura.nextLine();

        try {
            var livros = gutendexService.buscarPrimeirosLivrosPorTermo(titulo, 5);
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado para o titulo informado.");
                return;
            }

            System.out.println("Livros encontrados (primeiros 5):");
            livros.forEach(livro -> System.out.println(
                    "ID: " + livro.id()
                            + " | Titulo: " + livro.title()
                            + " | Downloads: " + livro.downloadCount()
            ));
        } catch (RuntimeException e) {
            System.out.println("Erro ao consultar a API: " + e.getMessage());
        }
    }
}

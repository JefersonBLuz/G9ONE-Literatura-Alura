package com.JefersonBLuz.literalura.views;

import com.JefersonBLuz.literalura.model.Livro;
import com.JefersonBLuz.literalura.service.CatalogoLivrosService;
import com.JefersonBLuz.literalura.service.GutendexService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MetodoListagemView {

    private final GutendexService gutendexService;
    private final CatalogoLivrosService catalogoLivrosService;

    public MetodoListagemView(GutendexService gutendexService, CatalogoLivrosService catalogoLivrosService) {
        this.gutendexService = gutendexService;
        this.catalogoLivrosService = catalogoLivrosService;
    }

    public void buscarLivroPorTitulo(Scanner leitura) {
        System.out.print("Digite o titulo para busca: ");
        String titulo = leitura.nextLine();

        try {
            var livros = gutendexService.buscarPrimeirosLivrosPorTermo(titulo, 1);
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado para o titulo informado.");
                return;
            }

            var livro = livros.getFirst();
            Livro livroSalvo = catalogoLivrosService.adicionarLivro(livro);

            System.out.println("Livro adicionado ao catalogo:");
            System.out.println(
                    "Titulo: " + livroSalvo.getTitulo()
                            + " | Autor: " + livroSalvo.getAutor().getNome()
                            + " | Idioma: " + livroSalvo.getIdioma()
                            + " | Downloads: " + livroSalvo.getDownloads()
            );
        } catch (RuntimeException e) {
            System.out.println("Erro ao buscar e salvar livro: " + e.getMessage());
        }
    }

    public void listarTodosLivros() {
        var livros = catalogoLivrosService.listarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro foi buscado ainda.");
            return;
        }

        System.out.println("Livros no catalogo:");
        livros.forEach(livro -> System.out.println(
                "Titulo: " + livro.getTitulo()
                        + " | Autor: " + livro.getAutor().getNome()
                        + " | Idioma: " + livro.getIdioma()
                        + " | Downloads: " + livro.getDownloads()
        ));
    }

    public void listarLivrosPorIdioma(Scanner leitura) {
        System.out.print("Digite o idioma para filtrar (ex: en, pt, es): ");
        String idioma = leitura.nextLine();

        var livros = catalogoLivrosService.listarPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma informado.");
            return;
        }

        System.out.println("Livros no idioma '" + idioma + "':");
        livros.forEach(livro -> System.out.println(
                "Titulo: " + livro.getTitulo()
                        + " | Autor: " + livro.getAutor().getNome()
                        + " | Idioma: " + livro.getIdioma()
                        + " | Downloads: " + livro.getDownloads()
        ));
    }

    public void listarAutores() {
        var autores = catalogoLivrosService.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor foi salvo ainda.");
            return;
        }

        System.out.println("Autores no catalogo:");
        autores.forEach(autor -> System.out.println(
                "Nome: " + autor.getNome()
                        + " | Nascimento: " + autor.getAnoNascimento()
                        + " | Falecimento: " + autor.getAnoFalecimento()
        ));
    }

    public void listarAutoresVivosNoAno(Scanner leitura) {
        System.out.print("Digite o ano para consulta: ");
        String entradaAno = leitura.nextLine();

        int ano;
        try {
            ano = Integer.parseInt(entradaAno);
        } catch (NumberFormatException e) {
            System.out.println("Ano invalido.");
            return;
        }

        var autores = catalogoLivrosService.listarAutoresVivosNoAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado no ano informado.");
            return;
        }

        System.out.println("Autores vivos no ano " + ano + ":");
        autores.forEach(autor -> System.out.println(
                "Nome: " + autor.getNome()
                        + " | Nascimento: " + autor.getAnoNascimento()
                        + " | Falecimento: " + autor.getAnoFalecimento()
        ));
    }

    public void exibirEstatisticasPorIdioma() {
        List<String> idiomas = List.of("en", "pt");
        var estatisticas = catalogoLivrosService.estatisticasIdiomasFixos(idiomas);

        System.out.println("Quantidade de livros por idioma:");
        estatisticas.forEach((idioma, quantidade) ->
                System.out.println("Idioma '" + idioma + "': " + quantidade + " livro(s)")
        );
    }
}

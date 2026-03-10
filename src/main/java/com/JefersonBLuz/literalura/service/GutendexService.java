package com.JefersonBLuz.literalura.service;

import com.JefersonBLuz.literalura.entidades.AutorAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class GutendexService {

    private static final String BASE_URL = "https://gutendex.com/books";

    private final APIConect apiConect;
    private final ObjectMapper objectMapper;

    public GutendexService() {
        this.apiConect = new APIConect();
        this.objectMapper = new ObjectMapper();
    }

    public GutendexResponse buscarLivrosPorTermo(String termo) {
        String url = BASE_URL + "?search=" + termo.replace(" ", "%20");
        String json = apiConect.obterDados(url);
        try {
            return objectMapper.readValue(json, GutendexResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter resposta da API Gutendex.", e);
        }
    }

    public List<Book> buscarPrimeirosLivrosPorTermo(String termo, int limite) {
        GutendexResponse resposta = buscarLivrosPorTermo(termo);
        if (resposta.results() == null) {
            return List.of();
        }
        return resposta.results().stream()
                .limit(limite)
                .toList();
    }

    /**
     * Retorna apenas os autores (em uma lista simples) a partir da busca.
     */
    public List<AutorAPI> buscarAutoresPorTermo(String termo) {
        GutendexResponse resposta = buscarLivrosPorTermo(termo);
        return resposta.results().stream()
                .flatMap(livro -> livro.authors().stream())
                .map(a -> new AutorAPI(a.name(), a.birthYear(), a.deathYear()))
                .distinct()
                .toList();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record GutendexResponse(
            int count,
            String next,
            String previous,
            List<Book> results
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Book(
            int id,
            String title,
            List<Person> authors,
            @JsonProperty("download_count") int downloadCount
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Person(
            @JsonProperty("birth_year") Integer birthYear,
            @JsonProperty("death_year") Integer deathYear,
            String name
    ) {}
}


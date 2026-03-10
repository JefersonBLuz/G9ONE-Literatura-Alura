package com.JefersonBLuz.literalura.service;

import com.JefersonBLuz.literalura.dto.AutorDTO;
import com.JefersonBLuz.literalura.dto.GutendexResponseDTO;
import com.JefersonBLuz.literalura.dto.LivroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class GutendexService {

    private static final String BASE_URL = "https://gutendex.com/books";

    private final APIConect apiConect;
    private final ObjectMapper objectMapper;

    public GutendexService(APIConect apiConect) {
        this.apiConect = apiConect;
        this.objectMapper = new ObjectMapper();
    }

    public GutendexResponseDTO buscarLivrosPorTermo(String termo) {
        if (termo == null || termo.isBlank()) {
            throw new RuntimeException("Informe um titulo valido para busca.");
        }
        String url = BASE_URL + "/?search=" + URLEncoder.encode(termo, StandardCharsets.UTF_8);
        String json = apiConect.obterDados(url);
        try {
            return objectMapper.readValue(json, GutendexResponseDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter dados recebidos da API Gutendex.", e);
        }
    }

    public List<LivroDTO> buscarPrimeirosLivrosPorTermo(String termo, int limite) {
        GutendexResponseDTO resposta = buscarLivrosPorTermo(termo);
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
    public List<AutorDTO> buscarAutoresPorTermo(String termo) {
        GutendexResponseDTO resposta = buscarLivrosPorTermo(termo);
        if (resposta.results() == null) {
            return List.of();
        }
        return resposta.results().stream()
                .filter(livro -> livro.authors() != null)
                .flatMap(livro -> livro.authors().stream())
                .distinct()
                .toList();
    }
}


package com.JefersonBLuz.literalura.entidades;

public record LivroCatalogo(
        String titulo,
        String autor,
        Integer autorAnoNascimento,
        Integer autorAnoFalecimento,
        String idioma,
        Integer downloads
) {}

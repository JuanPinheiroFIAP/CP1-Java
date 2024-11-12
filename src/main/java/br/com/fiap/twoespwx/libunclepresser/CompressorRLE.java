package br.com.fiap.twoespwx.libunclepresser;

import java.util.HashMap;
import java.util.Map;

public class CompressorRLE {
    // Método para comprimir a sequência usando RLE
    public static String compress(String input) {
        // Verifica se a entrada é nula ou vazia
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Remove espaços em branco da entrada
        input = input.replaceAll("\\s+", "");
        StringBuilder compressed = new StringBuilder();

        // Inicializa o primeiro caractere e contador
        char currentChar = input.charAt(0);
        int count = 1;

        // Percorre os caracteres da sequência a partir do segundo caractere
        for (int i = 1; i < input.length(); i++) {
            // Verifica se o caractere atual é igual ao anterior
            if (input.charAt(i) == currentChar) {
                count++; // Incrementa o contador se os caracteres forem iguais
            } else {
                // Adiciona a sequência comprimida no formato "caractere + contagem"
                appendCompressedSequence(compressed, currentChar, count);
                currentChar = input.charAt(i); // Atualiza o caractere atual
                count = 1; // Reinicia o contador para o novo caractere
            }
        }
        // Adiciona a última sequência comprimida ao resultado
        appendCompressedSequence(compressed, currentChar, count);

        return compressed.toString(); // Retorna a sequência comprimida como string
    }

    // Método auxiliar para adicionar uma sequência comprimida ao builder
    private static void appendCompressedSequence(StringBuilder builder,
                                                 char character, int count) {
        builder.append(character).append(count);
    }

    // Método para calcular a frequência de cada caractere na sequência de entrada
    public static Map<Character, Integer> calculateFrequencies(String input) {
        // Remove espaços em branco da entrada
        input = input.replaceAll("\\s+", "");
        Map<Character, Integer> frequencies = new HashMap<>();

        // Conta cada caractere e armazena no mapa de frequências
        for (char c : input.toCharArray()) {
            frequencies.merge(c, 1, Integer::sum);
        }

        return frequencies; // Retorna o mapa com as frequências de cada caractere
    }

    // Método para calcular a taxa de compressão da sequência original em relação à comprimida
    public static double calculateCompressionRate(String input, String compressed) {
        // Remove espaços em branco da sequência original
        input = input.replaceAll("\\s+", "");
        // Calcula a taxa de compressão em porcentagem
        return (double) compressed.length() / input.length() * 100;
    }
}

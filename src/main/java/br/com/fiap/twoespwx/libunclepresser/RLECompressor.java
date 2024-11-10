package br.com.fiap.twoespwx.libunclepresser;

import java.util.HashMap;
import java.util.Map;

public class RLECompressor {

    public static String compress(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Remove quebras de linha para processar como uma única sequência
        input = input.replaceAll("\\s+", "");

        StringBuilder compressed = new StringBuilder();
        int count = 1;
        char currentChar = input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                count++;
            } else {
                compressed.append(currentChar).append(count);
                currentChar = input.charAt(i);
                count = 1;
            }
        }
        compressed.append(currentChar).append(count);

        return compressed.toString();
    }

    public static Map<Character, Integer> calculateFrequencies(String input) {
        input = input.replaceAll("\\s+", ""); // Remove quebras de linha
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        return frequencies;
    }

    public static double calculateCompressionRate(String input, String compressed) {
        input = input.replaceAll("\\s+", ""); // Remove quebras de linha
        return (double) compressed.length() / input.length() * 100;
    }
}
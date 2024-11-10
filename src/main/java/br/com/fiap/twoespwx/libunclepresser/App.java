/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * GRUPO: DO CHAPÉU DE PALHA
 * Autores:
 *      - Juan Pinheiro     -   rm552202
 *      - Luigi Ferrara     -   rm98047
 *      - Lucas Rodrigues   -   rm98344 ( Foi para o next )
 *      - Kaiky Alvaro      -   rm98118 ( Foi para o next )
 */

package br.com.fiap.twoespwx.libunclepresser;

import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar <path/to/jar/file> <path/to/input> <path/to/output>");
            return;
        }

        String inputPath = args[0];
        String outputPath = args[1];

        try {
            String input = new String(Files.readAllBytes(Paths.get(inputPath)));
            String compressed = RLECompressor.compress(input);
            Files.write(Paths.get(outputPath), compressed.getBytes());

            printInterface(inputPath, outputPath, input, compressed);
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }

    private static void printInterface(String inputPath, String outputPath, String input, String compressed) {
        DecimalFormat df = new DecimalFormat("#,###.0");
        Map<Character, Integer> frequencies = RLECompressor.calculateFrequencies(input);
        double compressionRate = RLECompressor.calculateCompressionRate(input, compressed);

        System.out.println(" -----------------------------------------------------------");
        System.out.println("|           LIB UNCLE PRESSER - GRUPO DO CHAPÉU DE PALHA    |");
        System.out.println("|-----------------------------------------------------------|");
        System.out.println("|                                                           |");
        System.out.println("| INPUT  FILENAME: " + Paths.get(inputPath).getFileName() + "                               |");
        System.out.println("| OUTPUT FILENAME: " + Paths.get(outputPath).getFileName() + "                              |");
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.println("| INPUT FILE SIZE: " + df.format(input.length() / 1024.0) + "KB                             |");
        System.out.println("|                                                           |");
        System.out.println("| TOTAL INPUT CHARACTERS: " + df.format(input.length()) + "                       |");
        System.out.println("|                                                           |");
        System.out.println("| FREQUENCIES:                                              |");
        for (char nucleotide : new char[]{'A', 'C', 'T', 'G'}) {
            int count = frequencies.getOrDefault(nucleotide, 0);
            double percentage = (double) count / input.length() * 100;
            System.out.printf("| %c: %s  (%.2f%%)                                    |%n", nucleotide, df.format(count), percentage);
        }
        System.out.println("|                                                           |");
        System.out.println("| OPTIONS:                                                  |");
        System.out.println("|                                                           |");
        System.out.println("| ALGORITHM: Run-Length Encoding (RLE)                      |");
        System.out.println("| TEXT-CODIFICATION: UTF-8                                  |");
        System.out.println("| COMPRESSION RATE: " + df.format(compressionRate) + "%                               |");
        System.out.println("|                                                           |");
        System.out.println("| OUTPUT FILE SIZE: " + df.format(compressed.length()) + " BYTES                      |");
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.println("| SCORE: " + getScore(compressionRate) + "                                             |");
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
    }

    private static String getScore(double compressionRate) {
        if (compressionRate < 50) return "EXCELLENT";
        if (compressionRate < 70) return "WELL-DONE";
        if (compressionRate < 90) return "GOOD";
        return "BAD";
    }
}
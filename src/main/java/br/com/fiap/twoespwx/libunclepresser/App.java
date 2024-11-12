package br.com.fiap.twoespwx.libunclepresser;

import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.util.Map;

public class App {
    // Conjunto de nucleotídeos usados em DNA
    private static final char[] NUCLEOTIDES = {'A', 'C', 'T', 'G'};
    // Formatação para exibir números com vírgulas e uma casa decimal
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.0");

    public static void main(String[] args) {
        // Valida os argumentos de entrada (devem ser dois caminhos de arquivos)
        validateArguments(args);

        try {
            // Processa a compressão dos arquivos passados como argumento
            processCompression(args[0], args[1]);
        } catch (IOException e) {
            // Exibe mensagem de erro caso ocorra alguma falha na leitura/escrita de arquivos
            System.err.println("Erro ao processar arquivos: " + e.getMessage());
        }
    }

    private static void validateArguments(String[] args) {
        // Verifica se há exatamente dois argumentos (caminhos de arquivos)
        if (args.length != 2) {
            System.out.println("java -jar target/CHECKPOINT_1_GRUPO_BATATA_DOCE-1.0-SNAPSHOT.jar inputs/input.txt outputs/output.txt");
            System.exit(1); // Encerra o programa se os argumentos estiverem incorretos
        }
    }

    private static void processCompression(String inputPath, String outputPath) throws IOException {
        // Lê o conteúdo do arquivo de entrada
        String inputContent = readFile(inputPath);
        // Comprime o conteúdo usando o método compress da classe CompressorRLE
        String compressedContent = CompressorRLE.compress(inputContent);
        // Escreve o conteúdo comprimido no arquivo de saída
        writeFile(outputPath, compressedContent);

        // Exibe os resultados, incluindo estatísticas de compressão
        displayResults(inputPath, outputPath, inputContent, compressedContent);
    }

    private static String readFile(String path) throws IOException {
        // Lê o conteúdo de um arquivo para uma string
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private static void writeFile(String path, String content) throws IOException {
        // Escreve o conteúdo de uma string em um arquivo
        Files.write(Paths.get(path), content.getBytes());
    }

    private static void displayResults(String inputPath, String outputPath,
                                       String inputContent, String compressedContent) {
        // Calcula a frequência de cada nucleotídeo no conteúdo de entrada
        Map<Character, Integer> frequencies = CompressorRLE.calculateFrequencies(inputContent);
        // Calcula a taxa de compressão (quanto o arquivo foi reduzido)
        double compressionRate = CompressorRLE.calculateCompressionRate(inputContent, compressedContent);

        // Imprime o cabeçalho do relatório
        printHeader(inputPath, outputPath);
        // Imprime estatísticas detalhadas sobre o conteúdo e compressão
        printStatistics(inputContent, compressedContent, frequencies, compressionRate);
        // Imprime o rodapé com a nota de compressão
        printFooter(compressionRate);
    }

    private static void printHeader(String inputPath, String outputPath) {
        // Imprime o cabeçalho do relatório
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|                   COMPRESSOR DE SEQUÊNCIA DE DNA          |");
        System.out.println("|-----------------------------------------------------------|");
        System.out.println("|                                                           |");
        System.out.println("| NOME DO ARQUIVO DE ENTRADA : " + Paths.get(inputPath).getFileName() +
                "                                    |");
        System.out.println("| NOME DO ARQUIVO DE SAÍDA   : " + Paths.get(outputPath).getFileName() +
                "                                    |");
        System.out.println("|                                                           |");
    }

    private static void printStatistics(String inputContent, String compressedContent,
                                        Map<Character, Integer> frequencies, double compressionRate) {
        // Imprime estatísticas do arquivo de entrada e do processo de compressão
        System.out.println(" -----------------------------------------------------------");
        System.out.println("| TAMANHO DO ARQUIVO DE ENTRADA : " +
                DECIMAL_FORMAT.format(inputContent.length() / 1024.0) + " KB                     |");
        System.out.println("| TOTAL DE CARACTERES NO ARQUIVO : " +
                DECIMAL_FORMAT.format(inputContent.length()) + "                    |");
        System.out.println("|                                                           |");
        System.out.println("| FREQUÊNCIAS DOS NUCLEOTÍDEOS:                             |");

        // Imprime a frequência de cada nucleotídeo
        for (char nucleotide : NUCLEOTIDES) {
            printNucleotideFrequency(nucleotide, frequencies, inputContent.length());
        }

        // Imprime detalhes adicionais sobre a compressão
        printCompressionDetails(compressionRate, compressedContent);
    }

    private static void printNucleotideFrequency(char nucleotide,
                                                 Map<Character, Integer> frequencies,
                                                 int totalLength) {
        // Obtém a contagem de um nucleotídeo específico e calcula sua porcentagem
        int count = frequencies.getOrDefault(nucleotide, 0);
        double percentage = (double) count / totalLength * 100;
        // Exibe a frequência e a porcentagem de cada nucleotídeo
        System.out.printf("| %c: %s  (%.2f%%)                                    |%n",
                nucleotide, DECIMAL_FORMAT.format(count), percentage);
    }

    private static void printCompressionDetails(double compressionRate,
                                                String compressedContent) {
        // Imprime detalhes específicos da compressão, como o algoritmo e a taxa de compressão
        System.out.println("|                                                           |");
        System.out.println("| OPÇÕES DE COMPRESSÃO:                                     |");
        System.out.println("| ALGORITMO: Codificação de Comprimento de Execução (RLE)   |");
        System.out.println("| CODIFICAÇÃO DE TEXTO: UTF-8                               |");
        System.out.println("| TAXA DE COMPRESSÃO: " +
                DECIMAL_FORMAT.format(compressionRate) + "%                        |");
        System.out.println("| TAMANHO DO ARQUIVO DE SAÍDA: " +
                DECIMAL_FORMAT.format(compressedContent.length()) + " BYTES           |");
    }

    private static void printFooter(double compressionRate) {
        // Imprime o rodapé com a nota baseada na taxa de compressão
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.println("| NOTA: " + getCompressionGrade(compressionRate) +
                "                                              |");
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
    }

    private static String getCompressionGrade(double compressionRate) {
        // Determina a nota de compressão com base na taxa de compressão
        if (compressionRate < 50) return "Execelente";
        if (compressionRate < 70) return "Bom";
        if (compressionRate < 90) return "Aceitável";
        return "Precisa Melhorar";
    }
}

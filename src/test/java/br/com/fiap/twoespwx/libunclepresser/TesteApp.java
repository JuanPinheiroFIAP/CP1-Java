package br.com.fiap.twoespwx.libunclepresser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

// Classe de teste para a aplicação de compressão RLE (Run Length Encoding)
public class TesteApp extends TestCase {
    // Casos de teste pré-definidos com pares de entrada e saída esperada
    private static final String[][] TEST_CASES = {
            {"AAAACCCTTG", "A4C3T2G1"},
            {"GGAACCTTCC", "G2A2C2T2C2"},
            {"GGGGGGGGGG", "G10"},
            {"TGGGGGGGGC", "T1G8C1"},
            {"GGGGGGGGCC", "G8C2"},
            {"GGGGGGGCCC", "G7C3"}
    };

    // Construtor para a classe de teste
    public TesteApp(String testName) {
        super(testName);
    }

    // Define a suíte de testes para execução
    public static Test suite() {
        return new TestSuite(TesteApp.class);
    }

    // Método de teste para verificar a compressão RLE
    public void testRLECompression() {
        // Para cada caso de teste, verifica se o resultado da compressão está correto
        for (String[] testCase : TEST_CASES) {
            String input = testCase[0]; // Entrada do caso de teste
            String expectedOutput = testCase[1]; // Saída esperada do caso de teste
            assertEquals("Falha na compressão para entrada: " + input,
                    expectedOutput, CompressorRLE.compress(input));
        }
    }
}

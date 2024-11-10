package br.com.fiap.twoespwx.libunclepresser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testRLECompression() {
        String[] inputs = {
                "AAAACCCTTG",
                "GGAACCTTCC",
                "GGGGGGGGGG",
                "TGGGGGGGGC",
                "GGGGGGGGCC",
                "GGGGGGGCCC"
        };

        String[] expectedOutputs = {
                "A4C3T2G1",
                "G2A2C2T2C2",
                "G10",
                "T1G8C1",
                "G8C2",
                "G7C3"
        };

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expectedOutputs[i], RLECompressor.compress(inputs[i]));
        }
    }
}
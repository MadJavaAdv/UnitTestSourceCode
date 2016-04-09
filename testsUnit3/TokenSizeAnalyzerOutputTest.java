package java112.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

import java.lang.reflect.*;

import java.io.*;
import java.util.*;
import java112.analyzer.TokenSizeAnalyzer;

public class TokenSizeAnalyzerOutputTest {

    private static TokenSizeAnalyzer analyzer;
    private static BufferedReader testOutput;
    private static String testOutputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;
    private static Properties properties;
    private static PrintWriter out;

    @BeforeClass
    public static void initialSetUp()
            throws java.io.FileNotFoundException,
            java.io.IOException {

        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        properties = new Properties();
        properties.setProperty("output.dir", "output/");
        properties.setProperty("output.file.token.size", "test_token_size.txt");


        analyzer = new TokenSizeAnalyzer(properties);
        analyzer.processToken("the");
        analyzer.processToken("one");
        analyzer.processToken("if");
        analyzer.processToken("three");
        analyzer.processToken("and");
        analyzer.processToken("four");
        analyzer.processToken("five");
        analyzer.processToken("six");
        analyzer.processToken("if");
        analyzer.processToken("the");

        testOutputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.token.size");

        analyzer.writeOutputFile(inputFilePath);

        testOutput = new BufferedReader(new FileReader(testOutputFilePath));

        while (testOutput.ready()) {
            outputFileContents.add(testOutput.readLine());
        }
    }


    @AfterClass
    public static void tearDown() {

        File file = new File(testOutputFilePath);
        file.delete();

        analyzer = null;
    }


    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void writeOutputFileExistsTest() throws NoSuchMethodException {
        Method method = TokenSizeAnalyzer.class.getMethod ("writeOutputFile",
                String.class);
        assertNotNull(method);
    }

    @Test
    public void outputLineOneTest() {
        assertEquals("2	2", outputFileContents.get(0));
    }

    @Test
    public void outputLineTwoTest() {
        assertEquals("3	5", outputFileContents.get(1));
    }

    @Test
    public void outputLineFourTest() {
        assertEquals("4	2", outputFileContents.get(2));
    }

    @Test
    public void outputLineFiveTest() {
        assertEquals("5	1", outputFileContents.get(3));
    }



}

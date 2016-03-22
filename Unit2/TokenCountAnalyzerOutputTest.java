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
import java112.analyzer.TokenCountAnalyzer;

public class TokenCountAnalyzerOutputTest {

    private static TokenCountAnalyzer analyzer;
    private static BufferedReader testOutput;
    private static String testOutputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp()
            throws java.io.FileNotFoundException,
            java.io.IOException {

        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        properties = new Properties();
        properties.setProperty("output.dir", "output/");
        properties.setProperty("output.file.token.ount", "test_token_count.txt");

        analyzer = new TokenCountAnalyzer(properties);
        analyzer.processToken("one");
        analyzer.processToken("one");
        analyzer.processToken("two");
        analyzer.processToken("three");
        analyzer.processToken("three");
        analyzer.processToken("four");
        analyzer.processToken("five");
        analyzer.processToken("five");
        analyzer.processToken("six");
        analyzer.processToken("seven");
        analyzer.processToken("seven");
        analyzer.processToken("seven");
        analyzer.processToken("seven");
        analyzer.processToken("eight");
        analyzer.processToken("eight");
        analyzer.processToken("eight");

        testOutputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.token.count");

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
        Method method = TokenCountAnalyzer.class.getMethod ("writeOutputFile",
                String.class);
        assertNotNull(method);
    }

    @Test
    public void outputLineOneTest() {
        assertEquals("eight\t3", outputFileContents.get(0));
    }

    @Test
    public void outputLineTwoTest() {
        assertEquals("five\t2", outputFileContents.get(1));
    }

    @Test
    public void outputLineThreeTest() {
        assertEquals("four\t1", outputFileContents.get(2));
    }

    @Test
    public void outputLineFourTest() {
        assertEquals("one\t2", outputFileContents.get(3));
    }

    @Test
    public void outputLineFiveTest() {
        assertEquals("seven\t4", outputFileContents.get(4));
    }

    @Test
    public void outputLineSixTest() {
        assertEquals("six\t1", outputFileContents.get(5));
    }

    @Test
    public void outputLineSevenTest() {
        assertEquals("three\t2", outputFileContents.get(6));
    }

    @Test
    public void outputLineEightTest() {
        assertEquals("two\t1", outputFileContents.get(7));
    }
}

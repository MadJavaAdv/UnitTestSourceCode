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
import java112.analyzer.UniqueTokenAnalyzer;

public class UniqueTokenAnalyzerOutputTest {

    private static UniqueTokenAnalyzer analyzer;
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
        properties.setProperty("output.file.unique", "test_unique_tokens.txt");

        analyzer = new UniqueTokenAnalyzer(properties);
        analyzer.processToken("one");
        analyzer.processToken("one");
        analyzer.processToken("two");
        analyzer.processToken("three");
        analyzer.processToken("three");
        analyzer.processToken("four");
        analyzer.processToken("five");
        analyzer.processToken("six");
        analyzer.processToken("seven");
        analyzer.processToken("eight");

        testOutputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.unique");

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
        Method method = UniqueTokenAnalyzer.class.getMethod ("writeOutputFile",
                String.class);
        assertNotNull(method);
    }

    @Test
    public void outputLineOneTest() {
        assertEquals("eight", outputFileContents.get(0));
    }

    @Test
    public void outputLineTwoTest() {
        assertEquals("five", outputFileContents.get(1));
    }

    @Test
    public void outputLineThreeTest() {
        assertEquals("four", outputFileContents.get(2));
    }

    @Test
    public void outputLineFourTest() {
        assertEquals("one", outputFileContents.get(3));
    }

    @Test
    public void outputLineFiveTest() {
        assertEquals("seven", outputFileContents.get(4));
    }

    @Test
    public void outputLineSixTest() {
        assertEquals("six", outputFileContents.get(5));
    }

    @Test
    public void outputLineSevenTest() {
        assertEquals("three", outputFileContents.get(6));
    }

    @Test
    public void outputLineEightTest() {
        assertEquals("two", outputFileContents.get(7));
    }
}

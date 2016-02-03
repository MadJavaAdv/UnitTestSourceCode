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
    private static String outputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;


    @BeforeClass
    public static void initialSetUp()
            throws java.io.FileNotFoundException,
            java.io.IOException {

        outputFilePath = "output/test_summary.txt";
        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        analyzer = new UniqueTokenAnalyzer();
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

        analyzer.writeOutputFile(inputFilePath, outputFilePath);

        testOutput = new BufferedReader(new FileReader(outputFilePath));

        while (testOutput.ready()) {
            outputFileContents.add(testOutput.readLine());
        }
    }


    @AfterClass
    public static void tearDown() {

        File file = new File(outputFilePath);
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
                String.class, String.class);
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

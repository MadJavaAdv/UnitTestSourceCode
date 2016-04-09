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
import java112.analyzer.BigWordAnalyzer;

public class BigWordAnalyzerOutputTest {

    private static BigWordAnalyzer analyzer;
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
        properties.setProperty("output.file.bigwords", "test_big_tokens.txt");
        properties.setProperty("bigwords.minimum.length", "4");

        analyzer = new BigWordAnalyzer(properties);
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
                + properties.getProperty("output.file.bigwords");

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
        Method method = BigWordAnalyzer.class.getMethod ("writeOutputFile",
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
    public void outputLineFiveTest() {
        assertEquals("seven", outputFileContents.get(3));
    }

    @Test
    public void outputLineSevenTest() {
        assertEquals("three", outputFileContents.get(4));
    }

}

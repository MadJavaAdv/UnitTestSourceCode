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
import java112.analyzer.*;

public class KeywordAnalyzerOutputTest {

    private static KeywordAnalyzer analyzer;
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
        properties.setProperty("output.file.keyword", "test_keywords.txt");
        properties.setProperty("file.path.keywords", "config/test_keywords.txt");

        out = new PrintWriter(properties.getProperty("file.path.keywords"));

        out.println("the");
        out.println("and");
        out.println("if");

        out.close();

        analyzer = new KeywordAnalyzer(properties);
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
        //analyzer.processToken("and");

        testOutputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.keyword");

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

        File keywordFile = new File(properties.getProperty("file.path.keywords"));
        keywordFile.delete();

        analyzer = null;
    }


    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void writeOutputFileExistsTest() throws NoSuchMethodException {
        Method method = KeywordAnalyzer.class.getMethod ("writeOutputFile",
                String.class);
        assertNotNull(method);
    }

    @Test
    public void outputLineOneTest() {
        assertEquals("and =", outputFileContents.get(0));
    }

    @Test
    public void outputLineTwoTest() {
        assertEquals("[5]", outputFileContents.get(1));
    }

    @Test
    public void outputLineFourTest() {
        assertEquals("if =", outputFileContents.get(3));
    }

    @Test
    public void outputLineFiveTest() {
        assertEquals("[3, 9]", outputFileContents.get(4));
    }

    @Test
    public void outputLineSevenTest() {
        assertEquals("the =", outputFileContents.get(6));
    }

    @Test
    public void outputLineEightTest() {
        assertEquals("[1, 10]", outputFileContents.get(7));
    }
}

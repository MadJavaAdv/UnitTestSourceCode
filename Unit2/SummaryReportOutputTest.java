package java112.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

import java.lang.reflect.Method;

import java.io.*;
import java.util.*;
import java112.analyzer.SummaryReport;

public class SummaryReportOutputTest {

    private static SummaryReport report;
    private static BufferedReader testOutput;
    private static String outputFilePath;
    private static String inputFilePath;
    private static List<String> outputFileContents;
    private static Properties properties;
    private static String testOutputFilePath;


    @BeforeClass
    public static void initialSetUp() throws java.io.FileNotFoundException,
            java.io.IOException {

        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        properties = new Properties();
        properties.setProperty("author", "Eric Knapp");
        properties.setProperty("output.dir", "output/");
        properties.setProperty("email", "eknapp@matcmadison.edu");
        properties.setProperty("output.file.summary", "test_summary.txt");

        report = new SummaryReport(properties);
        report.processToken("one");
        report.processToken("one");
        report.processToken("two");
        report.processToken("three");
        report.processToken("three");
        report.processToken("four");
        report.processToken("five");
        report.processToken("six");
        report.processToken("seven");
        report.processToken("eight");

        report.writeOutputFile(inputFilePath);

        testOutputFilePath = properties.getProperty("output.dir")
                + properties.getProperty("output.file.summary");
        testOutput = new BufferedReader(new FileReader(testOutputFilePath));

        while (testOutput.ready()) {
            outputFileContents.add(testOutput.readLine());
        }
    }


    @AfterClass
    public static void tearDown() {

        File file = new File(testOutputFilePath);
//        file.delete();

        report = null;
    }


    @Test
    public void classExists() {
        assertNotNull(report);
    }

    @Test
    public void writeOutputFileExistsTest() throws NoSuchMethodException {
        Method method = SummaryReport.class.getMethod ("writeOutputFile", String.class);
        assertNotNull(method);
    }

    @Test
    public void inputFileNameInReportTest() {

       String reportLineFour = outputFileContents.get(3);

       //System.out.println("reportLineFour: " + reportLineFour);

       String[] lineArray = reportLineFour.split("\\W");

       //System.out.println("lineArray length: " + lineArray.length);
       String inputFileFromReport = lineArray[lineArray.length - 1];

       //System.out.println("inputFileFromReport: " + inputFileFromReport);

       assertEquals(inputFileFromReport, inputFilePath);
    }

    @Test
    public void tokenCountInReportTest() {

        String reportLineSix = outputFileContents.get(5);
        String[] lineArray = reportLineSix.split("\\W");
        String tokenCountString = lineArray[lineArray.length - 1];
        int tokenCount = Integer.parseInt(tokenCountString);

        assertEquals(10, tokenCount);

    }
}

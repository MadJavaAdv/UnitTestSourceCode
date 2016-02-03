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


    @BeforeClass
    public static void initialSetUp() throws java.io.FileNotFoundException,
            java.io.IOException {

        outputFilePath = "output/test_summary.txt";
        inputFilePath = "inputFile";
        outputFileContents = new ArrayList<String>();

        report = new SummaryReport();
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

        report.writeOutputFile(inputFilePath, outputFilePath);

        testOutput = new BufferedReader(new FileReader(outputFilePath));

        while (testOutput.ready()) {
            outputFileContents.add(testOutput.readLine());
        }
    }


    @AfterClass
    public static void tearDown() {

        File file = new File(outputFilePath);
        file.delete();

        report = null;
    }


    @Test
    public void classExists() {
        assertNotNull(report);
    }

    @Test
    public void writeOutputFileExistsTest() throws NoSuchMethodException {
        Method method = SummaryReport.class.getMethod ("writeOutputFile", String.class, String.class);
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

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
import java112.labs1.LabSix;

public class LabSixTests {

    private static LabSix lab;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static String testInputFile;
    private static String testOutputFile;
    private static String testDirectory;
    private static String testFilePath;
    private static BufferedReader testReader;
    private static PrintWriter testWriter;
    private static ArrayList<String> outputFileLineList;

    @BeforeClass
    public static void initialSetUp() throws IOException {
        lab = new LabSix();


        testInputFile = "testInputFile.txt";
        testOutputFile = "testOutputFile.txt";

        testWriter = new PrintWriter(new BufferedWriter(new FileWriter(testInputFile)));
        testWriter.println("test line one");
        testWriter.println("test line two");
        testWriter.println("test line three");

        testWriter.close();


        String[] testArray = {testInputFile, testOutputFile};
        LabSix.main(testArray);

        testReader = new BufferedReader(new FileReader(testOutputFile));

        outputFileLineList = new ArrayList<String>();

        while (testReader.ready()) {
            outputFileLineList.add(testReader.readLine());
        }
    }

    @AfterClass
    public static void tearDown() {
        lab = null;

        File testInput = new File(testInputFile);
        testInput.delete();

        File testOutput = new File(testOutputFile);
        testOutput.delete();

    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void classExists() {
        assertNotNull(lab);
    }


    @Test
    public void noInstanceVariablesTest() {
        Field[] fields = LabSix.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertTrue(fieldCount == 0);
    }

    @Test
    public void mainMethodExistsTest() throws NoSuchMethodException {
        Method method = LabSix.class.getMethod("main", String[].class);
        assertNotNull(method);
    }

    @Test
    public void mainMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabSix.class.getMethod("main", String[].class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void runMethodWithTwoStringParamsExistsTest() throws NoSuchMethodException {
        Method method = LabSix.class.getMethod("run", String.class, String.class);
        assertNotNull(method);
    }

    @Test
    public void runMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabSix.class.getMethod("run", String.class, String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void mainMethodSystemOutPrintlnNoArgumentsInputTest() {

        String[] testArray = {};
        LabSix.main(testArray);
        assertEquals("Please enter two arguments on the command"
                    + " line, an input file name and an output file name\n",
                    outContent.toString());
    }

    @Test
    public void mainMethodSystemOutPrintlnWithNotEnoughArgumentsTest() {
        String[] testArray = {"test"};
        LabSix.main(testArray);
        assertEquals("Please enter two arguments on the command"
                    + " line, an input file name and an output file name\n",
                    outContent.toString());
    }

    @Test
    public void classCreatesOutputFileTest() {
        assertNotNull(testReader);
    }

    @Test
    public void outputFileHasCorrectLineOneTextText() throws java.io.IOException {
        String line = outputFileLineList.get(0);
        assertEquals(line, "test line one");
    }

    @Test
    public void outputFileHasCorrectLineTwoTextText() throws java.io.IOException {
        String line = outputFileLineList.get(1);
        assertEquals(line, "test line two");
    }

    @Test
    public void outputFileHasCorrectLineThreeTextText() throws java.io.IOException {
        String line = outputFileLineList.get(2);
        assertEquals(line, "test line three");
    }
}



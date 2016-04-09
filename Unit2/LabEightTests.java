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
import java112.labs1.LabEight;

public class LabEightTests {

    private static LabEight lab;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static String testFile;
    private static String testDirectory;
    private static String testFilePath;
    private static String lineOne;
    private static BufferedReader testReader;
    private static List<String> outputFileContents;

    @BeforeClass
    public static void initialSetUp() throws IOException {
        lab = new LabEight();

        testFile = "TestFile8";

        String[] testArray = {testFile};
        LabEight.main(testArray);

        testReader = new BufferedReader(new FileReader(testFile));

        outputFileContents = new ArrayList<String>();
        while (testReader.ready()) {
            outputFileContents.add(testReader.readLine());
        }
    }

    @AfterClass
    public static void tearDown() {
        lab = null;

        File testInputFile = new File(testFile);
        testInputFile.delete();

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
    public void oneInstanceVariableTest() {
        Field[] fields = LabEight.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertTrue(fieldCount == 1);
    }

    @Test
    public void mainMethodExistsTest() throws NoSuchMethodException {
        Method method = LabEight.class.getMethod("main", String[].class);
        assertNotNull(method);
    }

    @Test
    public void mainMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabEight.class.getMethod("main", String[].class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void runMethodWithOneStringParameterExistsTest() throws NoSuchMethodException {
        Method method = LabEight.class.getMethod("run", String.class);
        assertNotNull(method);
    }

    @Test
    public void runMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabEight.class.getMethod("run", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void mainMethodSystemOutPrintlnNoArgumentsInputTest() {

        String[] testArray = {};
        LabEight.main(testArray);
        assertEquals("Please enter one argument on the command"
                    + " line, an output file name\n", outContent.toString());
    }

    @Test
    public void mainMethodSystemOutPrintlnWithTooManyArgumentsTest() {
        String[] testArray = {"test", "test"};
        LabEight.main(testArray);
        assertEquals("Please enter one argument on the command"
                    + " line, an output file name\n", outContent.toString());
    }

    @Test
    public void classCreatesOutputFileTest() {
        assertNotNull(testReader);
    }

    @Test
    public void outputLineOneTest() {
        assertEquals("five", outputFileContents.get(0));
    }

    @Test
    public void outputLineTwoTest() {
        assertEquals("four", outputFileContents.get(1));
    }

    @Test
    public void outputLineThreeTest() {
        assertEquals("one", outputFileContents.get(2));
    }

    @Test
    public void outputLineFourTest() {
        assertEquals("three", outputFileContents.get(3));
    }

    @Test
    public void outputLineFiveTest() {
        assertEquals("two", outputFileContents.get(4));
    }


}



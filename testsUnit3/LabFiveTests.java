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
import java112.labs1.LabFive;

public class LabFiveTests {

    private static LabFive lab;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static String testFile;
    private static String testDirectory;
    private static String testFilePath;
    private static String lineOne;
    private static String message;
    private static BufferedReader testReader;

    @BeforeClass
    public static void initialSetUp() throws IOException {
        lab = new LabFive();

        testFile = "TestFile";
        message = "TestMessage";

        String[] testArray = {testFile, message};
        LabFive.main(testArray);

        testReader = new BufferedReader(new FileReader(testFile));
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
    public void noInstanceVariablesTest() {
        Field[] fields = LabFive.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertTrue(fieldCount == 0);
    }

    @Test
    public void mainMethodExistsTest() throws NoSuchMethodException {
        Method method = LabFive.class.getMethod("main", String[].class);
        assertNotNull(method);
    }

    @Test
    public void mainMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabFive.class.getMethod("main", String[].class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void runMethodWithTwoStringParamsExistsTest() throws NoSuchMethodException {
        Method method = LabFive.class.getMethod("run", String.class, String.class);
        assertNotNull(method);
    }

    @Test
    public void runMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabFive.class.getMethod("run", String.class, String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void mainMethodSystemOutPrintlnNoArgumentsInputTest() {

        String[] testArray = {};
        LabFive.main(testArray);
        assertEquals("Please enter two arguments on the command"
                + " line, a file name and a message\n", outContent.toString());
    }

    @Test
    public void mainMethodSystemOutPrintlnWithNotEnoughArgumentsTest() {
        String[] testArray = {"test"};
        LabFive.main(testArray);
        assertEquals("Please enter two arguments on the command"
                + " line, a file name and a message\n", outContent.toString());
    }

    @Test
    public void classCreatesOutputFileTest() {
        assertNotNull(testReader);
    }

    @Test
    public void outputFileHasCorrectTextText() throws java.io.IOException {
        String line = testReader.readLine();

        assertEquals(line, message);
    }

}



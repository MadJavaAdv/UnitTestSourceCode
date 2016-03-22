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
import java112.labs1.LabSeven;

public class LabSevenTests {

    private static LabSeven lab;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static String testFile;
    private static String testDirectory;
    private static String testFilePath;
    private static String lineOne;
    private static BufferedReader testReader;
    private static List<String> outputFileContents;

    @BeforeClass
    public static void initialSetUp() throws IOException {
        lab = new LabSeven();

        testFile = "TestFile";

        String[] testArray = {testFile};
        LabSeven.main(testArray);

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
        Field[] fields = LabSeven.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertTrue(fieldCount == 1);
    }

    @Test
    public void mainMethodExistsTest() throws NoSuchMethodException {
        Method method = LabSeven.class.getMethod("main", String[].class);
        assertNotNull(method);
    }

    @Test
    public void mainMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabSeven.class.getMethod("main", String[].class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void runMethodWithOneStringParameterExistsTest() throws NoSuchMethodException {
        Method method = LabSeven.class.getMethod("run", String.class);
        assertNotNull(method);
    }

    @Test
    public void runMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabSeven.class.getMethod("run", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void mainMethodSystemOutPrintlnNoArgumentsInputTest() {

        String[] testArray = {};
        LabSeven.main(testArray);
        assertEquals("Please enter one argument on the command"
                    + " line, an output file name\n", outContent.toString());
    }

    @Test
    public void mainMethodSystemOutPrintlnWithTooManyArgumentsTest() {
        String[] testArray = {"test", "test"};
        LabSeven.main(testArray);
        assertEquals("Please enter one argument on the command"
                    + " line, an output file name\n", outContent.toString());
    }

    @Test
    public void classCreatesOutputFileTest() {
        assertNotNull(testReader);
    }

    @Test
    public void outputLineOneTest() {
        assertEquals("one", outputFileContents.get(0));
    }

    @Test
    public void outputLineTwoTest() {
        assertEquals("two", outputFileContents.get(1));
    }

    @Test
    public void outputLineThreeTest() {
        assertEquals("three", outputFileContents.get(2));
    }

    @Test
    public void outputLineFourTest() {
        assertEquals("four", outputFileContents.get(3));
    }

    @Test
    public void outputLineFiveTest() {
        assertEquals("five", outputFileContents.get(4));
    }

    @Test
    public void outputLineSixTest() {
        assertEquals("six", outputFileContents.get(5));
    }

    @Test
    public void outputLineSevenTest() {
        assertEquals("seven", outputFileContents.get(6));
    }

    @Test
    public void outputLineEightTest() {
        assertEquals("eight", outputFileContents.get(7));
    }

    @Test
    public void outputLineNineTest() {
        assertEquals("nine", outputFileContents.get(8));
    }

    @Test
    public void outputLineTenTest() {
        assertEquals("ten", outputFileContents.get(9));
    }

}



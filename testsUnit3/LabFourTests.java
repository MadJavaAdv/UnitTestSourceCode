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
import java112.labs1.LabFour;

public class LabFourTests {

    private static LabFour lab;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static String testFile;
    private static String testDirectory;
    private static String testFilePath;
    private static String lineOne;
    private static String lineTwo;

    @BeforeClass
    public static void initialSetUp() throws IOException {
        lab = new LabFour();

        testFile = "testFile";
        testDirectory = "testInput";
        testFilePath = testDirectory + "/" + testFile;

        File testInputDirectory = new File(testDirectory);
        testInputDirectory.mkdirs();


        PrintWriter outWriter = new PrintWriter(
                    new BufferedWriter(new FileWriter(testFilePath)));

        lineOne = "line one";
        lineTwo = "line two";

        outWriter.println(lineOne);
        outWriter.println(lineTwo);

        outWriter.close();
    }

    @AfterClass
    public static void tearDown() {
        lab = null;

        File testInputFile = new File(testFilePath);
        testInputFile.delete();

        File testDir = new File(testDirectory);
        testDir.delete();

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
    public void mainMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabFour.class.getMethod("main", String[].class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void mainMethodExistsTest() throws NoSuchMethodException {
        Method method = LabFour.class.getMethod("main", String[].class);
        assertNotNull(method);
    }

    @Test
    public void runMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabFour.class.getMethod("run", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void runMethodWithOneStringParamExistsTest() throws NoSuchMethodException {
        Method method = LabFour.class.getMethod("run", String.class);
        assertNotNull(method);
    }

    @Test
    public void runMethodSystemOutPrintlnWithInputFileTest() {
        lab.run(testFilePath);

        String output = lineOne;
        output += System.getProperty("line.separator");
        output += lineTwo;
        output += System.getProperty("line.separator");

        assertEquals(output, outContent.toString());
    }

    @Test
    public void mainMethodSystemOutPrintlnNoArgumentsInputTest() {

        String[] testArray = {};
        LabFour.main(testArray);
        assertEquals("Please enter one argument on the command line\n",
                outContent.toString());
    }

    @Test
    public void mainMethodSystemOutPrintlnTooManyArgumentsTest() {
        String[] testArray = {"test", "another test"};
        LabFour.main(testArray);
        assertEquals("Please enter one argument on the command line\n", outContent.toString());
    }

}



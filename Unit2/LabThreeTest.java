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
import java112.labs1.LabThree;

public class LabThreeTest {

    private static LabThree lab;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void initialSetUp() {
        lab = new LabThree();
    }

    @AfterClass
    public static void tearDown() {
        lab = null;
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
        Method method = LabThree.class.getMethod("main", String[].class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void mainMethodExistsTest() throws NoSuchMethodException {
        Method method = LabThree.class.getMethod("main", String[].class);
        assertNotNull(method);
    }

    @Test
    public void runMethodVoidReturnTest() throws NoSuchMethodException {
        Method method = LabThree.class.getMethod("run", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void runMethodWithOneStringParamExistsTest() throws NoSuchMethodException {
        Method method = LabThree.class.getMethod("run", String.class);
        assertNotNull(method);
    }

    @Test
    public void runMethodSystemOutPrintlnWithInputTest() {
        lab.run("test");
        assertEquals("input: test\n", outContent.toString());
    }

    @Test
    public void mainMethodSystemOutPrintlnNoArgumentsInputTest() {
        LabThree.main(new String[0]);
        assertEquals("Please enter one argument on the command line\n", outContent.toString());
    }

    @Test
    public void mainMethodOneArgumentInputCallRunMethodTest() {
        String[] testArray = {"test"};
        LabThree.main(testArray);
        assertEquals("input: test\n", outContent.toString());
    }

    @Test
    public void mainMethodSystemOutPrintlnTooManyArgumentsTest() {
        String[] testArray = {"test", "another test"};
        LabThree.main(testArray);
        assertEquals("Please enter one argument on the command line\n", outContent.toString());
    }

}



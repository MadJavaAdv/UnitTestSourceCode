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
import java.lang.reflect.*;
import java112.analyzer.AnalyzeFile;

public class AnalyzeFileMethodsTest {

    private static AnalyzeFile analyzeFile;

    @BeforeClass
    public static void initialSetUp() {
        analyzeFile = new AnalyzeFile();
    }

    @AfterClass
    public static void tearDown() {
        analyzeFile = null;
    }

    @Test
    public void classExists() {
        assertNotNull(analyzeFile);
    }

    @Test
    public void instanceVariablesExistTest() {
        Field[] fields = AnalyzeFile.class.getDeclaredFields();
        assertNotNull(fields);
    }

    @Test
    public void instanceVariablesCountTest() {
        Field[] fields = AnalyzeFile.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertTrue(fieldCount == 4);
    }

    @Test
    public void runAnalysisOneStringArrayParamExistsTest() throws NoSuchMethodException {
        Method method = AnalyzeFile.class.getMethod("runAnalysis", String[].class);
        assertNotNull(method);
    }

    @Test
    public void runAnalysisMethodReturnVoidTest() throws NoSuchMethodException {
        Method method = AnalyzeFile.class.getMethod("runAnalysis", String[].class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void methodCountGreaterThanFiveTest() {
        Method[] methods = AnalyzeFile.class.getDeclaredMethods();
        assertTrue(methods.length >= 7);
    }

    @Test
    public void writeAllOutputFileMethodTest() throws java.lang.NoSuchMethodException {
        Method method = AnalyzeFile.class.getDeclaredMethod("writeAllOutputFiles");
        assertNotNull(method);
    }

}

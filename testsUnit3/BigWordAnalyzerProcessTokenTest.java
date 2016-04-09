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
import java112.analyzer.*;

public class BigWordAnalyzerProcessTokenTest {

    private static BigWordAnalyzer analyzer;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() {
        properties = new Properties();
        properties.setProperty("output.dir", "output/");
        properties.setProperty("output.file.bigwords", "test_bigwords.txt");
        properties.setProperty("bigwords.minimum.length", "4");

        analyzer = new BigWordAnalyzer(properties);
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;
    }

    @Test
    public void bigWordsSetCreationTest() {
        Set testSet = analyzer.getBigWords();
        assertNotNull(testSet);
    }

    @Test
    public void bigWordsSetEmptyTest() {
        Set testSet = analyzer.getBigWords();
        int size = testSet.size();
        assertEquals(0, size);
    }

    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void instanceVariablesCountTest() {
        Field[] fields = BigWordAnalyzer.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertEquals(3, fieldCount);
    }

    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = BigWordAnalyzer.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = BigWordAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }

    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = BigWordAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }

    @Test
    public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = BigWordAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }

    @Test
    public void setMethodForBigWordsSetNotCreatedTest() {

        Method method = null;
        try {
            method = BigWordAnalyzer.class.getMethod("setBigWordsSet", Set.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op, we want this to fail
        }

        assertNull(method);
    }

    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = BigWordAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], Analyzer.class);
    }

    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = BigWordAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }

}

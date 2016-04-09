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

public class UniqueTokenAnalyzerProcessTokenTest {

    private static UniqueTokenAnalyzer analyzer;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() {
        properties = new Properties();
        properties.setProperty("output.dir", "output/");
        properties.setProperty("output.file.unique", "test_unique_tokens.txt");

        analyzer = new UniqueTokenAnalyzer(properties);
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;
    }

    @Test
    public void uniqueTokensSetCreationTest() {
        Set testSet = analyzer.getUniqueTokensList();
        assertNotNull(testSet);
    }

    @Test
    public void uniqueTokensSetEmptyTest() {
        Set testSet = analyzer.getUniqueTokensList();
        int size = testSet.size();
        assertEquals(0, size);
    }

    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = UniqueTokenAnalyzer.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = UniqueTokenAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }

    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = UniqueTokenAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }

    @Test
    public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = UniqueTokenAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }

    @Test
    public void setMethodForTokensListNotCreatedTest() {

        Method method = null;
        try {
            method = UniqueTokenAnalyzer.class.getMethod("setUniqueTokensList", Set.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op, we want this to fail
        }

        assertNull(method);
    }

    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = UniqueTokenAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], Analyzer.class);
    }

    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = UniqueTokenAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }

}

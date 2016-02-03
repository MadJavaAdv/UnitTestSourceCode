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
import java112.analyzer.Analyzer;
import java112.analyzer.TokenCountAnalyzer;

public class TokenCountAnalyzerProcessTokenTest {

    private static TokenCountAnalyzer analyzer;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() {
        properties = new Properties();
        properties.setProperty("output.dir", "output/");
        properties.setProperty("output.file.token.count", "test_token_count.txt");

        analyzer = new TokenCountAnalyzer(properties);
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;
    }

    @Test
    public void uniqueTokensSetCreationTest() {
        Map testMap = analyzer.getTokenCounts();
        assertNotNull(testMap);
    }

    @Test
    public void uniqueTokensSetEmptyTest() {
        Map testMap = analyzer.getTokenCounts();
        int size = testMap.size();
        assertEquals(0, size);
    }

    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void instanceVariablesCountTest() {
        Field[] fields = TokenCountAnalyzer.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertEquals(2, fieldCount);
    }

    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = TokenCountAnalyzer.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = TokenCountAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }

    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = TokenCountAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }

    @Test
    public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = TokenCountAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }

    @Test
    public void setMethodForTokenCountSetNotCreatedTest() {

        Method method = null;
        try {
            method = TokenCountAnalyzer.class.getMethod("setTokenCounts", Map.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op, we want this to fail
        }

        assertNull(method);
    }

    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = TokenCountAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], Analyzer.class);
    }

    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = TokenCountAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }

}

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

public class TokenSizeAnalyzerProcessTokenTest {

    private static TokenSizeAnalyzer analyzer;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() throws FileNotFoundException, Exception  {
        properties = new Properties();
        properties.setProperty("output.dir", "output/");
        properties.setProperty("output.file.token.size", "test_token_size.txt");

        analyzer = new TokenSizeAnalyzer(properties);
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;

        File file = new File(properties.getProperty("output.file.token.size"));
        file.delete();
    }

    @Test
    public void keywordsMapCreationTest() {
        Map testMap = analyzer.getTokenSizes();
        assertNotNull(testMap);
    }

    @Test
    public void keywordsMapHasZeroKeysTest() {
        Map testMap = analyzer.getTokenSizes();
        int size = testMap.size();
        assertEquals(0, size);
    }

    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void instanceVariablesCountTest() {
        Field[] fields = TokenSizeAnalyzer.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertEquals(3, fieldCount);
    }

    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = TokenSizeAnalyzer.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = TokenSizeAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }

    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = TokenSizeAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }

    @Test
    public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = TokenSizeAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }

    @Test
    public void setMethodForTokenSizesNotCreatedTest() {

        Method method = null;
        try {
            method = TokenSizeAnalyzer.class.getMethod("setTokenSizes", Set.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op, we want this to fail
        }

        assertNull(method);
    }

    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = TokenSizeAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], Analyzer.class);
    }

    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = TokenSizeAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }

}

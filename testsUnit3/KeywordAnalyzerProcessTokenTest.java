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

public class KeywordAnalyzerProcessTokenTest {

    private static KeywordAnalyzer analyzer;
    private static Properties properties;
    private static PrintWriter out;

    @BeforeClass
    public static void initialSetUp() throws FileNotFoundException, Exception  {
        properties = new Properties();
        properties.setProperty("output.dir", "output/");
        properties.setProperty("output.file.keyword", "test_keywords.txt");
        properties.setProperty("file.path.keywords", "config/test_keywords.txt");

        out = new PrintWriter(properties.getProperty("file.path.keywords"));

        out.println("the");
        out.println("and");
        out.println("if");

        out.close();

        analyzer = new KeywordAnalyzer(properties);
    }

    @AfterClass
    public static void tearDown() {
        analyzer = null;

        File file = new File(properties.getProperty("file.path.keywords"));
        file.delete();
    }

    @Test
    public void keywordsMapCreationTest() {
        Map testMap = analyzer.getKeywordMap();
        assertNotNull(testMap);
    }

    @Test
    public void keywordsMapHasThreeKeysTest() {
        Map testMap = analyzer.getKeywordMap();
        int size = testMap.size();
        assertEquals(3, size);
    }

    @Test
    public void classExists() {
        assertNotNull(analyzer);
    }

    @Test
    public void instanceVariablesCountTest() {
        Field[] fields = KeywordAnalyzer.class.getDeclaredFields();
        int fieldCount = fields.length;
        assertEquals(3, fieldCount);
    }

    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = KeywordAnalyzer.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = KeywordAnalyzer.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }

    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = KeywordAnalyzer.class.getConstructor();
        assertNotNull(constructor);
    }

    @Test
    public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = KeywordAnalyzer.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }

    @Test
    public void setMethodForKeywordsMapNotCreatedTest() {

        Method method = null;
        try {
            method = KeywordAnalyzer.class.getMethod("setKeywordMap", Set.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op, we want this to fail
        }

        assertNull(method);
    }

    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = KeywordAnalyzer.class.getInterfaces();
        assertEquals(interfaces[0], Analyzer.class);
    }

    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = KeywordAnalyzer.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }

}

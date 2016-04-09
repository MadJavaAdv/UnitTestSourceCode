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
import java112.analyzer.AnalyzerDriver;

public class AnalyzerDriverTest {

    private static AnalyzerDriver driver;

    @BeforeClass
    public static void initialSetUp() {

        driver = new AnalyzerDriver();

    }

    @AfterClass
    public static void tearDown() {
        driver = null;
    }

    @Test
    public void classExists() {
        assertNotNull(driver);
    }

    @Test
    public void methodOneReturnTest() throws NoSuchMethodException {

        Method method = AnalyzerDriver.class.getMethod("main", String[].class);

        assertEquals(void.class, method.getReturnType());

    }

    @Test
    public void methodOneExistsTest() throws NoSuchMethodException {

        Method method = AnalyzerDriver.class.getMethod("main", String[].class);
        assertNotNull(method);

    }



}

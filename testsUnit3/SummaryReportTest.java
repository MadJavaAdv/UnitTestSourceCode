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
import java112.analyzer.*;

public class SummaryReportTest {

    private static SummaryReport report;
    private static Properties properties;

    @BeforeClass
    public static void initialSetUp() {

        properties = new Properties();
        properties.setProperty("author", "Eric Knapp");
        properties.setProperty("output.dir", "output/");
        properties.setProperty("email", "eknapp@matcmadison.edu");
        properties.setProperty("output.file.summary", "test_summary.txt");

        report = new SummaryReport(properties);
    }

    @AfterClass
    public static void tearDown() {
        report = null;
    }

    @Test
    public void classExists() {
        assertNotNull(report);
    }

    @Test
    public void processTokenReturnVoidTest() throws NoSuchMethodException {
        Method method = SummaryReport.class.getMethod("processToken", String.class);
        assertEquals(void.class, method.getReturnType());
    }

    @Test
    public void processTokenExistsTest() throws NoSuchMethodException {
        Method method = SummaryReport.class.getMethod("processToken", String.class);
        assertNotNull(method);
    }

    @Test
    public void processTokenInitialCount() {
        assertEquals(0, report.getTotalTokensCount());
    }

    @Test
    public void processTokenAddTokenTest() {
        report.processToken("test");
        assertEquals(1, report.getTotalTokensCount());
    }

    @Test
    public void setMethodForTokensCountNotCreatedTest() {
        Method method = null;
        try {
            method = SummaryReport.class.getMethod("setTotalTokensCount", int.class);
        } catch (java.lang.NoSuchMethodException nsme) {
            //no op, we want this to fail
        }
        assertNull(method);
    }

    @Test
    public void implementAnalyzerInterfaceTest() {
        Class[] interfaces = SummaryReport.class.getInterfaces();
        assertEquals(interfaces[0], Analyzer.class);
    }

    @Test
    public void implementOneInterfaceOnlyTest() {
        Class[] interfaces = SummaryReport.class.getInterfaces();
        int size = interfaces.length;
        assertEquals(1, size);
    }

    @Test
    public void constructorExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = SummaryReport.class.getConstructor();
        assertNotNull(constructor);
    }

    @Test
    public void constructorOneParameterExistsTest() throws java.lang.NoSuchMethodException {
        Constructor constructor = SummaryReport.class.getConstructor(Properties.class);
        assertNotNull(constructor);
    }


}

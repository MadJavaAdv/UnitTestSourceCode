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
import java112.labs1.MysteryClassOne;

public class Lab2MysteryClassTest {

    private static MysteryClassOne mysteryClass;

    @BeforeClass
    public static void initialSetUp() {

        mysteryClass = new MysteryClassOne();

    }

    @AfterClass
    public static void tearDown() {
        mysteryClass = null;
    }

    @Test
    public void classExists() {
        assertNotNull(mysteryClass);
    }

    @Test
    public void methodOneReturnTest() throws NoSuchMethodException {

        Method method = MysteryClassOne.class.getMethod("mysteryMethodOne");

        assertEquals(int.class, method.getReturnType());

    }

    @Test
    public void methodOneExistsTest() throws NoSuchMethodException {

        Method method = MysteryClassOne.class.getMethod("mysteryMethodOne");
        assertNotNull(method);

    }

    @Test
    public void runMethodOneTest() {

        int result = mysteryClass.mysteryMethodOne();

        assertEquals(1, result);

    }




}

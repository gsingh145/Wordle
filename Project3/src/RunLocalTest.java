import org.junit.Test;
import org.junit.After;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.Timeout;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Purdue CS 
 * @version June 13, 2022
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Summer 2022</p>
     *
     * @author Purdue CS 
     * @version June 13, 2022
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        @Test(timeout = 1000)
        public void invalidGuessExceptionDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = InvalidGuessException.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `InvalidGuessException` is `public`!", Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `InvalidGuessException` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `InvalidGuessException` extends `Exception`!", Exception.class, superclass);
            Assert.assertEquals("Ensure that `InvalidGuessException` implements no interfaces!", 0, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void invalidWordExceptionDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = InvalidWordException.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `InvalidWordException` is `public`!", Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `InvalidWordException` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `InvalidWordException` extends `Exception`!", Exception.class, superclass);
            Assert.assertEquals("Ensure that `InvalidWordException` implements no interfaces!", 0, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void wordGameClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = WordGame.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `WordGame` is `public`!", Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `WordGame` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `WordGame` extends `Object`!", Object.class, superclass);
            Assert.assertEquals("Ensure that `WordGame` implements no interfaces!", 0, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void wordGuesserClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = WordGuesser.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `WordGuesser` is `public`!", Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `WordGuesser` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `WordGuesser` extends `Object`!", Object.class, superclass);
            Assert.assertEquals("Ensure that `WordGuesser` implements no interfaces!", 0, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void profileClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = WordLibrary.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `WordLibrary` is `public`!", Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `WordLibrary` is NOT `abstract`!", Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `WordLibrary` extends `Object`!", Object.class, superclass);
            Assert.assertEquals("Ensure that `WordLibrary` implements no interfaces!", 0, superinterfaces.length);
        }

        @Test(timeout = 1000)
        public void testExpectedOutput() {

            // Set the input        
            String input = "input.txt" + System.lineSeparator() +
                    "2" + System.lineSeparator();

            // Pair the input with the expected result
            String expected =
                    "Please enter a filename" +
                            System.lineSeparator() +
                            "Ready to play?" +
                            System.lineSeparator() +
                            "1.Yes" +
                            System.lineSeparator() +
                            "2.No" +
                            System.lineSeparator() +
                            "Maybe next time!" +
                            System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            WordGame.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n","\n");
            output = output.replace("\r\n", "\n");
            assertEquals("Make sure your output matches the expected format",
                    expected.trim(), output.trim());
        }



    }

}
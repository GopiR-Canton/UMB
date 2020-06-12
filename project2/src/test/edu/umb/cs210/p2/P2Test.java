package edu.umb.cs210.p2;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static edu.umb.cs210.p2.TestUtils.getInnerClassInstance;
import static edu.umb.cs210.p2.TestUtils.setFieldValue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static edu.umb.cs210.p2.TestUtils.callMethod;
import static edu.umb.cs210.p2.TestUtils.getClassInstance;

public class P2Test {
    private static final String packageName = "edu.umb.cs210.p2";
    private static final String packagePath = "src/main/edu/umb/cs210/p2/";
    private static final String thisFile = "P2Test";
    private static final ByteArrayOutputStream OUT;

    /*
      This is a static initializer. The code inside this block gets executed
      the first time a static field or method in this class is referenced,
      unless an instance of this class has already been initialized. It's like
      a constructor for static fields, but also gets called before a regular
      constructor.
     */
    static {
        TestUtils.setPackageName(packageName);
        OUT = new ByteArrayOutputStream();
        System.setOut(new PrintStream(OUT));
    }

    // Tests are in reverse order here so they appear in the correct order
    // in output. TODO: Find a better way to do that.

    /* ********************************************************************** */
    /* **************************** Style Tests ***************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> checkStyleArguments() {
        return Stream.of(
                arguments("BinaryStrings.java"),
                arguments("Primes.java"),
                arguments("MinMax.java"),
                arguments("Buffer.java"),
                arguments("Josephus.java"),
                arguments("LinkedDeque.java"),
                arguments("ResizingArrayRandomQueue.java"),
                arguments("Subset.java")
        );
    }



    /* ********************************************************************** */
    /* *************************** SubsetTest ******************************* */
    /* ********************************************************************** */
    public static Stream<Arguments> problem3Arguments() {
        return Stream.of(arguments("3", "data/input10.txt"));
    }

    @Nested
    @DisplayName("Problem3: Subset")
    public class Problem3 {
        @DisplayName("Testing (Use Gradescope for full test)...")
        @ParameterizedTest(name = "k={0}, file={1}")
        @MethodSource(packageName+"."+thisFile+"#problem3Arguments")
        public void problem3_test(String kay, String fileName) {
            In in = new In(fileName);
            LinkedList<String> src = new LinkedList<>();
            while (!in.isEmpty()) src.add(in.readString());
            String[] source = src.toArray(new String[0]);

            int k = Integer.parseInt(kay);
            Class<?>[] params = new Class<?>[] {String[].class};
            Object[] args = new Object[]{new String[]{kay,fileName}};

            Object act = callMethod("Subset","subset", params, args);

            String[] actual = new String[k];

            for (int i = 0; i < k; i++) actual[i] = callMethod(act, "dequeue").toString();

            String msg = "Result does not appear to be a subset of the input. "
                    + "The random nature of this class makes this test "
                    + "unstable. Upload to Gradescope for a more accurate test.";

            boolean found = false;
            for (String s : actual) {
                found = false;
                for (int j = 0; j < source.length; j++) {
                    if (s.equals(source[j])) {
                        source[j] = null;
                        found = true;
                        break;
                    }
                }
                if (!found) fail(msg);
            }
            assertTrue(found, msg);
        }
    }

    /* ********************************************************************** */
    /* ******************** ResizingArrayRandomQueueTest ******************** */
    /* ********************************************************************** */
    public static Stream<Arguments> problem2Arguments() {
        return Stream.of(
                arguments("iterator", new Integer[]{1,2,3,4,5,6,7,8,9,10,11}, new Class<?>[]{Object.class})
        );
    }

    @Nested
    @DisplayName("Problem2: ResizingArrayRandomQueue")
    public class Problem2 {
        @DisplayName("Testing...")
        @ParameterizedTest(name = "Method: {0}, Data: {1}")
        @MethodSource(packageName+"."+thisFile+"#problem2Arguments")
        public void problem2_test(String methodName, Object[] args, Class<?>[] params) {
            String msg;
            Object actualRQ = getClassInstance("ResizingArrayRandomQueue");
            for (Object o : args) callMethod(actualRQ, "enqueue", params, new Object[]{o});

            Object[] actualDQ = new Object[args.length];

            Iterator actualIter = (Iterator) callMethod(actualRQ, methodName);
            int i = 0;
            while (actualIter.hasNext()) actualDQ[i++] = actualIter.next();
            msg = "Unexpected iteration ordering. "
                    + "The random nature of this class makes this test "
                    + "unstable. Upload to Gradescope for a more accurate test.";
            int same = 0;
            for (int j = 0; j < actualDQ.length; j++) {
                if (actualDQ[j].equals(args[j])) same++;
            }
            if (same > 3) fail(msg);
            same = 0;
            for (int j = 0; j < actualDQ.length; j++) {
                if (actualDQ[j].equals(args[args.length-1-j])) same++;
            }
            if (same > 3) fail(msg);
        }
    }

    /* ********************************************************************** */
    /* ************************* LinkedDequeTest **************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> problem1Arguments() {
        String quote1 = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        return Stream.of(arguments(quote1, "iterator"));
    }

    @Nested
    @DisplayName("Problem1: LinkedDeque")
    public class Problem1 {
        @DisplayName("Testing...")
        @ParameterizedTest(name = "Testing {1} with input \"{0}\".")
        @MethodSource(packageName+"."+thisFile+"#problem1Arguments")
        public void problem1_test(String quote, String methodName) {
            Object actualLD = getClassInstance("LinkedDeque");
            Class<?>[] params = new Class<?>[]{Object.class};
            String msg = String.format("Method %s() returned unexpected "
                    + "value(s), sampled from several instances during Item "
                    + "removal. ", methodName);

            int r = StdRandom.uniform(0, quote.length());
            for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
                callMethod(actualLD, "addFirst", params, new Object[]{quote.charAt(i)});
            }
            for (int i = 0; i < quote.substring(r).length(); i++) {
                callMethod(actualLD, "addLast", params, new Object[]{quote.charAt(r + i)});
            }
            StringBuilder actualSB = new StringBuilder();
            Iterator actualIter = (Iterator) callMethod(actualLD, "iterator");
            while (actualIter.hasNext()) actualSB.append(actualIter.next());

            assertEquals(quote, actualSB.toString(), msg);
        }
    }

    /* ********************************************************************** */
    /* *************************** JosephusTest ***************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> josephusArguments() {
        return Stream.of(arguments(new String[]{"20","3"}, "calculateJosephus"));
    }
    @Nested
    @DisplayName("Exercise5: Josephus")
    public class Exercise5 {
        @DisplayName("Testing with args...")
        @ParameterizedTest(name = "[N, M]={0}")
        @MethodSource(packageName+"."+thisFile+"#josephusArguments")
        public void josephusTest(String[] args, String methodName) {
            Object actualQ = callMethod("Josephus"   , methodName, new Class<?>[] {String[].class}, new Object[] {args});
            assertNotNull(actualQ, "calculateJosephus() returned null");

            Iterator actualIter = ((LinkedQueue) actualQ).iterator();
            StringBuilder actualSB = new StringBuilder();
            while (actualIter.hasNext()) actualSB.append(actualIter.next() + " ");
            String msg = "Elimination sequence does not match expected sequence.";
            String expected = "3 6 9 12 15 18 1 5 10 14 19 4 11 17 7 16 8 2 13 20 ";
            assertEquals(expected, actualSB.toString(), msg);
        }
    }

    /* ********************************************************************** */
    /* **************************** BufferTest ****************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> bufferArguments() {
        String quote1 = "There is grandeur in this view of life, "
                + "with its several powers, having been originally "
                + "breathed into a few forms or into one; and that, "
                + "whilst this planet has gone cycling on according "
                + "to the fixed law of gravity, from so simple a "
                + "beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        return Stream.of(arguments(quote1, "toString"));
    }
    @Nested
    @DisplayName("Exercise4: Buffer")
    public class Exercise4 {
        @DisplayName("Testing:")
        @ParameterizedTest(name = "Method: {1}, quote: {0}")
        @MethodSource(packageName+"."+thisFile+"#bufferArguments")
        public void bufferTest(String quote, String methodName) {
            String msg = "This test uses every method in the Buffer class. If any of those don't work, this test fails.";

            Class<?>[] params = new Class<?>[]{char.class};
            Object actualBuf = getClassInstance("Buffer");

            for (int i = 0; i < quote.length(); i++) {
                callMethod(actualBuf, "insert", params, new Object[]{quote.charAt(i)});
            }
            callMethod(actualBuf, "left", new Class<?>[]{int.class}, new Object[]{callMethod(actualBuf, "size")});
            callMethod(actualBuf, "right", new Class<?>[]{int.class}, new Object[]{97});
            String s = "by the Creator ";
            for (int i = 0; i < s.length(); i++) {
                callMethod(actualBuf, "insert", params, new Object[]{s.charAt(i)});
            }
            callMethod(actualBuf, "right", new Class<?>[]{int.class}, new Object[]{229});
            callMethod(actualBuf, "delete");
            callMethod(actualBuf, "insert", params, new Object[]{'-'});
            callMethod(actualBuf, "insert", params, new Object[]{'-'});
            callMethod(actualBuf, "left", new Class<?>[]{int.class}, new Object[]{342});
            Object actual = callMethod(actualBuf, methodName);

            String expect = "|There is grandeur in this view of life, with its several powers, having been originally breathed by the Creator into a few forms or into one; and that, whilst this planet has gone cycling on according to the fixed law of gravity, from so simple a beginning endless forms most beautiful and most wonderful have been, and are being, evolved. -- Charles Darwin, The Origin of Species";
            assertEquals(expect, actual, msg);
        }
    }

    /* ********************************************************************** */
    /* *************************** MinMaxTest ******************************* */
    /* ********************************************************************** */
    public static Stream<Arguments> minMaxArguments() {
        return Stream.of(arguments((Object) new Random().ints(1000, -10000, 10000).toArray()));
    }
    @Nested
    @DisplayName("Exercise3: MinMax")
    public class Exercise3 {
        @DisplayName("Testing with inputs...")
        @ParameterizedTest(name = "{0}")
        @MethodSource(packageName+"."+thisFile+"#minMaxArguments")
        public void minMaxTest(int[] values) {

            Class<?>[] actualParam = new Class<?>[]{TestUtils.getClass("MinMax$Node")};

            Object actualList = null;
            if (values.length >= 1) {
                for (int i : values) {
                    Object actualPrev = actualList;
                    actualList = getInnerClassInstance("MinMax", "Node");
                    setFieldValue(actualList, "item", i);
                    setFieldValue(actualList, "next", actualPrev);
                }
            }
            Integer expect = StdStats.min(values);
            Object actual = callMethod("MinMax", "min", actualParam, new Object[]{actualList});
            assertEquals(expect, actual, "min() returned incorrect value.");

            expect = StdStats.max(values);
            actual = callMethod("MinMax", "max", actualParam, new Object[]{actualList});
            assertEquals(expect, actual, "max() returned incorrect value.");
        }
    }

    /* ********************************************************************** */
    /* *************************** PrimesTest ******************************* */
    /* ********************************************************************** */
    public static Stream<Arguments> exercise2Arguments() {
        return Stream.of(arguments(10));
    }
    @Nested
    @DisplayName("Exercise2: Primes")
    public class Exercise2 {
        @DisplayName("Testing...")
        @ParameterizedTest(name="n={0}")
        @MethodSource(packageName+"."+thisFile+"#exercise2Arguments")
        public void exercise2_test(int n) {
            Object actualObj = getClassInstance("Primes", new Class<?>[]{int.class}, new Object[]{n});

            Iterator actualIter = (Iterator) callMethod(actualObj, "iterator");

            StringBuilder actualSB = new StringBuilder();

            while (actualIter.hasNext()) actualSB.append(actualIter.next() + " ");
            String expect = "2 3 5 7 11 13 17 19 23 29 ";
            String msg = "Primes list does not match expected values.";
            assertEquals(expect, actualSB.toString(), msg);
        }
    }

    /* ********************************************************************** */
    /* ************************ BinaryStringsTest *************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> exercise1Arguments() {
        return Stream.of(arguments(4));
    }
    @Nested
    @DisplayName("Exercise1: BinaryStrings")
    public class Exercise1 {
        @DisplayName("Testing binary strings of length...")
        @ParameterizedTest(name="n={0}")
        @MethodSource(packageName+"."+thisFile+"#exercise1Arguments")
        public void exercise1_test(int n) {
            Object actualObj = getClassInstance("BinaryStrings"   , new Class<?>[]{int.class}, new Object[]{n});

            Iterator actualIter = (Iterator) callMethod(actualObj, "iterator");

            StringBuilder actualSB = new StringBuilder();

            while (actualIter.hasNext()) actualSB.append(actualIter.next() + " ");
            String expect = "0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111 ";
            String msg = "Binary Strings list does not match expected values.";
            assertEquals(expect, actualSB.toString(), msg);
        }
    }
}

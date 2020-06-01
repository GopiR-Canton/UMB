package edu.umb.cs210.p1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static edu.umb.cs210.p1.TestUtils.callMethod;
import static edu.umb.cs210.p1.TestUtils.getClassInstance;

public class P1Test {
    private static final String packageName = "edu.umb.cs210.p1";
    private static final String packagePath = "src/main/edu/umb/cs210/p1/";

    /*
      This is a static initializer. The code inside this block gets executed
      the first time a static field or method in this class is referenced,
      unless an instance of this class has already been initialized. It's like
      a constructor for static fields, but also gets called before a regular
      constructor.
     */
    static {
        TestUtils.setPackageName(packageName);
    }

    /* ********************************************************************** */
    /* ********************** PercolationStatsTest ************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> percolationStatsArguments() {
        return Stream.of(
                arguments("mean"),
                arguments("confidenceLow"),
                arguments("confidenceHigh")
        );
    }

    @Nested
    @DisplayName("Problem2: PercolationStats")
    public class Problem2 {
        @ParameterizedTest(name = "method {0}()")
        @DisplayName("Checking:")
        @MethodSource(packageName+".P1Test#percolationStatsArguments")
        public void percolationStats_test(String methodName) {
            int N = 50;
            int T = 10000;

            Object actualPercS = getClassInstance("PercolationStats",
                    new Class<?>[]{int.class,int.class}, new Object[]{N,T});

            String expect = "0.59";
            String actual = String.format("%.2f",
                    (Double) callMethod(actualPercS, methodName));

            String msg = "Method returned an incorrect value. Make sure to "
                    + "use the StdStats library and double-check your "
                    + "calculations in the PercolationStats constructor. It's "
                    + "also possible that errors propagate from the "
                    + "Percolation class";
            assertEquals(expect, actual, msg);
        }
    }

    /* ********************************************************************** */
    /* ************************* PercolationTest **************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> percolationMethodArguments() {
        Class<?>[] twoInt = new Class<?>[]{int.class,int.class};
        Class<?>[] noParam = new Class<?>[0];
        Object[] noArg = new Object[0];
        return Stream.of(
                arguments("data/input10.txt", "numberOfOpenSites", 56),
                arguments("data/input10-no.txt", "numberOfOpenSites", 55),
                arguments("data/input10.txt", "percolates", true),
                arguments("data/input10-no.txt", "percolates", false)
        );
    }

    @Nested
    @DisplayName("Problem1: Percolation")
    public class Problem1 {
        @ParameterizedTest(name = "Testing {1} with file: {0}")
        @DisplayName("Testing methods")
        @MethodSource(packageName+".P1Test#percolationMethodArguments")
        public void percolation_method_test(String inputFile, String methodName,
                                            Object expect) {
            In in = new In(inputFile);
            int N = in.readInt();

            Object perc = getClassInstance("Percolation",
                    new Class<?>[]{int.class}, new Object[]{N});

            while (!in.isEmpty()) {
                int i = in.readInt();
                int j = in.readInt();
                callMethod(perc, "open",
                        new Class<?>[]{int.class,int.class}, new Object[]{i,j});
            }
            Object actual = callMethod(perc, methodName);

            String msg = "Method does not produce expected output.";
            assertEquals(expect.toString(), actual.toString(), msg);
        }
    }

    /* ********************************************************************** */
    /* *************************** HarmonicTest ***************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> harmonicArguments() {
        return Stream.of(
                arguments(new String[]{"10"}, "harmonicSum")
        );
    }
    @Nested
    @DisplayName("Exercise6: Harmonic")
    public class Exercise6 {
        @ParameterizedTest(name = "The {0}th harmonic number")
        @DisplayName("Testing:")
        @MethodSource(packageName+".P1Test#harmonicArguments")
        public void harmonic_test(String[] input, String methodName) {
            Object actualSum = callMethod("Harmonic", methodName,
                    new Class<?>[] {String[].class}, new Object[] {input});

            String expect = "7381/2520";
            String actual = (String) callMethod(actualSum,"toString");

            String msg = "Resulting Rational does not match expected Rational. "
                    + "Follow the given hints closely.";
            assertEquals(expect, actual, msg);
        }
    }

    /* ********************************************************************** */
    /* *************************** RationalTest ***************************** */
    /* ********************************************************************** */

    @Nested
    @DisplayName("Exercise5: Rational")
    public class Exercise5 {
        @Test
        @DisplayName("Testing all methods, except main")
        public void rationalTest() {
            Object total, term, frac, expect;
            int n = 10;

            Class<?>[] oneLong = new Class<?>[] {long.class};
            Class<?>[] twoLong = new Class<?>[] {long.class,long.class};
            Class<?>[] param = new Class<?>[] {TestUtils.getClass("Rational")};

            total = getClassInstance("Rational", oneLong, new Object[]{0});
            term = getClassInstance("Rational", oneLong, new Object[]{1});
            frac = getClassInstance("Rational", twoLong, new Object[]{1,2});

            long x = (long) Math.pow(2, n) - 1;
            long y = (long) Math.pow(2, n - 1);
            expect = getClassInstance("Rational", twoLong, new Object[]{x,y});

            for (int i = 1; i <= n; i++) {
                total = callMethod(total, "add", param, new Object[] {term});
                term = callMethod(term, "multiply", param, new Object[] {frac});
            }

            String msg = "Resulting Rational does not match expected Rational. "
                    + "Follow the given hints closely.";

            assertEquals(expect.toString(), total.toString(), msg);
        }
    }

    /* ********************************************************************** */
    /* ************************** TransposeTest ***************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> transposeArguments() {
        return Stream.of(
                arguments((Object) new double[][]{{1,2,3,4},{5,6,7,8}},
                          (Object) new double[][]{{1,5},{2,6},{3,7},{4,8}})
        );
    }
    @Nested
    @DisplayName("Exercise4: Transpose")
    public class Exercise4 {
        @Test
        @DisplayName("Testing transpose():")
        public void transpose_test() {
            String msg = "Calculation does not match expected value. Follow "
                    + "the given hints closely. Did you set m and n correctly?";
            double[][] actual = (double[][]) callMethod("Transpose",
                    "transpose", new Class<?>[] {double[][].class},
                    new Object[]{new double[][]{{1,2,3,4},{5,6,7,8}}});
            double[][] expect = new double[][]{{1,5},{2,6},{3,7},{4,8}};
            assertArrayEquals(expect, actual, msg);
        }
    }

    /* ********************************************************************** */
    /* *************************** DistanceTest ***************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> distanceArguments() {
        return Stream.of(
                arguments(new double[]{-9.0, 1.0, 10.0, -1.0, 1.0},
                          new double[]{-5.0, 9.0,  6.0,  7.0, 4.0})
        );
    }
    @Nested
    @DisplayName("Exercise3: Distance")
    public class Exercise3 {
        @ParameterizedTest(name="Between {0} and {1}")
        @DisplayName("Testing distance():")
        @MethodSource(packageName+".P1Test#distanceArguments")
        public void distance_test(double[] inputX, double[] inputY) {
            String msg = "Calculation does not match expected value. Follow "
                    + "the given hints closely.";
            double expect = 13.0;
            double actual = (double)callMethod("Distance", "distance",
                    new Class<?>[] {double[].class, double[].class},
                    new Object[] {inputX, inputY});
            assertEquals(expect, actual, msg);
        }
    }

    /* ********************************************************************** */
    /* ************************* PrimeCounterTest *************************** */
    /* ********************************************************************** */
    public static IntStream primeCounterArguments() {
        return IntStream.of(100, 1000000);
    }
    @Nested
    @DisplayName("Exercise2: PrimeCounter")
    public class Exercise2 {

        @DisplayName("Testing primes():")
        @MethodSource(packageName+".P1Test#primeCounterArguments")
        @ParameterizedTest(name="Checking {0}")
        public void primes_test(int input) {
            String msg = "Calculation does not match expected value. "
                    + "Follow the given hints closely.";
            int expect = input == 100 ? 25 : 78498;
            int actual = (int) callMethod("PrimeCounter", "primes",
                    new Class<?>[] {int.class}, new Object[] {input});
            assertEquals(expect, actual, msg);
        }
    }

    /* ********************************************************************** */
    /* ************************** GreatCircleTest *************************** */
    /* ********************************************************************** */
    public static Stream<Arguments> greatCircleArguments() {
        return Stream.of(
            arguments("48.87 -2.33 37.8 -122.4", "London","San Francisco")
        );
    }
    @Nested
    @DisplayName("Exercise1: GreatCircle")
    public class Exercise1 {

        @DisplayName("Testing the distance between")
        @MethodSource(packageName+".P1Test#greatCircleArguments")
        @ParameterizedTest(name="{1} and {2}")
        public void calculateGreatCircleDistanceTest(String coords, String from, String to) {
            String[] args = coords.split(" ");
            String msg = "Calculation does not match expected value. Did you "
                    + "remember to convert back to degrees?";
            double expect = 8701.389543238289;
            double actual = (double) callMethod("GreatCircle",
                    "calculateGreatCircleDistance",
                    new Class<?>[] {String[].class}, new Object[] {args});
            assertEquals(expect, actual, msg);
        }
    }
}

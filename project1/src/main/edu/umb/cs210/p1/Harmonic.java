package edu.umb.cs210.p1;

import edu.princeton.cs.algs4.StdOut;

public class Harmonic {
    // Computes the nth harmonic number, where n is passed in through args
    protected static Rational harmonicSum(String[] args) {
        // Get n from command line as integer.
        int n = Integer.parseInt(args[0]);

        // Set total to the rational number 0.
        Rational total = new Rational(0);

        // For each 1 <= i <= n, add the rational term
        // 1 / i to total.
        for (int i = 1; i <= n ; i++) {
            total = total.add(new Rational(1, i));
        }

         return total;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        StdOut.println(Harmonic.harmonicSum(args));
    }
}

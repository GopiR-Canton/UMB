package edu.umb.cs210.p1;

import edu.princeton.cs.algs4.StdOut;

public class PrimeCounter {
    // Checks if x is prime
    protected static boolean isPrime(int x) {
        // For each 2 <= i <= sqrt(x), if x is divisible by
        // i, then x is not a prime. If no such i exists,
        // x is a prime.

        return true;
    }

    // Returns the number of primes <= N.
    protected static int primes(int N) {
        // For each 2 <= i <= N, use isPrime() to test if
        // i is prime, and if so increment a count. At the
        // end return count.

        return 0;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdOut.println(primes(N));
    }
}

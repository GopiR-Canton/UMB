package edu.umb.cs210.p2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

// An immutable data type to systematically iterate over the
// first n primes.
public class Primes implements Iterable<Integer> {
    private final int n; // need first n primes

    // Construct an iterable Primes object given the number
    // of primes needed.
    public Primes(int n) {
        this.n = n;
    }

    // A PrimesIterator object.
    @Override
    public Iterator<Integer> iterator() {
        return new PrimesIterator(n);
    }

    // Primes iterator.
    private class PrimesIterator implements Iterator<Integer> {
        // number of primes returned
        int numPrimesReturned = 0;
        int totalPrimesNeeded = 0;
        // current prime
        int p;

        // Construct a PrimesIterator object.
        public PrimesIterator(int totalPrimesNeeded) {
            this.totalPrimesNeeded = totalPrimesNeeded;
            this.p = 1;
        }

        // Are there anymore primes left to be iterated?
        public boolean hasNext(){
            if (numPrimesReturned < totalPrimesNeeded) {
                return true;
            }

            return false;
        }

        // The next prime.
        public Integer next() {
            // Increment count by 1.
            numPrimesReturned++;

            // As long as p is not prime, increment p by 1.
            p++;

            // Return current value of p and increment it
            // by 1.
            while (!isPrime(p)){
                p++;
            }

            return p;
        }

        // Remove is not supported.
        public void remove() {
            throw new UnsupportedOperationException();
        }

        // Is x (>= 2) prime?
        private boolean isPrime(int x) {
            for (int i = 2; i <= x / i; i++) {
                if (x % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (int i : new Primes(n)) {
            StdOut.println(i);
        }
    }
}

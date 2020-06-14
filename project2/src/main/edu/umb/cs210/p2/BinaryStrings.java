package edu.umb.cs210.p2;


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

// An immutable data type to systematically iterate over binary
// strings of length n.
public class BinaryStrings implements Iterable<String> {
    private final int n; // need all binary strings of length n

    //Constructor:  Construct an iterable BinaryStrings object given the length
    // of binary strings needed.
    public BinaryStrings(int n) {
        this.n = n;
    }

    // A BinaryStringsIterator object.
    public Iterator<String> iterator() {
        return new BinaryStringsIterator(n);
    }

    // Binary strings iterator.
    private class BinaryStringsIterator implements Iterator<String> {
        // number of binary strings returned
        int numBinaryStringsReturned = 0;
        int lengthOfBinaryString = 0;
        // current number
        int p;

        // Constructor Construct a BinaryStringsIterator object.

        public BinaryStringsIterator(int lengthOfBinaryString) {
            this.lengthOfBinaryString = lengthOfBinaryString;
            this.p = 0;
        }

        // Are there anymore binary strings left to be iterated?
        public boolean hasNext() {
            if (binary(p).length() <= lengthOfBinaryString){
                return true;
            }

            return false;
        }

        // The next binary string.
        public String next() {
            numBinaryStringsReturned++;

            String binaryValue = binary(p);

            p++;

            return binaryValue;
        }

        // Remove is not supported.
        public void remove() {
            throw new UnsupportedOperationException();
        }

        // The n-bit representation of x.
        private String binary(int x) {
            String s = Integer.toBinaryString(x);
            int padding = n - s.length();
            for (int i = 1; i <= padding; i++) {
                s = "0" + s;
            }
            return s;
        }
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (String s : new BinaryStrings(n)) {
            StdOut.println(s);
        }
    }
}

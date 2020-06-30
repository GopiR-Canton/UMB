package edu.umb.cs210.p4;


import edu.princeton.cs.algs4.MinPQ;

public class Ramanujan2 {
    // A data type that encapsulates a pair of numbers (i, j) 
    // and the sum of their cubes, ie, i^3 + j^3.
    private static class Pair implements Comparable<Pair> {
        private int i;          // first element of the pair
        private int j;          // second element of the pair
        private int sumOfCubes; // i^3 + j^3

        // Construct a pair (i, j).
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
            sumOfCubes = i * i * i + j * j * j;
        }

        // Compare this pair to the other by sumOfCubes.
        public int compareTo(Pair other) {
            return sumOfCubes - other.sumOfCubes;
        }

        public int getJ() {
            return j;
        }

        public int getI() {
            return i;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "i=" + i +
                    ", j=" + j +
                    ", sumOfCubes=" + sumOfCubes +
                    '}';
        }
    }


    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        MinPQ<Pair> minPQ = new MinPQ<>();

    }
}

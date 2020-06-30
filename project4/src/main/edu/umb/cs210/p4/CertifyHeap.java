package edu.umb.cs210.p4;


import edu.princeton.cs.algs4.StdOut;

public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    private static <T extends Comparable<T>> boolean less(T v, T w) {

        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap
    // and false otherwise.
    protected static <T extends Comparable<T>> boolean maxOrderedHeap(T[] a) {

        // For each node 1 <= i <= N / 2, if i is less than
        // either of its children, return false, meaning a[]
        // does not represent a maximum-ordered heap.
        // Otherwise, return true.(Shifting the starting index from 1 to 0)
        int N = a.length;

        for (int i = 0; i < N - 1; i++) {
            if (i == N - 2){
                if (a[i].compareTo(a[i+1]) < 0){
                    return false;
                }
            }
            else {
                if (i < N - 2) {
                    if (a[i].compareTo(a[i + 1]) < 0 || a[i].compareTo(a[i + 2]) < 0) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[][] a = new String[2][];
        a[0] = "T H R P S O A E I N G".split(" ");
        a[1] = "T S R P N O A E I H G".split(" ");

        for (int i = 0; i < a.length; i++) {
            StdOut.println(maxOrderedHeap(a[i]));
        }
    }
}

package edu.umb.cs210.p2;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class MinMax {
    // Linked list class.
    private static class Node {
        public int item;
        public Node next;
    }

    // Return the minimum value in the given linked list.
    public static int min(Node first) {
        // Set min to the largest integer
        int min = Integer.MAX_VALUE;

        // Compare each element in linked list with min and
        // if it is smaller, update min.


        // Return min
        return min;
    }

    // Return the maximum value in the given linked list.
    public static int max(Node first) {
        // Set max to the smallest integer
        int max = Integer.MIN_VALUE;
        
        // Compare each element in linked list with max and
        // if it is larger, update max.

        // Return max
        return max;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int[] items = new int[1000];
        for (int i = 0; i < 1000; i++) {
            items[i] = StdRandom.uniform(-10000, 10000);
        }
        Node first = null; 
        for (int item : items) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }
        StdOut.println(min(first) == StdStats.min(items)
                       && max(first) == StdStats.max(items));
    }
}

package edu.umb.cs210.p2;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    protected static ResizingArrayRandomQueue<String> subset(String[] args) {
        ResizingArrayRandomQueue<String> q = new ResizingArrayRandomQueue();
        String filename = args[1];

        In in = new In(filename);
        String N = in.readString();
        q.enqueue(N);

        while (!in.isEmpty()) {
            String i = in.readString();
            q.enqueue(i);
        }
        return q;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        ResizingArrayRandomQueue<String> q = subset(args);
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}

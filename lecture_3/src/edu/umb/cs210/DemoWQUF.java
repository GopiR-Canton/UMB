package edu.umb.cs210;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DemoWQUF {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF wuf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            wuf.union(p, q);
            StdOut.println("Uinon:" + p + " " + q);
            for (int i = 0; i < n; i++) {
                System.out.print(wuf.find(i)+"\t");
            }
            System.out.println();

        }

    }
}

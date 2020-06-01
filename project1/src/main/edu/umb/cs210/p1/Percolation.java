package edu.umb.cs210.p1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

// Models an N-by-N percolation system.
public class Percolation {


    // blocked: false
    // open: true
    // Creates an N-by-N grid(Boolean), with all sites blocked.
    public Percolation(int N) {

        }

    // Opens site (i, j) if it is not open already.
    public void open(int i, int j) {


    }

    // Checks if site (i, j) is open.
    public boolean isOpen(int i, int j) {
        return false;
    }

    // Checks if site (i, j) is full.
    public boolean isFull(int i, int j) {
        return false;
    }


    // Returns the number of open sites.
    public int numberOfOpenSites() {
        return 0;
    }

    // Checks if the system percolates.
    public boolean percolates() {

        return false;
    }

    // Returns an integer ID (1...N) for site (i, j).
    protected int encode(int i, int j) {
        return 0;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }

        for (int k = 0; k < N; k++) {
            if (perc.isFull(N-1 , k))
                System.out.println(perc.encode(N-1,k)+":");;
        }

        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}

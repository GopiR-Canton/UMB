package edu.umb.cs210.p1;

import edu.princeton.cs.algs4.StdArrayIO;

public class Transpose {
    // Returns a new matrix that is the transpose of x.
    protected static double[][] transpose(double[][] x) {
        // Create a new 2D matrix t (for transpose) with
        // dimensions n x m, where m x n are the dimensions
        // of x.
        double[][] t = new double[x[0].length][x.length];

        // For each 0 <= i < m and 0 <= j < n, set t[j][i]
        // to x[i][j].
        for (int row = 0; row < x.length; row++) {
            for (int col = 0; col < x[1].length; col++) {
                t[col][row] = x[row][col];
            }
        }

        // Return t.
        return t;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        double[][] x = StdArrayIO.readDouble2D();
        StdArrayIO.print(transpose(x));
    }
}

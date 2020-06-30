package edu.umb.cs210.p4;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

// Models a board in the 8-puzzle game or its generalization.
public class Board {
    private final int[][] tiles;    // tiles in the board
    private int N;                  // board size


    // Construct a board from an N-by-N array of tiles, where 
    // tiles[i][j] = tile at row i and column j, and 0 represents the blank 
    // square.
    public Board(int[][] tiles) {
        this.tiles = cloneTiles();
    }

    // Tile at row i and column j.
    public int tileAt(int i, int j) {
        return tiles[i][j];
    }

    // Size of this board.
    public int size() {
        return N;
    }

    // Number of tiles out of place.
    public int hamming() {

        return 0;
    }

    // Sum of Manhattan distances between tiles and goal.
    public int manhattan() {

        return 0;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        return false;
    }

    // Is this board solvable?
    public boolean isSolvable() {
        return false;
    }

    // Does this board equal that?
    public boolean equals(Object that) {
        return false;
    }

    // All neighboring boards.
    public Iterable<Board> neighbors() {

        LinkedQueue<Board> boards = new LinkedQueue<Board>();


        return boards;
    }

    // String representation of this board.
    public String toString() {
        StringBuilder s = new StringBuilder(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d", tiles[i][j]));
                if (j < N - 1) {
                    s.append(" ");
                }
            }
            if (i < N - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    // Helper method that returns the position (in row-major order) of the 
    // blank (zero) tile.
    private int blankPos() {
        return 0;
    }

    // Helper method that returns the number of inversions.
    private int inversions() {
        int num = 0;

        return num;


    }

    // Helper method that clones the tiles[][] array in this board and 
    // returns it.  (Do not use array.clone methods)
    private int[][] cloneTiles() {
        int[][] a = new int[N][N];

        return a;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        Board board = new Board(tiles);
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        StdOut.println(board.isGoal());
        StdOut.println(board.isSolvable());
        for (Board neighbor : board.neighbors()) {
            StdOut.println(neighbor);
        }
    }
}

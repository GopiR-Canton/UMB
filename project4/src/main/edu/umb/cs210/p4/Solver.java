package edu.umb.cs210.p4;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

// A solver based on the A* algorithm for the 8-puzzle and its generalizations.
public class Solver {
    private LinkedStack<Board> solution = new LinkedStack<>();
    private int moves;

    // Helper search node class.
    private class SearchNode {

        private Board board;
        private int moves;
        SearchNode pre;

        // Construct a new SearchNode
        public SearchNode(Board board, int moves, SearchNode pre) {
            this.board = board;
            this.moves = moves;
            this.pre = pre;
        }
    }

    // Find a solution to the initial board (using the A* algorithm).
    public Solver(Board initial) {
    // when we stop we have the node with the goal board
        // so for this node and ite pre node and pre unit null
        //push them into a stack
        // and the stack is our solution which has the node from initial board
        // to the goal board
    }

    // The minimum number of moves to solve the initial board.
    public int moves() {
        return 1;
    }

    // Sequence of boards in a shortest solution.
    public Iterable<Board> solution() {
        return null;
    }

    // Helper hamming priority function comparator.
    private static class HammingOrder implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode a, SearchNode b) {
            return 1;
        }
    }

    // Helper manhattan priority function comparator.
    private static class ManhattanOrder implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode a, SearchNode b) {
            return 1;
        }
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
        Board initial = new Board(tiles);
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        } else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}

package edu.umb.cs210.p1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

// Models an N-by-N percolation system.
public class Percolation {
    private Site[][] sites;
    private int gridSize = 0;
    private int numOfOpenSites = 0;

    class Site {
        public boolean isOpen = false;
        public boolean isFull = false;
    }

    // blocked: false
    // open: true
    // Creates an N-by-N grid(Boolean), with all sites blocked.
    public Percolation(int N) {
        gridSize = N;
        sites = new Site[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Site site = new Site();
                site.isOpen = false;
                site.isFull = false;
                sites[i][j] = site;
            }
        }
    }

    // Opens site (i, j) if it is not open already.
    public void open(int i, int j) {
        if (!isOpen(i, j)) {
            // setting a site to true marks it as open
            sites[i][j].isOpen = true;

            // by default, set sites in the top rows as full sites
            if (i == 0) {
                setFull(i, j);
            }

            numOfOpenSites++;
        }
    }

    private void setFull(int i, int j) {
        if (sites[i][j].isOpen && !sites[i][j].isFull) {
            sites[i][j].isFull = true;

            for (int moveDown = i; moveDown < gridSize - 1; moveDown++) {
                if (moveDown > i && sites[moveDown - 1][j].isFull && !sites[moveDown][j].isFull) {
                    setFull(moveDown, j);
                }
            }

            for (int moveRight = j; moveRight < gridSize - 1; moveRight++) {
                if (moveRight > j && sites[i][moveRight].isFull && !sites[i][moveRight + 1].isFull) {
                    setFull(i, moveRight+1);
                }
            }

            for (int moveLeft = j; moveLeft > 0; moveLeft--) {
                if (moveLeft < j && sites[i][moveLeft].isFull && !sites[i][moveLeft - 1].isFull) {
                    setFull(i, moveLeft - 1);
                }
            }
        }
    }

    // Checks if site (i, j) is open.
    public boolean isOpen(int i, int j) {
        return sites[i][j].isOpen;
    }

    // Checks if site (i, j) is full.
    public boolean isFull(int siteRow, int siteCol) {
        // A full site is an open site that can be connected to an open site
        // in the top row via a chain of neighboring (left, right, up, down) open sites.
        if (siteRow == 0) {
            return sites[siteRow][siteCol].isFull;
        }

        for (int row = siteRow; row < 0; row--) {
            for (int col = siteCol; col < 0; col++) {

            }
        }

        return sites[siteRow][siteCol].isFull;
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // Checks if the system percolates.
    public boolean percolates() {
        // If there is a full site in the bottom row, then we say that the system percolates.
        int bottomRow = gridSize - 1;
        for (int col = 0; col < gridSize; col++) {
            if (isFull(bottomRow, col)) {
                return true;
            }
        }

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
            if (perc.isFull(N - 1, k))
                System.out.println(perc.encode(N - 1, k) + ":");
            ;
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

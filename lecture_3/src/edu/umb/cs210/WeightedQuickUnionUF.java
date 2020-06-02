/******************************************************************************
 Compilation:
 *  javac -cp  /home/haoyu/lib/algs4.jar -d out/production/lecture_3/ src/edu/umb/cs210/WeightedQuickUnionUF.java
 *  Execution:
 *  java -cp  /home/haoyu/lib/algs4.jar:out/production/lecture_3/ edu.umb.cs210.WeightedQuickUnionUF< Data/input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                https://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                https://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Weighted quick-union (without path compression).
 *
 ******************************************************************************/

package edu.umb.cs210;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.*;


public class WeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of elements in subtree rooted at i
    private int count;      // number of components

    /**
     * Initializes an empty union-find data structure with
     * {@code n} elements {@code 0} through {@code n-1}.
     * Initially, each elements is in its own set.
     *
     * @param n the number of elements
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public WeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of sets.
     *
     * @return the number of sets (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the canonical element of the set containing element {@code p}.
     *
     * @param p an element
     * @return the canonical element of the set containing {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    /**
     * Returns true if the two elements are in the same set.
     *
     * @param p one element
     * @param q the other element
     * @return {@code true} if {@code p} and {@code q} are in the same set;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     * @deprecated Replace with two calls to {@link #find(int)}.
     */
    @Deprecated
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Merges the set containing element {@code p} with the
     * the set containing element {@code q}.
     *
     * @param p one element
     * @param q the other element
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }


    /**
     * Reads an integer {@code n} and a sequence of pairs of integers
     * (between {@code 0} and {@code n-1}) from standard input, where each integer
     * in the pair represents some element;
     * if the elements are in different sets, merge the two sets
     * and print the pair to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
            for (int i = 0; i < n; i++) {
                System.out.print(uf.find(i) + "\t");
            }
            System.out.println();
        }
        StdOut.println(uf.count() + " components");
        for (int i = 0; i < uf.parent.length; i++) {
            System.out.print(uf.parent[i] + "\t");
        }

        System.out.println();
        System.out.println(uf.connected(0,1));
        System.out.println(uf.connected(3,1));
    }

}


/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/

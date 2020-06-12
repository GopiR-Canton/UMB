package edu.umb.cs210.p3;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

// Implements binary search for clients that may want to know the index of 
// either the first or last key in a (sorted) collection of keys.
public class BinarySearchDeluxe {
    // The index of the first key in a[] that equals the search key, 
    // or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> c) {

        return -1;
    }

    // The index of the last key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {

        return -1;

    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {

        Integer[] a = new Integer[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4};
        Arrays.sort(a);

        System.out.printf("Expected 0: Actualy get:%d\n",
                firstIndexOf(a, 1, new IntegerComparator()));
        System.out.printf("Expected 2: Actualy get:%d\n",
                lastIndexOf(a, 1, new IntegerComparator()));

        String filename, prefix;
        int k;
        if (args.length != 0) {
            filename = args[0];
            prefix = args[1];
        } else {
            filename = "wiktionary.txt";
            prefix = "cook";
        }
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.byPrefixOrder(prefix.length());
        Arrays.sort(terms, prefixOrder);
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.printf("Expected 3: Actualy get:%d\n",count);
        System.out.println(count);


    }

    static class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer integer, Integer t1) {
            return integer.compareTo(t1);
        }
    }
}

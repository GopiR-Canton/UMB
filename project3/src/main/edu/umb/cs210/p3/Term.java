package edu.umb.cs210.p3;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

// An immutable data type that represents an autocomplete term: a query string 
// and an associated real-valued weight.
public class Term implements Comparable<Term> {
    private String query;
    private long weight;

    // Construct a term given the associated query string, having weight 0.
    public Term(String query) {
        setQuery(query);
    }

    // Construct a term given the associated query string and weight.
    public Term(String query, long weight) {
        setQuery(query);
        setWeight(weight);
    }

    public void setQuery(String query) {
        if (query == null){
            throw new NullPointerException();
        }

        this.query = query;
    }

    public void setWeight(long weight) {
        if (weight < 0){
            throw new IllegalArgumentException();
        }

        this.weight = weight;
    }

    // Compare this term to that in lexicographic order by query and
    // return a negative, zero, or positive integer based on whether this 
    // term is smaller, equal to, or larger than that term.
    public int compareTo(Term that) {
        return 0;
    }

    // A reverse-weight comparator.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // Helper reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {

        @Override
        public int compare(Term v, Term w) {

            return 0;
        }
    }

    // A prefix-order comparator.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0){
            throw new IllegalArgumentException();
        }

        return new PrefixOrder(r);
    }

    // Helper prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {

        private int r;
        // Construct a new PrefixOrder object
        PrefixOrder(int r) {

        }

        public int compare(Term v, Term w) {

            return 1;
        }
    }

    // A string representation of this term.
    public String toString() {
        return "";
    }



    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {

        String filename;
        int k;
        if (args.length!=0) {
            filename = args[0];
            k = Integer.parseInt(args[1]);
        }else {
            filename="data/cities.txt";
            k = 5;
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
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}

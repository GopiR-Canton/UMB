package edu.umb.cs210.p3;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.min;

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
        if (query == null) {
            throw new NullPointerException();
        }

        this.query = query;
    }

    public void setWeight(long weight) {
        if (weight < 0) {
            throw new IllegalArgumentException();
        }

        this.weight = weight;
    }

    // Compare this term to that in lexicographic order by query and
    // return a negative, zero, or positive integer based on whether this 
    // term is smaller, equal to, or larger than that term.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // A reverse-weight comparator.
    public static Comparator<Term> byReverseWeightOrder() {

        return new ReverseWeightOrder();
    }

    // Helper reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {

        @Override
        public int compare(Term v, Term w) {
        /*
            Return a -1, 0, or +1 based on whether v.weight is smaller, equal to, or larger than w.weight
        */
            if (v.weight < w.weight) {
                return 1;
            }
            if (v.weight > w.weight) {
                return -1;
            }

            return 0;
        }
    }

    // A prefix-order comparator.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
            throw new IllegalArgumentException();
        }

        return new PrefixOrder(r);
    }

    // Helper prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {

        private int r;

        // Construct a new PrefixOrder object
        PrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term v, Term w) {
            /*
                Return a negative, zero, or positive integer based on whether
                a is smaller, equal to, or larger than b,
                where a is a substring of v of length min(r, v.query.length())
                and b is a substring of w of length min(r, w.query.length())
             */
            String a = v.query.substring(0, min(r, v.query.length()));
            String b = w.query.substring(0, min(r, w.query.length()));

            return a.compareTo(b);
        }
    }

    // A string representation of this term.
    public String toString() {
        return query;
    }


    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {

        String filename;
        int k;
        if (args.length != 0) {
            filename = args[0];
            k = Integer.parseInt(args[1]);
        } else {
            filename = "C:\\Users\\getgo\\IdeaProjects\\cs210_samplecode\\project3\\data\\cities.txt";
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

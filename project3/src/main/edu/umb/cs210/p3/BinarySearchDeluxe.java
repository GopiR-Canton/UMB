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
        CheckForNull(a);
        CheckForNull(key);
        CheckForNull(c);

        return Search(a, key, c, 0, a.length, true, -1);
    }

    static <Key> int Search(Key[] a, Key keyToFind, Comparator<Key> c,
                            int start, int end, boolean findFirst, int prevFoundIndex) {
        int mid = end / 2;
        int index = -1;

        if (end < 0 || mid < 0) {
            return prevFoundIndex;
        }


        Key currentKey = a[mid];
        int compareResult = (c.compare(currentKey, keyToFind));

        switch (compareResult) {
            case -1: // currentKey < keyToFind
                if (prevFoundIndex == -1) {
                    index = Search(a, keyToFind, c, mid + 1, end, findFirst, index);
                } else {
                    index = prevFoundIndex;
                }
                break;
            case 1: // currentKey > keyToFind
                if (prevFoundIndex == -1) {
                    index = Search(a, keyToFind, c, start, mid - 1, findFirst, index);
                } else {
                    index = prevFoundIndex;
                }
                break;
            default: // currentKey = keyToFind
                index = mid;

                if (findFirst) {
                    if (index == 0) {
                        // at first element of array; first index of key is also found
                        return index;
                    }

                    if (index > 0) {
                        if (a[index - 1] != keyToFind) {
                            // previous element of array is not the key to find; so just return current index as first index of key
                            return index;
                        } else {
                            // previous element of array is also the key to find; so do another check from start of array to previous element
                            index = Search(a, keyToFind, c, start, index - 1, true, index);
                        }
                    }
                }

                if (!findFirst){
                    if (index == a.length-1){
                        // at last element of array; last index of key is also found
                        return index;
                    }

                    if (index < a.length-1){
                        if (a[index + 1] != keyToFind){
                            // next element of array is not the key to find; so just return current index as last index of key
                            return index;
                        }
                        else{
                            // next element of array is also the key to find; so do another check from next element to end of array
                            index = Search(a, keyToFind, c, index + 1, end, false, index);
                        }
                    }
                }
        }

        return index;
    }

    // The index of the last key in a[] that equals the search key,
    // or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {
        CheckForNull(a);
        CheckForNull(key);
        CheckForNull(c);

        return Search(a, key, c, 0, a.length, false, -1);
    }

    static void CheckForNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
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
            filename = "C:\\Users\\getgo\\IdeaProjects\\cs210_samplecode\\project3\\data\\wiktionary.txt";
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
        StdOut.printf("Expected 3: Actualy get:%d\n", count);
        System.out.println(count);


    }

    static class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer integer, Integer t1) {
            return integer.compareTo(t1);
        }
    }
}

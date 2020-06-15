package edu.umb.cs210.p2;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<T> implements Iterable<T> {
    private int N;
    private Node first, last;


    // Helper doubly-linked list class.
    private class Node {
        private T item;  // This Node's value
        private Node next;  // The next Node
        private Node prev;  // The previous Node
    }

    // Construct an empty deque.
    public LinkedDeque() {
        N = 0;
    }

    // Is the deque empty?
    public boolean isEmpty() {
        if (N == 0) {
            return true;
        } else {
            return false;
        }
    }

    // The number of items on the deque.
    public int size() {
        return N;
    }

    // Add item to the front of the deque.
    public void addFirst(T item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (first == null) {
            first = new Node();
            first.item = item;
        } else {
            if (last == null){
                this.addLast(item);
            }
            else{
                Node oldFirst = first;
                Node newFirst = new Node();
                newFirst.item = item;
                newFirst.next = oldFirst;
                oldFirst.prev = newFirst;
                first = newFirst;
            }
        }
        N++;
    }

    // Add item to the end of the deque.
    public void addLast(T item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (first == null) {
            this.addFirst(item);
        } else {
            if (last == null) {
                last = new Node();
                last.item = item;
                last.prev = first;
                first.next = last;
            } else {
                Node oldLast = last;
                Node newLast = new Node();
                newLast.item = item;
                newLast.prev = oldLast;
                oldLast.next = newLast;
                last = newLast;
            }
        }
        N++;
    }

    // Remove and return item from the front of the deque.
    public T removeFirst() {
        if (N == 0) {
            return null;
        }

        T item = null;

        if (first != null) {
            Node currentFirst = first;
            item = currentFirst.item;

            if (currentFirst.next == null) {
                // first is last remaining node
                first = null;
            } else {
                if (currentFirst.next != last) {
                    // set new first
                    first = currentFirst.next;
                }
            }
        }

        N--;

        return item;
    }

    // Remove and return item from the end of the deque.
    public T removeLast() {
        if (N == 0) {
            return null;
        }

        T item = null;

        if (last == null) {
            if (first != null) {
                return removeFirst();
            }
            else {
                return null;
            }
        }

        Node currentLast = last;
        item = currentLast.item;

        if (currentLast.prev == first) {
            first.next = null;
            last = null;
        } else {
            last = currentLast.prev;
            last.next = null;
        }

        N--;

        return item;
    }

    // An iterator over items in the queue in order from front to end.
    public Iterator<T> iterator() {
        return new DequeIterator(first);
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<T> {
        private Node currentNode;

        // DequeIterator constructor
        public DequeIterator(Node currentNode) {
            this.currentNode = currentNode;
        }

        public boolean hasNext() {
            if (currentNode != null) {
                return true;
            }

            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            T item = currentNode.item;
            currentNode = currentNode.next;

            return item;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item + " ");
        }
        if (s.length() < 1) return "[Empty]";
        else return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}

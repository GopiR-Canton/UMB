package edu.umb.cs210.p2;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

// Random queue implementation using a resizing array.
// Using the StdRandom.uniform(0,N) for sample and
// StdRandom.shuffle(a) for shuffling
public class ResizingArrayRandomQueue<T> implements Iterable<T> {
    private static final String UNCHECKED = "unchecked";
    private T[] q;
    private int N;

    // Construct an empty queue.
    public ResizingArrayRandomQueue() {
//      @SuppressWarnings(UNCHECKED) // Use to suppress unchecked cast warning

    }

    // Is the queue empty?
    public boolean isEmpty() {
        return false;
    }

    // The number of items on the queue.
    public int size() {
        return N;
    }

    // Add item to the queue.
    public void enqueue(T item) {

    }

    // Remove and return a random item from the queue.
    public T dequeue() {

        return q[0];
    }

    // Return a random item from the queue, but do not remove it.
    public T sample() {

        if (isEmpty()) throw  new NoSuchElementException();
        return q[StdRandom.uniform(0,N)];
    }

    // An independent iterator over items in the queue in random order.
    @Override
    public Iterator<T> iterator()  {
        return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<T> {


        RandomQueueIterator() {
//          @SuppressWarnings(UNCHECKED) // to suppress unchecked cast warning

        }

        public boolean hasNext() {
            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return q[0];
        }
    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item).append(" ");
        }
        if (s.length() < 1) return "[Empty]";
        else return s.toString().substring(0, s.length() - 1);
    }

    // Helper method for resizing the underlying array.
    private void resize(int max) {
        @SuppressWarnings(UNCHECKED) // Else compiler will warn about cast
                T[] temp = (T[]) new Object[max];
        for (int i = 0; i < N; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q =
                new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }

        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}

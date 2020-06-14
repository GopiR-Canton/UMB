package edu.umb.cs210.p2;


import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

// A data type representing a text editor buffer.
public class Buffer {
    protected LinkedStack<Character> left;  // chars left of cursor
    protected LinkedStack<Character> right; // chars right of cursor

    // Create an empty buffer.
    public Buffer() {
        left = new LinkedStack<>();
        right = new LinkedStack<>();
    }

    // Insert c at the cursor position: push to left
    public void insert(char c) {
        left.push(c);
    }

    // Delete and return the character at the cursor: pop the left
    public char delete() {
        return left.pop();
    }

    // Move the cursor k positions to the left.
    public void left(int k) {
        if (k == 0 || left.isEmpty()){
            return;
        }

        int totalPops = 0;
        while(totalPops < k && !left.isEmpty()){
            right.push(left.pop());
            totalPops++;
        }
    }

    // Move the cursor k positions to the right.
    public void right(int k) {
        if (k == 0 || right.isEmpty()){
            return;
        }

        int totalPops = 0;
        while(totalPops < k && !right.isEmpty()){
            left.push(right.pop());
            totalPops++;
        }
    }

    // Return the number of characters in the buffer.
    public int size() {
        return left.size() + right.size() + 1;
    }

    // Return a string representation of the buffer with
    // a "|" character (not part of the buffer) at the
    // cursor position.
    public String toString() {
        // we know exactly how big the StringBuilder object needs to be
        StringBuilder sb = new StringBuilder(left.size() + 1 + right.size());

        // Push chars from left into a temporary stack.
        LinkedStack<Character> tempBuffer = new LinkedStack<>();
        Iterator<Character> iterator = left.iterator();
        while (iterator.hasNext()){
            tempBuffer.push(iterator.next());
        }

        // Append chars from temporary stack to sb.
        iterator = tempBuffer.iterator();
        while (iterator.hasNext()){
            sb.append(iterator.next());
        }

        // Append "|" to sb.
        sb.append("|");

        // Append chars from right to sb.
        iterator = right.iterator();
        while (iterator.hasNext()){
            sb.append(iterator.next());
        }

        // Return the string from sb.
        return sb.toString();
    }

    // Test client (DO NOT EDIT).
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        String s = "There is grandeur in this view of life, "
                + "with its several powers, having been originally "
                + "breathed into a few forms or into one; and that, "
                + "whilst this planet has gone cycling on according "
                + "to the fixed law of gravity, from so simple a "
                + "beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.left(buf.size());
        buf.right(97);
        s = "by the Creator ";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.right(229);
        buf.delete();
        buf.insert('-');
        buf.insert('-');
        buf.left(342);
        StdOut.println(buf);
    }
}

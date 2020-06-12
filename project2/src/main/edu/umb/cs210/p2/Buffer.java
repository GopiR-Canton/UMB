package edu.umb.cs210.p2;


import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.StdOut;

// A data type representing a text editor buffer.
public class Buffer {
    protected LinkedStack<Character> left;  // chars left of cursor
    protected LinkedStack<Character> right; // chars right of cursor

    // Create an empty buffer.
    public Buffer() {

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

    }

    // Move the cursor k positions to the right.
    public void right(int k) {

    }

    // Return the number of characters in the buffer.
    public int size() {
        return 0;
    }

    // Return a string representation of the buffer with
    // a "|" character (not part of the buffer) at the
    // cursor position.
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Push chars from left into a temporary stack.


        // Append chars from temporary stack to sb.

        // Append "|" to sb.


        // Append chars from right to sb.

        // Return the string from sb.
        return "";
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

package edu.umb.cs210.p3;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// A data type representing a six-sided die.
public class Die implements Comparable<Die> {
    private int value; // face value

    // Construct a die.
    public Die() {

    }

    // Roll the die.
    public void roll() {
        this.value = StdRandom.uniform(1, 7);
    }

    // Face value of the die.
    public int value() {
        return value;
    }

    // Does the die have the same face value as that?
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        Die thatDie = (Die) that;
        if (this.value == thatDie.value) return true;
        return false;
    }

    // A negative integer, zero, or positive integer depending on
    // whether this die's value is less than, equal to, or greater
    // than the that die's value.
    public int compareTo(Die that) {
        int result = 1;

        if (that != null) {
            if (this.value < that.value) {
                result = -1;
            }
            if (this.value == that.value) {
                result = 0;
            }
            if (this.value > that.value) {
                result = 1;
            }
        }

        return result;
    }

    // A string representation of the die giving the current
    // face value.
    public String toString() {
        String valueOne = "   \n" + " * \n" + "   ";
        String valueTwo = "*  \n" + "   \n" + "  *";
        String valueThree = "*  \n" + " * \n" + "  *";
        String valueFour = "* *\n" + "   \n" + "* *";
        String valueFive = "* *\n" + " * \n" + "* *";
        String valueSix = "***\n" + "   \n" + "***";

        String currentFaceValue = "";

        switch (this.value){
            case 1:
                currentFaceValue = valueOne;
                break;

            case 2:
                currentFaceValue = valueTwo;
                break;

            case 3:
                currentFaceValue = valueThree;
                break;

            case 4:
                currentFaceValue = valueFour;
                break;

            case 5:
                currentFaceValue = valueFive;
                break;

            case 6:
                currentFaceValue = valueSix;
                break;

            default:
                currentFaceValue = "\nInvalid (Specify a value for args[0] between 1 and 6.)\n";
        }

        return currentFaceValue;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {

        if (args.length != 3) args = new String[]{"5", "3", "3"};
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);
        Die a = new Die();
        a.roll();
        while (a.value() != x) {
            a.roll();
        }
        Die b = new Die();
        b.roll();
        while (b.value() != y) {
            b.roll();
        }
        Die c = new Die();
        c.roll();
        while (c.value() != z) {
            c.roll();
        }
        StdOut.println(a);
        StdOut.println(a.equals(b));
        StdOut.println(b.equals(c));
        StdOut.println(a.compareTo(b) > 0);
        StdOut.println(b.compareTo(c) > 0);
    }
}

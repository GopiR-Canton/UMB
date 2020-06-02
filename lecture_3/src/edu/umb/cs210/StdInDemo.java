package edu.umb.cs210;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class StdInDemo {
    public static void main(String[] args) {

        final int a = 100;

        int b = 100;
        System.out.println(a==b);// true

//        assert false;
        Integer a1 = 100;
        Integer a2 = 100;
        System.out.println(a1 == a2); // true


        Integer b1 = new Integer(100);
        Integer b2 = new Integer(100);
        System.out.println(b1 == b2); // false
        Integer c1 = 150;
        Integer c2 = 150;
        System.out.println(c1 == c2); // false
        System.out.println(c1.equals(c2));
        Counter counter = new Counter("one");
        Counter counter1 = new Counter("one");
        System.out.println(counter.hashCode());
        System.out.println(counter1.hashCode());
//        Counter counter1 =  counter;// Alise
//
//        int i = 0;
//        int j = i;
//
//        int p = StdIn.readInt();
//        int q = StdIn.readInt();
//        StdOut.println(p + " " + q);
//        Byte i = StdIn.readByte();
//        Byte j = StdIn.readByte();
//        StdOut.println(i + " " + j);
//
//        while(!StdIn.isEmpty()){
//            System.out.println(StdIn.readInt());
//        }

//        Boolean b1 = StdIn.readBoolean();
//        Boolean b2 = StdIn.readBoolean();
//        StdOut.println(b1 + " " + b2);
    }
}

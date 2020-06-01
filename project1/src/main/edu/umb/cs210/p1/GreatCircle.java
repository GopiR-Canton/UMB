/******************************************************************************
 Compilation:
 *  javac -cp  src/test/resources/algs4.jar -d out src/main/edu/umb/cs210/p1/GreatCircle.java
 *  Execution:
 *  java -cp  src/test/resources/algs4.jar:out edu.umb.cs210.p1.GreatCircle 48.87 -2.33 37.8 -122.4
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/15uf/tinyUF.txt
 *                https://algs4.cs.princeton.edu/15uf/mediumUF.txt
 *                https://algs4.cs.princeton.edu/15uf/largeUF.txt
 *
 *  Weighted quick-union (without path compression).
 *
 ******************************************************************************/


package edu.umb.cs210.p1;

import edu.princeton.cs.algs4.StdOut;

public class GreatCircle {
    // calculates the great circle distance given two sets of coordinates
    protected static double calculateGreatCircleDistance(String[] args) {

        double[] d = new double[4];
        // Get angles lat1, lon1, lat2, and lon2 from command line as
        // doubles in degree. Convert the angles from degree to radians.
        for (int i = 0; i < 4; i++) {
            d[i] = Math.toRadians(Double.parseDouble(args[i]));
        }

        // Calculate great-circle distance d, using the equation given.


        // Return d.
        return 111 * Math.toDegrees(Math.acos(  Math.sin(d[0]) * Math.sin(d[2]) +
                Math.cos(d[0]) * Math.cos(d[2]) * Math.cos(d[1] - d[3])));

    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        StdOut.println(GreatCircle.calculateGreatCircleDistance(args));
    }
}

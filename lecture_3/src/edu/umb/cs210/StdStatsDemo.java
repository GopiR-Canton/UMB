package edu.umb.cs210;

import edu.princeton.cs.algs4.StdStats;

public class StdStatsDemo {
    public static void main(String[] args) {
        double[] a = new double[]{1,2,3,4,5,6};
        System.out.println(StdStats.max(a));
        System.out.println(StdStats.min(a));
        System.out.println(StdStats.mean(a));
        System.out.println(StdStats.stddev(a));

        double max = StdStats.max(a);
        double[] b = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] / max;
        }
        StdStats.plotBars(b);
        StdStats.plotLines(b);
        StdStats.plotPoints(b);
    }
}

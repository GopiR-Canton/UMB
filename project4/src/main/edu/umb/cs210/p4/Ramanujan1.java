package edu.umb.cs210.p4;


import edu.princeton.cs.algs4.StdOut;

public class Ramanujan1 {
    public static int cubic(int n) {
        return n * n * n;
    }

    public static int rootCubic(int n) {

        return 1;
    }


    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        long a, b, c, d;
        int i, j, k, l;

        for (int m = 1; m <= N; m++) {
            k = 0;

            if (m == 36864){
                int u = 0;
            }

            for (i = 1; i <= m; i++) {
                if (k > 0) {
                    break;
                }

                a = cubic(i);

                if (a >= m) {
                    break;
                }

                for (j = i + 1; j < m - a; j++) {
                    b = cubic(j);
                    if (b + a > m) {
                        break;
                    }

                    if (b + a == m) {
                        for (k = 1; k < m; k++) {
                            if (k == i) {
                                // i has already been picked up in the outermost loop
                                continue;
                            }

                            c = cubic(k);

                            if (c < 0){
                                break;
                            }

                            if (k >= m) {
                                break;
                            }

                            for (l = k + 1; l < m - c; l++) {
                                d = cubic(l);

                                if (d + c > m) {
                                    break;
                                }
                                if (d + c == m) {
                                    StdOut.println(String.format("%d = %d^3 + %d^3 = %d^3 + %d^3", m, i, j, k, l));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

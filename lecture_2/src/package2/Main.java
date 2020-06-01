package package2;

import package1.Pub;

public class Main {
    public static void main(String[] args) {
        Pub pu = new Pub();
        Pub pu1 = new Pub();
        System.out.println(Pub.ss);


        System.out.println(args[1]);

        System.out.println(pu.s);

        Pub.printHello();
        pu.printWorld();
    }
}

package package1;

public class Pub {
   public String s = "From Public Class";
   private String s_pr = "From Private class";
   static public String ss = "coming from static property";

   static public void printHello(){
        System.out.println("hello");
   }

   public void printWorld(){
        System.out.println("workd");
   }
}

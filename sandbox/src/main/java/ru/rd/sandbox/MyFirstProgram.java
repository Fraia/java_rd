package ru.rd.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
   Point p1 = new Point(1.0, 1.0);
   Point p2 = new Point(1.0, 3.0);
   System.out.println("Результат = " + p2.distance(p1));
 }

}

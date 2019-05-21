public class MyFirstProgram {

  public static void main(String[] args) {
   Point p1 = new Point (1.0, 1.0);
   Point p2 = new Point (1.0, 2.0);
   System.out.println("Результат = " + distance(p1, p2));
 }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(square(p1.x - p2.x) + square(p1.y - p2.y));
  }

  public static double square(double n) {
    return n * n;
  }
}


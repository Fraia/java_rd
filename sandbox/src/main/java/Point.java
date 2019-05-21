public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        return Math.sqrt(square(this.x - p2.x) + square(this.y - p2.y));
    }

    public double square(double n) {
        return n * n;
    }
}

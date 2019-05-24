package ru.rd.sandbox;

import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance1() {
        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(1.0, 3.0);
        assert p2.distance(p1) == 2.0;
    }

    @Test
    public void testDistance2() {
        Point p1 = new Point(0.0, 1.0);
        Point p2 = new Point(1.0, 3.0);
        assert p2.distance(p1) == 2.23606797749979;
    }

    @Test
    public void testDistance3() {
        Point p1 = new Point(5.5, 5.5);
        Point p2 = new Point(5.5, 5.5);
        assert p2.distance(p1) == 0.0;
    }

    @Test
    public void testDistance4() {
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(0.0, 0.0);
        assert p2.distance(p1) == 0.0;
    }

    @Test
    public void testDistance5() {
        Point p1 = new Point(1.5, 5.5);
        Point p2 = new Point(5.5, 5.5);
        assert p2.distance(p1) == 4.0;
    }

    @Test
    public void testDistance6() {
        Point p1 = new Point(-2.5, 5.5);
        Point p2 = new Point(5.5, 5.5);
        assert p2.distance(p1) == 8.0;
    }

}

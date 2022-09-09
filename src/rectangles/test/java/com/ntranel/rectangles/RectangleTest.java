package rectangles.test.java.com.ntranel.rectangles;

import org.junit.jupiter.api.Test;
import rectangles.main.java.com.ntranel.rectangles.Point;
import rectangles.main.java.com.ntranel.rectangles.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class RectangleTest {

    @Test
    public void testSeparate() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(6, 6), 2, 3);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("separate"));
    }

    @Test
    public void testSeparateNegative() {
        Rectangle r1 = new Rectangle(new Point(-1,-3), 4, 3);
        Rectangle r2 = new Rectangle(new Point(2, 6), 2, 3);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("separate"));
    }

    @Test
    public void testIntersectCorner() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(2, 3), 5, 4);

        HashMap<String, ArrayList<Point>> result = r1.overlap(r2);

        assert(result.containsKey("intersect"));

        double x1 = result.get("intersect").get(0).getX();
        double y1 = result.get("intersect").get(0).getY();
        double x2 = result.get("intersect").get(1).getX();
        double y2 = result.get("intersect").get(1).getY();

        assert(Double.compare(x1, 2) == 0);
        assert(Double.compare(y1, 5) == 0);
        assert(Double.compare(x2, 4) == 0);
        assert(Double.compare(y2, 3) == 0);
    }

    @Test
    public void testIntersectSide() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(2, 1), 2, 5);

        HashMap<String, ArrayList<Point>> result = r1.overlap(r2);

        assert(result.containsKey("intersect"));

        double x1 = result.get("intersect").get(0).getX();
        double y1 = result.get("intersect").get(0).getY();
        double x2 = result.get("intersect").get(1).getX();
        double y2 = result.get("intersect").get(1).getY();

        assert(Double.compare(x1, 4) == 0);
        assert(Double.compare(y1, 3) == 0);
        assert(Double.compare(x2, 4) == 0);
        assert(Double.compare(y2, 1) == 0);
    }

    @Test
    public void testInsideAdjacentIntersect() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(0, 2), 5, 2);

        HashMap<String, ArrayList<Point>> result = r1.overlap(r2);

        assert(result.containsKey("intersect"));

        double x1 = result.get("intersect").get(0).getX();
        double y1 = result.get("intersect").get(0).getY();
        double x2 = result.get("intersect").get(1).getX();
        double y2 = result.get("intersect").get(1).getY();
        double x3 = result.get("intersect").get(2).getX();
        double y3 = result.get("intersect").get(2).getY();

        assert(Double.compare(x1, 0) == 0);
        assert(Double.compare(y1, 5) == 0);
        assert(Double.compare(x2, 0) == 0);
        assert(Double.compare(y2, 2) == 0);
        assert(Double.compare(x3, 2) == 0);
        assert(Double.compare(y3, 5) == 0);
    }

    @Test
    public void testStackIntersect() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(0, 2), 5, 4);

        HashMap<String, ArrayList<Point>> result = r1.overlap(r2);

        assert(result.containsKey("intersect"));

        double x1 = result.get("intersect").get(0).getX();
        double y1 = result.get("intersect").get(0).getY();
        double x2 = result.get("intersect").get(1).getX();
        double y2 = result.get("intersect").get(1).getY();
        double x3 = result.get("intersect").get(2).getX();
        double y3 = result.get("intersect").get(2).getY();
        double x4 = result.get("intersect").get(3).getX();
        double y4 = result.get("intersect").get(3).getY();

        assert(Double.compare(x1, 0) == 0);
        assert(Double.compare(y1, 5) == 0);
        assert(Double.compare(x2, 0) == 0);
        assert(Double.compare(y2, 2) == 0);
        assert(Double.compare(x3, 4) == 0);
        assert(Double.compare(y3, 2) == 0);
        assert(Double.compare(x4, 4) == 0);
        assert(Double.compare(y4, 5) == 0);
    }

    @Test
    public void testContainment() {
        Rectangle r1 = new Rectangle(new Point(0,0), 6, 10);
        Rectangle r2 = new Rectangle(new Point(2, 2), 2, 3);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("containment"));
        assert(result.values().size() == 1);

        result = r2.overlap(r1);
        assert(result.containsKey("containment"));
    }

    @Test
    public void testContainmentSameStart() {
        Rectangle r1 = new Rectangle(new Point(0,0), 6, 10);
        Rectangle r2 = new Rectangle(new Point(0, 0), 2, 3);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("containment"));
        assert(result.values().size() == 1);
    }

    @Test
    public void testContainmentSameCorners() {
        Rectangle r1 = new Rectangle(new Point(0,0), 6, 10);
        Rectangle r2 = new Rectangle(new Point(0, 3), 3, 10);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("containment"));
        assert(result.values().size() == 1);
    }

    @Test
    public void testContainmentSameDimensions() {
        Rectangle r1 = new Rectangle(new Point(0,0), 6, 10);
        Rectangle r2 = new Rectangle(new Point(0, 0), 6, 10);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("containment"));
        assert(result.values().size() == 1);
    }

    @Test
    public void testProperAdjacent() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(4, 0), 5, 7);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("adjacent"));
        assert(result.values().size() == 1);
    }

    @Test
    public void testPartialAdjacent() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(4, 2), 5, 2);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("adjacent"));
        assert(result.values().size() == 1);
    }

    @Test
    public void testSublineAdjacent() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(4, 1), 2, 3);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("adjacent"));
        assert(result.values().size() == 1);
    }

    @Test
    public void testSublineAdjacentNegative() {
        Rectangle r1 = new Rectangle(new Point(-5,-2), 1, 3);
        Rectangle r2 = new Rectangle(new Point(-8, -1.75), .5, 3);

        HashMap result = r1.overlap(r2);

        assert(result.containsKey("adjacent"));
        assert(result.values().size() == 1);
    }

    @Test
    public void testCornersTouch() {
        Rectangle r1 = new Rectangle(new Point(0,0), 5, 4);
        Rectangle r2 = new Rectangle(new Point(4, 5), 2, 3);

        HashMap<String, ArrayList<Point>> result = r1.overlap(r2);

        assert(result.containsKey("adjacent"));
    }

}

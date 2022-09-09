package rectangles.main.java.com.ntranel.rectangles;
/**
 * Point represents a single coordinate on an 2D (x,y) grid
 *
 * @author Nate Tranel
 */
public class Point {
    private double x, y;

    /**
     * Construct a Point with its x,y coordinates
     * @param x the x coordinate of the Point
     * @param y the y coordinate of the Point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x coordinate of this Point
     * @return x coordinate of this Point
     */
    public double getX() {
        return x;
    }

    /**
     * Get the y coordinate of this Point
     * @return y coordinate of this Point
     */
    public double getY() {
        return y;
    }

    /**
     * Compare this Point to a Point p to determine if they have the same coordinates
     * @param p Point to compare to
     * @return Boolean indicating same or different coordinates
     */
    public Boolean samePoint(Point p) {
        return (Double.compare(p.getX(), this.x) == 0 && Double.compare(p.getY(), this.y) == 0);
    }

}

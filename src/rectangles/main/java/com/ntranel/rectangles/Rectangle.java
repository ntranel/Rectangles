package rectangles.main.java.com.ntranel.rectangles;

import java.util.*;

/**
 * Rectangle represents a rectangle on a 2D (x,y) grid made up of 4 lines with a starting Point as a corner
 *
 * @author Nate Tranel
 */
public class Rectangle {

    private Point start, botRight, topRight, topLeft;
    private Line bottom, right, top, left;
    private HashMap<String, Line> sides;
    private HashMap<String, Point> vertices;

    /**
     * Construct a Rectangle given a start Point, a height and a width as doubles
     * @param start the Point for the lower left corner of the Rectangle object
     * @param height the height of the sides as a double
     * @param width the width of the top and bottom as a double
     */
    public Rectangle(Point start, double height, double width) {
        height = Math.abs(height);
        width = Math.abs(width);
        this.start = start;
        this.botRight = new Point(start.getX() + width, start.getY());
        this.topRight = new Point(botRight.getX(), botRight.getY() + height);
        this.topLeft = new Point(start.getX(), start.getY() + height);

        this.vertices = new HashMap<>();
        vertices.put("bottomLeft", start);
        vertices.put("bottomRight", botRight);
        vertices.put("topRight", topRight);
        vertices.put("topLeft", topLeft);

        this.bottom = new Line(start, botRight);
        this.right = new Line(botRight, topRight);
        this.left = new Line(start, topLeft);
        this.top = new Line(topLeft, topRight);

        this.sides = new HashMap<>();
        sides.put("bottom", this.bottom);
        sides.put("right", this.right);
        sides.put("left", this.left);
        sides.put("top", this.top);
;    }

    /**
     * Given Rectangle r, find how it overlaps with this Rectangle (if at all)
     * @param r other Rectangle to compare overlap against this object
     * @return HashMap with overlap type (String) as the key and the value an ArrayList:
     * null if containment or adjacent, or a list of Points where intersection occurs if intersect
     */
    public HashMap<String, ArrayList<Point>> overlap(Rectangle r) {
        HashMap<String, ArrayList<Point>> result = new HashMap<>();

        // if topRight of this Rectangle is left of bottomLeft of r OR bottomLeft of this Rectangle is right of topRight of r, rectangles do not overlap
        if (Double.compare(topRight.getX(), r.getStart().getX()) < 0 || Double.compare(start.getX(), r.getVertices().get("topRight").getX()) > 0) {
            result.put("separate", null);
        }
        // x overlaps; if topRight of this Rectangle is under bottomLeft of r OR bottomLeft of this Rectangle is over topRight of r, rectangles do not overlap
        else if (Double.compare(topRight.getY(), r.getStart().getY()) < 0 || Double.compare(start.getY(), r.getVertices().get("topRight").getY()) > 0) {
            result.put("separate", null);
        }
        // rectangles overlap, find how
        else {
            if (containment(r)) {
                result.put("containment", null);
            } else {
                // check adjacency, intersection
                HashMap<String, ArrayList<Point>> overlap = adjacentOrIntersect(r);
                if (overlap.size() > 0) {
                    return overlap;
                }
                // if no adjacency or intersection, rectangles were reverse contained (this within r)
                else {
                    result.put("containment", null);
                }
            }
        }
        return result;
    }

    /**
     * Given another Rectangle r, return a HashMap where the key is the type of overlap (adjacent or intersect) and the value
     * is an ArrayList of Points for an intersection or null for adjacent
     * @param r other Rectangle object to check for overlap
     * @return HashMap where key is the type of overlap (adjacent or intersect) and the value is an ArrayList of Points if intersect, otherwise null
     */
    private HashMap<String, ArrayList<Point>> adjacentOrIntersect(Rectangle r) {
        HashMap<String, ArrayList<Point>> result = new HashMap<>();
        ArrayList<Point> intersectPoints = new ArrayList<>();

        // loop through sides of both rectangles and check if they overlap/intersect
        for (Line compareSide : r.getSides().values()) {
            for (Line side : this.sides.values()) {
                // if lines overlap, rectangles are adjacent, so we should return with that
                if (side.overlap(compareSide)) {
                    result.put("adjacent", null);
                    return result;
                }

                // if lines do not overlap, then check if they intersect; if they do, add the point to the list
                Point p = side.intersection(compareSide);
                if (p != null) {
                    intersectPoints.add(p);
                }
            }
        }
        // if there are intersection points, add them to the result
        if (intersectPoints.size() > 0) {
            result.put("intersect", intersectPoints);
        }
        return result;
    }

    /**
     * Given another Rectangle r, return a Boolean value indicating if r is wholly contained within this Rectangle
     * @param r other Rectangle to check for containment
     * @return Boolean indicating with r is wholly contained within this Rectangle
     */
    private Boolean containment(Rectangle r) {
        // if the start point of this Rectangle is below and left of the start point of r and
        // the topRight of this Rectangle is above and right of the topRight of r, r is contained
        // within this
        return Double.compare(start.getX(), r.getVertices().get("bottomLeft").getX()) < 0 &
            Double.compare(start.getY(), r.getVertices().get("bottomLeft").getY()) < 0 &
            Double.compare(topRight.getX(), r.getVertices().get("topRight").getX()) > 0 &
            Double.compare(topRight.getY(), r.getVertices().get("topRight").getY()) > 0;
    }

    /**
     * Return the start Point of this Rectangle
     * @return the Point indicating the lower left/start corner of this Rectangle object
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Return a HashMap of sides of this Rectangle
     * @return a HashMap of sides of this Rectangle where the key is the side name and the value is the Line object for that side
     */
    public HashMap<String, Line> getSides() {
        return this.sides;
    }

    /**
     * Return a HashMap of vertices of this Rectangle
     * @return a HashMap of Points of this Rectangle where the key is the Point name and the value is the Point object for that vertex
     */
    public HashMap<String, Point> getVertices() {
        return this.vertices;
    }

}

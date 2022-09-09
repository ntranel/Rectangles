package rectangles.main.java.com.ntranel.rectangles;

/**
 * Line represents a line on a 2D (x,y) grid
 *
 * @author Nate Tranel
 */
public class Line {
    private Point start, end;

    /**
     * Construct a line with start and end Point objects
     * @param start line start Point coordinate
     * @param end line end Point coordinate
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Given another Line l, find the intersection of this Line and l
     * @param l other Line object to check for intersection
     * @return the Point where intersection occurs, otherwise return null
     */
    public Point intersection(Line l) {
        // if this Line is horizontal
        if (Double.compare(start.getY(), end.getY()) == 0) {
            // if l is also horizontal, then the lines cannot intersect
            if (Double.compare(l.getStart().getY(), l.getEnd().getY()) == 0) {
                return null;
            }
            // l is within horizontal range of this Line and this Line is within vertical range of l, then return intersect Point
            else if (Double.compare(start.getX(), l.getStart().getX()) <= 0 && Double.compare(end.getX(), l.getStart().getX()) >= 0 &&
                    Double.compare(l.getStart().getY(), start.getY()) <= 0 && Double.compare(l.getEnd().getY(), start.getY()) >= 0) {
                return new Point(l.getStart().getX(), start.getY());
            } else {
                return null;
            }
        }
        // if this Line is vertical
        else if (Double.compare(start.getX(), end.getX()) == 0) {
            // if l is also vertical, similarly lines cannot intersect
          if (Double.compare(l.getStart().getX(), l.getEnd().getX()) == 0) {
              return null;
          }
          // l is within vertical range of this Line and this Line is within horizontal range of l, then return intersect Point
          else if (Double.compare(start.getY(), l.getStart().getY()) <= 0 && Double.compare(end.getY(), l.getStart().getY()) >= 0 &&
                  Double.compare(l.getStart().getX(), start.getX()) <= 0 && Double.compare(l.getEnd().getX(), start.getX()) >= 0) {
              return new Point(start.getX(), l.getStart().getY());
          } else {
              return null;
          }
        }
        else {
            return null;
        }
    }

    /**
     * Given another Line l, determine if it overlaps this Line
     * @param l other Line to find overlap
     * @return Boolean indicating lines overlap
     */
    public Boolean overlap(Line l) {
        if (this.sameLine(l)) {
            return true;
        }
        // if both starts have same x, then they are on the same vertical line
        else if (Double.compare(start.getX(), l.getStart().getX()) == 0) {
            // check if the lines are outside each other/do not overlap
            return !(Double.compare(l.getStart().getY(), end.getY()) >= 0 ||
                    Double.compare(start.getY(), l.getEnd().getY()) >= 0);
        }
        // otherwise if starts have same y, then they are on the same horizontal line
        else if (Double.compare(start.getY(), l.getStart().getY()) == 0) {
            // check if the lines are outside each other/do not overlap
            return !(Double.compare(l.getStart().getX(), end.getX()) >= 0 ||
                    Double.compare(start.getX(), l.getEnd().getX()) >= 0);
        }
        return false;
    }

    /**
     * Get the start Point of this Line object
     * @return the start Point of this Line
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Get the end Point of this Line object
     * @return the end Point of this Line
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Compare this Line to a Line l to determine if they have the same start and end Points
     * @param l Line to compare to
     * @return Boolean indicating same or different start, end Points
     */
    public Boolean sameLine(Line l) {
        return (this.start.samePoint(l.getStart()) && this.end.samePoint(l.getEnd())) ||
                (this.start.samePoint(l.getEnd()) && this.end.samePoint(l.getStart()));
    }

}

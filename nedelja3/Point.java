import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // koordinata x
    private final int y;     // koordinata y

    
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    
    public double slopeTo(Point that) {
        if (x == that.x) {
            if (y == that.y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        else if (y == that.y) {
            return 0;
        }
        return ((that.y - y) / (that.x - x));
    }

    /
    public int compareTo(Point that) {
        if (y > that.y) {
            return 1;
        }
        else if (y == that.y) {
            if (x > that.x) {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return -1;
        }
    }

    
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double aSlope = slopeTo(a);
            double bSlope = slopeTo(b);
            if (aSlope < bSlope) return -1;
            if (bSlope > aSlope) return 1;
            return 0;
        }
    }
    
    public String toString() {
       
        return "(" + x + ", " + y + ")";
    }

    
    public static void main(String[] args) {
        
    }
}

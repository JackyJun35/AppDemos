
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
 private Point[] points;
 private int numOfSegs;

   public BruteCollinearPoints(Point[] points) {
    for (int i = 0; i < points.length; i++) {
     for (int j = i+1; j < points.length; j++){
      if (points[i] == null) throw new NullPointerException();
      if (points[i] == points[j]) throw new IllegalArgumentException("Containing repeated points");
     }
    }
    this.points = points;
   }    // finds all line segments containing 4 points
   public int numberOfSegments() {
    return this.numOfSegs;
   }       // the number of line segments
   public LineSegment[] segments() {
    List<LineSegment> list = findAllCollinearPoints();
    LineSegment[] lineSegments = new LineSegment[numOfSegs];
    for (int i = 0; i < numOfSegs; i++){
     lineSegments[i] = list.get(i);
    }
    return lineSegments;
   }            // the line segments

   private List<LineSegment> findAllCollinearPoints() {
    ArrayList<LineSegment> list = new ArrayList<LineSegment>();
    for (int i = 0; i < points.length ; i++) {
     for (int j = i+1; j < points.length; j++) {
      for (int k = j+1; k < points.length; k++) {
       for (int l = k+1 ; l < points.length; l++) {
        Point p1 = points[i];
        Point p2 = points[j];
        Point p3 = points[k];
        Point p4 = points[l];

        Point[] result = new Point[4];
        result[0] = p1;
        result[1] = p2;
        result[2] = p3;
        result[3] = p4;

        if (p1.slopeTo(p2) == p2.slopeTo(p3) && p2.slopeTo(p3) == p3.slopeTo(p4)) {
	         Arrays.sort(result);
	         numOfSegs++;
	         list.add(new LineSegment(result[0], result[3]));
        }
       }
      }
     }
    } return list;
   }

   /*
   public static void main(String[] args) {
     // read the n points from a file
     In in = new In(args[0]);
     int n = in.readInt();
     Point[] points = new Point[n];
     for (int i = 0; i < n; i++) {
         int x = in.readInt();
         int y = in.readInt();
         points[i] = new Point(x, y);
     }

     // draw the points
     StdDraw.enableDoubleBuffering();
     StdDraw.setXscale(0, 32768);
     StdDraw.setYscale(0, 32768);
     for (Point p : points) {
         p.draw();
     }
     StdDraw.show();

     // print and draw the line segments
     BruteCollinearPoints collinear = new BruteCollinearPoints(points);
     for (LineSegment segment : collinear.segments()) {
         StdOut.println(segment);
         segment.draw();
     }
     StdDraw.show();

 }
  */


}

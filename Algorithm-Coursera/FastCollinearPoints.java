import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.Line;





public class FastCollinearPoints {
 private Point[] points;
 private List<LineSegment> lineSegments = new ArrayList<>();
 
   public FastCollinearPoints(Point[] points) {
    for (int i = 0; i < points.length; i++) {
     for (int j = i+1; j < points.length; j++){
      if (points[i] == null) throw new NullPointerException("Your have point not assigned");
      if (points[i] == points[j]) throw new IllegalArgumentException("Containing repeated points");
     }
    }
    this.points = points;
    this.lineSegments = findAllCollinearPoints();
   }    // finds all line segments containing 4 or more points

   public int numberOfSegments() {
    return lineSegments.size();
   }        // the number of line segments

   public LineSegment[] segments() {
     return lineSegments.toArray(new LineSegment[lineSegments.size()]);
   }                // the line segments

   private List<LineSegment> findAllCollinearPoints() {
	   List<LineSegment> lsegs = new ArrayList<>();
	   
	   for (int i = 0; i < points.length - 3; i++) {
		   Point[] pts = new Point[points.length - i - 1 ];
		   for (int j = i+1; j < points.length; j++) {
			   pts[j-i-1] = points[j];
			   }

			   Arrays.sort(pts,points[0].slopeOrder());
			   addNewLineSegment(lsegs, pts, points[i]);
	   }
	   return lsegs;
   }

   private void addNewLineSegment(List<LineSegment> list, Point[] pts, Point o){
	   int start = 0;
	   int end = 1;
	   int count = 1;

	   while (start < pts.length -1 && end < pts.length) {
		   if (pts[start].slopeTo(o) == pts[end].slopeTo(o)) {
			   end++;
			   count++;
		   }
		   else {
			   if (count < 3) {
				   count = 1;
				   start = end;
				   end = start + 1;
				   }
			   if (count >= 3) {
				   Point[] result = new Point[count+1];
				   for (int i = 0; i < count; i++) {
					   result[i] = pts[start+i];
				   }
				   result[count] = o;
				   Arrays.sort(result);
				   list.add(new LineSegment(result[0], result[count]));
				   
				   count = 1;
				   start = end;
				   end = start + 1;
			   	   }
			   }
	   }
	   if (count >= 3) {
		   Point[] result = new Point[count+1];
		   for (int i = 0; i < count; i++) {
			   result[i] = pts[start+i];
		   }
		   result[count] = o;
		   Arrays.sort(result);
		   list.add(new LineSegment(result[0], result[count]));
	   }
   }


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
	     FastCollinearPoints collinear = new FastCollinearPoints(points);
	     for (LineSegment segment : collinear.segments()) {
	         StdOut.println(segment);
	         segment.draw();
	     }
	     StdDraw.show();

	 }


}


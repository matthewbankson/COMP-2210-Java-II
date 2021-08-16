import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  YOUR NAME (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) {
      try {
         Scanner scanFile = new Scanner(new File(filename));
         int amt = scanFile.nextInt();
         points = new Point[amt];
         int x;
         int y;
         for (int i = 0; i < amt; i++) {
            x = scanFile.nextInt();
            y = scanFile.nextInt();
            Point add = new Point(x, y);
            points[i] = add;
         }
      }
      catch (java.io.IOException e) {
      
      }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      for (int a = 0; a < points.length; a++) {
         for (int b = a + 1; b < points.length; b++) {
            for (int c = b + 1; c < points.length; c++) {
               for (int d = c + 1; d < points.length; d++) {
                  Line test = new Line();
                  test.add(points[a]);
                  test.add(points[b]);
                  test.add(points[c]);
                  test.add(points[d]);
                  if (test.length() == 4) {
                     lines.add(test);
                  }
               }
            }
         }
      }
      
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      Line test = new Line();
      Point[] copy = Arrays.copyOf(points, points.length);
      boolean added = true;
      for (int i = 0; i < points.length; i++) {
         Arrays.sort(copy, points[i].slopeOrder);
         for (int j = 0; j < copy.length; j++) {
            test.add(copy[0]);
            added = test.add(copy[j]);
            if (!added) {
               if (test.length() >= 4) {
                  lines.add(test);
               }
               test = new Line();
               test.add(copy[j]);
            }
         }
      }
      return lines;
   }
   
}

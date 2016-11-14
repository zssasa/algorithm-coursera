/**
 * Created by zhangsheng on 2016/11/10.
 */

import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private LineSegment[] segments;
    
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.NullPointerException();
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new java.lang.NullPointerException();
            }

            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }

            }
        }

        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        Point[] clone = Arrays.copyOf(points, points.length);
        Arrays.sort(clone);

        for (int a = 0; a < clone.length - 3; a++) {
            for (int b = a + 1; b < clone.length - 2; b++) {
                for (int c = b + 1; c < clone.length - 1; c++) {
                    if (clone[a].slopeTo(clone[b]) == clone[a].slopeTo(clone[c])) {
                        for (int d = c + 1; d < clone.length; d++) {
                            if (clone[a].slopeTo(clone[b]) == clone[a].slopeTo(clone[d])) {
                                foundSegments.add(new LineSegment(clone[a], clone[d]));
                            }
                            }
                    }
                }
            }
        }
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;

    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }
}
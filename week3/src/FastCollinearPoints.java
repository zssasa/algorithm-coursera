/**
 * Created by zhangsheng on 2016/11/10.
 */
import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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

        for (int i = 0; i < clone.length; i++) {
            Point p = clone[i];

            Arrays.sort(clone, p.slopeOrder());

            int count = 0;
            Point min = null;
            Point max = null;

            for (int j = 1; j < clone.length - 1; j++) {
                if (p.slopeTo(clone[j]) == p.slopeTo(clone[j+1])) {
                    if (min == null) {
                        if (p.compareTo(clone[j]) < 0) {
                            min = p;
                            max = clone[j];
                        } else {
                            max = p;
                            min = clone[j];
                        }
                    }


                    if (min.compareTo(clone[j + 1]) > 0) {
                        min = clone[j + 1];
                    }

                    if (max.compareTo(clone[j + 1]) < 0) {
                        max = clone[j + 1];
                    }

                    count++;

                    if (j == clone.length - 2) {
                        if (count >= 2 && p.compareTo(min) == 0) {
                            foundSegments.add(new LineSegment(min, max));
                        }
                        count = 0;
                        min = null;
                        max = null;
                    }

                } else {
                    if (count >= 2 && p.compareTo(min) == 0) {
                        foundSegments.add(new LineSegment(min, max));
                    }
                    count = 0;
                    min = null;
                    max = null;
                }
            }
            Arrays.sort(clone);
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
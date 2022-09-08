/*
3392. 화성 지도
https://www.acmicpc.net/problem/3392
*/

import java.util.*;

public class MarsMap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numMaps = sc.nextInt();
        TreeSet<Integer> xSet = new TreeSet<>();
        Map<Integer, List<Rectangle>> xStartRectanglesMap = new HashMap<>();

        long totalArea = 0;
        for (int i = 0; i < numMaps; i++) {
            int minX = sc.nextInt();
            int minY = sc.nextInt();
            int maxX = sc.nextInt();
            int maxY = sc.nextInt();
            xStartRectanglesMap.putIfAbsent(minX, new ArrayList<>());
            xStartRectanglesMap.get(minX).add(new Rectangle(minX, minY, maxX, maxY));
            xSet.add(minX);
            xSet.add(maxX);
        }
        Range[] ranges = new Range[xSet.size() - 1];
        Iterator<Integer> it = xSet.iterator();
        int startX;
        int endX = -1;
        int index = 0;
        if (it.hasNext())
            endX = it.next();
        while (it.hasNext()) {
            startX = endX;
            endX = it.next();
            ranges[index] = new Range(startX, endX);
            index++;
        }
        for (Range r : ranges) {
            List<Rectangle> rectangles = xStartRectanglesMap.get(r.start);
            if (rectangles != null && !rectangles.isEmpty()) {
                List<Range> yRanges = new ArrayList<>(rectangles.size());
                for (Rectangle rect : rectangles) {
                    yRanges.add(new Range(rect.minY, rect.maxY));
                    if (rect.maxX > r.end) {
                        xStartRectanglesMap.putIfAbsent(r.end, new ArrayList<>());
                        xStartRectanglesMap.get(r.end).add(new Rectangle(r.end, rect.minY, rect.maxX, rect.maxY));
                    }
                }
                List<Range> mergedRanges = Range.mergeRanges(yRanges);
                for (Range range : mergedRanges) {
                    totalArea += (long) (range.end - range.start) * (r.end - r.start);
                }
            }
        }
        System.out.println(totalArea);
    }

    private static class Range {
        private int start;
        private int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public static List<Range> mergeRanges(List<Range> ranges) {
            ranges.sort(new Comparator<Range>() {
                @Override
                public int compare(Range o1, Range o2) {
                    return Integer.compare(o1.start, o2.start);
                }
            });
            List<Range> toReturn = new ArrayList<>();
            int start = -1;
            int end = -1;
            for (Range r : ranges) {
                if (start < 0) {
                    start = r.start;
                    end = r.end;
                } else {
                    if (r.start > end) {
                        toReturn.add(new Range(start, end));
                        start = r.start;
                        end = r.end;
                    } else {
                        end = Math.max(end, r.end);
                    }
                }
            }
            toReturn.add(new Range(start, end));
            return toReturn;
        }
    }

    private static class Rectangle {
        private int minX;
        private int minY;
        private int maxX;
        private int maxY;

        public Rectangle(int minX, int minY, int maxX, int maxY) {
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }
    }
}

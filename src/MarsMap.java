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
        TreeSet<Integer> ySet = new TreeSet<>();
        List<Rectangle> rectangles = new ArrayList<>();
        long totalArea = 0;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numMaps; i++) {
            int minX = sc.nextInt();
            int minY = sc.nextInt();
            int maxX = sc.nextInt();
            int maxY = sc.nextInt();
            rectangles.add(new Rectangle(minX, minY, maxX, maxY));
            xSet.add(minX);
            xSet.add(maxX);
            ySet.add(minY);
            ySet.add(maxY);
        }
        Range[] xRanges = new Range[xSet.size() - 1];
        Iterator<Integer> it = xSet.iterator();
        int startX;
        int endX = -1;
        int index = 0;
        if (it.hasNext())
            endX = it.next();
        while (it.hasNext()) {
            startX = endX;
            endX = it.next();
            xRanges[index] = new Range(startX, endX);
            index++;
        }
        Range[] yRanges = new Range[ySet.size() - 1];
        it = ySet.iterator();
        int startY;
        int endY = -1;
        index = 0;
        if (it.hasNext())
            endY = it.next();
        while (it.hasNext()) {
            startY = endY;
            endY = it.next();
            yRanges[index] = new Range(startY, endY);
            index++;
        }
        boolean[][] map = new boolean[xRanges.length][yRanges.length];
        for (Rectangle rect : rectangles) {
            int startIndex = Arrays.binarySearch(xRanges, new Range(rect.minX, rect.maxX));
            for (int i = startIndex; i < xRanges.length; i++) {
                Range currXRange = xRanges[i];
                if (rect.minX <= currXRange.start && rect.maxX >= currXRange.end){
                    int yStart = Arrays.binarySearch(yRanges, new Range(rect.minY, rect.maxY));
                    for(int j = yStart; j < yRanges.length; j++){
                        Range currYRange = yRanges[j];
                        if(rect.minY <= currYRange.start && rect.maxY >= currYRange.end && !map[i][j]){
                            totalArea += (long) (currXRange.end - currXRange.start) * (currYRange.end - currYRange.start);
                            map[i][j] = true;
                        }
                        else if (rect.maxY < currYRange.start)
                            break;
                    }
                }
                else if (rect.maxX < currXRange.start)
                    break;
            }
        }
        System.out.println(totalArea);
        System.out.println("Elapsed : " + (double) (System.currentTimeMillis() - startTime) / 1000);
    }

    private static class Range implements Comparable<Range>{
        private final int start;
        private final int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Range o) {
            return Integer.compare(start, o.start);
        }
    }

    private static class Rectangle {
        private int minX;
        private int maxX;
        private int minY;
        private int maxY;

        public Rectangle(int minX, int minY, int maxX, int maxY) {
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
        }
    }
}

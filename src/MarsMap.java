/*
3392. 화성 지도
https://www.acmicpc.net/problem/3392
*/

import java.util.*;

public class MarsMap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numMaps = Integer.parseInt(sc.nextLine());
        TreeSet<Integer> xSet = new TreeSet<>();
        TreeSet<Integer> ySet = new TreeSet<>();
        Set<Rectangle> inputRectangles = new HashSet<>();
        Set<Rectangle> splitRectangles = new HashSet<>();
        long totalArea = 0;
        for (int i = 0; i < numMaps; i++) {
            String[] splitString = sc.nextLine().split(" ");
            for (int j = 0; j < splitString.length; j++) {
                int minX = Integer.parseInt(splitString[0]);
                int minY = Integer.parseInt(splitString[1]);
                int maxX = Integer.parseInt(splitString[2]);
                int maxY = Integer.parseInt(splitString[3]);
                inputRectangles.add(new Rectangle(minX, minY, maxX, maxY));
                xSet.add(minX);
                xSet.add(maxX);
                ySet.add(minY);
                ySet.add(maxY);
            }
        }
        Iterator<Integer> it = xSet.iterator();
        int startX = -1;
        int endX = -1;
        if (it.hasNext())
            endX = it.next();
        while (it.hasNext()) {
            startX = endX;
            endX = it.next();

            Iterator<Integer> it2 = ySet.iterator();
            int startY = -1;
            int endY = -1;
            if (it2.hasNext())
                endY = it2.next();
            while (it2.hasNext()) {
                startY = endY;
                endY = it2.next();
                splitRectangles.add(new Rectangle(startX, startY, endX, endY));
            }
        }
        for (Rectangle r : splitRectangles) {

        }
        System.out.println(totalArea);
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

        public Rectangle getRectangleInSpace(int minX, int minY, int maxX, int maxY) {
            if (this.minX >= maxX || this.maxX <= minX || this.minY >= maxY || this.maxY <= minY)
                return null;
            else
                return new Rectangle(minX, minY, maxX, maxY);
        }

        public long getArea() {
            return (long) (maxY - minY) * (maxX - minX);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Rectangle))
                return false;
            return minX == ((Rectangle) obj).minX &&
                    minY == ((Rectangle) obj).minY &&
                    maxX == ((Rectangle) obj).maxX &&
                    maxY == ((Rectangle) obj).maxY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(minX, minY, maxX, maxY);
        }
    }
}

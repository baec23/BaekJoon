/*
3392. 화성 지도
https://www.acmicpc.net/problem/3392
*/

import java.util.Scanner;

public class MarsMap2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numMaps = sc.nextInt();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numMaps; i++) {
            int minX = sc.nextInt();
            int minY = sc.nextInt();
            int maxX = sc.nextInt();
            int maxY = sc.nextInt();
            for (int j = minY; j < maxY; j++) {
                int start = (j * 30000) + minX;
                int end = (j * 30000) + maxX;
            }
        }
        //System.out.println(totalArea);
        SegmentTree test = new SegmentTree(50);
        test.setRange(0, 0, test.segTree.length-1, 0, 20);
        System.out.println(test.getTotal(0,0,test.segTree.length-1, 0, 49));
        System.out.println("Elapsed : " + (double) (System.currentTimeMillis() - startTime) / 1000);
    }

    private static class SegmentTree {
        private int[] segTree;

        public SegmentTree(int numElements) {
            segTree = new int[numElements * 4];
        }

        public void setRange(int currVertex, int currLeft, int currRight, int startIndex, int endIndex) {
            long total = getTotal(currVertex, currLeft, currRight, startIndex, endIndex);
            if (total == endIndex - startIndex)
                return;
            if (total == 0) {
                for (int i = startIndex; i <= endIndex; i++) {
                    set(0, 0, segTree.length-1, i);
                }
            }
            else{
                int mid = (currLeft + currRight) / 2;
                setRange(currVertex * 2, currLeft, mid, startIndex, Math.min(endIndex, mid));
                setRange(currVertex * 2+1, mid+1, currRight, Math.max(currLeft, mid+1), endIndex);
            }

        }

        private void set(int currVertex, int currLeft, int currRight, int setPos) {
            if (currLeft == currRight) {
                segTree[currVertex] = 1;
            } else {
                int mid = (currLeft + currRight) / 2;
                if (setPos <= mid) {
                    set(currVertex * 2, currLeft, mid, setPos);
                } else {
                    set(currVertex * 2 + 1, mid + 1, currRight, setPos);
                }
                segTree[currVertex] = segTree[currVertex * 2] + segTree[currVertex * 2 + 1];
            }
        }

        public long getTotal(int currVertex, int currLeft, int currRight, int queryLeft, int queryRight) {
            if (queryLeft > queryRight)
                return 0;
            if (currLeft == queryLeft && currRight == queryRight) {
                return segTree[currVertex];
            }
            int mid = (currLeft + currRight) / 2;
            return getTotal(currVertex * 2, currLeft, mid, queryLeft, Math.min(queryRight, mid)) +
                    getTotal(currVertex * 2 + 1, mid + 1, currRight, Math.max(currLeft, mid + 1), queryRight);
        }
    }
}

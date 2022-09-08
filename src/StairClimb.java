/*
2579. 계단 오르기
https://www.acmicpc.net/problem/2579
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StairClimb {
    private static Map<Integer, Integer> indexTotalMap = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numStairs = sc.nextInt();
        int[] stairs = new int[numStairs];
        for (int i = 0; i < numStairs; i++) {
            stairs[i] = sc.nextInt();
        }
        System.out.println(Math.max(helper(stairs, 0, 0), helper(stairs, 1, 0)));
    }

    private static int helper(int[] stairs, int currIndex, int numConsecutive) {
        if (currIndex >= stairs.length)
            return Integer.MIN_VALUE;
        if(currIndex == stairs.length-1)
            return stairs[currIndex];
        int index;
        if(numConsecutive == 0)
            index = currIndex;
        else
            index = currIndex * -1;
        if(indexTotalMap.containsKey(index))
            return indexTotalMap.get(index);
        int toReturn;
        if(numConsecutive == 1)
            toReturn = stairs[currIndex] + helper(stairs, currIndex + 2, 0);
        else
            toReturn = stairs[currIndex] + Math.max(helper(stairs, currIndex + 1, numConsecutive + 1), helper(stairs, currIndex + 2, 0));
        if(numConsecutive == 0)
            indexTotalMap.put(currIndex, toReturn);
        else
            indexTotalMap.put(currIndex * -1, toReturn);
        return toReturn;
    }
}
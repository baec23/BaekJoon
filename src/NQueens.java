/*
9663. N-Queen
https://www.acmicpc.net/problem/9663
 */

import java.util.*;

public class NQueens {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numQueens = sc.nextInt();
        System.out.println(helper(numQueens, 0, 0, new int[numQueens][2]));
    }

    private static int helper(int totalNumQueens, int currNumQueens, int row, int[][] currQueens) {
        if (currNumQueens == totalNumQueens)
            return 1;
        int toReturn = 0;
        for (int currCol = 0; currCol < totalNumQueens; currCol++) {
            boolean canPlace = true;
            for(int i=0; i<currNumQueens; i++){
                int[] queen = currQueens[i];
                if(queen[0] == row || queen[1] == currCol || (Math.abs(queen[0] - row) == Math.abs(queen[1] - currCol))){
                    canPlace = false;
                    break;
                }
            }
            if (canPlace) {
                currQueens[currNumQueens][0] = row;
                currQueens[currNumQueens][1] = currCol;
                int result = helper(totalNumQueens, currNumQueens + 1, row + 1, currQueens);
                toReturn += result;
            }
        }
        return toReturn;
    }
}

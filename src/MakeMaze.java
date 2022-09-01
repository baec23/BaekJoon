/*
1347. 미로 만들기
https://www.acmicpc.net/problem/1347
*/

import java.util.Scanner;

public class MakeMaze {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numMoves = Integer.parseInt(sc.nextLine());
        String moves = sc.nextLine();

        int[][] visitedGrid = new int[100][100];
        Position startPosition = new Position(50, 50);
        HongJoon hj = new HongJoon(startPosition, 180, visitedGrid);

        for (char move : moves.toCharArray()) {
            switch (move) {
                case 'R' -> hj.turnRight();
                case 'L' -> hj.turnLeft();
                case 'F' -> hj.moveForward();
            }
        }

        int topRowIndex = visitedGrid.length-1;
        int bottomRowIndex = 0;
        int leftColIndex = visitedGrid[0].length-1;
        int rightColIndex = 0;

        for(int i=0; i< visitedGrid.length; i++){
            for(int j=0; j<visitedGrid[0].length; j++){
                if(visitedGrid[i][j] == 1){
                    if(i < topRowIndex)
                        topRowIndex = i;
                    if(i > bottomRowIndex)
                        bottomRowIndex = i;
                    if(j < leftColIndex)
                        leftColIndex = j;
                    if(j > rightColIndex)
                        rightColIndex = j;
                }
            }
        }
        for(int i=topRowIndex; i<=bottomRowIndex; i++){
            for(int j=leftColIndex; j<=rightColIndex; j++){
                if(visitedGrid[i][j] == 1)
                    System.out.print(".");
                else
                    System.out.print("#");
            }
            System.out.println();
        }
    }

    private static int normalizeDirection(int direction) {
        while (direction < 0) {
            direction += 360;
        }
        return direction % 360;
    }

    private static class HongJoon {
        private Position currPosition;
        private int facingDirection;
        private int[][] visitedGrid;

        public HongJoon(Position currPosition, int facingDirection, int[][] visitedGrid) {
            this.currPosition = currPosition;
            this.facingDirection = facingDirection;
            this.visitedGrid = visitedGrid;
            updateVisitedGrid();
        }

        public void turnLeft() {
            facingDirection = normalizeDirection(facingDirection - 90);
        }

        public void turnRight() {
            facingDirection = normalizeDirection(facingDirection + 90);
        }

        public void moveForward() {
            switch (facingDirection) {
                case 0 ->     //NORTH
                        currPosition.row -= 1;
                case 90 ->    //EAST
                        currPosition.col += 1;
                case 180 ->   //SOUTH
                        currPosition.row += 1;
                case 270 ->   //WEST
                        currPosition.col -= 1;
                default -> System.out.println("WHAT");
            }
            updateVisitedGrid();
        }

        private void updateVisitedGrid() {
            visitedGrid[currPosition.row][currPosition.col] = 1;
        }
    }

    private static class Position {
        private int row;
        private int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
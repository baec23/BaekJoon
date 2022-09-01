/*
14891. 톱니바퀴
https://www.acmicpc.net/problem/14891
*/

import java.util.Scanner;

public class Gears {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String gear1String = sc.nextLine().trim();
        String gear2String = sc.nextLine().trim();
        String gear3String = sc.nextLine().trim();
        String gear4String = sc.nextLine().trim();
        Gear gear1 = new Gear(gear1String);
        Gear gear2 = new Gear(gear2String);
        Gear gear3 = new Gear(gear3String);
        Gear gear4 = new Gear(gear4String);
        gear1.rightGear = gear2;
        gear2.leftGear = gear1;
        gear2.rightGear = gear3;
        gear3.leftGear = gear2;
        gear3.rightGear = gear4;
        gear4.leftGear = gear3;

        Gear[] gears = {null, gear1, gear2, gear3, gear4};

        int numCommands = sc.nextInt();
        for (int i = 0; i < numCommands; i++) {
            int gearNum = sc.nextInt();
            int direction = sc.nextInt();
            if (direction > 0)
                gears[gearNum].rotateCw(From.INPUT);
            else
                gears[gearNum].rotateCcw(From.INPUT);
        }

        int toPrint = 0;
        if (gear1.getTopPolarity() == '1')
            toPrint += 1;
        if (gear2.getTopPolarity() == '1')
            toPrint += 2;
        if (gear3.getTopPolarity() == '1')
            toPrint += 4;
        if (gear4.getTopPolarity() == '1')
            toPrint += 8;
        System.out.println(toPrint);
    }

    private static class Gear {
        private char[] polarities;
        private int leftPolarityIndex;
        private int rightPolarityIndex;
        private int topPolarityIndex;
        private Gear leftGear;
        private Gear rightGear;

        public Gear(String s) {
            polarities = s.toCharArray();
            leftPolarityIndex = 6;
            rightPolarityIndex = 2;
            topPolarityIndex = 0;
        }

        public void rotateCw(From from) {
            switch (from) {
                case LEFT -> notifyRightNeighbor(RotationDirection.CW);
                case RIGHT -> notifyLeftNeighbor(RotationDirection.CW);
                case INPUT -> {
                    notifyLeftNeighbor(RotationDirection.CW);
                    notifyRightNeighbor(RotationDirection.CW);
                }
            }
            leftPolarityIndex -= 1;
            if (leftPolarityIndex < 0)
                leftPolarityIndex += polarities.length;

            rightPolarityIndex -= 1;
            if (rightPolarityIndex < 0)
                rightPolarityIndex += polarities.length;

            topPolarityIndex -= 1;
            if (topPolarityIndex < 0)
                topPolarityIndex += polarities.length;
        }

        public void rotateCcw(From from) {
            switch (from) {
                case LEFT -> notifyRightNeighbor(RotationDirection.CCW);
                case RIGHT -> notifyLeftNeighbor(RotationDirection.CCW);
                case INPUT -> {
                    notifyLeftNeighbor(RotationDirection.CCW);
                    notifyRightNeighbor(RotationDirection.CCW);
                }
            }
            leftPolarityIndex = (leftPolarityIndex + 1) % polarities.length;
            rightPolarityIndex = (rightPolarityIndex + 1) % polarities.length;
            topPolarityIndex = (topPolarityIndex + 1) % polarities.length;

        }

        public char getLeftPolarity() {
            return polarities[leftPolarityIndex];
        }

        public char getRightPolarity() {
            return polarities[rightPolarityIndex];
        }

        public char getTopPolarity() {
            return polarities[topPolarityIndex];
        }

        private void notifyLeftNeighbor(RotationDirection myRotationDirection) {

            if (leftGear != null) {
                if (getLeftPolarity() != leftGear.getRightPolarity()) {
                    if (myRotationDirection == RotationDirection.CW)
                        leftGear.rotateCcw(From.RIGHT);
                    else
                        leftGear.rotateCw(From.RIGHT);
                }
            }
        }

        private void notifyRightNeighbor(RotationDirection myRotationDirection) {
            if (rightGear != null) {
                if (getRightPolarity() != rightGear.getLeftPolarity()) {
                    if (myRotationDirection == RotationDirection.CW)
                        rightGear.rotateCcw(From.LEFT);
                    else
                        rightGear.rotateCw(From.LEFT);
                }
            }
        }

        private enum RotationDirection {
            CW,
            CCW
        }
    }

    private enum From {
        LEFT,
        RIGHT,
        INPUT
    }
}
/*
1557. 제곱ㄴㄴ
https://www.acmicpc.net/problem/1557
*/

import java.util.BitSet;
import java.util.Scanner;

public class ExponentNoNo {
    private static final BitSet sieve = new BitSet(1650000000);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        calculateSieve(input);
        int numExponentNoNos = 0;
        int toReturn = 1;
        while (true) {
            if (!sieve.get(toReturn))
                numExponentNoNos++;
            if (numExponentNoNos == input) {
                System.out.print(toReturn);
                break;
            }
            toReturn++;
        }
    }

    private static void calculateSieve(int input) {
        int currNum = 2;
        int currSquare;
        int loopEnd = Math.min(input*2, 40620);
        while (currNum < loopEnd) {
            currSquare = currNum * currNum;
            int mult = currSquare;
            while (mult > 0 && mult < loopEnd*loopEnd) {
                sieve.set(mult);
                mult += currSquare;
            }
            currNum++;
        }
    }
}
/*
1557. 제곱ㄴㄴ
https://www.acmicpc.net/problem/1557
*/

import java.util.BitSet;
import java.util.Scanner;

public class ExponentNoNo
{
    private static BitSet sieve;
    private static BitSet primeSieve;
    private static int numSkipped = 0;
    private static int lastSkippedIndex = 0;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if(input <= 3){
            System.out.println(input);
            return;
        }
        if(input == 4){
            System.out.println(5);
            return;
        }

        sieve = new BitSet(input*2);
        primeSieve = new BitSet((int)Math.sqrt(input*2));
        calculateSieves(input);

        int index = lastSkippedIndex;
        while(index - numSkipped > input){
            while(sieve.get(index)){
                index--;
                numSkipped--;
            }
            index--;
        }
        while(sieve.get(index))
            index--;
        System.out.println(index);
    }

    private static void calculateSieves(int input)
    {
        double limit = Math.sqrt(input * 2);
        int currNum = 2;
        while(currNum < limit)
        {
            markPrimeSieve(currNum, limit);
            markSieve(currNum * currNum, input * 2);
            currNum++;
            while(primeSieve.get(currNum))
            {
                currNum++;
            }
        }
    }

    private static void markPrimeSieve(int currNum, double limit)
    {
        int mult = currNum + currNum;
        while(mult < limit)
        {
            primeSieve.set(mult);
            mult += currNum;
        }
    }

    private static void markSieve(long currSquare, long limit)
    {
        long mult = currSquare;
        while(mult < limit && mult < 1650000000)
        {
            if(!sieve.get((int)mult))
                numSkipped++;
            sieve.set((int) mult);
            lastSkippedIndex = Math.max(lastSkippedIndex, (int) mult);
            mult += currSquare;
        }
    }
}
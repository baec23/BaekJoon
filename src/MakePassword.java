/*
1759. 암호 만들기
https://www.acmicpc.net/problem/1759
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MakePassword {
    private static List<String> toPrint = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] splitString = line.split(" ");
        int passwordLength = Integer.parseInt(splitString[0]);
        int numChars = Integer.parseInt(splitString[1]);
        line = sc.nextLine();
        splitString = line.split(" ");
        char[] chars = new char[numChars];
        for (int i = 0; i < numChars; i++) {
            chars[i] = splitString[i].toCharArray()[0];
        }

        boolean[] charUsed = new boolean[numChars];
        helper(chars, charUsed, "", passwordLength);
        Collections.sort(toPrint);
        for (String s : toPrint) {
            System.out.println(s);
        }
    }

    private static void helper(char[] chars, boolean[] charUsed, String currString, int targetLength) {
        if (currString.length() == targetLength) {
            if (isValid(currString))
                toPrint.add(currString);
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!charUsed[i] && (currString.isEmpty() || chars[i] > currString.charAt(currString.length() - 1))) {
                boolean[] charUsedToPass = charUsed.clone();
                charUsedToPass[i] = true;
                String toPass = currString + chars[i];
                helper(chars, charUsedToPass, toPass, targetLength);
            }
        }
    }

    private static boolean isValid(String s) {
        int numVowels = 0;
        int numConsonants = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                numVowels++;
            else
                numConsonants++;
        }
        return numVowels >= 1 && numConsonants >= 2;
    }
}
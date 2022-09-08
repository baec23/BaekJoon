/*
1395. Light Switching
https://www.acmicpc.net/problem/1395
 */

import java.util.*;

public class LightSwitching {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSwitches = sc.nextInt();
        int numCommands = sc.nextInt();
        BitSet switches = new BitSet(numSwitches+1);
        for(int i=0; i<numCommands; i++){
            int commandType = sc.nextInt();
            int startIndex = sc.nextInt();
            int endIndex = sc.nextInt();
            if(commandType == 0){
                switches.flip(startIndex, endIndex+1);
            }
            else{
                int toPrint = (int) switches.stream().filter(index -> index >= startIndex && index <= endIndex).count();
                System.out.println(toPrint);
            }
        }
    }
}

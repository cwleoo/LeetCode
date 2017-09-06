/**
 * Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:
 * 
 * Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 * 
 * Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.
 * 
 * Example 1:
 * Input: 3
 * Output: 3
 * Explanation:
 * Intitally, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 * 
 * Note:
 * The n will be in the range [1, 1000].
 * */
import java.util.*;

public class Q650TwoKeysKeyboard {
    
    // 1. straight forward 16ms
    public int minSteps1(int n) {
        int[] steps = new int[n + 1];
        for(int i = 2; i <= n; i++) {
            int min = i;
            for(int j = 2; j * j <= i; j++) {
                if(i % j == 0) {
                    min = Math.min(min, Math.min(steps[j] + i / j, steps[i / j] + j));
                }
            }
            steps[i] = min;
        }
        return steps[n];
    }
    
    // 2. top-down no dp version, slightly promoted 9ms
    public int minSteps(int n) {
        int result = 0;
        for (int divisor = 2; divisor <= n; divisor++) {
            while (n % divisor == 0) {
                result += divisor;
                n /= divisor;
            }
        }
        return result;
    }
    
}
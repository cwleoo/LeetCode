/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ¡Ü x < 10^n.
 * Example:
 * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ¡Ü x < 100, excluding [11,22,33,44,55,66,77,88,99])
 */

import java.util.*;

public class Q357CountNumbersWithUniqueDigits {
    
    // static cache here
    private static int[] numbers = new int[11];
    
    public int countNumbersWithUniqueDigits(int n) {
        if(n > 10) {
            n = 10;
        }
        if(numbers[n] != 0) {
            return numbers[n];
        }
        numbers[0] = 1;
        int numbersWithoutZero = 9;
        int distinctDigits = 9;
        for(int i = 1; i <= n; i++) {
            if(i > 1) {
                numbersWithoutZero *= distinctDigits--;
            }
            if(numbers[i] == 0) {
                numbers[i] = numbersWithoutZero + numbers[i - 1];
            }
        }
        return numbers[n];
    }
    
}
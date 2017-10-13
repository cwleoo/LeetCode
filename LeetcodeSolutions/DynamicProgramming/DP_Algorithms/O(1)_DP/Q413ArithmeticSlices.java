/**
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * Example:
 * 
 * A = [1, 2, 3, 4]
 * 
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */

import java.util.*;

public class Q413ArithmeticSlices {
    
    // 1. O(n) time straight forward
    public int numberOfArithmeticSlices1(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int length = A.length;
        int start = 0;
        int end = 2;
        Integer difference = null;
        int result = 0;
        while(end < length) {
            if(difference == null) {
                if(A[end] - A[end - 1] != A[start + 1] - A[start]) {
                    start++;
                    end++;
                } else {
                    difference = A[end] - A[end - 1];
                    end++;
                }
            } else {
                if(A[end] - A[end - 1] != difference) {
                    result += sum(end - start - 2);
                    start = end - 1;
                    end = start + 2;
                    difference = null;
                } else {
                    end++;
                }
            }
        }
        if(end >= start + 2) {
            result += sum(end - start - 2);
        }
        return result;
    }
    
    private int sum(int n) {
        return (1 + n) * n / 2;
    }
    
    // 2. O(n) space O(n) time dp
    public int numberOfArithmeticSlices2(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int length = A.length;
        int[] sliceCount = new int[length];  // sliceCount[i] represents the count of slices which end with i
        int result = 0;
        for(int i = 2; i < length; i++) {
            if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                sliceCount[i] = sliceCount[i - 1] + 1;
            }
            result += sliceCount[i];
        }
        return result;
    }
    
    // 3. O(1) space dp, promoted from 2
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3) {
            return 0;
        }
        int length = A.length;
        int sliceCount = 0;
        int result = 0;
        for(int i = 2; i < length; i++) {
            if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                sliceCount++;
            } else {
                sliceCount = 0;
            }
            result += sliceCount;
        }
        return result;
    }
    
}
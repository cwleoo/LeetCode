/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * 
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; 
 * given n = 13, return 2 because 13 = 4 + 9.
 */

import java.util.*;

public class Q279PerfectSquares {
    
    // 1. O(n^3/2) dp optimal routine is 85ms.
    public int numSquares1(int n) {
        if(n < 1) {
            return 0;
        }
        int[] squares = new int[n + 1];
        squares[0] = 0;
        for(int i = 1; i <= n; i++) {
            squares[i] = i;
            //for(int j = 1; j * j <= i; j++) {
            for(int j = (int)Math.sqrt(i); j >= 0; j--){
                squares[i] = Math.min(squares[i], squares[i - j * j] + 1);
                if(squares[i] == 1) {
                    break;
                }
            }
        }
        return squares[n];
    }
    
    // 2. Tricky DP: use static array! 20ms
    // It will faster if huge number of cases are executed because previous functions call
    private static final List<Integer> CACHE = new ArrayList<>(Arrays.asList(0));
    
    public int numSquares2(int n) {
        if(n < 1) {
            return 0;
        }
        
        while (CACHE.size() <= n) {
            int size = CACHE.size();
            int minCount = Integer.MAX_VALUE;
            for (int i = 1; i*i <= size; i++) {
                minCount = Math.min(minCount, CACHE.get(size - i*i) + 1);
            }
            CACHE.add(minCount);
        }
        
        return CACHE.get(n);
    }

    // 3.(2ms) Based on Lagrange's Four Square theorem, there 
    // are only 4 possible results: 1, 2, 3, 4.
    public int numSquares3(int n) 
    {  
        // If n is a perfect square, return 1.
        if(isSquare(n)) {
            return 1;  
        }
        
        /*
         * The result is 4 if and only if n can be written in the 
         * form of (4^k)*(8*m + 7). Please refer to 
         * Legendre's three-square theorem. https://en.wikipedia.org/wiki/Legendre%27s_three-square_theorem
         * Lagrange's four-square theorem. https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem
         * i.e. any nature number can be represented as n = a^2 + b^2 + c^2 + d^2;
         *      a number can be written as n = x^2 + y^2 + z^2 iff n = (4^k) * (8 * m + 7).
         */
        while ((n & 3) == 0) {  // n%4 == 0  
            n >>= 2;  
        }
        if ((n & 7) == 7) {  // n%8 == 7
            return 4;
        }
        
        // Check whether 2 is the result.
        int sqrtN = (int)(Math.sqrt(n)); 
        for(int i = 1; i <= sqrtN; i++) {  
            if (isSquare(n - i*i)) {
                return 2;  
            }
        }
        return 3;  
    }
    
    private boolean isSquare(int n) {  
        int sqrtN = (int)(Math.sqrt(n));  
        return sqrtN * sqrtN == n;  
    }
    
    // 4. BFS: Consider a graph which consists of numbers 1...n. (54ms)
    //    use BFS to find the shortest path from each perfect numbers (1,4,9,...,i*i) to n.
    //    node i is connected to j iff: i + (a perfect square number) = j
    public int numSquares(int n) {
        if(n < 1) {
            return 0;
        }
        List<Integer> squares = new ArrayList<>();  // all perfect sqares that no more than n
        int[] numberOfSquares = new int[n];         // numberOfSquares[i]: the number of perfect squares that sum to (i+1)
        // initial two DP arrays
        for(int i = 1; i * i <= n; i++) {
            squares.add(i * i);
            numberOfSquares[i * i - 1] = 1;
        }
        if(squares.get(squares.size() - 1) == n) {
            return 1;
        }
        
        int currentNumbers = 1;   // the numbers of perfect squares that sum to current value
        // the operation queue for BFS
        Queue<Integer> numbers = new LinkedList<>();
        for(int square : squares) {
            numbers.offer(square);
        }
        while(!numbers.isEmpty()) {
            currentNumbers++;
            int size = numbers.size();
            for(int i = 0; i < size; i++) {
                int current = numbers.poll();
                for(int square : squares) {
                    if(current + square == n) {
                        return currentNumbers;
                    } else if(current + square < n 
                              && numberOfSquares[current + square - 1] == 0) {  // here need to guarantee the node has not been traversed
                        numberOfSquares[current + square - 1] = currentNumbers;
                        numbers.offer(current + square);
                    }
                }
            }
        }
        return 0; // never hit this if input is valid.
    }
    
}
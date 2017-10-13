# 3. Algorithms:

## 1) [Indexed DP (O(n) DP):](O(n)_DP/README.md) 

initial an extra array for DP with the same size as input array. Sometimes several such arrays are used but each element are associated to element in input array which has same index. It is the most common case when confronted with a DP problem.

(Q53 maximum subarray, Q62/63 unique paths I/II, Q64 minimum path sum, Q91 decode ways, Q95 unique binary search trees II (also available in 2D DP), Q120 triangle, Q123 best time to buy and sell stock III, Q139 word break, Q264 ugly number II, Q300 longest increasing subsequence, Q322 coin change, Q338 counting bits, Q368 largest divisible subset, Q377 combination sum IV, Q650 2 keys keyboard)

## 2) [O(1) space DP:](O(1)_DP/README.md)

only use several variables to cache/store previous result of calculation. It is often an optimization from O(n) DP for less space use. Normally the condition to use this is that desired value only need few preceding calculated values. E.g. f(n)=f(n-1)+f(n-2) or f(n)=min(f(n-1),f(n-2)) 

(Q70 climbing stairs, Q121 best time to buy and sell stock, Q198/213 house robber I/II, Q357 count numbers with unique digits, Q376 wiggle subsequence, Q413 arithmetic slices, Q646 maximum length of pair chain)

## 3) [2D DP for 1D input:](2D_DP_for_1D_input/README.md)

sometimes need 2D array DP to solve problem with 1D input array. These cases can happen if index mapping happened and the mapping is an instance of Cartesian Product (A x A) or simply a triangle relationship (e.g. for each `i <= j`).

(Q10 regular expression matching, Q95 unique binary search trees II (also available in 1D DP), Q140 word break II, Q516 longest palindromic subsequence) 

## 4) [HashMap for DP:](HashMap_for_DP/README.md)

directly cache intermediate result with Hash Map. For O(1) time retrieving. Pay extra attention to the initial condition, and be extremely careful about the differences between escape status and end status.

(Q140 word break II, Q464 can I win, Q523 continuous subarray sum, Q638 shopping offers)

## 5) [Fix-sized DP:](Fix-sized_DP/README.md)

especially for handling problems that has fix-sized range of elements: like string or digits (string with 256 characters and digits with 10 numbers). Fix-sized DP provides a useful classification of elements so that it's easier to handle some problems. 

(Q3 longest substring without repeating characters, Q467 unique substrings in wraparound string)

## 6) [Multiple DP Arrays:](Multiple_DP_Arrays/README.md)

Particularly, sometimes need multiple DP arrays to handle single problem. In such problem, often multiple states or multiple kinds of elements are needed, and using only one pointer with only one DP array would be hard to solve. 

(see Q309 best time to buy and sell stock with cooldown)

## 7) [Custom type DP:](Custom_type_DP/README.md)

to keep track of multiple information in a DP array, sometimes use custom type/class can simplify the problem. 

(Q368 largest divisible subset)

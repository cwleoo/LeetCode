## 2) O(1) space DP:

only use several variables to cache/store previous result of calculation. It is often an optimization from O(n) DP for less space use. Normally the condition to use this is that desired value only need few preceding calculated values. E.g. f(n)=f(n-1)+f(n-2) or f(n)=min(f(n-1),f(n-2)) 

([Q70 climbing stairs](Q70ClimbingStairs.java), [Q121 best time to buy and sell stock](Q121BestTimeToBuyAndSellStock.java), [Q198/213 house robber I](Q198HouseRobber.java)/[II](Q213HouseRobberII.java), [Q357 count numbers with unique digits](Q357CountNumbersWithUniqueDigits.java), [Q376 wiggle subsequence](Q376WiggleSubsequence.java), [Q413 arithmetic slices](Q413ArithmeticSlices.java), [Q646 maximum length of pair chain](Q646MaximumLengthOfPairChain.java))
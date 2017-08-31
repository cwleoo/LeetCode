### 4. Tricky tips:

## 1) Space use promotion: 

sometimes it is necessary to promote the extra space use. If a DP algorithm only including several preceding elements, then it is possible to promote space from O(n) to O(1). Doing so can promote time performance, too. However, as for 2D arrays, the promotion from O(n^2) space to O(n) may or may not promote running time. It is worth to discuss the trade-off between these two approaches. (see Q221 maximal square)

## 2) Static cache DP: 

if a routine will be executed many times, and each time they share a common DP array, we can cache the DP array with a static field. So the whole running time will be promoted a lot. (see Q279 perfect squares, Q392 is subsequence)

## 3) Use binary search in DP: 

	
## 4) [Use DP for back tracking:](DP_for_backTracking/README.md)

if we use back tracking only for count of result or whether a certain result can be reached, then DP can help promote both time and (sometimes) space. DP will be used in such way: 

    a. for each input, we need to check/update the elements in DP array; 

    b. then we will traverse elements in DP arrays backwards and update them based on the conditions.


## 5) Use DP for minimax/game tree: 

for certain games with minimax tree applied, some nodes in tree are shared with different subtrees. So cache for these nodes is necessary. Usually we use HashMap or 2D arrays to cache them. (see Q464 can I win, Q486 predict the winner) 

For minimax tree/game tree:

    a. the value of a leaf is the payoff to the first player when the game terminates in the position represented by this leaf.

    b. the value of an internal vertex at an even level is the maximum of the values of its children, and the value of an internal vertex at an odd level is the minimum of the values of its children. (because the opposite will perform wisely to maximize his score)

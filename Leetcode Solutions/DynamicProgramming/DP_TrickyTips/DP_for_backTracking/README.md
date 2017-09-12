## 4. Use DP for back tracking: 

if we use back tracking only for count of result or whether a certain result can be reached, then DP can help promote both time and (sometimes) space. DP will be used in such way: 

    a. for each input, we need to check/update the elements in DP array; 
    b. then we will traverse elements in DP arrays backwards and update them based on the conditions.

We can do this because we don't need to get every desired result. See below "back Tracking". 
(See [Q368 largest divisible subset](../../DP_Algorithms/Custom_type_DP/Q368LargestDivisibleSubset.java), [Q416 partition equal subset sum](Q416PartitionEqualSubsetSum.java), [Q474 ones and zeroes](Q474OnesAndZeroes.java), [Q494 target sum](Q494TargetSum.java))

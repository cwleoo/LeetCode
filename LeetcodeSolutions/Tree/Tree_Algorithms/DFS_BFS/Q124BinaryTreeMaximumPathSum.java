/**
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * 
 * For example:
 * Given the below binary tree,
 *     1
 *    / \
 *   2   3
 * Return 6.
 */

package DFS_BFS;

import java.util.*;

public class Q124BinaryTreeMaximumPathSum {
    
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxPathDown(root);
        return maxSum;
    }
    
    private int maxPathDown(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftSum = Math.max(0, maxPathDown(root.left));
        int rightSum = Math.max(0, maxPathDown(root.right));
        maxSum = Math.max(maxSum, leftSum + rightSum + root.val);
        return Math.max(leftSum, rightSum) + root.val;
    }
    
}
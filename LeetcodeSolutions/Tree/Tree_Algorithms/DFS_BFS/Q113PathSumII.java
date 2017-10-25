/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 *            5
 *           / \
 *          4   8
 *         /   / \
 *        11  13  4
 *       /  \    / \
 *      7    2  5   1
 *      
 * return
 * [
 *     [5,4,11,2],
 *     [5,8,4,5]
 * ]
 */

package DFS_BFS;

import java.util.*;

public class Q113PathSumII {
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumHelper(root, sum, result, path);
        return result;
    }
    
    private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> result, List<Integer> path) {
        if(root == null) {
            return;
        }
        sum -= root.val;
        path.add(root.val);
        if(root.left == null && root.right == null && sum == 0) {
            result.add(new ArrayList<>(path));
        } else {
            pathSumHelper(root.left, sum, result, path);
            pathSumHelper(root.right, sum, result, path);
        }
        path.remove(path.size() - 1);
    }
    
}
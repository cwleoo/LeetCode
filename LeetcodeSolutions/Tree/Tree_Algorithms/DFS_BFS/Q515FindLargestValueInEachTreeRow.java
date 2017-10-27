/**
 * You need to find the largest value in each row of a binary tree.
 * 
 * Example:
 * Input: 
 *        1
 *       / \
 *      3   2
 *     / \   \  
 *    5   3   9 
 * Output: [1, 3, 9]
 */

package DFS_BFS;

import java.util.*;

public class Q515FindLargestValueInEachTreeRow {
    
    // simple BFS
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        int max = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(max);
            max = Integer.MIN_VALUE;
        }
        return result;
    }
    
}
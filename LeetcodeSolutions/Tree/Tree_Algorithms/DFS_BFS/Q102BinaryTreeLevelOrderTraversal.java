/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 * 
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */

package DFS_BFS;

import java.util.*;

public class Q102BinaryTreeLevelOrderTraversal {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> level = new LinkedList<>();
        level.add(root);
        while(!level.isEmpty()) {
            int size = level.size();
            List<Integer> newLevel = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = level.poll();
                newLevel.add(node.val);
                if(node.left != null) {
                    level.add(node.left);
                }
                if(node.right != null) {
                    level.add(node.right);
                }
            }
            result.add(newLevel);
        }
        return result;
    }
    
}
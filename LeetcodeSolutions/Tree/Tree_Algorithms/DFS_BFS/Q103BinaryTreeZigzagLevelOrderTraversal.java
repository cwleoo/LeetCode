/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 * 
 * return its zigzag level order traversal as:
 * 
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */

package DFS_BFS;

import java.util.*;

public class Q103BinaryTreeZigzagLevelOrderTraversal {
    
    // 1. BFS
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        boolean flip = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> newLevel = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                newLevel.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            if(flip) {
                Collections.reverse(newLevel);
            }
            result.add(newLevel);
            flip = !flip;
        }
        return result;
    }
    
    //2. DFS
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        DFS(root, result, 0);
        return result;
    }
    
    private void DFS(TreeNode root, List<List<Integer>> result, int level) {
        if(root == null) {
            return;
        }
        if(result.size() <= level) {
            List<Integer> oneLevel = new ArrayList<>();
            result.add(oneLevel);
        }
        List<Integer> currentLevel = result.get(level);
        if(level % 2 == 0) {
            currentLevel.add(root.val);
        } else {
            currentLevel.add(0, root.val);
        }
        DFS(root.left, result, level + 1);
        DFS(root.right, result, level + 1);
    }
    
}
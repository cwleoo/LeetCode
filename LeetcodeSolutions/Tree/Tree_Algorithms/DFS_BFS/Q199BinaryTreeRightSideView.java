/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree,
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * You should return [1, 3, 4].
 */

package DFS_BFS;

import java.util.*;

public class Q199BinaryTreeRightSideView {
    
    // 1. BFS: O(n) time and space
    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(i == 0) {
                    result.add(node.val);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
                if(node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return result;
    }
    
    // 2. recursive: depth-based, the first one that hit the deepest depth will be added.
    //    O(n) time and space in the worst case
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        getRightMost(root, result, 0);
        return result;
    }
    
    private void getRightMost(TreeNode current, List<Integer> result, int currentDepth) {
        if(current == null) {
            return;
        }
        if(currentDepth == result.size()) {
            result.add(current.val);
        }
        getRightMost(current.right, result, currentDepth + 1);
        getRightMost(current.left, result, currentDepth + 1);
    }
    
}
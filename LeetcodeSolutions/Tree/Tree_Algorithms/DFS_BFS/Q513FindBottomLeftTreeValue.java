/**
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * 
 * Example 1:
 * Input:
 *   2
 *  / \
 * 1   3
 * Output:
 * 1
 * 
 * Example 2: 
 * Input:
 *      1
 *     / \
 *    2   3
 *   /   / \
 *  4   5   6
 *     /
 *    7
 * Output:
 * 7
 * 
 * Note: You may assume the tree (i.e., the given root node) is not NULL.
 */

package DFS_BFS;

import java.util.*;

public class Q513FindBottomLeftTreeValue {
    
    // 1. recursive + dp [dumb solution]
    public int findBottomLeftValue1(TreeNode root) {
        TreeNode node = root;
        int result = 0;
        Map<TreeNode, Integer> heightMap = new HashMap<>();
        int rootHeight = getHeight(root, heightMap);
        while(node != null) {
            result = node.val;
            node = getHeight(node.left, heightMap) == --rootHeight ? node.left : node.right;
        }
        return result;
    }
    
    private int getHeight(TreeNode root, Map<TreeNode, Integer> heightMap) {
        if(root == null) {
            return -1;
        }
        int result = 0;
        if(heightMap.containsKey(root)) {
            return heightMap.get(root);
        } else {
            result = Math.max(getHeight(root.left, heightMap), getHeight(root.right, heightMap)) + 1;
            heightMap.put(root, result);
        }
        return result;
    }

    // 2. iterative
    public int findBottomLeftValue2(TreeNode root) {
        if (root == null) return 0;
        
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) result = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }
    
    // 3. recursive wrapper
    private class Wrapper {
        int value;
        int depth;
        Wrapper(int value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }
    
    public int findBottomLeftValue(TreeNode root) {
        return helper(root, 1, new Wrapper(0, 0));
    }
    
    private int helper(TreeNode root, int depth, Wrapper result) {
        if(result.depth < depth) {
            result.value = root.val;
            result.depth = depth;
        }
        // Left to right DFS
        if(root.left != null) {
            helper(root.left, depth + 1, result);
        }
        if(root.right != null) {
            helper(root.right, depth + 1, result);
        }
        return result.value;
    }
    
}
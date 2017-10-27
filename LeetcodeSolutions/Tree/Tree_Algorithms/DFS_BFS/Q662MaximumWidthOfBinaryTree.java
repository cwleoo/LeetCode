/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
 * 
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 * 
 * Example 1:
 * 
 * Input: 
 *         1
 *       /   \
 *      3     2
 *     / \     \  
 *    5   3     9 
 *    
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * 
 * Example 2:
 * 
 * Input: 
 *        1
 *       /  
 *      3    
 *     / \       
 *    5   3     
 * 
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * 
 * Example 3:
 * 
 * Input: 
 *        1
 *       / \
 *      3   2 
 *     /        
 *    5      
 * 
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * 
 * Example 4:
 * 
 * Input: 
 *        1
 *       / \
 *      3   2
 *     /     \  
 *    5       9 
 *   /         \
 *  6           7
 * 
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 * 
 * Note: Answer will in the range of 32-bit signed integer.
 */

package DFS_BFS;

import java.util.*;

public class Q662MaximumWidthOfBinaryTree {
    
    // 1. straight forward BFS: O(2^h) 52ms
    //    sometimes this solution can be very slow as it count every nodes in a full binary tree
    public int widthOfBinaryTree1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int max = 1;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while(!deque.isEmpty()) {
            while(!deque.isEmpty() && deque.peekFirst() == null) deque.removeFirst();
            while(!deque.isEmpty() && deque.peekLast() == null) deque.removeLast();
            int size = deque.size();
            max = Math.max(max, size);
            for(int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if(node != null) {
                    deque.addLast(node.left);
                    deque.addLast(node.right);
                } else { // fill the blanks with null nodes
                    deque.addLast(null);
                    deque.addLast(null);
                }
            }
        }
        return max;
    }
    
    /**
     * 2. Traditional BFS O(n). Based on the property of full binary tree, directly assign the index of each "node"
     *    Use wrapper class to store each node and its index
     *           4                          4(1st)                         i
     *         /   \                      /         \                    /   \
     *        5     8                5(2nd)        8(3rd)               2i  2i+1
     *       / \   / \              /     \        /     \
     *    null  6  2  3      null(4th)  6(5th)  2(6th)   3(7th)
     */
    private static class Wrapper {
        TreeNode node;
        int index;
        Wrapper(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int max = 0;
        Queue<Wrapper> q = new LinkedList<Wrapper>();
        q.offer(new Wrapper(root, 1));

        while (!q.isEmpty()) {
            int l = q.peek().index;
            int r = l; // right started same as left
            for (int i = 0, n = q.size(); i < n; i++) {
                TreeNode node = q.peek().node;
                r = q.poll().index;
                if (node.left != null) q.offer(new Wrapper(node.left, r * 2));
                if (node.right != null) q.offer(new Wrapper(node.right, r * 2 + 1));
            }
            max = Math.max(max, r + 1 - l);  // the difference of two index
        }

        return max;
    }
    
}
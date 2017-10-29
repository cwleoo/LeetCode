/**
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
 * 
 * Example 1:
 * 
 * Input: 
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * Target = 9
 * 
 * Output: True
 * 
 * Example 2:
 * 
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * Target = 28
 * 
 * Output: False
 */

package Inorder_Traversal_BST;

import java.util.*;

public class Q653TwoSumIVInputIsABST {
    
    // 0. simply use HashSet + preorder traversal: O(n) time and space 38ms
    public boolean findTarget0(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        Set<Integer> values = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            if(values.contains(k - node.val)) {
                return true;
            }
            values.add(node.val);
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
        return false;
    }
    
    // 1. Binary search + preorder. O(nlogn) in worst case. "O(n) in average." (not sure) 32ms
    public boolean findTarget1(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            int target = k - node.val;
            if(target != node.val && containsTarget(root, target)) {
                return true;
            }
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return false;
    }
    
    private boolean containsTarget(TreeNode root, int target) {
        if(root == null) {
            return false;
        }
        if(root.val == target) {
            return true;
        }
        return target < root.val ? containsTarget(root.left, target) : containsTarget(root.right, target);
    }
    
    // 2. use inorder list + two pointer O(n) space and time. 29ms
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        int left = 0;
        int right = values.size() - 1;
        while(left < right) {
            int sum = values.get(left) + values.get(right);
            if(sum == k) {
                return true;
            } else if(sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
    
    private void inorder(TreeNode root, List<Integer> values) {
        if(root == null) {
            return;
        }
        inorder(root.left, values);
        values.add(root.val);
        inorder(root.right, values);
    }
    
}
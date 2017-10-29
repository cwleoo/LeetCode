/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note: 
 * You may assume k is always valid, 1 ¡Ü k ¡Ü BST's total elements.
 * 
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */

package Inorder_Traversal_BST;

import java.util.*;

public class Q230KthSmallestElementInABST {
    
    /**
     * Fllow up: if the tree is modified often, we could keep a sorted list of all values of tree nodes.
     *           when insert/delete happend, we should update the list in the meantime.
     */
    
    /* 1. O(n) algorithm: O(n) to get amountMap, O(logn) to find target, O(n) + O(logn) = O(n)
     * if can modify TreeNode structure, this solution can be treat as O(logn)
     */
    public int kthSmallest1(TreeNode root, int k) {
        Map<Integer, Integer> amountMap = new HashMap<>();
        getAmount(root, amountMap);
        return kthSmallest(root, amountMap, k);
    }
    
    private int kthSmallest(TreeNode root, Map<Integer, Integer> amountMap, int k) {
        int leftAmount = root.left == null ? 0 : amountMap.get(root.left.val);
        if(k == leftAmount + 1) {
            return root.val;
        } else if(k <= leftAmount) {
            return kthSmallest(root.left, amountMap, k);
        }
        return kthSmallest(root.right, amountMap, k - leftAmount - 1);
    }
    
    private int getAmount(TreeNode root, Map<Integer, Integer> amountMap) {
        if(root.left == null && root.right == null) {
            amountMap.put(root.val, 1);
            return 1;
        } else if(root.left != null && root.right == null) {
            int leftAmount = getAmount(root.left, amountMap);
            amountMap.put(root.val, leftAmount + 1);
            return leftAmount + 1;
        } else if(root.left == null && root.right != null) {
            int rightAmount = getAmount(root.right, amountMap);
            amountMap.put(root.val, rightAmount + 1);
            return rightAmount + 1;
        } else {
            int leftAmount = getAmount(root.left, amountMap);
            int rightAmount = getAmount(root.right, amountMap);
            amountMap.put(root.val, leftAmount + rightAmount + 1);
            return leftAmount + rightAmount + 1;
        }
    }
    
    // 2. inorder traverse O(k) iterative
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        
        while (root != null) {
            st.push(root);
            root = root.left;
        }
            
        while (k != 0) {
            TreeNode n = st.pop();
            k--;
            if (k == 0) return n.val;
            TreeNode right = n.right;
            // find successor
            while (right != null) {
                st.push(right);
                right = right.left;
            }
        }
        return -1; // never hit if k is valid
    }
    
    // 3. inorder traverse O(k) recursive
    private static class Wrapper {
        Integer count;
        Integer target;
        public Wrapper(int count) {
            this.count = count;
        }
    }
    
    public int kthSmallest3(TreeNode root, int k) {
        Wrapper result = new Wrapper(k);
        kthHelper(root, result);
        return result.target;
    }
    
    private void kthHelper(TreeNode root, Wrapper result) {
        if(root.left != null) {
            kthHelper(root.left, result);
        }
        result.count--;
        if(result.count == 0) {
            result.target = root.val;
            return;
        }
        if(root.right != null) {
            kthHelper(root.right, result);
        }
    }
    
    // 4. O(k) recursive solution: (Binary Search) calculate counts of each node
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) {
            throw new IllegalArgumentException("root cannot be null!");
        }
        int leftCount = count(root.left);
        if(leftCount >= k) {
            return kthSmallest(root.left, k);
        } else if(leftCount == k - 1) {
            return root.val;
        } else {
            return kthSmallest(root.right, k - 1 - leftCount);
        }
    }
    
    private int count(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }
    
}
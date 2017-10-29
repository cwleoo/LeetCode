/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 1. The left subtree of a node contains only nodes with keys less than the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees must also be binary search trees.
 * 
 * Example 1:
 *   2
 *  / \
 * 1   3
 * Binary tree [2,1,3], return true.
 * 
 * Example 2:
 *   1
 *  / \
 * 2   3
 * Binary tree [1,2,3], return false.
 */

package Inorder_Traversal_BST;

import java.util.*;

public class Q98ValidBinarySearchTree {
    
    // 1. Inorder traversal
    public boolean isValidBST1(TreeNode root) {
        if(root == null) {
            return true;
        }
        List<Integer> inorderList = new ArrayList<>();
        inorderTraverseBST(root, inorderList);
        Iterator<Integer> iterator = inorderList.iterator();
        int previous = iterator.next();
        int current = 0;
        while(iterator.hasNext()) {
            current = iterator.next();
            if(current <= previous) {
                return false;
            }
            previous = current;
        }
        return true;
    }
    
    private void inorderTraverseBST(TreeNode root, List<Integer> inorderList) {
        if(root.left != null) {
            inorderTraverseBST(root.left, inorderList);
        }
        inorderList.add(root.val);
        if(root.right != null) {
            inorderTraverseBST(root.right, inorderList);
        }
    }
    
    // 2. recursive
    public boolean isValidBST2(TreeNode root) {
        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean validBST(TreeNode root, long min, long max) {
        if(root == null) {
            return true;
        }
        if(root.val >= max || root.val <= min) {
            return false;
        }
        return validBST(root.left, min, root.val) && validBST(root.right, root.val, max);
        
    }
    
    // 3. updated inorder
    private TreeNode previous = null;
    public boolean isValidBST(TreeNode root) {
        return inorderTraverseBST1(root);
    }
    
    private boolean inorderTraverseBST1(TreeNode root) {
        if(root == null) {
            return true;
        }
        if(!inorderTraverseBST1(root.left)) {
            return false;
        }
        if(previous != null && previous.val >= root.val) {
            return false;
        }
        previous = root;
        return inorderTraverseBST1(root.right);
    }
    
}
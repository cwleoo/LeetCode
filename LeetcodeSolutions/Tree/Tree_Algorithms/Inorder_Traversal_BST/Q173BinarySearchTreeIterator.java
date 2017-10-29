/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */

package Inorder_Traversal_BST;

import java.util.*;

public class Q173BinarySearchTreeIterator {
    
    private final Stack<TreeNode> predecessors = new Stack<>();

    public Q173BinarySearchTreeIterator(TreeNode root) {
        pushNodes(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !predecessors.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode next = predecessors.pop();
        pushNodes(next.right);
        return next.val;
    }
    
    private void pushNodes(TreeNode node) {
        if(node != null) {
            predecessors.push(node);
            while(node.left != null) {
                predecessors.push(node.left);
                node = node.left;
            }
        }
    }
    
}
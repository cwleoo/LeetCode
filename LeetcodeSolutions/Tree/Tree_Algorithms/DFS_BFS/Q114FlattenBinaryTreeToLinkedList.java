/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 * 
 *       1
 *      / \
 *     2   5
 *    / \   \
 *   3   4   6
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */

package DFS_BFS;

import java.util.*;

public class Q114FlattenBinaryTreeToLinkedList {
    
    // 1. non-nested recursion
    public void flatten(TreeNode root) {
        if(root == null) {
            return;
        }
        flatten(root.right);
        if(root.left != null) {
            flatten(root.left);
            TreeNode predecessor = root.left;
            while(predecessor.right != null) {
                predecessor = predecessor.right;
            }
            predecessor.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }
    
    // 2. nested recursion (less stack depth)
    public void flatten2(TreeNode root) {
        if(root == null) {
            return;
        }
        preorder(root);
    }
    
    // return the tail of current flatten linked list
    private TreeNode preorder(TreeNode root) {
        if(root.left == null && root.right == null) {
            return root;
        }
        TreeNode rightTree = root.right;
        TreeNode tail = root;
        if(root.left != null) {
            root.right = root.left;
            root.left = null;
            tail = preorder(root.right);
        }
        if(rightTree != null) {
            tail.right = rightTree;
            tail = preorder(rightTree);
        }
        return tail;
    }
    
    // 3. use global variable to store `tail` node
    
}
/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */

package Inorder_Traversal_BST;

import java.util.*;

public class Q99RecoverBinarySearchTree {
    
    // 1. Inorder -- Straight forward
    public void recoverTree1(TreeNode root) {
        if(root == null) {
            return;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        inorder(root, nodeList);
        TreeNode first = null;
        TreeNode second = null;
        Iterator<TreeNode> iterator = nodeList.iterator();
        TreeNode previous = iterator.next();
        TreeNode current = null;
        while(iterator.hasNext()) {
            current = iterator.next();
            if(current.val < previous.val) {
                if(first == null && second == null) {
                    first = previous;
                    second = current;
                } else {
                    second = current;
                }
            }
            previous = current;
        }
        if(first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
    
    private void inorder(TreeNode root, List<TreeNode> nodeList) {
        if(root.left != null) {
            inorder(root.left, nodeList);
        }
        nodeList.add(root);
        if(root.right != null) {
            inorder(root.right, nodeList);
        }
    }
    
    // 2. Constant Space (Global variables)
    TreeNode first = null;
    TreeNode second = null;
    TreeNode previous = null;
    
    public void recoverTree(TreeNode root) {
        if(root == null) {
            return;
        }
        traverseFind(root);
        //System.out.printf("%s, %s, %s%n", previous == null, first == null, second == null);
        if(first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
    
    private void traverseFind(TreeNode root) {
        if(root.left != null) {
            traverseFind(root.left);
        }
        if(previous != null && previous.val > root.val) {
            if(first == null) {
                first = previous;
            }
            second = root;
        }
        previous = root;
        if(root.right != null) {
            traverseFind(root.right);
        }
    }
    
}
/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 *  \
 *   2
 *  /
 * 3
 * return [1,2,3].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 */

package Stack_Traversal;

import java.util.*;

public class Q144BinaryTreePreorderTraversal {
    
 // 1. O(n) extra space stack
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        while(!nodes.empty()) {
            TreeNode node = nodes.pop();
            result.add(node.val);
            if(node.right != null) {
                nodes.push(node.right);
            }
            if(node.left != null) {
                nodes.push(node.left);
            }
        }
        return result;
    }
    
    // 2. Threading binary tree, with O(1) space
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        TreeNode current = root;
        TreeNode predecessor = null;
        while(current != null) {
            if(current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                predecessor = current.left;
                while(predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null) {
                    predecessor.right = current;
                    result.add(current.val);
                    current = current.left;
                } else if(predecessor.right == current) {
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }
    
}
/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * For example:
 * Given binary tree [1,null,2,3],
 * 1
 *  \
 *   2
 *  /
 *  3
 *  return [1,3,2].
 *  
 *  Note: Recursive solution is trivial, could you do it iteratively?
 */

package Stack_Traversal;

import java.util.*;

public class Q94BinaryTreeInorderTraversal {
    
    // 1. recursive version
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        inorderHelper(root, result);
        return result;
    }
    
    private void inorderHelper(TreeNode root, List<Integer> result) {
        if(root.left != null) {
            inorderHelper(root.left, result);
        }
        result.add(root.val);
        if(root.right != null) {
            inorderHelper(root.right, result);
        }
    }
    
    // 2. iterative version
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.empty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // if curr == null, then reach a leaf, we need to pop() and output the val
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }
    
    // 3. Threading Binary Tree Morris Traversal O(1) space no stack use
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        TreeNode predecessor = null;
        while(current != null) {
            if(current.left == null) {  // the left most leaf
                result.add(current.val);
                current = current.right;
            } else {  // if left is not null, we should find the predecessor in the left subtree, and let its right child points to current node
                predecessor = current.left;
                while(predecessor.right != null && predecessor.right != current) {  // find the predecessor
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null) {  // if predecessor has not been visited
                    predecessor.right = current;  // points to current node
                    current = current.left;
                } else { // if visited, that is to say all left subtree is visited
                    predecessor.right = null;  // restore original tree structure
                    result.add(current.val);
                    current = current.right;
                }
            }
        }
        return result;
    }
    
}
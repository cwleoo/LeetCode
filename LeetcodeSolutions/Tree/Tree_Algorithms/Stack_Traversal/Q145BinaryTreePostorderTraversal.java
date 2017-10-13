/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 *  \
 *   2
 *  /
 *  3
 *  return [3,2,1].
 */

package Stack_Traversal;

import java.util.*;

public class Q145BinaryTreePostorderTraversal {
    
 // 1. O(n) stack iterative version
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
    
    // 2. threading binary tree/ morris traversal algorithm (much complex than pre-order and in-order)
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode current = dummy;
        TreeNode predecessor = null;
        while(current != null) {
            if(current.left == null) {
                current = current.right;
            } else {
                predecessor = current.left;
                while(predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else if(predecessor.right == current) {
                    reverse(current.left, predecessor);
                    TreeNode runner = predecessor;
                    while(true) {
                        result.add(runner.val);
                        if(runner == current.left) {
                            break;
                        }
                        runner = runner.right;
                    }
                    reverse(predecessor, current.left);
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }
    
    // reverse part nodes along with a certain path, and keep other nodes unchanged.
    private void reverse(TreeNode from, TreeNode to) {
        if(from == null || from == to || from.right == null) {
            return;
        }
        TreeNode prev = from;  // we cannot set prev with null because we just reverse part of whole tree
        TreeNode curr = from.right;
        TreeNode next;
        while(prev != to) {
            next = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }
    }
    
}
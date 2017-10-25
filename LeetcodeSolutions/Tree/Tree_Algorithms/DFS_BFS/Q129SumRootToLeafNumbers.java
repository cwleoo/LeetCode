/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * 
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * 
 * Find the total sum of all root-to-leaf numbers.
 * 
 * For example,
 *   1
 *  / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 */

package DFS_BFS;

import java.util.*;

public class Q129SumRootToLeafNumbers {
    
    // 1. stack
    private int result = 0;
    
    public int sumNumbers1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        StringBuilder path = new StringBuilder();
        DFS(root, path);
        return result;
    }
    
    private void DFS(TreeNode root, StringBuilder path) {
        path.append(root.val);
        if(root.left == null && root.right == null) {
            result += Integer.parseInt(path.toString());
            //System.out.printf("%d", Integer.parseInt(path.toString()));
            path.deleteCharAt(path.length() - 1);
            return;
        }
        if(root.left != null) {
            DFS(root.left, path);
        }
        if(root.right != null) {
            DFS(root.right, path);
        }
        path.deleteCharAt(path.length() - 1);
    }
    
    // 2. optimize: no need of global field
    public int sumNumbers(TreeNode root) {
        return DFS(root, 0);
    }
    
    private int DFS(TreeNode root, int sum) {
        if(root == null) {
            return 0;
        }
        sum = 10 * sum + root.val;
        if(root.left == null && root.right == null) {
            return sum;
        }
        return DFS(root.left, sum) + DFS(root.right, sum);
    }
    
}
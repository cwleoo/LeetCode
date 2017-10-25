/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: 
 * ¡°The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).¡±
 *      _______3______
 *     /              \
 *  ___5__          ___1__
 * /      \        /      \
 * 6      _2       0       8
 *       /  \
 *       7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
 * Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 */

package DFS_BFS;

import java.util.*;

public class Q236LowestCommonAncestorOfABinaryTree {
    
    // 1. O(n) time recursive DFS (In the worst case, all elements are visited once)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) {
            return right;
        } else if(right == null) {
            return left;
        } else {
            return root;
        }
    }
    
    // 2. O(n) another version of DFS T(n) = T(n/2) + O(n) [not recommended]
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        boolean isPLeft = DFS(root.left, p);
        boolean isQLeft = DFS(root.left, q);
        if(isPLeft && isQLeft) {
            return lowestCommonAncestor(root.left, p, q);
        } else if(!isPLeft && !isQLeft) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
    
    private boolean DFS(TreeNode root, TreeNode node) {
        if(root == null) {
            return false;
        }
        if(root == node) {
            return true;
        }
        return DFS(root.left, node) || DFS(root.right, node);
    }
    
}
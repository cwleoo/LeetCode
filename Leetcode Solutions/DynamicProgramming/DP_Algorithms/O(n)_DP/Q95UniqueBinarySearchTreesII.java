/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * 
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 * 
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 */

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Q95UniqueBinarySearchTreesII {
    
    // 1. recursion without dp 4ms O(n^3)
    public List<TreeNode> generateTrees1(int n) {
        if(n < 1) {
            return new ArrayList<>();
        }
        return generateTreesBetween(1, n);
    }
    
    private List<TreeNode> generateTreesBetween(int low, int high) {
        List<TreeNode> result = new ArrayList<>();
        if(low > high) {
            result.add(null);
            return result;
        }
        if(low == high) {
            result.add(new TreeNode(low));
            return result;
        }
        List<TreeNode> leftTrees, rightTrees;
        for(int i = low; i <= high; i++) {
            leftTrees = generateTreesBetween(low, i - 1);
            rightTrees = generateTreesBetween(i + 1, high);
            for(TreeNode left : leftTrees) {
                for(TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }
    
    // 2. 2d dp: trees[low][high] = a list of BSTs with node.val from 'low' to 'high' 2ms O(n^2) [calculate with master theorem]
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) {
            return new ArrayList<>();
        }
        // for corner cases, i.e. index = 0 or (n + 1), it will be a list which contains only a 'null' node.
        @SuppressWarnings("unchecked")
        List<TreeNode>[][] trees = new ArrayList[n + 2][n + 2];
        return generateTreesBetween(1, n, trees);
    }
    
    private List<TreeNode> generateTreesBetween(int low, int high, List<TreeNode>[][] trees) {
        if(trees[low][high] != null) {
            return trees[low][high];
        }
        List<TreeNode> result = new ArrayList<>();
        if(low > high) {
            result.add(null);
        } else if(low == high) {
            result.add(new TreeNode(low));
        } else {
            List<TreeNode> leftTrees, rightTrees;
            for(int i = low; i <= high; i++) {
                leftTrees = generateTreesBetween(low, i - 1, trees);
                rightTrees = generateTreesBetween(i + 1, high, trees);
                for(TreeNode leftTree : leftTrees) {
                    for(TreeNode rightTree : rightTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftTree;
                        root.right = rightTree;
                        result.add(root);
                    }
                }
            }
        }
        trees[low][high] = result;
        return trees[low][high];
    }
    
    // 3. use 1D DP to cache all [1...i] trees, and utilize offset to obtain [1+k...i+k] trees. 3ms
    public List<TreeNode> generateTrees3(int n) {
        if(n < 1) {
            return new ArrayList<>();
        }
        // trees[i] means the subtree in the range [1, i] (i >= 1)
        // for corner cases, i.e. index = 0 or (n + 1), it will be a list which contains only a 'null' node.
        @SuppressWarnings("unchecked")
        List<TreeNode>[] trees = new ArrayList[n + 1];
        trees[0] = new ArrayList<>();
        trees[0].add(null);
        for(int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                for(TreeNode leftTree : trees[j]) {
                    for(TreeNode rightTree : trees[i - j - 1]) {
                        TreeNode root = new TreeNode(j + 1);
                        root.left = leftTree;
                        root.right = cloneWithOffset(rightTree, j + 1);
                        trees[i].add(root);
                    }
                }
            }
        }
        return trees[n];
    }
    
    private TreeNode cloneWithOffset(TreeNode root, int offset) {
        if(root == null) {
            return null;
        }
        TreeNode node = new TreeNode(root.val + offset);
        node.left = cloneWithOffset(root.left, offset);
        node.right = cloneWithOffset(root.right, offset);
        return node;
    }
    
}
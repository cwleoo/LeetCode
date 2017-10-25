/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */

package DFS_BFS;

import java.util.*;

public class Q105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length - 1, indexMap);
    }
    
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int curr, int start, int end, Map<Integer, Integer> indexMap) {
        if(curr > preorder.length - 1 || start > end) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[curr]);
        int indexOfInorder = indexMap.get(preorder[curr]);
        node.left = buildTreeHelper(preorder, inorder, curr + 1, start, indexOfInorder - 1, indexMap);
        node.right = buildTreeHelper(preorder, inorder, curr + (indexOfInorder - start + 1), indexOfInorder + 1, end, indexMap);
        return node;
    }
    
}
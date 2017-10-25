/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 */

package DFS_BFS;

import java.util.*;

public class Q106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        int length = inorder.length;
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for(int i = 0; i < length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return buildTreeHelper(inorder, postorder, length - 1, 0, length - 1, valToIndex);
    }
    
    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int curr, int start, int end, Map<Integer, Integer> valToIndex) {
        if(curr < 0 || start > end) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[curr]);
        int rootIndex = valToIndex.get(postorder[curr]);
        root.left = buildTreeHelper(inorder, postorder, curr - (end - rootIndex) - 1, start, rootIndex - 1, valToIndex);
        root.right = buildTreeHelper(inorder, postorder, curr - 1, rootIndex + 1, end, valToIndex);
        return root;
    }
    
}
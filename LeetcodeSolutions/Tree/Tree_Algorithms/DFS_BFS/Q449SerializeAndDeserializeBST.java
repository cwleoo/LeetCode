/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

package DFS_BFS;

import java.util.*;

/**
 * The most important difference from "Q297 Serialize and Deserialize Binary Tree":
 *   -- Here is the BST where we could figure the order out so that we could determine where a certain node should locate.
 */
public class Q449SerializeAndDeserializeBST {
    
    private static final String DELIMITER = ",";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            sb.append(node.val).append(DELIMITER);
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) {
            return null;
        }
        String[] nodes = data.split(DELIMITER);
        int length = nodes.length;
        int[] values = new int[length];
        for(int i = 0; i < length; i++) {
            values[i] = Integer.parseInt(nodes[i]);
        }
        return buildTree(values, 0, length - 1);
    }
    
    private TreeNode buildTree(int[] values, int start, int end) {
        if(start > end) {
            return null;
        }
        TreeNode root = new TreeNode(values[start]);
        int leftEnd = end + 1;
        while(values[--leftEnd] > values[start]);
        root.left = buildTree(values, start + 1, leftEnd);
        root.right = buildTree(values, leftEnd + 1, end);
        return root;
    }
    
}
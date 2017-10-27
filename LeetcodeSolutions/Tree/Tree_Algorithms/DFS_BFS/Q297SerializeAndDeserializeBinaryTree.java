/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following tree
 *   1
 *  / \
 * 2   3
 *    / \
 *   4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

package DFS_BFS;

import java.util.*;

public class Q297SerializeAndDeserializeBinaryTree {
    
    private static final String DELIMITER = ",";
    private static final String NULL_NODE = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int lastNodePos = 0;              // the position of the last non-null node in current level
        boolean isNextLevelEmpty = true;  // always assume next level is empty
        while(!queue.isEmpty()) {
            int size = queue.size();
            StringBuilder currentLevel = new StringBuilder();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node != null) {
                    currentLevel.append(node.val).append(DELIMITER);
                    lastNodePos = currentLevel.length() - DELIMITER.length();
                    queue.add(node.left);
                    queue.add(node.right);
                    if(node.left != null || node.right != null) isNextLevelEmpty = false;
                } else {
                    currentLevel.append(NULL_NODE).append(DELIMITER);
                }
            }
            if(isNextLevelEmpty) {
                result.append(currentLevel.substring(0, lastNodePos));
                break;
            }
            result.append(currentLevel);
            lastNodePos = 0;           // reset to 0, prepare to process next level
            isNextLevelEmpty = true;   // reset to true, i.e. next level is assumpted not empty
        }
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null) {
            return null;
        }
        String[] nodeVals = data.split(DELIMITER);
        TreeNode root = new TreeNode(Integer.parseInt(nodeVals[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty() && i < nodeVals.length) {
            TreeNode parent = queue.poll();
            TreeNode left = parseNode(nodeVals[i++]);
            TreeNode right = i < nodeVals.length ? parseNode(nodeVals[i++]) : null;    // we need to check whether out of bounds here
            parent.left = left;
            parent.right = right;
            if(left != null) queue.add(left);
            if(right != null) queue.add(right);
        }
        return root;
    }
    
    private TreeNode parseNode(String nodeVal) {
        return NULL_NODE.equals(nodeVal) ? null : new TreeNode(Integer.parseInt(nodeVal));
    }
    
}
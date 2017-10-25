/**
 * Given a binary tree
 * 
 * struct TreeLinkNode {
 *     TreeLinkNode *left;
 *     TreeLinkNode *right;
 *     TreeLinkNode *next;
 * }
 * 
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 1. You may only use constant extra space.
 * 2. You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * 
 * For example,
 * Given the following perfect binary tree,
 *       1
 *     /  \
 *    2    3
 *   / \  / \
 *  4  5  6  7
 * 
 * After calling your function, the tree should look like:
 *       1 -> NULL
 *     /  \
 *    2 -> 3 -> NULL
 *   / \  / \
 *  4->5->6->7 -> NULL
 */

package DFS_BFS;

import java.util.*;

public class Q116PopulatingNextRightPointersInEachNode {
    
    // 1. simple BFS. O(n) space
    public void connect1(TreeLinkNode root) {
        if(root == null) {
            return;
        }
        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode pre = null;
        TreeLinkNode curr = null;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            pre = dummy;
            for(int i = 0; i < size; i++) {
                curr = queue.poll();
                if(curr.left != null && curr.right != null) {
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
                pre.next = curr;
                pre = curr;
            }
            curr.next = null;
        }
    }
    
    // 2. DFS. Tricky tip here is: "next" can help shift to sibling. 0~1ms
    public void connect(TreeLinkNode root) {
        if(root == null) {
            return;
        }
        TreeLinkNode levelHead = root;
        TreeLinkNode curr = null;
        while(levelHead.left != null) {
            curr = levelHead;
            while(curr != null) {  // here we connect siblings through there parents' level
                curr.left.next = curr.right;
                if(curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            levelHead = levelHead.left;
        }
    }
    
    // 3. DFS. O(n) stack recursion version. Not O(1) space. Fatest though. 0ms
    public void connect3(TreeLinkNode root) {
        if(root == null) {
            return;
        }
        TreeLinkNode prev = root.left;
        TreeLinkNode then = root.right;
        while(prev != null) {
            prev.next = then;
            prev = prev.right;
            then = then.left;
        }
        connect(root.left);
        connect(root.right);
    }
    
}
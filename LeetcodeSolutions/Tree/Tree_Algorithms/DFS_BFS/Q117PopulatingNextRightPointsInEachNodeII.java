/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * 
 * Note:
 * - You may only use constant extra space.
 * 
 * For example,
 * Given the following binary tree,
 *       1
 *     /  \
 *    2    3
 *   / \    \
 *  4   5    7
 * 
 * After calling your function, the tree should look like:
 *       1 -> NULL
 *     /  \
 *    2 -> 3 -> NULL
 *   / \    \
 *  4-> 5 -> 7 -> NULL
 */

package DFS_BFS;

import java.util.*;

public class Q117PopulatingNextRightPointsInEachNodeII {
    
    //based on level order traversal
    public void connect(TreeLinkNode root) {

        TreeLinkNode head = null;   // head of the next level
        TreeLinkNode pre = null;    // the preceding node on the next level
        TreeLinkNode curr = root;   // current node of current level

        while (curr != null) {
            //iterate on the current level
            while (curr != null) {
                //left child
                if (curr.left != null) {
                    if (pre != null) {
                        pre.next = curr.left;
                    } else {
                        head = curr.left;
                    }
                    pre = curr.left;
                }
                //right child
                if (curr.right != null) {
                    if (pre != null) {
                        pre.next = curr.right;
                    } else {
                        head = curr.right;
                    }
                    pre = curr.right;
                }
                //move to next node
                curr = curr.next;
            }
            
            //advance to next level
            curr = head;
            head = null;
            pre = null;
        }
        
    }
    
}
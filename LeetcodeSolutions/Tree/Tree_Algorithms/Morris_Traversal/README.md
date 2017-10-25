## 2) Morris traversal algorithm (Treading binary tree): 

the algorithm that can traverse a binary tree without extra space (like O(n) stack) or extra stacks (in recursion). 

The key point here is: 

> **1**) using two pointers (one is `current` the other is `predecessor`) and try to find the **predecessor** of the current node (which is a leaf), then

> **2**) let the **right child** of the predecessor points to current node. (that why ¡°threading¡± comes) Thus, we build a path that enable us trace back to parents or ancestors. After visiting this leaf, we should

> **3**) restore its original structure ¨C set the right child of predecessor to `null` so that keeping the tree unchanged.

The total running time is O(n) with O(1) space. Since each node is visited at most twice, so running time is linear.
	
   - In-order: `current` + `predecessor`. One thing needs to notice is we need to output current node when we SECOND time visit the predecessor node and before we move to its right child. ([Q94 binary tree inorder traversal](../Stack_Traversal/Q94BinaryTreeInorderTraversal.java))
	
   - Pre-order: `current` + `predecessor`. The difference with in-order traversal is we need to output current node when we FIRST time visit the predecessor node and before we move to right child. ([Q144 binary tree preorder traversal](../Stack_Traversal/Q144BinaryTreePreorderTraversal.java))
	
   - Post-order: `dummy` + `current` + `predecessor`. There are two new notations: `dummy` is a temp node whose left child points to root; when we find that it is the SECOND time to visit predecessor, we need to output all nodes in the path "`current.left` -> ¡­ -> `predecessor`" REVERSELY. Then we need to reverse it again to restore original structure. ([Q145 binary tree postorder traversal](../Stack_Traversal/Q145BinaryTreePostorderTraversal.java))
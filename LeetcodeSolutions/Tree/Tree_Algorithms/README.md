# 3. Algorithms:
	
## 1) [Using Stack<> to traverse a tree](Stack_Traversal/README.md): 

no matter what kind of traversal is needed, Stack<> will be a perfect data structure to help.
   
   - In-order: need an extra pointer `curr` + stack. First push nodes along the left most path till we reach a leaf, then use `curr` to trace the stack back with order `curr = pop() -> curr.right`. (Q94 binary tree inorder traversal)
	
   - Pre-order: simply use a stack. We push nodes in the order of ¡°right, left¡± so that the left child will always be the first one to pop. We output nodes by popping the top element of stack. (Q144 binary tree preorder traversal)
	
   - Post-order: simply use a stack. One thing different with pre-order traversal is that, we push nodes in the order of ¡°left, right¡±. When we finished the output, we need to REVERSE the whole output so that meet the post-order requirement. (Q145 binary tree post order traversal)
	
## 2) [Morris traversal algorithm (Treading binary tree)](Morris_Traversal/README.md): 

the algorithm that can traverse a binary tree without extra space (like O(n) stack) or extra stacks (in recursion). 

The key point here is: 

> **1**) using two pointers (one is `current` the other is `predecessor`) and try to find the **predecessor** of the current node (which is a leaf), then

> **2**) let the **right child** of the predecessor points to current node. (that why ¡°threading¡± comes) Thus, we build a path that enable us trace back to parents or ancestors. After visiting this leaf, we should

> **3**) restore its original structure ¨C set the right child of predecessor to `null` so that keeping the tree unchanged.

The total running time is O(n) with O(1) space. Since each node is visited at most twice, so running time is linear.
	
   - In-order: `current` + `predecessor`. One thing needs to notice is we need to output current node when we SECOND time visit the predecessor node and before we move to its right child. (Q94 binary tree inorder traversal)
	
   - Pre-order: `current` + `predecessor`. The difference with in-order traversal is we need to output current node when we FIRST time visit the predecessor node and before we move to right child. (Q144 binary tree preorder traversal)
	
   - Post-order: `dummy` + `current` + `predecessor`. There are two new notations: `dummy` is a temp node whose left child points to root; when we find that it is the SECOND time to visit predecessor, we need to output all nodes in the path "`current.left` -> ¡­ -> `predecessor`" REVERSELY. Then we need to reverse it again to restore original structure. (Q145 binary tree postorder traversal)
	
## 3) [DFS & BFS](DFS_BFS/README.md): 

   - DFS in a tree is the same to perform preorder traversal. It is perfect for searching certain nodes or paths. It is implemented by using recursion or Stack. Also, DFS is often seen as the straight-forward approach of using **recursion structure**.
(See Q105 construct binary tree from preorder and inorder traversal, Q106 construct binary tree from inorder and postorder traversal, Q112/113 path sum I/II, Q114 flatten binary tree to linked list, Q116/117 populating next right pointers in each node I/II, Q124 binary tree maximum path sum, Q129 sum root to leaf numbers, Q199 binary tree right side view, Q236 lowest common ancestor of a binary tree, Q449 serialize and deserialize BST, Q513 find bottom left tree value)

   - BFS in a tree is viewed as "level order" traversal. It is perfect to handle problems in the field of "level" or "height" and find the shortest path in a tree. It is implemented by using **Queue in an iterative style**.
(See Q102 binary tree level order traversal, Q103 binary tree zigzag level order traversal, Q199 binary tree right side view, Q297 serialize and deserialize binary tree, Q513 find bottom left tree value, Q515 find largest value in each tree row, Q623 add one row to tree, Q662 maximum width of binary tree)
	
## 4) In-order traversal of BST: 

apply in-order traversal on a BST will get an ascending ordered array. One thing need to note is: if we use recursion and we only use O(1) extra space, using global variables would be a good choice. (Q98 validate binary search tree, Q99 recover binary search tree, Q173 binary search tree iterator, Q230 kth smallest element in a BST, Q449 serialize and deserialize BST [preorder], Q653 two sum IV ¨C input is a BST)
	
## 5) DP for Tree: 

because a tree has a recursion structure, sometimes DP array/Hash Map DP (or O(1) DP) is a useful tool for tree problems. (Q96/95 unique binary search trees I/II, Q337 house robber III, Q437 path sum III, Q508 most frequent subtree sum, Q652 find duplicate subtrees)
	
## 6) Binary Search for BST: 

BST has nature mapping to binary search. (Q222 count complete tree, Q235 lowest common ancestor of a binary search Tree, Q230 kth smallest element in a BST, Q450 delete node in a BST)
	
## 7) Use height/depth of a tree: 

height/depth can serve as a perfect index of every level of tree. It can also be used in checking balance of a tree. (e.g. AVL tree) As for binary tree, it can be used to count nodes. (Q199 binary tree right side view, Q222 count complete tree, Q513 find bottom left tree value)
	
## 8) Trie: 

also called Prefix Tree. A trie is a variant of an n-ary tree in which characters are stored at each node. Each path down the tree may represent a word. A trie can check if a string is a valid prefix in O(k) time, where k is the length of the string. This is actually the same runtime as a hash table will take. There are two ways to implements ¡°null nodes¡± in a trie:
	
   - A new node: it may be just a normal `TrieNode` with value `*`, or a special type of child `TerminatingTrieNode` which inherits from `TrieNode`;
	
   - A boolean flag: a boolean `terminates` within the parent node.

(See Q208 implement trie (prefix tree), Q211 add and search word ¨C data structure design, Q421 maximum XOR of two numbers in an array, Q648 replace words)
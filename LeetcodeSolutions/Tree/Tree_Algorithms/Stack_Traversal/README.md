## 1) Using Stack<> to traverse a tree: 

no matter what kind of traversal is needed, Stack<> will be a perfect data structure to help.
   
   - In-order: need an extra pointer `curr` + stack. First push nodes along the left most path till we reach a leaf, then use `curr` to trace the stack back with order `curr = pop() -> curr.right`. ([Q94 binary tree inorder traversal](Q94BinaryTreeInorderTraversal.java))
	
   - Pre-order: simply use a stack. We push nodes in the order of ¡°right, left¡± so that the left child will always be the first one to pop. We output nodes by popping the top element of stack. (Q144 binary tree preorder traversal)
	
   - Post-order: simply use a stack. One thing different with pre-order traversal is that, we push nodes in the order of ¡°left, right¡±. When we finished the output, we need to REVERSE the whole output so that meet the post-order requirement. (Q145 binary tree post order traversal)
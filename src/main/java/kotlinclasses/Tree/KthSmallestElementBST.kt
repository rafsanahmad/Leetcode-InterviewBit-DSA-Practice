/*
 * *
 *  * Kth Smallest Element in a BST.kt
 *  * Created by Rafsan Ahmad on 7/3/25, 2:14PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

class KthSmallestElementBST {
    //https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
    /*Given the root of a binary search tree, and an integer k, return the kth smallest
    value (1-indexed) of all the values of the nodes in the tree.

Example 1:
          3
       /     \
     1        4
      \
       2

Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3


Constraints:
The number of nodes in the tree is n.
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and
you need to find the kth smallest frequently, how would you optimize?

Follow Up:
 if your BST is frequently modified and you want to run kthSmallest(k) efficiently and
 repeatedly, you need to do more.

The Proper & Correct Optimized Way:
Use a Balanced BST with Augmented Metadata.
This guarantees:

insert, delete, and kthSmallest all in O(log n) time.
Tree remains balanced, avoiding skew (which causes O(n)).

🔧 Your Options (Balanced Trees that support rank queries):
1. AVL Tree with Subtree Sizes
Maintain a self-balancing AVL Tree.
Augment each node with:
count: for duplicates
size: total size of subtree including duplicates
After every insert/delete, rebalance and update size.

Time complexity:
insert, delete, kthSmallest → O(log n) always

2. Treap or Red-Black Tree (with size)
Same idea — pick any balanced BST and augment it.
Treap is often easier to implement for custom behavior.

3. Binary Indexed Tree (Fenwick Tree) or Segment Tree (if range of values is bounded)
If values are integers within a known range (e.g., 0–10⁶):
Use a Segment Tree / Fenwick Tree to store frequencies
insert(x) → freq[x]++
delete(x) → freq[x]--

kthSmallest(k) → binary search the prefix sum tree

Time complexity:
insert, delete → O(log n)
kthSmallest(k) → O(log n)

*/

    //Time complexity: O(n)
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        if (root == null) return -1
        val list = mutableListOf<Int>()
        inorderTraversal(root, list, k)
        return list[k - 1]
    }

    fun inorderTraversal(node: TreeNode?, list: MutableList<Int>, k: Int) {
        if (list.size >= k) return
        if (node != null) {
            inorderTraversal(node.left, list, k)
            list.add(node.`val`)
            inorderTraversal(node.right, list, k)
        }
    }
}

fun main() {
    val obj = KthSmallestElementBST()
    // Construct the tree: [3,1,4,null,2]
    val root = TreeNode(3).apply {
        left = TreeNode(1).apply {
            right = TreeNode(2)
        }
        right = TreeNode(4)
    }

    println(obj.kthSmallest(root, 1))
}
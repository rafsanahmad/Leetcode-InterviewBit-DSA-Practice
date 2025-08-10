/*
 * *
 *  * Binary Search Tree Iterator.kt
 *  * Created by Rafsan Ahmad on 8/3/25, 1:11AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import java.util.Stack

class BinarySearchTreeIterator {
    //https://leetcode.com/problems/binary-search-tree-iterator/description/
    /*Implement the BSTIterator class that represents an iterator over the in-order traversal of a
    binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is
given as part of the constructor. The pointer should be initialized to a non-existent number smaller
than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer,
 otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next()
will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number
in the in-order traversal when next() is called.


Example 1:
           7
         /   \
        3     15
             /  \
            9    20
Input
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
Output
[null, 3, 7, true, 9, true, 15, true, 20, false]

Explanation
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False


Constraints:

The number of nodes in the tree is in the range [1, 10^5].
0 <= Node.val <= 10^6
At most 10^5 calls will be made to hasNext, and next.


Follow up:

Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is
the height of the tree?*/

    class BSTIterator(root: TreeNode?) {
        var node: TreeNode? = root
        var list: MutableList<Int> = mutableListOf()
        var index = 0

        //Time: O(n) — visits each node once.
        //Space: O(n)
        init {
            traverseBST(node)
        }

        fun traverseBST(node: TreeNode?) {
            if (node != null) {
                traverseBST(node.left)
                list.add(node.`val`)
                traverseBST(node.right)
            }
        }

        fun next(): Int {
            return list[index++]
        }

        fun hasNext(): Boolean {
            if (index < list.size)
                return true
            return false
        }
    }


    /*Time Complexity
1. init + traverseLeft(root)
Visits only the left path from the root.
Worst case: O(h), where h is the height of the tree.

2. next()
Each call to next() does:
stack.pop() → O(1)
traverseLeft(node.right) → O(h) in worst case but only once per node.
Thus:Amortized time complexity per next() = O(1)

Why?
Each node is pushed and popped exactly once during the whole iteration.

3. hasNext()
Just checks if stack is not empty → O(1)

Space Complexity
Stack holds at most O(h) nodes at any time (i.e., the path from root to leaf).
No full list of values is stored.
So:
Space = O(h)
Where h is the tree height:
*/
    class BSTIteratorOptimized(root: TreeNode?) {
        var node: TreeNode? = root
        var stack: Stack<TreeNode> = Stack()

        init {
            traverseLeft(node)
        }

        fun traverseLeft(node: TreeNode?) {
            var curr = node
            while (curr != null) {
                stack.add(curr)
                curr = curr.left
            }
        }

        fun next(): Int {
            val node = stack.pop()
            //println("Node: ${node?.`val`}")
            //println("Node Right: ${node?.right?.`val`}")
            traverseLeft(node?.right)
            return node.`val`
        }

        fun hasNext(): Boolean {
            return stack.isNotEmpty()
        }
    }
}

fun main() {
    val root = TreeNode(7).apply {
        left = TreeNode(3)
        right = TreeNode(15).apply {
            left = TreeNode(9)
            right = TreeNode(20)
        }
    }

    val iterator = BinarySearchTreeIterator.BSTIterator(root)
    while (iterator.hasNext()) {
        println(iterator.next())
    }

    val iterator2 = BinarySearchTreeIterator.BSTIteratorOptimized(root)
    println(iterator2.next())    // return 3
    println(iterator2.next())    // return 7
    println(iterator2.hasNext()) // return True
    println(iterator2.next())    // return 9
    println(iterator2.hasNext()) // return True
    println(iterator2.next())    // return 15
    println(iterator2.hasNext()) // return True
    println(iterator2.next())    // return 20
    println(iterator2.hasNext()) // return False
}
/*
 * *
 *  * Binary Tree Maximum Path Sum.kt
 *  * Created by Rafsan Ahmad on 3/17/25, 11:14PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Tree

import javaclasses.Tree.TreeNode
import kotlin.math.max

class BinaryTreeMaximumPathSum {
    /*A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in
    the sequence has an edge connecting them. A node can only appear in the sequence at most
     once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.



Example 1:

         1
       /   \
      2     3

Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:
        -10
       /   \
      9    20
          /  \
         15   7

Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.


Constraints:

The number of nodes in the tree is in the range [1, 3 * 10^4].
-1000 <= Node.val <= 1000*/

    /*Our goal is to find the maximum path sum. Now consider the following example.

   10
  /  \
null null
In this simple case we know that the max sum would be just the root node itself and the
answer would be 10. So fo all leaf nodes the max sum path is the value of the node itself.

Now let's consider the following example.

   20
  /  \
10    30
Here there are multiple possibilities and we need to take care of the following FOUR PATHS
 that could be our max.

The root iself : 20
The root with the maximum from it's left subTree :
	20
	/
  10
3. The root with the maximum from it's right subTree :

		20
		  \
	       30
The root with it's left, right and itself
   	 20
   	 / \
    10  30
In case you are wondering why we did not choose the root.left (10) or root.right(30) alone
 in the calculation ( like I wondered ), that's because we would have already computed
 the result of it as a node in our recursion separately.

This actually breaks down our code to a very simple pseudo code:

if( root == null) return 0;
left = recurse(leftChild);
right = recurse(rightChild);

// now find the max of all the four paths
leftPath = root.value + left;
rightPath = root.value + right;
completePath = root.value + right + left;

result = max( root.value, leftPath, rightPath, completePath );

return max(root.value, leftPath, rightPath);
What's interesting to note here is the last line of the code :

return max(root.value, leftPath, rightPath);
Wondering why did we do that ?

Well, we know that we did all the calculations possible if the tree only consists of the current node as root in any possible recursion cycle. And the result of that cycle would have been stored in the result variable.
But, what if the current node is just a child of it's parent. Then it needs to return a value, such that the root had to be part of the answer.
So if the root has to be part of the answer, it should return what's the maximum value it can return if it's part of it.
That would be either of the three cases here :

The root iself : 20
The root with the maximum from it's left subTree :
	20
	/
  10
3. The root with the maximum from it's right subTree :

		20
		  \
	       30*/

    private var maxSum = 0

    fun maxSumHelper(root: TreeNode?): Int {
        // base case
        if (root == null) return 0


        // recursing through left and right subtree
        val leftMax = maxSumHelper(root.left)
        val rightMax = maxSumHelper(root.right)

        // finding all the four paths and the maximum between all of them
        val maxRightLeft = max(leftMax, rightMax)
        val maxOneNodeRoot =
            max(root.`val`, (root.`val` + maxRightLeft))
        val maxAll =
            max(maxOneNodeRoot, (leftMax + rightMax + root.`val`))


        // Storing the result in the maxSum holder
        maxSum = max(maxSum, maxAll)


        // returning the value if root was part of the answer
        return maxOneNodeRoot
    }

    fun maxPathSum(root: TreeNode?): Int {
        maxSum = Int.MIN_VALUE
        maxSumHelper(root)
        return maxSum // as maxSum will always store the result
    }
}

fun main(args: Array<String>) {
    val maxSum = BinaryTreeMaximumPathSum()
    val root = TreeNode(-10)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right.left = TreeNode(15)
    root.right.right = TreeNode(7)

    println(maxSum.maxPathSum(root))
}
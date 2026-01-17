/*
 *
 *  * MonotonicStack.kt
 *  *
 *  * Created by Rafsan Ahmad on 01/15/26, 12:36 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.Stack_Queue

class MonotonicStack {
    /*
    A monotonic stack is a stack that maintains its elements in a monotonic order (either
    increasing or decreasing).

“Monotonic” means the values in the stack never violate the chosen order.
It is a pattern, not a new data structure.
Why Monotonic Stack?

Many array problems ask questions like:
Next greater element?
Next smaller element?
How far can something extend?
How long until a better value appears?

Brute force = O(n²)
Monotonic stack = O(n)

Core Property (Very Important)
Each element:
Is pushed once
Is popped once
So total operations = O(n)
Amortized O(1) per operation

Types of Monotonic Stack
Monotonically Increasing Stack
Definition
Values in the stack are in increasing order (bottom → top).
Bottom → [2, 4, 7, 10] → Top

Pop rule
Pop when current < stackTop

Used for
Next Smaller Element
Previous Smaller Element
Largest Rectangle in Histogram
Rain Water Trapping (part)

Example
Input: [5, 7, 3]
Stack: [5, 7] → 3 arrives → pop 7, pop 5

Monotonically Decreasing Stack
Definition
Values in the stack are in decreasing order (bottom → top).
Bottom → [10, 7, 4, 2] → Top
Pop rule
Pop when current > stackTop

Used for
Next Greater Element
Daily Temperatures
Stock Span
Sliding Window Maximum

Example
Input: [4, 2, 6]
Stack: [4, 2] → 6 arrives → pop 2, pop 4

What Do We Store in the Stack?
Store	When
Values	When index not needed
Indices	When distance/width is needed
(value, count)	Span compression (Stock Span)

Traversal Direction Matters
Direction	Typical use
Left → Right	Next element problems
Right → Left	Previous element problems

How to Identify Monotonic Stack Problems
If the problem says:
“next”
“previous”
“nearest”
“first greater/smaller”
“how many days until…”
“span / width / range”
    */

    /*Next Greater Element (NGE)
Problem Definition
Given an array nums,
for each element nums[i], find the next element to the right that is strictly greater than nums[i].
If no such element exists → return -1 for that index.

Example
Input:  [4, 5, 2, 10, 8]
Output: [5, 10, 10, -1, -1]

Explanation:
4 → next greater is 5
5 → next greater is 10
2 → next greater is 10
10 → none → -1
8 → none → -1

Intuition
Brute force: check every element to the right → O(n²)
Efficient: use a monotonically decreasing stack
Stack keeps elements waiting for a bigger number.

Stack Type
Monotonically DECREASING stack

Algorithm (Left → Right)
Initialize result array with -1
Traverse array from left to right
While stack not empty and current element is greater than stack top:
Pop index from stack
Set result for that index
Push current index*/


    fun nextGreaterElement(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n) { -1 }
        val stack = ArrayDeque<Int>() // store indices

        for (i in nums.indices) {
            while (stack.isNotEmpty() && nums[i] > nums[stack.last()]) {
                val idx = stack.removeLast()
                result[idx] = nums[i]
            }
            stack.addLast(i)
        }
        return result
    }

    /*Next Smaller Element (NSE)
Problem Definition
Given an array nums,
for each element nums[i], find the next element to the right that is strictly smaller than nums[i].
If no such element exists → return -1

Example
Input:  [5, 7, 3, 4]
Output: [3, 3, -1, -1]

Explanation:
5 → next smaller is 3
7 → next smaller is 3
3 → none
4 → none

Intuition
We want to know when a smaller value appears
Use a monotonically increasing stack

Stack Type
Monotonically INCREASING stack

Algorithm (Left → Right)
Initialize result array with -1
Traverse from left to right
While stack not empty and current < stack top:
Pop index
Set result
Push current index*/

    fun nextSmallerElement(nums: IntArray): IntArray {
        val n = nums.size
        val result = IntArray(n) { -1 }
        val stack = ArrayDeque<Int>() // store indices

        for (i in nums.indices) {
            while (stack.isNotEmpty() && nums[i] < nums[stack.last()]) {
                val idx = stack.removeLast()
                result[idx] = nums[i]
            }
            stack.addLast(i)
        }
        return result
    }
}

fun main() {
    val monotonicStack = MonotonicStack()
    val nums = intArrayOf(4, 5, 2, 10, 8)
    val result = monotonicStack.nextGreaterElement(nums)
    println(result.joinToString(", ")) // Output: 5, 10, 10, -1, -1

    val nums2 = intArrayOf(5, 7, 3, 4)
    val result2 = monotonicStack.nextSmallerElement(nums2)
    println(result2.joinToString(", ")) // Output: 3, 3, -1, -1
}
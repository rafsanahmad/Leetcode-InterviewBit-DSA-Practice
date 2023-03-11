/*
 * *
 *  * Square Root Decomposition.java
 *  * Created by Rafsan Ahmad on 3/11/23, 12:50 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Algorithm;

public class SquareRootDecomposition {
    /*Square Root Decomposition Technique is one of the most common query optimization techniques used by competitive
    programmers. This technique helps us to reduce Time Complexity by a factor of sqrt(N)

The key concept of this technique is to decompose a given array into small chunks specifically of size sqrt(N)
Problem :

Given an array of n elements. We need to answer q
queries telling the sum of elements in range l to
r in the array. Also the array is not static i.e
the values are changed via some point update query.

Range Sum Queries are of form : Q l r ,
where l is the starting index r is the ending index

Point update Query is of form : U idx val,
where idx is the index to update val is the
updated value.

Below is the illustration of the above approach:

Let us consider that we have an array of 9 elements: A[] = {1, 5, 2, 4, 6, 1, 3, 5, 7}
1. Let’s decompose this array into sqrt(9) blocks, where each block will contain the sum of elements lying in it.
Therefore now our decomposed array would look like this:

        8        11        15
    | 1 5 2 |  4 6 1  |  3 5 7 |

2. Till now we have constructed the decomposed array of sqrt(9) blocks and now we need to print the sum of elements in a
given range.
So first let’s see two basic types of overlap that a range query can have on our array:

Range Query of type 1 (Given Range is on Block Boundaries) :

res/sqrt_root1.png
In this type the query, the range may totally cover the continuous sqrt blocks. So we can easily answer the sum of values in
this range as the sum of completely overlapped blocks.

So the answer for the above query in the described image will be: ans = 11 + 15 = 26

Time Complexity: O(sqrt(N)). In the worst case, our range can be 0 to N-1(where N is the size of the array and assuming N
to be a perfect square). In this case, all the blocks are completely overlapped by our query range.
Therefore, to answer this query we need to iterate over all the decomposed blocks for the array and we know that the number
of blocks = sqrt(N). Hence, the complexity for this type of query will be O(sqrt(N)) in the worst case.

Range Query of type 2 (Given Range is NOT on boundaries):

res/sqrt_root2.png

We can deal with these types of queries by summing the data from the completely overlapped decomposed blocks lying in the
query range and then summing elements one by one from the original array whose corresponding block is not completely
overlapped by the query range.

So the answer for the above query in the described image will be: ans = 5 + 2 + 11 + 3 = 21

Time Complexity: O(sqrt(N)). Let’s consider a query [l = 1 and r = n-2] (n is the size of the array and has 0-based indexing).
 Therefore, for this query exactly ( sqrt(n) – 2 ) blocks will be completely overlapped whereas the first and last blocks
 will be partially overlapped with just one element left outside the overlapping range. So, the completely overlapped blocks
 can be summed up in ( sqrt(n) – 2 ) ~ sqrt(n) iterations, whereas the first and last blocks are needed to be traversed one
 by one separately. But as we know that the number of elements in each block is at max sqrt(n), to sum up, the last block
 individually we need to make,
(sqrt(n)-1) ~ sqrt(n) iterations and same for the last block.

So, the overall Complexity = O(sqrt(n)) + O(sqrt(n)) + O(sqrt(n)) = O(3*sqrt(N)) = O(sqrt(N))


Update Queries(Point update):
In this query, we simply find the block in which the given index lies, then subtract its previous value and add the new
updated value as per the point update query.

Time Complexity: O(1)
*/

    static int MAXN = 10000;
    static int SQRSIZE = 100;

    static int[] arr = new int[MAXN]; // original array
    static int[] block = new int[SQRSIZE]; // decomposed array
    static int blk_sz; // block size

    // Time Complexity : O(1)
    static void update(int idx, int val) {
        int blockNumber = idx / blk_sz;
        block[blockNumber] += val - arr[idx];
        arr[idx] = val;
    }

    // Time Complexity : O(sqrt(n))
    static int query(int l, int r) {
        int sum = 0;
        while (l < r && l % blk_sz != 0 && l != 0) {
            // traversing first block in range
            sum += arr[l];
            l++;
        }
        while (l + blk_sz - 1 <= r) {
            // traversing completely overlapped blocks in range
            sum += block[l / blk_sz];
            l += blk_sz;
        }
        while (l <= r) {
            // traversing last block in range
            sum += arr[l];
            l++;
        }
        return sum;
    }

    // Fills values in input[]
    static void preprocess(int[] input, int n) {
        // initiating block pointer
        int blk_idx = -1;

        // calculating size of block
        blk_sz = (int) Math.sqrt(n);

        // building the decomposed array
        for (int i = 0; i < n; i++) {
            arr[i] = input[i];
            if (i % blk_sz == 0) {
                // entering next block incrementing block pointer
                blk_idx++;
            }
            block[blk_idx] += arr[i];
        }
    }

    public static void main(String[] args) {
        int[] input = {1, 5, 2, 4, 6, 1, 3, 5, 7, 10};
        int n = input.length;

        preprocess(input, n);

        System.out.println("query(3, 8) : " + query(3, 8));
        System.out.println("query(1, 6) : " + query(1, 6));
        update(8, 0);
        System.out.println("query(8, 8) : " + query(8, 8));
    }
}


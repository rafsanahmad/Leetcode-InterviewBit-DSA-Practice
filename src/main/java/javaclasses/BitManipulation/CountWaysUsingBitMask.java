/*
 * *
 *  * CountWaysUsingBitMask.java
 *  * Created by Rafsan Ahmad on 9/21/23, 1:58 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.BitManipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountWaysUsingBitMask {
    /*There are 100 different types of caps each having a unique id from 1 to 100. Also,
    there are ‘n’ persons each having a collection of a variable number of caps.
    One day all of these persons decide to go in a party wearing a cap but to look unique
    they decided that none of them will wear the same type of cap. So, count the total number
    of arrangements or ways such that none of them is wearing the same type of cap.
    Constraints: 1 <= n <= 10 Example:

The first line contains the value of n, next n lines contain collections
of all the n persons.
Input:
3
5 100 1     // Collection of the first person.
2           // Collection of the second person.
5 100       // Collection of the third person.

Output:
4
Explanation: All valid possible ways are (5, 2, 100),  (100, 2, 5),
            (1, 2, 5) and  (1, 2, 100)
Since, number of ways could be large, so output modulo 1000000007

Approach:
A Simple Solution is to try all possible combinations. Start by picking the first element from
the first set, marking it as visited and recur for remaining sets. It is basically a Backtracking
 based solution.

What is Bitmasking?

Suppose we have a collection of elements which are numbered from 1 to N. If we want to represent
a subset of this set then it can be encoded by a sequence of N bits (we usually call this
sequence a “mask”). In our chosen subset the i-th element belongs to it if and only if the i-th
bit of the mask is set i.e., it equals to 1. For example, the mask 10000101 means that the
subset of the set [1… 8] consists of elements 1, 3 and 8. We know that for a set of N elements
there are total 2N subsets thus 2N masks are possible, one representing each subset.
Each mask is, in fact, an integer number written in binary notation.

How is this problem solved using Bitmasking + DP? The idea is to use the fact that there are
upto 10 persons. So we can use an integer variable as a bitmask to store which person is wearing
 a cap and which is not.

Let i be the current cap number (caps from 1 to i-1 are already
processed). Let integer variable mask indicates that the persons w
earing and not wearing caps.  If i'th bit is set in mask, then
i'th person is wearing a cap, else not.

             // consider the case when ith cap is not included
                     // in the arrangement
countWays(mask, i) = countWays(mask, i+1) +

                    // when ith cap is included in the arrangement
                    // so, assign this cap to all possible persons
                    // one by one and recur for remaining persons.
                    ∑ countWays(mask | (1 << j), i+1)
                       for every person j that can wear cap i

Note that the expression "mask | (1 << j)" sets j'th bit in mask.
And a person can wear cap i if it is there in the person's cap list
provided as input.
If we draw the complete recursion tree, we can observe that many subproblems are solved again
and again. So we use Dynamic Programming. A table dp[][] is used such that in every entry
dp[i][j], i is mask and j is cap number. Since we want to access all persons that can wear a
given cap, we use an array of vectors, capList[101]. A value capList[i] indicates the list of
persons that can wear cap i.
*/

    final int MOD = 1000000007;

    // dp[2^10][101] .. in dp[i][j], i denotes the mask i.e., it tells that
    // how many and which persons are wearing cap. j denotes the first j caps
    // used. So, dp[i][j] tells the number ways we assign j caps to mask i
    // such that none of them wears the same cap
    int[][] dp = new int[1025][101];

    // This is used for base case, it has all the N bits set
    // so, it tells whether all N persons are wearing a cap.
    int allmask;

    // Mask is the set of persons, i is cap-id (OR the
    // number of caps processed starting from first cap).
    long countWaysUtil(int mask, int i, List<Integer>[] capList) {
        // If all persons are wearing a cap so we
        // are done and this is one way so return 1
        if (mask == allmask) return 1;

        // If not everyone is wearing a cap and also there are no more
        // caps left to process, so there is no way, thus return 0;
        if (i > 100) return 0;

        // If we already have solved this subproblem, return the answer.
        if (dp[mask][i] != -1) return dp[mask][i];

        // Ways, when we don't include this cap in our arrangement
        // or solution set.
        long ways = countWaysUtil(mask, i + 1, capList);

        // size is the total number of persons having cap with id i.
        int size = capList[i].size();

        // So, assign one by one ith cap to all the possible persons
        // and recur for remaining caps.
        for (int j = 0; j < size; j++) {
            // if person capList[i][j] is already wearing a cap so continue as
            // we cannot assign him this cap
            if ((mask & (1 << capList[i].get(j))) != 0) continue;

                // Else assign him this cap and recur for remaining caps with
                // new updated mask vector
            else
                ways += countWaysUtil(mask | (1 << capList[i].get(j)), i + 1, capList);
            ways %= MOD;
        }

        // Save the result and return it.
        return dp[mask][i] = (int) ways;
    }

    void countWays(int n, List<Integer>[] capList) {
        // All mask is used to check of all persons
        // are included or not, set all n bits as 1
        allmask = (1 << n) - 1;

        // Initialize all entries in dp as -1
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        // Call recursive function count ways
        System.out.println(countWaysUtil(0, 1, capList));
    }

    public static void main(String[] args) {
        CountWaysUsingBitMask bitMask = new CountWaysUsingBitMask();
        int n = 3;
        List<Integer>[] capList = new ArrayList[101];

        for (int i = 0; i < capList.length; i++)
            capList[i] = new ArrayList<>();

        // 1st person collection: 5, 100
        // 2nd person collection: 2
        // 3rd person collection: 5, 100, 1
        capList[5].addAll(Arrays.asList(0, 2));
        capList[2].addAll(Arrays.asList(1));
        capList[1].addAll(Arrays.asList(2));
        capList[100].addAll(Arrays.asList(0, 2));
        bitMask.countWays(n, capList);
    }
}

/*
 * *
 *  * Edit Distance.java
 *  * Created by Rafsan Ahmad on 2/11/22, 12:45 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;


import java.util.Arrays;

public class EditDistance {
    //https://leetcode.com/problems/edit-distance/description/
    /*
    Given two strings word1 and word2, return the minimum number of operations required to
    convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Constraints:
0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/
    /*The Levenshtein distance (or Edit distance) is a way of quantifying how different two strings are from one
    another by counting the minimum number of operations required to transform one string into the other.

The Levenshtein distance between two words is the minimum number of single-character edits (i.e., insertions,
deletions, or substitutions) required to change one word into the other. Each of these operations has a unit cost.


For example, the Levenshtein distance between kitten and sitting is 3. The minimal edit script that transforms the
former into the latter is:

kitten —> sitten (substitution of s for k)
sitten —> sittin (substitution of i for e)
sittin —> sitting (insertion of g at the end)

The Edit distance problem has optimal substructure. That means the problem can be broken down into smaller,
simple “subproblems”, which can be broken down into yet simpler subproblems, and so on, until, finally,
the solution becomes trivial.


Problem: Transform string X[1…m] into Y[1…n] by performing edit operations on string X.

Subproblem: Transform substring X[1…i] into Y[1…j] by performing edit operations on substring X.


Case 1: We have reached the end of either substring.

If substring X is empty, insert all remaining characters of substring Y into X. The cost of this operation is equal to
the number of characters left in substring Y.

('', 'ABC') ——> ('ABC', 'ABC') (cost = 3)

If substring Y is empty, insert all remaining characters of substring X into Y. The cost of this operation is equal to
the number of characters left in substring X.

('ABC', '') ——> ('', '') (cost = 3)


Case 2: The last characters of substring X and Y are the same.

If the last characters of substring X and substring Y matches, nothing needs to be done – simply recur for the remaining
substring X[0…i-1], Y[0…j-1]. As no edit operation is involved, the cost will be 0.

('ACC', 'ABC') ——> ('AC', 'AB') (cost = 0)


Case 3: The last characters of substring X and Y are different.

If the last characters of substring X and Y are different, return the minimum of the following operations:

Insert the last character of Y into X. The size of Y reduces by 1, and X remains the same.
This accounts for X[1…i], Y[1…j-1] as we move in on the target substring, but not in the source substring.

('ABA', 'ABC') ——> ('ABAC', 'ABC') == ('ABA', 'AB') (using case 2)

Delete the last character of X. The size of X reduces by 1, and Y remains the same.
This accounts for X[1…i-1], Y[1…j] as we move in on the source string, but not in the target string.

('ABA', 'ABC') ——> ('AB', 'ABC')

Substitute (Replace) the current character of X by the current character of Y.
The size of both X and Y reduces by 1. This accounts for X[1…i-1], Y[1…j-1] as we move in both the source and target
string.

('ABA', 'ABC') —> ('ABC', 'ABC') == ('AB', 'AB') (using case 2)

It is basically the same as case 2, where the last two characters match, and we move in both the source and target
string, except it costs an edit operation.

So, we can define the problem recursively as:

             | max(i, j)                            when min(i, j) = 0

dist[i][j] = | dist[i – 1][j – 1]                   when X[i-1] == Y[j-1]

             | 1 + minimum { dist[i – 1][j],        when X[i-1] != Y[j-1]
             |            dist[i][j – 1],
             |            dist[i – 1][j – 1] }

*/

    //Top down-Memoization
    int minDistanceUtil(String word1, String word2, int i, int j, int[][] dp) {
        // Base cases
        if (i < 0)
            return j + 1;
        if (j < 0)
            return i + 1;

        // If the result is already computed, return it
        if (dp[i][j] != -1)
            return dp[i][j];

        // If the characters at the current positions match, no edit is needed
        if (word1.charAt(i) == word2.charAt(j))
            return dp[i][j] = minDistanceUtil(word1, word2, i - 1, j - 1, dp);

            // Minimum of three choices:
            // 1. Replace the character in S1 with the character in S2.
            // 2. Delete the character in S1.
            // 3. Insert the character from S2 into S1.
        else
            return dp[i][j] = 1 +
                    Math.min(minDistanceUtil(word1, word2, i - 1, j - 1, dp),
                            Math.min(minDistanceUtil(word1, word2, i - 1, j, dp),
                                    minDistanceUtil(word1, word2, i, j - 1, dp)));
    }

    int minDistanceRecursion(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Call the recursive helper function
        return minDistanceUtil(word1, word2, m - 1, n - 1, dp);
    }

    //Bottom up-Tabulation
    /*Time Complexity: O(M*N)
    Space Complexity: O(M*N)
    */
    int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the dp array using a bottom-up approach
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistanceRecursion("horse", "ros"));
        System.out.println(editDistance.minDistance("horse", "ros"));
    }
}

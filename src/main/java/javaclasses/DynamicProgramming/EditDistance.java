/*
 * *
 *  * Edit Distance.java
 *  * Created by Rafsan Ahmad on 2/11/22, 12:45 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.DynamicProgramming;

public class EditDistance {
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

    // Utility function to find the minimum of three numbers
    public static int minimum(int a, int b, int c) {
        return Integer.min(a, Integer.min(b, c));
    }

    // Function to find Levenshtein distance between string `X` and `Y`.
    public static int dist(String X, String Y) {
        // `m` and `n` is the total number of characters in `X` and `Y`, respectively
        int m = X.length();
        int n = Y.length();

        // For all pairs of `i` and `j`, `T[i, j]` will hold the Levenshtein distance
        // between the first `i` characters of `X` and the first `j` characters of `Y`.
        // Note that `T` holds `(m+1)×(n+1)` values.

        int[][] T = new int[m + 1][n + 1];

        // we can transform source prefixes into an empty string by
        // dropping all characters

        for (int i = 1; i <= m; i++) {
            T[i][0] = i;                // (case 1)
        }

        // we can reach target prefixes from empty source prefix
        // by inserting every character

        for (int j = 1; j <= n; j++) {
            T[0][j] = j;                // (case 1)
        }

        int cost;

        // fill the lookup table in a bottom-up manner
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {   // (case 2)
                    cost = 0;                           // (case 2)
                } else {
                    cost = 1;                           // (case 3c)
                }

                T[i][j] = minimum(T[i - 1][j] + 1,      // deletion (case 3b)
                        T[i][j - 1] + 1,                // insertion (case 3a)
                        T[i - 1][j - 1] + cost);        // replace (case 2 + 3c)
            }
        }

        return T[m][n];
    }

    public static void main(String[] args) {
        String X = "kitten", Y = "sitting";
        System.out.print("The Levenshtein distance is " + dist(X, Y));
    }
}

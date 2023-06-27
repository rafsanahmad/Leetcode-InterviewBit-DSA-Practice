/*
 * *
 *  * StoneGameIII.java
 *  * Created by Rafsan Ahmad on 6/27/23, 5:08 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.DynamicProgramming;

public class StoneGameIII {
    //https://leetcode.com/problems/stone-game-iii/
    /*Alice and Bob continue their games with piles of stones. There are several stones arranged
    in a row, and each stone has an associated value which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take
 1, 2, or 3 stones from the first remaining stones in the row.

The score of each player is the sum of the values of the stones taken. The score of each player
is 0 initially.

The objective of the game is to end with the highest score, and the winner is the player with the

 highest score and there could be a tie. The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with
the same score.

Example 1:

Input: stoneValue = [1,2,3,7]
Output: "Bob"
Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6.
Now the score of Bob is 7 and Bob wins.

Example 2:

Input: stoneValue = [1,2,3,-9]
Output: "Alice"
Explanation: Alice must choose all the three piles at the first move to win and leave Bob with negative
 score.
If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. In the next move, Alice will take the pile with value = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. In the next move, Alice will take the pile with value = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.


Example 3:

Input: stoneValue = [1,2,3,6]
Output: "Tie"
Explanation: Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.


Constraints:

1 <= stoneValue.length <= 5 * 10^4
-1000 <= stoneValue[i] <= 1000*/

    public String stoneGameIII(int[] stoneValue) {
        int len = stoneValue.length;
        int[] dp = new int[3];

        int score = helper(stoneValue, dp);

        if (score > 0)
            return "Alice";
        else if (score == 0)
            return "Tie";
        return "Bob";
    }

    public int helper(int[] stoneValue, int[] dp) {
        int len = stoneValue.length;

        for (int j = len - 1; j >= 0; j--) {
            // Take 1
            int take1 = stoneValue[j] - dp[(j + 1) % 3];

            int take2 = Integer.MIN_VALUE;
            if (j + 1 < len) {
                take2 = stoneValue[j] + stoneValue[j + 1] - dp[(j + 2) % 3];
            }

            int take3 = Integer.MIN_VALUE;
            if (j + 2 < len) {
                take3 = stoneValue[j] + stoneValue[j + 1] + stoneValue[j + 2] - dp[(j + 3) % 3];
            }

            dp[j % 3] = max(take1, take2, take3);
        }

        return dp[0];

    }

    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static void main(String[] args) {
        StoneGameIII gameIII = new StoneGameIII();
        int[] stones = {1, 2, 3, -9};
        int[] stones2 = {1, 2, 3, 6};
        System.out.println(gameIII.stoneGameIII(stones));
        System.out.println(gameIII.stoneGameIII(stones2));
    }
}
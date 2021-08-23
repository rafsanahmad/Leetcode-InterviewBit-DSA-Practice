package javaclasses.DynamicProgramming;

public class NthStairCase {
    /*A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time.
     Implement a method to count how many possible ways the child can run up the stairs.

Examples:
Input : 4
Output : 7
Explanation:
Below are the seven ways
 1 step + 1 step + 1 step + 1 step
 1 step + 2 step + 1 step
 2 step + 1 step + 1 step
 1 step + 1 step + 2 step
 2 step + 2 step
 3 step + 1 step
 1 step + 3 step

Input : 3
Output : 4
Explanation:
Below are the four ways
 1 step + 1 step + 1 step
 1 step + 2 step
 2 step + 1 step
 3 step

 There are two methods to solve this problem:
Recursive Method
Dynamic Programming
*/

    // Returns count of ways to reach
    // n-th stair using 1 or 2 or 3 steps.
    //Time Complexity: O(3n).
    public static int findStep(int n) {
        if (n == 1 || n == 0)
            return 1;
        else if (n == 2)
            return 2;
        else
            return findStep(n - 3) + findStep(n - 2)
                    + findStep(n - 1);
    }

    /*Dynamic Programming.
The idea is similar, but it can be observed that there are n states but the recursive function is called 3 ^ n times.
That means that some states are called repeatedly. So the idea is to store the value of states. This can be done in two ways.

Top-Down Approach: The first way is to keep the recursive structure intact and just store the value in a HashMap
and whenever the function is called again return the value store without computing ().
Bottom-Up Approach: The second way is to take an extra space of size n and start computing values of states
from 1, 2 .. to n, i.e. compute values of i, i+1, i+2 and then use them to calculate the value of i+3.*/

    // A recursive function used by countWays
    //Time Complexity: O(n).
    public static int countWays(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        res[2] = 2;

        for (int i = 3; i <= n; i++)
            res[i] = res[i - 1] + res[i - 2] + res[i - 3];

        return res[n];
    }

    public static void main(String argc[]) {
        int n = 4;
        System.out.println(countWays(n));
    }
}

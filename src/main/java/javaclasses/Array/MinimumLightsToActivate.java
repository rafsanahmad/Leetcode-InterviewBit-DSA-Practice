package javaclasses.Array;

public class MinimumLightsToActivate {
    //https://www.interviewbit.com/problems/minimum-lights-to-activate/
    /*Problem Description

There is a corridor in a Jail which is N units long. Given an array A of size N. The ith index of this array is 0
if the light at ith position is faulty otherwise it is 1.

All the lights are of specific power B which if is placed at position X, it can light the corridor from [ X-B+1,
X+B-1].

Initially all lights are off.
Return the minimum number of lights to be turned ON to light the whole corridor or -1 if the whole corridor cannot
be lighted.

Problem Constraints
1 <= N <= 1000
1 <= B <= 1000

Input Format
First argument is an integer array A where A[i] is either 0 or 1.
Second argument is an integer B.

Output Format
Return the minimum number of lights to be turned ON to light the whole corridor or -1 if the whole corridor cannot
be lighted.

Example Input
Input 1:
A = [ 0, 0, 1, 1, 1, 0, 0, 1].
B = 3

Input 2:
A = [ 0, 0, 0, 1, 0].
B = 3

Example Output
Output 1:
2

Output 2:
-1

Example Explanation

Explanation 1:
In the first configuration, Turn on the lights at 3rd and 8th index.
Light at 3rd index covers from [ 1, 5] and light at 8th index covers [ 6, 8].

Explanation 2:
In the second configuration, there is no light which can light the first corridor.
*/
    public int solve(int[] A, int B) {
        int n = A.length;
        if (B >= n)
            return 1;

        int i = 0;
        int t = 0;
        int ans = 0;
        while (i < n) {
            int flag = 0;
            for (int x = i + B - 1; x >= t; x--) {
                if (x < n && A[x] == 1) {
                    t = x + 1;   //if in case there is no working bulb after (x+B-1) then we will have to use bulb from x to x+B-1 range
                    flag = 1;
                    ans++;
                    i = x + B;    //Because all units till (x+B-1) will have light
                    break;
                }
            }
            if (i >= n)
                return ans;
            if (flag == 0)   // No working bulb found in given range
                return -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ar = {1, 1, 1, 1, 1, 0, 0};
        MinimumLightsToActivate lights = new MinimumLightsToActivate();
        System.out.println(lights.solve(ar, 3));
    }
}

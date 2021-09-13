package javaclasses.Strings;

import java.util.Arrays;

public class OrderlyQueue {
    /*You are given a string s and an integer k. You can choose one of the first k letters of s and append
    it at the end of the string..

Return the lexicographically smallest string you could have after applying the mentioned step any number of moves.

Example 1:
Input: s = "cba", k = 1
Output: "acb"
Explanation:
In the first move, we move the 1st character 'c' to the end, obtaining the string "bac".
In the second move, we move the 1st character 'b' to the end, obtaining the final result "acb".

Example 2:
Input: s = "baaca", k = 3
Output: "aaabc"
Explanation:
In the first move, we move the 1st character 'b' to the end, obtaining the string "aacab".
In the second move, we move the 3rd character 'c' to the end, obtaining the final result "aaabc".
 */

    /*We can divide the question in two parts

When k = 1
When k > 1, where we can prove that after some number of moves, the string can always be sorted
Example of String Rotation
12345 -> 23451 -> 34512 -> 45123 -> 51234

If k = 1, we can only rotate the whole string. There are s.length() different states and we return the
lexicographically smallest string.

If k > 1, we have two possible operations-
Rotate the whole string.
Rotate the whole string except the first letter.
We can rotate i+1th big letter to the start (1), then rotate ith big letter to the end (2).
We can look at it as an bubble sort: doing these moves we can sort all string, so directly return that.

Time complexity is O(n^2) for k = 1, else for k > 1 it is O(NlogN)
Space complexity is O(n)*/

    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            String ans = s;
            for (int i = 1; i < ans.length(); ++i) {
                String S2 = ans.substring(i) + ans.substring(0, i);
                if (ans.compareTo(S2) > 0)
                    ans = S2;
            }
            return ans;
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        OrderlyQueue queue = new OrderlyQueue();
        System.out.println(queue.orderlyQueue("cba", 1));
    }
}

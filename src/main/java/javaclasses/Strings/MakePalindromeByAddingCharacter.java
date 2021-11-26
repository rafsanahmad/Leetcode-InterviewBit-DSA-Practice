/*
 * *
 *  * Created by Rafsan Ahmad on 11/26/21, 9:55 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

public class MakePalindromeByAddingCharacter {
    /*[Problem Description]

You are given a string S consisting of lowercase English characters.

You have to add exactly one more character in S (you can add it at any position).

You have to answer whether you can make the resultant string a palindrome or not.

Note: A palindrome is a sequence that reads the same backwards as forwards, e.g. madam.

Input Format

The input contains the string S in a single line.

Constraints

1 ≤ size of S ≤ 100
S contains only lowercase English letters (a to z).

Output Format

Print one of the following as the answer:

Yes
No

Sample Input 0
abcb

Sample Output 0
Yes

Explanation 0
You can make this string a palindrome by adding the character 'a' at the end (abcb ⟶ abcba).

Sample Input 1
qp

Sample Output 1
Yes

Explanation 1
You can make this string a palindrome by adding 'p' at the begining or 'q' at the end (qp ⟶ pqp, qpq).

Sample Input 2
acca

Sample Output 2
Yes

Explanation 2
You can make this string a palindrome in multiple ways (acca ⟶ acaca, acbca, accca, etc).

Sample Input 3
xyz

Sample Output 3
No

Explanation 3
There are no ways to make this string a palindrome.*/

    public String checkPalindrome(String val) {
        int left = 0;
        int right = val.length() - 1;
        boolean conversion = true;
        while (left <= right) {
            if (val.charAt(left) != val.charAt(right)) {
                String s1 = val + val.charAt(left);
                String s2 = val.charAt(right) + val;
                String s3 = val.charAt(left) + val;
                String s4 = val + val.charAt(right);
                if (isPalindrome(s1, 0, s1.length() - 1) || isPalindrome(s2, 0, s2.length() - 1) ||
                        isPalindrome(s3, 0, s3.length() - 1) || isPalindrome(s4, 0, s4.length() - 1)) {
                    return "Yes";
                } else {
                    return "No";
                }
            } else {
                //No insertion
                conversion = false;
            }
            left++;
            right--;
        }

        //Already palindrome & length is odd (Ex: Madam)
        if (!conversion && val.length() % 2 != 0) {
            return "No";
        }
        return "Yes";
    }

    public static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        MakePalindromeByAddingCharacter palindrome = new MakePalindromeByAddingCharacter();
        System.out.println(palindrome.checkPalindrome("abcb"));
    }
}

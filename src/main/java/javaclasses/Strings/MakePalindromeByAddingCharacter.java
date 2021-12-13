/*
 * * Make Palindrome By Adding Character.java
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
        while (left <= right) {
            if (val.charAt(left) != val.charAt(right)) {
                String s1 = new StringBuilder(val).insert(right + 1, val.charAt(left)).toString();
                if (isPalindrome(s1, 0, s1.length() - 1)) {
                    return "Yes";
                }
                if (left > 0) {
                    String s2 = new StringBuilder(val).insert(left - 1, val.charAt(right)).toString();
                    if (isPalindrome(s2, 0, s2.length() - 1)) {
                        return "Yes";
                    }
                }
                String s3 = new StringBuilder(val).insert(left, val.charAt(right)).toString();
                if (isPalindrome(s3, 0, s3.length() - 1)) {
                    return "Yes";
                }
                String s4 = new StringBuilder(val).insert(right, val.charAt(left)).toString();
                if (isPalindrome(s4, 0, s4.length() - 1)) {
                    return "Yes";
                }
                return "No";
            }
            ++left;
            --right;
        }

        //Already palindrome check for duplicate character
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
        System.out.println(palindrome.checkPalindrome("acca"));
        System.out.println(palindrome.checkPalindrome("madam"));
        System.out.println(palindrome.checkPalindrome("xz"));
        System.out.println(palindrome.checkPalindrome("xyz"));
        System.out.println(palindrome.checkPalindrome("accca"));
        System.out.println(palindrome.checkPalindrome("abcdxcba"));
        //System.out.println(palindrome.checkPalindrome("mnbvcxzlkjhgfdspoiuytrewqqwertyuiopasdfghjklzxcvbnm"));
        System.out.println(palindrome.checkPalindrome("z"));
        //System.out.println(palindrome.checkPalindrome("zlkjhgfdapoiuytrewertyuiopadfghjklz"));
        //System.out.println(palindrome.checkPalindrome("zlkjhfdapoiuytrewertyuiopadfghjklz"));
        //System.out.println(palindrome.checkPalindrome("aaaaaaaaaaaaabbbbbbbbbbbbbccccccccccdxccccccccccbbbbbbbbbbbbbaaaaaaaaaaaaa"));
    }
}

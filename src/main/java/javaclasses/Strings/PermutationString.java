package javaclasses.Strings;

/*Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False
 

Constraints:

The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].*/

public class PermutationString {
    public boolean checkInClusion(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();

        if (s2 == null || s2.length() == 0 || s1Len > s2Len)
            return false;

        int[] s1Arr = new int[26];
        int[] s2Arr = new int[26];

        for (int i = 0; i < s1Len; i++) {
            s1Arr[s1.charAt(i) - 'a']++;
            s2Arr[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2Len - s1Len; i++) {
            if (isPermutation(s1Arr, s2Arr))
                return true;

            //remove character at ith index and include
            //character at i+sLen index
            s2Arr[s2.charAt(i) - 'a']--;
            s2Arr[s2.charAt(i + s2Len) - 'a']++;
        }

        if (isPermutation(s1Arr, s2Arr))
            return true;
        return false;
    }

    public boolean isPermutation(int[] s1Arr, int[] s2Arr) {
        for (int i = 0; i < s1Arr.length; i++) {
            if (s1Arr[i] != s2Arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
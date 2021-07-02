package javaclasses.Strings;
/*Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"
Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input:
s: "abab" p: "ab"
Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".*/

/*LeetCode 438*/
//Using map & sliding window
//instead of using hashmap here we are going to use array of fixed size (26)


import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {
    //String s = base String
    //String p = Sub string
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLen = p.length();
        int sLen = s.length();

        if (s == null || s.length() == 0 || sLen < pLen) {
            return result;
        }

        int[] pArr = new int[26];
        int[] sArr = new int[26];

	/*put character count of array p in pArr
	also create first window of size pLen*/
        for (int i = 0; i < p.length(); i++) {
            pArr[p.charAt(i) - 'a']++;
            sArr[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < sLen - pLen; i++) {
            if (isAnagram(pArr, sArr)) {
                result.add(i);
            }
            sArr[s.charAt(i) - 'a']--;
            sArr[s.charAt(i + pLen) - 'a']++;
        }

        if (isAnagram(pArr, sArr)) {
            result.add(sLen - pLen);
        }
        return result;
    }

    public boolean isAnagram(int[] pArr, int[] sArr) {
        for (int i = 0; i < pArr.length; i++) {
            if (pArr[i] != sArr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> result = new FindAnagrams().findAnagrams("abab", "ba");
        System.out.println(result.toString());
    }
}

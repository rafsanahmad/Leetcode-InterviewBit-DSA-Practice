/*
 * * Roman To Integer.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    //https://leetcode.com/problems/roman-to-integer/
    /*
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as
XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral
for four is not IIII. Instead, the number four is written as IV. Because the one is before the
five we subtract it making four. The same principle applies to the number nine, which is written
as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.


Example 1:
Input: s = "III"
Output: 3
Explanation: III = 3.

Example 2:
Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 3:
Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Constraints:
1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
*/

    public static int convertRomanToInteger(String str) {
        //If string value is null or zero
        int len = str.length();
        if (str.isEmpty()) {
            return 0;
        }

        //Create a mapping of roman numerals and it's integer value
        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('I', 1);
        charMap.put('V', 5);
        charMap.put('X', 10);
        charMap.put('L', 50);
        charMap.put('C', 100);
        charMap.put('D', 500);
        charMap.put('M', 1000);

        int result = 0;
 
       /*
         Traverse a string and pick each character at a time.
       */
        for (int i = 0; i < len - 1; i++) {
 
             /*
               If current Roman numeral value is greater than
               then the next value, the do addition.
             */
            if (charMap.get(str.charAt(i)) >= charMap.get(str.charAt(i + 1))) {
                result += charMap.get(str.charAt(i));
            } else {
                result -= charMap.get(str.charAt(i));
            }
        }

        //Add the value of last numeral
        result = result + charMap.get(str.charAt(len - 1));

        return result;
    }


    public static void main(String[] args) {
        System.out.println(convertRomanToInteger("XC"));
        System.out.println(convertRomanToInteger("LVIII"));
        System.out.println(convertRomanToInteger("MCMXCIV"));
    }
}
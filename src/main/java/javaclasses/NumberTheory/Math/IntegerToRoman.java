/*
 * * Integer To Roman.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

import java.util.LinkedHashMap;
import java.util.Map;

public class IntegerToRoman {
    //https://leetcode.com/problems/integer-to-roman/description/
    /*
    Seven different symbols represent Roman numerals with the following values:

Symbol	Value
I	1
V	5
X	10
L	50
C	100
D	500
M	1000
Roman numerals are formed by appending the conversions of decimal place values from highest to
lowest. Converting a decimal place value into a Roman numeral has the following rules:

If the value does not start with 4 or 9, select the symbol of the maximal value that can be
subtracted from the input, append that symbol to the result, subtract its value, and convert the
remainder to a Roman numeral.
If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from
the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than
10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC),
400 (CD) and 900 (CM).
Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent
multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to
append a symbol 4 times use the subtractive form.
Given an integer, convert it to a Roman numeral.


Example 1:
Input: num = 3749
Output: "MMMDCCXLIX"

Explanation:
3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 700 = DCC as 500 (D) + 100 (C) + 100 (C)
  40 = XL as 10 (X) less of 50 (L)
   9 = IX as 1 (I) less of 10 (X)
Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places

Example 2:
Input: num = 58
Output: "LVIII"
Explanation:

50 = L
 8 = VIII

Example 3:
Input: num = 1994
Output: "MCMXCIV"

Explanation:
1000 = M
 900 = CM
  90 = XC
   4 = IV


Constraints:
1 <= num <= 3999
*/

    public String intToRoman(int num) {
        //Create a mapping of roman numerals and it's integer value
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("CD", 400);
        map.put("C", 100);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("XL", 40);
        map.put("X", 10);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("IV", 4);
        map.put("I", 1);
        StringBuilder res = new StringBuilder();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int matches = num / entry.getValue();
            res.append(romanLiteral(entry.getKey(), matches));
            num = num % entry.getValue();
        }
        return res.toString();
    }

    public static String romanLiteral(String s, int n) {
        if (s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman roman = new IntegerToRoman();
        System.out.println(roman.intToRoman(120));
        System.out.println(roman.intToRoman(3749));
        System.out.println(roman.intToRoman(1994));
    }
}

package javaclasses.NumberTheory.Math;

import java.util.LinkedHashMap;
import java.util.Map;

public class IntegerToRoman {
    //Leetcode 12
    //https://www.interviewbit.com/problems/integer-to-roman/
    /*Given an integer A, convert it to a roman numeral, and return a string corresponding to its
    roman numeral version

Note : This question has a lot of scope of clarification from the interviewer. Please take a moment to
think of all the needed clarifications and see the expected response using “See Expected Output”

For the purpose of this question, https://projecteuler.net/about=roman_numerals has very detailed
explanations.

Input Format

The only argument given is integer A.
Output Format

Return a string denoting roman numeral version of A.
Constraints

1 <= A <= 3999
For Example

Input 1:
    A = 5
Output 1:
    "V"

Input 2:
    A = 14
Output 2:
    "XIV"
*/

    public String intToRoman(int A) {
        //Create a mapping of roman numerals and it's integer value
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for (Map.Entry<String, Integer> entry : roman_numerals.entrySet()) {
            int matches = A / entry.getValue();
            res += repeat(entry.getKey(), matches);
            A = A % entry.getValue();
        }
        return res;
    }

    public static String repeat(String s, int n) {
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
    }
}

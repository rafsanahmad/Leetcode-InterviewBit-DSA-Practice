package javaclasses.NumberTheory;

/*Given a Roman numeral, Write a code to convert roman to integer value.

Roman numerals are represented by seven different letters (I, V, X, L, C, D, M).
These seven letters are used to make thousand of numbers.

For example –
Example 1 –
Input  : II , Output : 2

Example 2 –
Input  : XI , Output : 11

Example 3 –
Input  : IV , Output : 4

Example 4 –
Input : LVII , Output: 57 

NOTE : The given input is guaranteed to be within the range from 1 to 3999.

*/

/*Before solving this problem, Let’s understand few important points about Roman Numerals.

Roman numerals are usually written largest to smallest from left to right. For example – XII (12), VII (7), LVII (57) etc.

However, the numeral for four is not IIII. Instead, the number four is written as IV.
Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX. There are six instances where we need to do subtraction.

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Keeping these points in mind, Here are the following steps to solve this problem.

Traverse a string and pick one roman numeral (each character) at a time.
Convert each symbol of Roman Numeral into the value it represents.
If current value of numeral is greater than or equal to the value of next numeral,
then add this value to the running total. Else subtract this value from the result.
The time complexity of this approach is O(n) and it’s space complexity is O(1).*/


import java.util.HashMap;
import java.util.Map;

//Convert Roman Numerals to Decimal Value
public class RomanToInteger {

    public static int convertRomanToInteger(String str) {

        //If string value is null or zero
        if (str == null || str.length() == 0) {
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
        for (int i = 0; i < str.length() - 1; i++) {
 
             /*
               If current Roman numeral value is greater than
               then the next value, the do addition.
             */
            if (charMap.get(str.charAt(i)) >= charMap.get(str.charAt(i + 1))) {
                result = result + charMap.get(str.charAt(i));
            } else {
                result = result - charMap.get(str.charAt(i));
            }
        }

        //Add the value of last numeral
        result = result + charMap.get(str.charAt(str.length() - 1));

        return result;
    }


    public static void main(String[] args) {

        //String str = "IV";
        //String str = "XXIV";
        String str = "XC";

        int result = convertRomanToInteger(str);
        System.out.println(result);
    }
}
package javaclasses.Strings;
/*Write a java program to find first non-repeating character in a string. Given an input string, Write a java code to find first non-repeating character in a string.

For example –

i) Input string – java
Output – j (j is the first non-repeating character in a string)

ii) Input string – web rewrite
Output – b (b is the first non-repeating character in a string)

*/

/*In this solution, we are going to use LinkedHashMap to store character and their count. Java LinkedHashMap maintains the insertion order. So, once we traversed the string and created the map of a character and it’s count, we just need to iterate through LinkedHashMap and choose the first entry with a value 1.

Java LinkedHashMap to find the first non-repeating character in a String.

Step 1: Traverse the complete string and create a map for character and their count.
Step 2: Loop through LinkedHashMap to find an entry with a value 1.*/


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class FindFirstNonRepeatedCharacter {

    public static void main(String[] args) {
        String s;

        System.out.println("Enter an input string ");

        Scanner inp = new Scanner(System.in);
        s = inp.nextLine();

        Map<Character, Integer> charCount = new LinkedHashMap<>();
        Character ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
                 /* If key exist then increment it's value
                    otherwise set it's value to 1 */
            if (charCount.containsKey(ch)) {
                charCount.put(ch, charCount.get(ch) + 1);
            } else {
                charCount.put(ch, 1);
            }
        }

        //Traverse LinkedHashMap
        for (Entry<Character, Integer> entry : charCount.entrySet()) {

            if (entry.getValue() == 1) {

                System.out.println("First non-repeated character is " + entry.getKey());
                break;

            }
        }
    }
}
package javaclasses.Strings;
/*Write a java program to find first non-repeating character in a string. Given an input string, Write a java code to find first non-repeating character in a string.

For example –

i) Input string – java
Output – j (j is the first non-repeating character in a string)

ii) Input string – web rewrite
Output – b (b is the first non-repeating character in a string)

*/

/*In this solution, we are going to use HashMap to find first non-repeating character in a String.

Step 1: Traverse a String and store each character and their count in a HashMap.
Step 2: In the next step, let’s traverse a string again and check the count for each character in a Map. Once we found the character with count 1 we break the loop.


 
We are traversing the string again from first to the last character as HashMap doesn’t maintain the insertion order. 

 */


/*
Find first non-repeating character in a string using hashmap
*/

import java.util.*;

public class FirstNonRepeatedCharacter {


    public void findFirstCharacter(String str) {
        // Assign length of a string
        int len = str.length();

        // HashMap implementation for key and value pair
        HashMap<Character, Integer> charcount = new HashMap<Character, Integer>();

        Character ch;

        /*Create a key and value pair for character and it's count.
          If there  is no value stored for a character then set it to 1.
          Else we increment the character value by 1 */

        for (int i = 0; i < len; i++) {

            ch = str.charAt(i);

            /* If character is already exists
               then increment it's count by 1 */
            charcount.put(ch, charcount.getOrDefault(ch, 0) + 1);
        }

        for (int j = 0; j < len; j++) {

            ch = str.charAt(j);
            // Check character with value 1 (First non-repeated character)
            if (charcount.get(ch) == 1) {

                System.out.println("First non-repeated character is " + ch);
                break;

            }
        }
    }

    //LinkedHashMap maintains order of insertion
    public void findFirstUsingLinkedHashMap(String str) {
        Map<Character, Integer> charCount = new LinkedHashMap<>();
        Character ch;

        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            /* If key exist then increment it's value
              otherwise set it's value to 1 */
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        //Traverse LinkedHashMap
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {

            if (entry.getValue() == 1) {

                System.out.println("First non-repeated character is " + entry.getKey());
                break;

            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Enter an input string ");
        // Take an input
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        FirstNonRepeatedCharacter f = new FirstNonRepeatedCharacter();
        f.findFirstUsingLinkedHashMap(str);
    }
}
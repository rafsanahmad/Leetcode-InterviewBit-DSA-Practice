/*
 * * Non Repeating Stream.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Given a string A denoting a stream of lowercase alphabets. We have to write a code to make a new string B.

Here is the rule to form string B.

i) We have to find first non-repeating character each time a character is inserted to the stream and append
it at the end to B.

ii) If no non-repeating character is found then append ‘#’ at the end of B.

For example – 

Example 1 –

Input   = “abadbc”

Output  = “aabbdd”

Explanation:

“a”      –   From the stream the first character is a, So at this point first non repeating character is ‘a’.

“ab”     –   When b comes, Still the first non repeating character is ‘a’.

“aba”    –   This time the character is a. Character a is repeated twice so the first non repeating character is ‘b’

“abad”   –   When character d comes, first non repeating character is ‘b’.

“abadb”  –   When character b comes, it’s count is 2 now. So the first non repeating character at this point is ‘d’.

“abadbc” –   Next character is c. At this point the first non repeating character is ‘d’.

Example 2 –

Input  = “abdabd”

Output = “aaabd#”

*/

/*In this example, i am going to example how we can solve this problem using HashMap and Queue data structure.

Here are the following steps –

i) When a character comes from the stream, we put the character and it’s count in a HashMap. Also,
we enqueue that character in a queue.

ii) If it’s count is 1, then we append that character in a new string. Else we poll the queue until
we found the character whose count is 1. If queue is empty then we append #.

*/


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//First non-repeating character in a stream of characters
public class NonRepeatingStream {

    public static String nonRepeatingCharacter(String A) {

        StringBuilder ans = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();

        Map<Character, Integer> charCountMap = new HashMap<>();


        for (int i = 0; i < A.length(); i++) {

            char c = A.charAt(i);
            //put character and it's count in a map
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            //Add them in a queue
            queue.add(c);

            //While queue is not empty
            while (!queue.isEmpty()) {

                if (charCountMap.containsKey(queue.peek()) &&
                        charCountMap.get(queue.peek()) > 1) {
                    queue.poll();
                } else {
                    break;
                }
            }

            if (queue.isEmpty()) {
                ans.append('#');
            } else {
                ans.append(queue.peek());
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {

        String str = "aabadbc";
        String result = nonRepeatingCharacter(str);

        System.out.println(result);
    }
}
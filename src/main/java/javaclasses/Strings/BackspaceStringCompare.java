package javaclasses.Strings;
/*Backspace String Compare. Given two strings S and T, we have to write a code to check if they are equal when both are typed into empty text editors.  In string # means a backspace character.

Example 1:

Input: S = “ab#c”, T = “ad#c”

Output: true

Explanation: Both S and T become “ac”.

Example 2:

Input: S = “ab##”, T = “c#d#”

Output: true

Explanation: Both S and T become “”.

Example 3:

Input: S = “a##c”, T = “#a#c”

Output: true

Explanation: Both S and T become “c”.

Example 4:

Input: S = “a#c”, T = “b”

Output: false

Explanation: S becomes “c” while T becomes “b”.

*/

/*The simplest approach to solve this problem is to use two stacks. Here are the following steps –

i) Declare two stacks.

ii) Perform following operations for both the strings.

a) Traverse a string and pick one character at a time. If the character is not a backspace character (#) then push them into a stack.

b) If it’s a backspace character then pop out the last pushed character from a stack.

iii) After that compare both the stacks. If both are equal then return true else false.

The time complexity of this approach is O(n) and it’s space complexity is also O(n).*/


import java.util.Stack;

//Backspace String Compare using Stack
public class BackspaceStringCompare {
 
     public static boolean backspaceCompareUsingStack(String S, String T) {
 
         Stack<Character> st1 = backspaceOperationWithStack(S);
         Stack<Character> st2 = backspaceOperationWithStack(T);
 
         return st1.equals(st2);
      }
 
      private static Stack<Character> backspaceOperationWithStack(String str) {
 
          Stack<Character> st = new Stack<>();
 
          //Traverse a string
          for(int i = 0; i < str.length(); i++) {
 
            char ch = str.charAt(i);
 
            /*
              If current char is not a backspace,
              then push them in a stack. Else pop the
              last pushed character from a stack.
            */
            if(ch != '#') {
             st.push(ch);
 
            } else if (!st.isEmpty()){
               st.pop();
           } 
         }
 
         return st;
     }
 
    public static void main(String[] args) {
         String str1 = "a##c";
         String str2 = "#a#c";
 
          boolean result = backspaceCompareUsingStack(str1, str2);
 
         System.out.println(result);
     }
 }
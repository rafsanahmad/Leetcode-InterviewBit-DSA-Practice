package javaclasses.Strings;


import java.util.HashSet;
import java.util.Set;

/*Given an input string, Write a java code to find duplicate characters in a String. In this tutorial, I am going to explain multiple approaches to find duplicate characters in a string.

For example :

Input string: “Java”
Duplicate character : a

Input string: “programming”
Duplicate character : m,g,r*/


/*In this example, we are going to use set data structure. A set doesn’t contain duplicate element. Using this property we can easily find duplicate characters.*/

//print duplicate characters of a string using set
public class DuplicateCharacter {
 
   public static void printDuplicateUsingSet(String str) {
 
      //Declare set
      Set<Character> set = new HashSet<>();
 
      //Traverse a string
     for (int i = 0; i < str.length(); i++) {
        Character ch = str.charAt(i);
 
       //If character is already present in a set
      if (set.contains(ch)) {
         System.out.println(ch);
 
       } else {
         set.add(ch);
 
       }
    }
  }
 
  public static void main(String[] args) {
 
      String str = "java";
      //Method call
      printDuplicateUsingSet(str);
   }
}
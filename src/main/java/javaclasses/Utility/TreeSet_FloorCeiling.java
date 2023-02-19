/*
 * *
 *  * TreeSet Floor and Ceiling.java
 *  * Created by Rafsan Ahmad on 2/18/23, 12:02 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.util.TreeSet;

public class TreeSet_FloorCeiling {
    /*The floor() method of java.util.TreeSet<E> class is used to return the greatest element in this set less than or
    equal to the given element, or null if there is no such element.

Syntax: public E floor(E e)
Parameters: This method takes the value e as a parameter which is to be matched.
Return Value: This method returns the greatest element less than or equal to e, or null if there is no such element

The ceiling() method of java.util.TreeSet<E> class is used to return the least element in this set greater than or equal
to the given element, or null if there is no such element.

Syntax: public E ceiling(E e)
Parameters: This method takes the value e as a parameter which is to be matched.
Return Value: This method returns the least element greater than or equal to e, or null if there is no such element.
*/

    public static void main(String[] argv) throws Exception {
        try {
            TreeSet<Integer> treeSet = new TreeSet<>();

            // populate the TreeSet using add() method
            treeSet.add(10);
            treeSet.add(20);
            treeSet.add(30);
            treeSet.add(40);

            // Print the TreeSet
            System.out.println("TreeSet: " + treeSet);

            // getting the floor value for 25 using floor() method
            int fValue = treeSet.floor(25);
            // printing the floor value
            System.out.println("Floor value for 25: " + fValue); //prints 20

            // getting ceiling value for 25 using ceiling() method
            int cValue = treeSet.ceiling(25); //prints 30

            // printing  the ceiling value
            System.out.println("Ceiling value for 25: " + cValue);
        } catch (NullPointerException e) {
            System.out.println("Exception thrown : " + e);
        }
    }
}

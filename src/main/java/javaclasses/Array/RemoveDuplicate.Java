/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

/*Given an unsorted array of integers. Write a code to remove duplicates from unsorted array.

For example:

Input   :{5, 1, 2, 6, 4, 4, 5}
Output :{5, 1, 2, 6, 4}

In this example, 4 and 5 appear multiple times in an input array.

In the output, we have removed duplicates and printed only the distinct elements of an array.*/

/*Set does not allow duplicates. By using this property we can remove duplicates from an array.

We traverse an array and put the array elements in a set. Once the traversal is complete, print all the elements present in a set.

The time complexity of this approach is O(n) and it’s space complexity is also O(n).*/

import java.util.HashSet;
import java.util.Set;

/*
Remove duplicates using set
*/
public class RemoveDuplicate {

    public static void removeDuplicateUsingSet(int arr[]) {

        //Declare set
        Set st = new HashSet<>();
        int len = arr.length;

        //Traverse an array and add element in a set
        for (int i = 0; i < len - 1; i++) {
            //It only add unique value
            st.add(arr[i]);
        }

        //Print each element 
        st.forEach(elem -> System.out.print(elem + " "));
    }

    public static void main(String[] args) {

        int arr[] = {5, 1, 2, 6, 4, 4, 5, 6, 8, 7};

        removeDuplicateUsingSet(arr);
    }
}
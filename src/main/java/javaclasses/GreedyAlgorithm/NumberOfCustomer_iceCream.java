/*
 * *
 *  * Number of customers able to get desired flavor of ice cream.java
 *  * Created by Rafsan Ahmad on 6/14/22, 1:56 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

public class NumberOfCustomer_iceCream {
    /*Given two flavors of ice-cream chocolate and vanilla denoted by 0 and 1 respectively. People are standing in queue to
    get their desired flavor of ice cream from the stack of ice cream.

If the customer at the front of the queue prefers the ice cream pack on the top of the stack, they will take it and leave
the queue.
Otherwise, they will leave it and go to the queue’s end.
The process continues until no one from the queue wants to take the top ice cream pack.

Given two arrays customer[] and icecream[] where customer[i] is the preference of the customer ith customer(i =0 is the
front of the queue) and icecream[i] denotes the type of ith icecream in the stack(i =0 is the top of the stack).
The task is to find the number of customers able to get their desired flavor of ice cream.

Examples:

Input: customer = {1, 1, 0, 0}, icecream = {0, 1, 0, 1}
Output: 4
Explanation: Following is the sequence in which customers got their desired flavor of ice cream.
Front customer leaves top icecream pack and move to end of line . Now customer = {1, 0, 0, 1}.
Front customer leaves top icecream pack and moves to end of line . Now customer  = {0, 0, 1, 1}.
Front customer get icecream and leave line. Now icecream = [1, 0, 1] and customer = {0, 1, 1}.
Front customer leaves top icecream pack and move to end. Now customer = {1, 1, 0} .
Front customer get icecream pack and leaves the line. Now icecream = {0, 1} and customer = {1, 0}.
Front customer leaves line and move to end. Now customer = {0, 1}.
Front customer get icecream pack and leaves line. Now customer = {1} and icecream {1}.
Front customer get icecream pack and now line is empty.
Therefore, all four customer able to get desired icecream pack.

Input: customer = {1, 1, 1, 0, 0, 1}, icecream = {1, 0, 0, 0, 1, 1}
Output: 3

Approach:
Follow the steps below to solve the given problem.

Storing the order of the queue is of no use.
Declare an array say count[] that will keep track of customer preferences.
Increment count[0] if customer[i] is 0, else increment count[1].
Now, Initialize k which will store the number of customers who will get desired ice cream pack.
Iterate through icecream array and check if anyone in left customer will take food.
At last print k as the final answer.
*/

    // Function to find the number of customers who will get their desired flavor of ice cream
    public static int NumberOfCustomer(
            int[] customer, int[] icecream) {

        // Count array stores the count of preference of the customer
        int[] count = {0, 0};
        int n = customer.length;
        int k;
        for (int a : customer) {
            count[a]++;
        }

        for (k = 0; k < n && count[icecream[k]] > 0; ++k) {
            count[icecream[k]]--;
        }

        // Return k as the final answer
        return k;
    }

    public static void main(String[] args) {
        int[] customer = {1, 1, 0, 0};
        int[] icecream = {0, 1, 0, 1};

        int ans = NumberOfCustomer(customer, icecream);

        // Print the final answer
        System.out.print(ans);
    }
}

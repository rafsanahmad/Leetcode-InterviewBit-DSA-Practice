/*
 * *
 *  * Number Of Customers to get desired iceCream.java
 *  * Created by Rafsan Ahmad on 6/14/22, 11:27 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.StackQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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


Approach 1: This problem can be solved by using Stack and Queue. Follow the steps below to solve the given problem.

Create a stack and push icecream[] array in the stack.
Create  a queue and push customer[] array in queue
Initialize a variable say, topRejected=0 to keep track of rejected element.
If the front of the queue is equal to the stack top pop elements from the stack and remove the element from the queue and
update topRejected=0.
Else increment the count of topRejected and remove the element from the front of queue and add in last.
If the queue size is equal to topRejected then break loop.
Print icecream.length–queue.size as the required answer(because the remaining element in the queue will not be able to get
desired ice cream pack).

*/

    public static void NumberOfCustomer(int[] customer, int[] icecream) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = icecream.length - 1; i >= 0; i--) {
            stack.push(icecream[i]);
        }

        for (int i = 0; i < customer.length; i++) {
            queue.add(customer[i]);
        }

        int topRejected = 0;

        while (true) {
            if (topRejected == queue.size())
                break;

            if (queue.peek() == stack.peek()) {
                queue.remove();
                stack.pop();
                topRejected = 0;
            } else {
                topRejected++;
                queue.add(queue.remove());
            }
        }

        System.out.println(icecream.length - queue.size());
    }

/*
Approach 2:
Above approach can be further optimized and extra O(N) space can be avoided.
Follow the steps below to solve the given problem.

Storing the order of the queue is of no use.
Declare an array say count[] that will keep track of customer preferences.
Increment count[0] if customer[i] is 0, else increment count[1].
Now, Initialize k which will store the number of customers who will get desired ice cream pack.
Iterate through icecream array and check if anyone in left customer will take food.
At last print k as the final answer.

*/

    public static int NumberOfCustomerOptimized(int[] customer, int[] icecream) {
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

        NumberOfCustomer(customer, icecream);

        int ans = NumberOfCustomerOptimized(customer, icecream);
        // Print the final answer
        System.out.print(ans);
    }
}

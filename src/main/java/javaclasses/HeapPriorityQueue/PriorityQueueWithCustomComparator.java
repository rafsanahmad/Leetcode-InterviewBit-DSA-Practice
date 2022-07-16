/*
 * *
 *  * Priority Queue With Custom Comparator.java
 *  * Created by Rafsan Ahmad on 7/15/22, 1:17 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueWithCustomComparator {
    /*What is a priority queue?
As mentioned earlier, a regular queue has a first in first out structure. But in some scenarios we want to process
messages in a queue based on their priority and not based on when the message entered the queue.

Priority queues help consumers consume the higher priority messages first followed by the lower priority messages.

What about having a custom ordering?
This is possible as well, and we can do it with the help of a comparator.

Let's create an integer priority queue now. But this time let's get the result in descending order of value.

In order to achieve this, first we need to create an integer comparator:
*/

    private static void testStringsNaturalOrdering() {
        Queue<String> testStringsPQ = new PriorityQueue<>();
        testStringsPQ.add("abcd");
        testStringsPQ.add("1234");
        testStringsPQ.add("23bc");
        testStringsPQ.add("zzxx");
        testStringsPQ.add("abxy");

        System.out.println("Strings Stored in Natural Ordering in a Priority Queue\n");
        while (!testStringsPQ.isEmpty()) {
            System.out.println(testStringsPQ.poll());
        }
    }

    static class CustomIntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 < o2 ? 1 : -1;
        }
    }

    private static void testPriorityQueueCustomOrder() {
        Queue<Integer> testIntegersPQ = new PriorityQueue<>(new CustomIntegerComparator());
        testIntegersPQ.add(11);
        testIntegersPQ.add(5);
        testIntegersPQ.add(-1);
        testIntegersPQ.add(12);
        testIntegersPQ.add(6);

        System.out.println("Integers stored in reverse order of priority in a Priority Queue\n");
        while (!testIntegersPQ.isEmpty()) {
            System.out.println(testIntegersPQ.poll());
        }
    }

    /*Priority queue with Java objects
Up to this point, we've seen how we can use strings and integers with priority queues.

In real life applications we would generally be using priority queues with custom Java objects.

Let's first create a class called CustomerOrder which is used to store customer order details:*/

    public static class CustomerOrder implements Comparable<CustomerOrder> {
        private int orderId;
        private double orderAmount;
        private String customerName;

        public CustomerOrder(int orderId, double orderAmount, String customerName) {
            this.orderId = orderId;
            this.orderAmount = orderAmount;
            this.customerName = customerName;
        }

        @Override
        public int compareTo(CustomerOrder o) {
            return o.orderId > this.orderId ? 1 : -1;
        }

        @Override
        public String toString() {
            return "orderId:" + this.orderId + ", orderAmount:" + this.orderAmount + ", customerName:" + customerName;
        }

        public double getOrderAmount() {
            return orderAmount;
        }
    }

    /**
     * Class to implement a comparator for CustomerOrder to store in descending order of order amount
     */
    static class CustomerOrderComparator implements Comparator<CustomerOrder> {

        @Override
        public int compare(CustomerOrder o1, CustomerOrder o2) {
            return o1.getOrderAmount() < o2.getOrderAmount() ? 1 : -1;
        }
    }

    /**
     * Function to show how a priority queue works with Custom Objects
     */
    private static void testPriorityQueueCustomObjects() {
        CustomerOrder c1 = new CustomerOrder(1, 100.0, "customer1");
        CustomerOrder c2 = new CustomerOrder(3, 50.0, "customer3");
        CustomerOrder c3 = new CustomerOrder(2, 300.0, "customer2");
        Queue<CustomerOrder> customerOrders = new PriorityQueue<>(new CustomerOrderComparator());
        customerOrders.add(c1);
        customerOrders.add(c2);
        customerOrders.add(c3);
        while (!customerOrders.isEmpty()) {
            System.out.println(customerOrders.poll());
        }
    }

    public static void main(String[] args) {
        testStringsNaturalOrdering();
        System.out.println();
        testPriorityQueueCustomOrder();
        System.out.println();
        testPriorityQueueCustomObjects();
    }
}

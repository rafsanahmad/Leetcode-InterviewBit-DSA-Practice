/*
 *
 *  * OnlineStackPlan.kt
 *  *
 *  * Created by Rafsan Ahmad on 01/14/26, 11:26 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.Stack_Queue

class OnlineStackPlan {
    /*Design an algorithm that collects daily price quotes for some stock and returns the span of
    that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from
that day and going backward) for which the stock price was less than or equal to the price of that
day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the
stock today is 2, then the span of today is 4 because starting from today, the price of the stock
was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock
today is 8, then the span of today is 3 because starting from today, the price of the stock was
less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.


Example 1:

Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6


Constraints:

1 <= price <= 10^5
At most 10^4 calls will be made to next.*/

    /*Monotonic Stack (O(1) amortized)
Key idea
Instead of storing only price, store:
(price, span)

When a new price comes:
Pop all prices ≤ current price
Add their spans to the current span
Push the merged result back
Each element is:
pushed once
popped once
Amortized O(1) per next()
Overall O(n) for n calls

Time & Space Complexity
next()	O(1) amortized
Total for N calls	O(N)
Space	O(N)*/

    /*A monotonic stack is a stack that keeps its elements in a specific order (either increasing
    or decreasing) at all times.

It’s a pattern, not a data structure.

Definition (Interview-friendly)
A monotonic stack is a stack where elements are maintained in monotonic order
(either increasing or decreasing), and elements that break this order are removed.

Types of Monotonic Stack:
Monotonic Increasing Stack
Stack elements are increasing from bottom to top
Top element is the largest

Example:
Bottom → [2, 5, 8] → Top

Used when you want:
Previous smaller element
Next smaller element

Monotonic Decreasing Stack
Stack elements are decreasing from bottom to top
Top element is the smallest
Example:
Bottom → [9, 7, 4] → Top

Used when you want:
Previous greater element
Next greater element

Why do we use it?
Without monotonic stack → O(n²)
With monotonic stack → O(n)

Because:
Each element is pushed once
Each element is popped once

Amortized O(1) per operation
Stock Span = Monotonic Decreasing Stack

Problem requirement

Count consecutive previous days where price ≤ today’s price
Stack rule
Keep prices in strictly decreasing order
Stack contents
(price, span)

How it works (step-by-step)
Prices: [100, 80, 60, 70, 60, 75, 85]
Day	Price	Stack (bottom → top)	Span
1	100	      [(100,1)]	              1
2	80	      [(100,1),(80,1)]	      1
3	60	[(100,1),(80,1),(60,1)]	      1
4	70	        pop 60 → span=2	      2
5	60	          push	              1
6	75	        pop 60,70 → span=4	  4
7	85	        pop 75,80 → span=6	  6*/


    class StockSpanner() {
        //Price, Span
        val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        fun next(price: Int): Int {
            var count = 1

            //monotonic decreasing stack
            while (queue.isNotEmpty() && queue.last().first <= price) {
                val last = queue.removeLast()
                count += last.second
            }
            queue.add(Pair(price, count))
            return count
        }
    }
}

fun main() {
    val stockSpanner = OnlineStackPlan.StockSpanner()

    val prices = listOf(100, 80, 60, 70, 60, 75, 85)

    for (price in prices) {
        val span = stockSpanner.next(price)
        println("Price: $price -> Span: $span")
    }
}

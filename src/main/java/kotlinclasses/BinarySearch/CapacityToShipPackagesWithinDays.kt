/*
 * *
 *  * Capacity To Ship Packages Within D Days.kt
 *  * Created by Rafsan Ahmad on 9/30/25, 3:10PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.BinarySearch

class CapacityToShipPackagesWithinDays {
    //https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
    /*A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages
 on the conveyor belt (in the order given by weights). We may not load more weight than the maximum
 weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt
being shipped within days days.


Example 1:
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the
packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:
Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:
Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1


Constraints:
1 <= days <= weights.length <= 5 * 10^4
1 <= weights[i] <= 500*/

    /*
Time Complexity:
- Binary search range: from max(weights) to sum(weights) → O(log(sum(weights)))
- For each mid, we iterate through weights → O(n)
- Total: O(n * log(sum(weights))), where n = weights.size

Space Complexity:
- O(1) extra space (no extra data structures used)
*/
    fun shipWithinDays(weights: IntArray, days: Int): Int {

        // Helper function to check if we can ship all packages within 'days'
        // using a ship with given 'capacity'
        fun canShip(capacity: Int): Boolean {
            var daysRequired = 1        // Start counting from day 1
            var currentWeight = 0       // Current load on the ship for the day

            for (w in weights) {
                // If adding the current package exceeds capacity, start a new day
                if (currentWeight + w > capacity) {
                    daysRequired++
                    currentWeight = 0
                }
                currentWeight += w

                // Early exit: if days required exceed given days, capacity is too small
                if (daysRequired > days) return false
            }

            return true // All packages can be shipped within 'days' with this capacity
        }

        // Binary search boundaries:
        // Left bound: max single package weight (ship must at least carry heaviest package)
        // Right bound: sum of all weights (ship can carry everything in 1 day)
        var left = weights.maxOrNull() ?: 0
        var right = weights.sum()

        // Binary search for the minimum ship capacity
        while (left < right) {
            val mid = (left + right) / 2
            if (canShip(mid)) {
                right = mid  // mid is enough, try smaller capacity
            } else {
                left = mid + 1  // mid is too small, try larger capacity
            }
        }

        // At the end, left == right == minimum required capacity
        return left
    }
}

fun main() {
    val obj = CapacityToShipPackagesWithinDays()
    val weights = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val days = 5
    val result = obj.shipWithinDays(weights, days)
    println(result) // Expected Output: 15
}
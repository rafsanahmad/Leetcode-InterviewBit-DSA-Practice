/*
 *
 *  * DesignAuctionSystem.kt
 *  *
 *  * Created by Rafsan Ahmad on 02/12/26, 2:33 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.HeapPriorityQueue

import java.util.PriorityQueue

class DesignAuctionSystem {
    //https://leetcode.com/problems/design-auction-system/description/
    /*You are asked to design an auction system that manages bids from multiple users in real
    time.

Each bid is associated with a userId, an itemId, and a bidAmount.

Implement the AuctionSystem class:

AuctionSystem(): Initializes the AuctionSystem object.
void addBid(int userId, int itemId, int bidAmount): Adds a new bid for itemId by userId with
bidAmount. If the same userId already has a bid on itemId, replace it with the new bidAmount.
void updateBid(int userId, int itemId, int newAmount): Updates the existing bid of userId for
itemId to newAmount. It is guaranteed that this bid exists.
void removeBid(int userId, int itemId): Removes the bid of userId for itemId. It is guaranteed
that this bid exists.
int getHighestBidder(int itemId): Returns the userId of the highest bidder for itemId. If
multiple users have the same highest bidAmount, return the user with the highest userId.
If no bids exist for the item, return -1.

Example 1:

Input:
["AuctionSystem", "addBid", "addBid", "getHighestBidder", "updateBid", "getHighestBidder",
"removeBid", "getHighestBidder", "getHighestBidder"]
[[], [1, 7, 5], [2, 7, 6], [7], [1, 7, 8], [7], [2, 7], [7], [3]]

Output:
[null, null, null, 2, null, 1, null, 1, -1]

Explanation

AuctionSystem auctionSystem = new AuctionSystem(); // Initialize the Auction system
auctionSystem.addBid(1, 7, 5); // User 1 bids 5 on item 7
auctionSystem.addBid(2, 7, 6); // User 2 bids 6 on item 7
auctionSystem.getHighestBidder(7); // return 2 as User 2 has the highest bid
auctionSystem.updateBid(1, 7, 8); // User 1 updates bid to 8 on item 7
auctionSystem.getHighestBidder(7); // return 1 as User 1 now has the highest bid
auctionSystem.removeBid(2, 7); // Remove User 2's bid on item 7
auctionSystem.getHighestBidder(7); // return 1 as User 1 is the current highest bidder
auctionSystem.getHighestBidder(3); // return -1 as no bids exist for item 3


Constraints:

1 <= userId, itemId <= 5 * 10^4
1 <= bidAmount, newAmount <= 10^9
At most 5 * 10^4 total calls to addBid, updateBid, removeBid, and getHighestBidder.
The input is generated such that for updateBid and removeBid, the bid from the given userId for the
given itemId will be valid.*/

    /*Complexity
Operation	Complexity
addBid	    O(log n)
updateBid	O(log n)
removeBid	O(1)
getHighestBidder	Amortized O(1) (worst case O(log n) when cleaning outdated bids)*/
    class AuctionSystem() {
        // Data class to hold bid info
        data class Bid(val userId: Int, val amount: Int)

        // itemId -> max heap of bids (amount DESC, userId DESC)
        private val heapMap = mutableMapOf<Int, PriorityQueue<Bid>>()

        // itemId -> (userId, latest bid amount)
        private val latestBidMap = mutableMapOf<Int, MutableMap<Int, Int>>()

        /** Add a new bid for a user and item */
        fun addBid(userId: Int, itemId: Int, bidAmount: Int) {
            val userMap = latestBidMap.getOrPut(itemId) { mutableMapOf() }
            userMap[userId] = bidAmount

            val heap = heapMap.getOrPut(itemId) {
                PriorityQueue { a: Bid, b: Bid ->
                    when {
                        a.amount != b.amount -> b.amount - a.amount      // higher bid first
                        else -> b.userId - a.userId                     // tie: higher userId first
                    }
                }
            }

            heap.add(Bid(userId, bidAmount))
        }

        /** Update an existing bid for a user and item */
        fun updateBid(userId: Int, itemId: Int, newAmount: Int) {
            addBid(userId, itemId, newAmount)   // just re-add bid, old will be lazily ignored
        }

        /** Remove a user's bid for an item */
        fun removeBid(userId: Int, itemId: Int) {
            latestBidMap[itemId]?.remove(userId)
            // Heap still contains old entries; they'll be ignored during getHighestBidder
        }

        /** Return the userId of the highest bidder for an item */
        fun getHighestBidder(itemId: Int): Int {
            val heap = heapMap[itemId] ?: return -1
            val userMap = latestBidMap[itemId] ?: return -1

            while (heap.isNotEmpty()) {
                val top = heap.peek()
                val latestAmount = userMap[top.userId]

                if (latestAmount != null && latestAmount == top.amount) {
                    return top.userId   // valid bid
                }

                // outdated bid, remove
                heap.poll()
            }

            return -1   // no valid bids
        }
    }
}

fun main() {
    val auction = DesignAuctionSystem.AuctionSystem()

    val commands = listOf(
        "AuctionSystem",
        "addBid",
        "addBid",
        "getHighestBidder",
        "updateBid",
        "getHighestBidder",
        "removeBid",
        "getHighestBidder",
        "getHighestBidder"
    )

    val arguments = listOf(
        listOf<Int>(),       // AuctionSystem constructor
        listOf(1, 7, 5),     // addBid(userId=1, itemId=7, bid=5)
        listOf(2, 7, 6),     // addBid(userId=2, itemId=7, bid=6)
        listOf(7),           // getHighestBidder(itemId=7)
        listOf(1, 7, 8),     // updateBid(userId=1, itemId=7, newAmount=8)
        listOf(7),           // getHighestBidder(itemId=7)
        listOf(2, 7),        // removeBid(userId=2, itemId=7)
        listOf(7),           // getHighestBidder(itemId=7)
        listOf(3)            // getHighestBidder(itemId=3) (no bids)
    )

    val output = mutableListOf<Any?>()

    for (i in commands.indices) {
        when (commands[i]) {
            "AuctionSystem" -> output.add(null)  // constructor
            "addBid" -> {
                val (userId, itemId, bidAmount) = arguments[i]
                auction.addBid(userId, itemId, bidAmount)
                output.add(null)
            }

            "updateBid" -> {
                val (userId, itemId, newAmount) = arguments[i]
                auction.updateBid(userId, itemId, newAmount)
                output.add(null)
            }

            "removeBid" -> {
                val (userId, itemId) = arguments[i]
                auction.removeBid(userId, itemId)
                output.add(null)
            }

            "getHighestBidder" -> {
                val (itemId) = arguments[i]
                output.add(auction.getHighestBidder(itemId))
            }

            else -> throw IllegalArgumentException("Unknown command: ${commands[i]}")
        }
    }

    println(output)
}

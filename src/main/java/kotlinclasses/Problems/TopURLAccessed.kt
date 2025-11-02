/*
 * *
 *  * Top URL Accessed.kt
 *  * Created by Rafsan Ahmad on 10/9/22, 9:24 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package kotlinclasses.Problems

import java.util.*

class TopURLAccessed {
    /*
    Top N URLs with 200 Status Code
You are given an array of web access logs, each representing a single HTTP request.
Each log entry follows the format:

IP_ADDRESS STATUS_CODE URL

where:
IP_ADDRESS → a string like "10.10.0.2"
STATUS_CODE → an integer as a string (e.g., "200", "404", "500")
URL → a string like "https://www.google.com"

Your task is to:
Filter the logs to only include entries with a status code of 200.
Count how many times each URL appears with a 200 status code.
Return the top n most frequently accessed URLs.
If multiple URLs have the same count, sort them alphabetically.
Return the result as an array of strings, containing only the URLs.

Input
n: An integer representing how many top URLs to return.
logs: An array of strings, where each string is a log entry.

Output
An array of strings containing the top n URLs based on access frequency (only for status code 200).

Example
Input:
val logs = arrayOf(
    "10.10.0.2 200 https://www.google.com",
    "10.10.0.3 404 https://www.yahoo.com",
    "10.10.0.2 200 https://www.google.com",
    "10.10.0.5 200 https://www.yahoo.com",
    "10.10.0.7 200 https://www.bing.com",
    "10.10.0.8 200 https://www.bing.com",
    "10.10.0.9 200 https://www.bing.com"
)
val n = 2

Output:
["https://www.bing.com", "https://www.google.com"]

Explanation:
https://www.bing.com → 3 accesses with status 200
https://www.google.com → 2 accesses with status 200
https://www.yahoo.com → 1 access with status 200
→ Top 2 URLs are Bing and Google.

Approach Summary
Parse each log and filter by status 200.
Count the number of occurrences of each URL in a HashMap.
Sort the entries:
Primary key: Count (descending)
Secondary key: URL (ascending for ties)
Return the top n URLs.
    */

    /*Total Time Complexity: O(m + k log k)
    Space Complexity: O(k) for the frequency map
    k = unique URLs
    m is number of log entries
    */
    fun solution(n: Int, logs: Array<String>): Array<String> {
        val result: MutableList<String> = mutableListOf()
        val countMap: MutableMap<String, Int> = mutableMapOf()
        for (i in 0 until logs.size) {
            val logsSplitted = logs[i].split(" ")
            if (logsSplitted[1] == "200") {
                //Add to map
                val url = logsSplitted[2]
                countMap[url] = countMap.getOrDefault(url, 0) + 1
            }
        }

        // Create a list from elements of HashMap
        var list: List<Map.Entry<String, Int>> = LinkedList(countMap.entries)
        // Sort the list
        list = list
            .sortedWith(object : Comparator<Map.Entry<String, Int>> {
                override fun compare(p0: Map.Entry<String, Int>, p1: Map.Entry<String, Int>): Int {
                    if (p0.value == p1.value) {
                        return p0.key.compareTo(p1.key)
                    }
                    return p1.value - p0.value
                }
            })

        var total = n
        for (i in list.indices) {
            if (total > 0) {
                val mapEntry = list[i]
                result.add(mapEntry.key)
                total--
            }
        }

        return result.toTypedArray()
    }

    /*Approach 2 — Using Priority Queue (Min-Heap)
Key Idea:
Instead of sorting all URLs (which takes O(k log k) where k is the number of unique URLs),
you can use a min-heap (priority queue) of size n to always keep only the top n URLs by frequency.
This reduces time complexity when n << k.

Algorithm Steps:
Parse each log entry and filter only status 200.
Count the frequency of each URL in a HashMap.
Use a min-heap (PriorityQueue) that stores (url, count) pairs with a comparator:
Primary sort: count ascending (lowest frequency at top)
Secondary sort: alphabetical descending (so lexicographically larger URLs are popped first in ties)
For each URL in the map:
Add it to the heap.
If heap size > n, remove the smallest element.
Finally, extract URLs from the heap, sort them by frequency descending, then alphabetically
ascending, and return.
*/

    /*Total Time Complexity: O(m + k log n) (Better than O(k log k))
    Space Complexity: O(k) for the map + O(n) for the heap
    */
    fun solutionOptimized(n: Int, logs: Array<String>): Array<String> {
        val freqMap = mutableMapOf<String, Int>()

        // Step 1: Count frequency for URLs with 200 status code
        for (log in logs) {
            val parts = log.split(" ")
            if (parts.size == 3 && parts[1] == "200") {
                val url = parts[2]
                freqMap[url] = freqMap.getOrDefault(url, 0) + 1
            }
        }

        // Step 2: Define a min-heap (PriorityQueue)
        val pq = PriorityQueue<Pair<String, Int>> { a, b ->
            a.second - b.second // smaller count first
        }

        // Step 3: Keep only top n in heap
        for ((url, count) in freqMap) {
            pq.offer(Pair(url, count))
            if (pq.size > n) pq.poll()
        }

        // Step 4: Extract from heap, sort correctly
        val result = pq.toList()
            .sortedWith(compareByDescending<Pair<String, Int>> { it.second }
                .thenBy { it.first })
            .map { it.first }

        return result.toTypedArray()
    }
}

fun main() {
    val obj = TopURLAccessed()
    val logs = arrayOf(
        "10.10.0.2 200 https://www.google.com",
        "10.10.0.3 404 https://www.yahoo.com",
        "10.10.0.6 200 https://www.google.com",
        "10.10.0.2 200 https://www.google.com",
        "10.10.0.5 200 https://www.yahoo.com",
        "10.10.0.7 200 https://www.bing.com",
        "10.10.0.8 200 https://www.bing.com",
        "10.10.0.9 200 https://www.bing.com"
    )

    val n = 2
    println(obj.solution(n, logs).contentToString())
    println(obj.solutionOptimized(n, logs).contentToString())
}

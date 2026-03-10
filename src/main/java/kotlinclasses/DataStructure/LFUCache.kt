/*
 *
 *  * LFUCache.kt
 *  *
 *  * Created by Rafsan Ahmad on 03/04/26, 6:49 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package kotlinclasses.DataStructure

class LFUCache(private val capacity: Int) {
    /*Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not
already present. When the cache reaches its capacity, it should invalidate and remove the least
frequently used key before inserting a new item. For this problem, when there is a tie
(i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache.
The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put
operation). The use counter for a key in the cache is incremented either a get or put operation
is called on it.

The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3


Constraints:

1 <= capacity <= 10^4
0 <= key <= 10^5
0 <= value <= 10^9
At most 2 * 10^5 calls will be made to get and put.*/

    /*Data Structures Used

We maintain:
keyToValue → key → value
keyToFreq → key → frequency
freqToKeys → frequency → ordered set of keys (to handle LRU tie)
minFreq → smallest frequency currently in cache

GET(key) — Algorithm Steps
If key does not exist → return -1.
Find current frequency of key.
Remove key from its current frequency bucket.
If that bucket becomes empty:
Remove the bucket.
If this frequency was minFreq, increase minFreq by 1.
Increase key’s frequency by 1.
Add key to the new frequency bucket.
Return value.

PUT(key, value) — Algorithm Steps
Case 1: Capacity = 0
→ Do nothing.

Case 2: Key already exists
Update value.
Perform GET logic (increase frequency).

Case 3: Key does NOT exist
If cache is full:
Find keys with minimum frequency (minFreq).
From that group, remove the least recently used key (first element).
Remove that key from:
keyToValue
keyToFreq
freqToKeys
Insert New Key:
Insert key with value.
Set frequency = 1.
Add key to frequency bucket 1.
Set minFreq = 1.
Why LRU Works Here
Inside each frequency bucket:
We use LinkedHashSet
It keeps insertion order
Oldest key = Least Recently Used within same frequency

So eviction rule becomes:
Remove key with lowest frequency
If tie → remove least recently used

Overall Logic in One Line
LFU =
Group keys by frequency → inside each group maintain LRU → track minimum frequency for O(1) eviction*/

    private val keyToValue = mutableMapOf<Int, Int>()
    private val keyToFreq = mutableMapOf<Int, Int>()
    private val freqToKeys = mutableMapOf<Int, LinkedHashSet<Int>>()

    private var minFreq = 0

    /*Time: O(1)
    Space: O(1) extra per call*/
    fun get(key: Int): Int {
        val value = keyToValue[key] ?: return -1
        increaseFrequency(key)
        return value
    }

    /*Time: O(1)
    Space: O(1) extra per call*/
    fun put(key: Int, value: Int) {
        if (capacity == 0) return

        // If key exists
        if (keyToValue.containsKey(key)) {
            keyToValue[key] = value
            increaseFrequency(key)
            return
        }

        // Evict if full
        if (keyToValue.size >= capacity) {
            val keys = freqToKeys[minFreq] ?: return
            val iterator = keys.iterator()
            if (iterator.hasNext()) {
                val lruKey = iterator.next()
                keys.remove(lruKey)

                keyToValue.remove(lruKey)
                keyToFreq.remove(lruKey)
            }

            if (keys.isEmpty()) {
                freqToKeys.remove(minFreq)
            }
        }

        // Insert new key
        keyToValue[key] = value
        keyToFreq[key] = 1
        minFreq = 1

        freqToKeys.getOrPut(1) { LinkedHashSet() }.add(key)
    }

    private fun increaseFrequency(key: Int) {
        val freq = keyToFreq[key] ?: return

        keyToFreq[key] = freq + 1

        freqToKeys[freq]?.let { keys ->
            keys.remove(key)

            if (keys.isEmpty()) {
                freqToKeys.remove(freq)
                if (minFreq == freq) {
                    minFreq++
                }
            }
        }

        freqToKeys.getOrPut(freq + 1) { LinkedHashSet() }.add(key)
    }
}

fun main() {
    val operations = listOf(
        "LFUCache", "put", "put", "get", "put",
        "get", "get", "put", "get", "get", "get"
    )

    val values = listOf(
        listOf(2),
        listOf(1, 1),
        listOf(2, 2),
        listOf(1),
        listOf(3, 3),
        listOf(2),
        listOf(3),
        listOf(4, 4),
        listOf(1),
        listOf(3),
        listOf(4)
    )

    var cache: LFUCache? = null
    val output = mutableListOf<Any?>()

    for (i in operations.indices) {
        when (operations[i]) {
            "LFUCache" -> {
                cache = LFUCache(values[i][0])
                output.add(null)
            }

            "put" -> {
                cache?.put(values[i][0], values[i][1])
                output.add(null)
            }

            "get" -> {
                val result = cache?.get(values[i][0])
                output.add(result)
            }
        }
    }

    println(output)
}
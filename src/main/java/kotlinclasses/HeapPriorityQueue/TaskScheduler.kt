/*
 * *
 *  * Task Scheduler.kt
 *  * Created by Rafsan Ahmad on 4/28/25, 10:33PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.HeapPriorityQueue

import java.util.PriorityQueue

class TaskScheduler {
    /*You are given an array of CPU tasks, each labeled with a letter from A to Z, and a
    number n. Each CPU interval can be idle or allow the completion of one task.
    Tasks can be completed in any order, but there's a constraint: there has to be a
    gap of at least n intervals between two tasks with the same label.

Return the minimum number of CPU intervals required to complete all tasks.



Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2

Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.

After completing task A, you must wait two intervals before doing A again. The same
applies to task B. In the 3rd interval, neither A nor B can be done, so you idle.
By the 4th interval, you can do A again as 2 intervals have passed.

Example 2:

Input: tasks = ["A","C","A","B","D","B"], n = 1

Output: 6

Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.

With a cooling interval of 1, you can repeat a task after just one other task.

Example 3:

Input: tasks = ["A","A","A", "B","B","B"], n = 3

Output: 10

Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle ->
idle -> A -> B.

There are only two types of tasks, A and B, which need to be separated by 3 intervals.
This leads to idling twice between repetitions of these tasks.



Constraints:
1 <= tasks.length <= 10^4
tasks[i] is an uppercase English letter.
0 <= n <= 100*/

    /* Using priority queue
1. First count the number of occurrences of each task and store that in a map/vector.
2. Then push the count into a priority queue, so that the maximum frequency task can be
accessed and completed first.
Then until all tasks are completed (i.e the priority queue is not empty):
3. intialise the cycle length as n+1. n is the cooldown period so the cycle will be of
n+1 length.
Example: for ['A','A','A','B','B'] and n=2,
the tasks can occur in the following manner:
[A B idle]->[A B idle]->[A]. See here each cycle is n+1 length long,
only then A can repeat itself.
4. for all elements in the priority queue, until the cycle length is exhausted, pop the
elements out of the queue and if the task is occurring more than once then add it to the
remaining array (which stores the remaining tasks).
5. This means that we are completing that task once in this cycle.So keep counting the time.
6. Then, add the occurrence of tasks back to the priority queue.
7. Add the idle time into the time count.
Idle time is the time that was needed in the cycle because no task was available.
It is the remaining cycle length in our algorithm. Idle time should be only added
if the priority queue is empty (i.e all tasks are completed).*/

    fun leastInterval(tasks: CharArray, n: Int): Int {
        val priorityQueue = PriorityQueue<Int> { a, b -> b - a }
        var countMap = IntArray(32) { 0 }
        for (task in tasks) {
            countMap[task - 'A']++
        }

        for (count in countMap) {
            if (count > 0)
                priorityQueue.add(count)
        }

        var time = 0

        while (priorityQueue.isNotEmpty()) {
            val remain = arrayListOf<Int>()
            var cycle = n + 1

            while (cycle > 0 && priorityQueue.isNotEmpty()) {
                val maxFreq = priorityQueue.poll()
                if (maxFreq > 1) {
                    remain.add(maxFreq - 1)
                }
                time++
                cycle--
            }

            for (count in remain) {
                priorityQueue.add(count)
            }

            if (priorityQueue.isEmpty()) break

            time += cycle
        }

        return time
    }
}

fun main() {
    val scheduler = TaskScheduler()
    println(scheduler.leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 3))
}
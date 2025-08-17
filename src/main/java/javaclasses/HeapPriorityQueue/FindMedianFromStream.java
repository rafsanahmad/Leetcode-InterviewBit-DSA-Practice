/*
 * *
 *  * Find Median from Data Stream.java
 *  * Created by Rafsan Ahmad on 5/18/24, 5:34 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.HeapPriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromStream {
    //https://leetcode.com/problems/find-median-from-data-stream/description/
    /*The median is the middle value in an ordered integer list. If the size of the list is even,
    there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual
answer will be accepted.


Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0


Constraints:

-10^5 <= num <= 10^5
There will be at least one element in the data structure before calling findMedian.
At most 5 * 10^4 calls will be made to addNum and findMedian.


Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?*/

    //res/find_median_stream.png
    /*Example: Insert [5, 2, 10, 3]

We maintain:
maxHeap = smaller half (largest at top).
minHeap = larger half (smallest at top).
Median = either one top, or average of tops.

Insert 5
maxHeap: [ ]
minHeap: [5]
Median = 5

Insert 2
maxHeap: [2]
minHeap: [5]
Median = (2 + 5) / 2 = 3.5

Insert 10
maxHeap: [2]
minHeap: [5, 10]
Median = 5

Insert 3
maxHeap: [2, 3]
minHeap: [5, 10]
Median = (3 + 5) / 2 = 4.0*/

    static class MedianFinder {

        PriorityQueue<Integer> min;
        PriorityQueue<Integer> max;
        int size;

        public MedianFinder() {
            min = new PriorityQueue<>();
            max = new PriorityQueue<>(Collections.reverseOrder());
            size = 0;
        }

        public void addNum(int num) {
            min.add(num);
            max.add(min.poll());

            if (min.size() < max.size()) {
                min.add(max.poll());
            }

            size++;
        }

        public double findMedian() {
            if (size % 2 == 0) {
                if (max.peek() != null && min.peek() != null)
                    return (max.peek() + min.peek()) / 2.0;
            } else {
                return min.peek();
            }
            return 0.0;
        }
    }

    //Follow-up
    /*
    If the range of the numbers is in [0...100], we use a bucket to collect the frequency of
    each number. By accumulating the frequency of elements in the bucket, we can know the median
    numbers.
    */
    static class MedianFinderFollowUp1 {

        int[] A = new int[101];
        int n = 0;

        // O(1)
        public void addNum(int num) {
            A[num]++;
            n++;
        }

        // O(100) = O(1)
        public double findMedian() {

            // find 1st median number
            int count = 0, i = 0;
            while (count < n / 2) count += A[i++];

            // find 2nd median number
            int j = i;
            while (count < n / 2 + 1) count += A[j++];

            return (n % 2 == 1) ? i : (i - 1 + j - 1) / 2.0;
        }
    }

    /*
    If 1% numbers are outside of the range [0...100], we know that when the set of numbers is
    large, the median numbers must be in the range of [0...100], because this range contains 99%
    numbers. We don't need to store values of 1% numbers, but the counts of these numbers
    (countLessZero & countGreater100). The findMedian method is almost the same, the difference
    is we start counting from countLessZero value
    */

    static class MedianFinderFollowUp2 {
        int[] A = new int[101];
        int n = 0;
        int countLessZero = 0;
        // int countGreater100 = 0; // not needed

        // O(1)
        public void addNum(int num) {
            if (num < 0) countLessZero++;
                // else if (num > 100) countGreater100++;
            else A[num]++;
            n++;
        }

        // O(100) = O(1)
        public double findMedian() {

            // find 1st median number
            int count = countLessZero, i = 0;
            while (count < n / 2) count += A[i++];

            // find 2nd median number
            int j = i;
            while (count < n / 2 + 1) count += A[j++];

            return (n % 2 == 1) ? i : (i - 1 + j - 1) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(5);    // arr = [1]
        medianFinder.addNum(2);    // arr = [2,5]
        System.out.println(medianFinder.findMedian()); // return 3.5 (i.e., (5 + 2) / 2)
        medianFinder.addNum(6);    // arr[2,5,6,6,10]
        medianFinder.addNum(6);
        medianFinder.addNum(10);
        System.out.println(medianFinder.findMedian()); // return 6.0
    }
}

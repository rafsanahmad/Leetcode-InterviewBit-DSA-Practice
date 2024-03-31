/*
 * *
 *  * Max Min Heap Implementation.java
 *  * Created by Rafsan Ahmad on 12/18/22, 12:07 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.HeapPriorityQueue;

public class MaxMinHeapImplementation {
    //https://iq.opengenus.org/max-heap-min-heap/
    //res/pq-with-heap.jpg
    /*Heap is a binary tree with two special properties: it must have all its nodes in specific order and its shape
    must be complete.

Keep in mind-

We can have duplicate values in a heap. there’s no restriction against that.
A heap doesn’t follow the rules of a binary search tree; unlike binary search trees, the left node does not have to be
smaller than the right node! The ordering of the child nodes isn’t important for a heap; the only ordering that matters is
the heap-order property, or the ordering of parent nodes compared to their children.
Heap can be broadly classified in two types :
1. Min heap
2. Max heap

Implementation
1. Use array to store the data.
2. Start storing from index 1, not 0.
3. For any given node at position i:
4. Its Left Child is at [2*i] if available.
5. Its right child is at [2*i+1] if available.
6. Its Parent Node is at [i/2] if available.

Heap Majorly has 3 operations –
1. Insert Operation(Time complexity O(log n))
2. Delete Operation (Time complexity O(log n))
3. Extract-Min (OR Extract-Max) (Time complexity O(n))

Insert Operation
Steps:
1. Add the element at the bottom leaf of the Heap.
2. Perform the Bubble-Up operation.
All Insert Operations must perform the bubble-up operation(it is also called as up-heap, percolate-up, sift-up,
trickle-up, heapify-up, or cascade-up)

Bubble-up Operation
res/Insert-Bubble-Up-Min-Heap.gif
Steps:
1. If inserted element is smaller than its parent node in case of Min-Heap OR greater than its parent node in case of
Max-Heap, swap the element with its parent.
2. Keep repeating the above step, if node reaches its correct position, STOP.

Extract-Min OR Extract-Max Operation
res/Delete-OR-Extract-Min-from-Heap.gif
Steps:
1. Take out the element from the root.( it will be minimum in case of Min-Heap and maximum in case of Max-Heap).
2. Take out the last element from the last level from the heap and replace the root with the element.
3. Perform Sink-Down.
4. All delete operation must perform Sink-Down Operation ( also known as bubble-down, percolate-down, sift-down,
trickle down, heapify-down, cascade-down).

Sink-Down Operation
Steps:
1. If replaced element is greater than any of its child node in case of Min-Heap OR smaller than any if its child node
in case of Max-Heap, swap the element with its smallest child(Min-Heap) or with its greatest child(Max-Heap).
2. Keep repeating the above step, if node reaches its correct position, STOP.

Delete Operation
Steps:
1. Find the index for the element to be deleted.
2. Take out the last element from the last level from the heap and replace the index with this element .
3. Perform Sink-Down.
*/

    //Complete implementation of Min-heap in Java
    public int capacity;
    public int[] minHeap;
    public int currentSize;

    public MaxMinHeapImplementation(int capacity) {
        this.capacity = capacity;
        minHeap = new int[capacity + 1];
        currentSize = 0;
    }

    public void createHeap(int[] arrA) {
        if (arrA.length > 0) {
            for (int i = 0; i < arrA.length; i++) {
                insert(arrA[i]);
            }
        }
    }

    public void display() {
        for (int i = 1; i < minHeap.length; i++) {
            System.out.print(" " + minHeap[i]);
        }
        System.out.println();
    }

    public void insert(int x) {
        if (currentSize == capacity) {
            System.out.println("heap is full");
            return;
        }
        currentSize++;
        int idx = currentSize;
        minHeap[idx] = x;
        bubbleUp(idx);
    }

    public void bubbleUp(int pos) {
        int parentIdx = pos / 2;
        int currentIdx = pos;
        while (currentIdx > 0 && minHeap[parentIdx] > minHeap[currentIdx]) {

            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx / 2;
        }
    }

    public int extractMin() {
        int min = minHeap[1];
        minHeap[1] = minHeap[currentSize];
        minHeap[currentSize] = 0;
        sinkDown(1);
        currentSize--;
        return min;
    }

    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k + 1;
        if (leftChildIdx < heapSize() && minHeap[smallest] > minHeap[leftChildIdx]) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < heapSize() && minHeap[smallest] > minHeap[rightChildIdx]) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {

            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    public void swap(int a, int b) {
        int temp = minHeap[a];
        minHeap[a] = minHeap[b];
        minHeap[b] = temp;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int heapSize() {
        return currentSize;
    }

    public static void main(String[] args) {
        int[] arrA = {3, 2, 1, 7, 8, 4, 10, 16, 12};
        System.out.print("Original Array : ");
        for (int i = 0; i < arrA.length; i++) {
            System.out.print("  " + arrA[i]);
        }
        MaxMinHeapImplementation m = new MaxMinHeapImplementation(arrA.length);
        System.out.print("\nMin-Heap : ");
        m.createHeap(arrA);
        m.display();
        System.out.print("Extract Min :");
        for (int i = 0; i < arrA.length; i++) {
            System.out.print("  " + m.extractMin());
        }
    }
}

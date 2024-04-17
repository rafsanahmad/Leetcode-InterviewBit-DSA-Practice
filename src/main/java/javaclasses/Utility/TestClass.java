/*
 * *
 *  * Solution.java
 *  * Created by Rafsan Ahmad on 4/20/22, 7:22 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument;

import javaclasses.Tree.TreeNode;

public class TestClass {
    public static void testFunc(int[] arr) {

    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;
        int[][] dp = new int[len + 1][2];

        dp[len][0] = 0;
        dp[len][1] = 0;

        int profit;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) {
                if (j == 0) {
                    //Buy
                    int buy = -prices[i] + dp[i + 1][1];
                    int notBuy = dp[i + 1][0];
                    profit = Math.max(buy, notBuy);
                } else {
                    //Sell
                    int sell = prices[i] + dp[i + 1][0];
                    int notSell = dp[i + 1][1];
                    profit = Math.max(sell, notSell);
                }

                dp[i][j] = profit;
            }
        }

        return dp[0][0];
    }

    // type = 1 = Buy, 0 = Sell
    public int maxProfitHelper(int[] prices, int index, int type, int[][] dp) {
        if (index == prices.length) {
            return 0;
        }

        if (dp[index][type] != -1) return dp[index][type];

        int maxProfit;
        if (type == 1) {
            //Buy
            int buy = -prices[index] + maxProfitHelper(prices, index + 1, 0, dp);
            int notBuy = maxProfitHelper(prices, index + 1, 1, dp);
            maxProfit = Math.max(buy, notBuy);
        } else {
            //Sell
            int sell = prices[index] + maxProfitHelper(prices, index + 1, 1, dp);
            int notSell = maxProfitHelper(prices, index + 1, 0, dp);
            maxProfit = Math.max(sell, notSell);
        }

        return dp[index][type] = maxProfit;
    }

    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        maxLength(root);
        return res;
    }

    public int maxLength(TreeNode node) {
        if (node == null) return 0;

        int left = maxLength(node.left);
        int right = maxLength(node.right);

        res = Math.max(res, left + right);
        return Math.max(left, right) + 1;
    }

    public int inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            res++;
            inOrderTraversal(node.right);
        }

        return res;
    }

    public String reorganizeString(String s) {
        int len = s.length();
        if (len <= 1) return "";
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            int count = map.getOrDefault(ch, 0) + 1;
            if (count > (len + 1) / 2) return "";
            map.put(ch, count);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(new int[]{entry.getKey(), entry.getValue()});
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            char ch = (char) curr[0];
            if (sb.isEmpty() || ch != sb.charAt(sb.length() - 1)) {
                sb.append(ch);
                if (--curr[1] > 0) {
                    queue.add(curr);
                }
            } else {
                int[] curr2 = queue.poll();
                if (curr2 != null) {
                    char ch2 = (char) curr2[0];
                    sb.append(ch2);

                    if (--curr2[1] > 0) {
                        queue.add(curr2);
                    }

                    queue.add(curr);
                }
            }
        }

        return sb.toString();
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        return lisRec(nums, 0);
    }

    public int lisRec(int[] nums, int index) {
        if (index >= nums.length) return 0;

        int res = Integer.MIN_VALUE;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                res = Math.max(res, 1 + lisRec(nums, i));
            }
        }

        return Math.max(res, 1);
    }

//    static class MedianFinder {
//
//        int A[] = new int[101], n = 0;
//
//        // O(1)
//        public void addNum(int num) {
//            A[num]++;
//            n++;
//        }
//
//        // O(100) = O(1)
//        public double findMedian() {
//
//            // find 1st median number
//            int count = 0, i = 0;
//            while (count < n / 2) count += A[i++];
//
//            // find 2nd median number
//            int j = i;
//            while (count < n / 2 + 1) count += A[j++];
//
//            return (n % 2 == 1) ? i : (i - 1 + j - 1) / 2.0;
//        }
//    }

    static class SnapshotArray {
        int snap_id;
        Map<Integer, Integer>[] s;  // snap_id , value

        public SnapshotArray(int length) {
            snap_id = 0;
            s = new HashMap[length];
            for (int i = 0; i < length; i++) {
                s[i] = new HashMap<>();
            }
        }

        public void set(int index, int val) {
            s[index].put(snap_id, val);
        }

        public int snap() {
            this.snap_id++;
            return snap_id - 1;
        }

        public int get(int index, int snap_id) {
            for (int i = snap_id; i >= 0; i--) if (s[index].get(i) != null) return s[index].get(i);
            return 0;
        }
    }

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

    // Encodes a tree to a single string.
    public String splitter = ",";
    public String nullNode = "NN";
    StringBuilder builder;

    public String serialize(TreeNode root) {
        builder = new StringBuilder();
        return buildString(builder, root);
    }

    //Pre-order traversal
    public String buildString(StringBuilder sb, TreeNode node) {
        if (node == null) {
            sb.append(splitter).append(nullNode);
            return sb.toString();
        }

        sb.append(node.val).append(splitter);
        buildString(sb, node.left);
        buildString(sb, node.right);

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(splitter)));
        return buildTree(queue);
    }

    public TreeNode buildTree(Queue<String> queue) {
        if (queue.isEmpty())
            return null;

        String v = queue.poll();
        if (v.equals(nullNode)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(v));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }

    public static void main(String[] args) {
        TestClass solution = new TestClass();
        int[] arr = {7, 6, 4, 3, 1};
        //System.out.println(solution.maxProfit(arr));

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.left.right.left = new TreeNode(6);
        root.left.right.left.left = new TreeNode(7);
        root.left.right.left.left.left = new TreeNode(8);
        root.left.right.left.left.left.left = new TreeNode(9);

        //System.out.println(solution.diameterOfBinaryTree(root));
        int[] arr2 = {3, 2, 4};
        //System.out.println(Arrays.toString(solution.twoSum(arr2, 6)));

        int[] arr3 = {10, 9, 2, 5, 3, 7, 101, 18};
        //System.out.println(solution.lengthOfLIS(arr3));

        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(5);    // arr = [1]
        medianFinder.addNum(2);    // arr = [2,5]
        System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(6);    // arr[2,5,6,6,10]
        medianFinder.addNum(6);
        medianFinder.addNum(10);
        System.out.println(medianFinder.findMedian()); // return 2.0


        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0, 5);  // Set array[0] = 5
        //System.out.println(snapshotArr.snap());  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0, 6);
        //System.out.println(snapshotArr.get(0, 0));  // Get the value of array[0] with snap_id = 0, return 5
    }
}
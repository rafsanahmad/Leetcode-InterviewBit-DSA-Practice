/*
 * *
 *  * Fractional Knapsack.java
 *  * Created by Rafsan Ahmad on 1/28/22, 4:36 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    /*Given weights and values of n items, we need to put these items in a knapsack of capacity W to get the
    maximum total value in the knapsack.

In the 0-1 Knapsack problem, we are not allowed to break items. We either take the whole item or don’t take it.

Input:
Items as (value, weight) pairs
arr[] = {{60, 10}, {100, 20}, {120, 30}}
Knapsack Capacity, W = 50;

Output:
Maximum possible value = 240
by taking items of weight 10 and 20 kg and 2/3 fraction
of 30 kg. Hence total price will be 60+100+(2/3)(120) = 240*/

    /*An efficient solution is to use Greedy approach. The basic idea of the greedy approach is to calculate the
    ratio value/weight for each item and sort the item on basis of this ratio.
    Then take the item with the highest ratio and add them until we can’t add the next item as a whole and at the
    end add the next item as much as we can.
    Which will always be the optimal solution to this problem..*/

    // item value class
    static class ItemValue {
        Double cost;
        double wt, val, ind;

        // item value function
        public ItemValue(int wt, int val, int ind) {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            cost = (double) val / (double) wt;
        }
    }

    // function to get maximum value
    private static double getMaxValue(int[] wt, int[] val,
                                      int capacity) {
        ItemValue[] iVal = new ItemValue[wt.length];

        for (int i = 0; i < wt.length; i++) {
            iVal[i] = new ItemValue(wt[i], val[i], i);
        }

        // sorting items by value;
        Arrays.sort(iVal, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2) {
                return o2.cost.compareTo(o1.cost);
            }
        });

        double totalValue = 0d;

        for (ItemValue i : iVal) {

            int curWt = (int) i.wt;
            int curVal = (int) i.val;

            if (capacity - curWt >= 0) {
                // this weight can be picked while
                capacity = capacity - curWt;
                totalValue += curVal;
            } else {
                // item cant be picked whole
                double fraction
                        = ((double) capacity / (double) curWt);
                totalValue += (curVal * fraction);
                capacity = (int) (capacity - (curWt * fraction));
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int[] wt = {10, 40, 20, 30};
        int[] val = {60, 40, 100, 120};
        int capacity = 50;

        double maxValue = getMaxValue(wt, val, capacity);

        // Function call
        System.out.println("Maximum value we can obtain = "
                + maxValue);
    }
}

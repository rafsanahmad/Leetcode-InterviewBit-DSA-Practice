/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.GreedyAlgorithm;

public class GasStation {
    //https://www.interviewbit.com/problems/gas-station/
    /*Given two integer arrays A and B of size N.

There are N gas stations along a circular route, where the amount of gas at station i is A[i].

You have a car with an unlimited gas tank and it costs B[i] of gas to travel from station i

to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the minimum starting gas station’s index if you can travel around the circuit once, otherwise return -1.

You can only travel in one direction. i to i+1, i+2, … n-1, 0, 1, 2.. Completing the circuit means starting at i and

ending up at i again.


Input Format

The first argument given is the integer array A.
The second argument given is the integer array B.
Output Format

Return the minimum starting gas station's index if you can travel around the circuit once, otherwise return -1.
For Example

Input 1:
    A =  [1, 2]
    B =  [2, 1]
Output 1:
    1
    Explanation 1:
        If you start from index 0, you can fill in A[0] = 1 amount of gas. Now your tank has 1 unit of gas.
        But you need B[0] = 2 gas to travel to station 1.
        If you start from index 1, you can fill in A[1] = 2 amount of gas. Now your tank has 2 units of gas.
        You need B[1] = 1 gas to get to station 0. So, you travel to station 0 and still have 1 unit of gas
        left over. You fill in A[0] = 1 unit of additional gas, making your current gas = 2.
        It costs you B[0] = 2 to get to station 1, which you do and complete the circuit.*/

    public int canCompleteCircuit(final int[] gas, final int[] dist) {
        int start = 0, totalGasUsed = 0, totalGasFound = 0;

        int gasRequiredBefore = 0;

        int gasFoundBefore = 0;


        for (int i = 0; i < gas.length; i++) {
            totalGasUsed += dist[i];
            totalGasFound += gas[i];
            if (totalGasFound - totalGasUsed < 0) {
                gasRequiredBefore += totalGasUsed;
                gasFoundBefore += totalGasFound;
                totalGasUsed = 0;
                totalGasFound = 0;
                start = i + 1;
            }
        }

        if (((totalGasFound + gasFoundBefore) - (totalGasUsed + gasRequiredBefore)) >= 0)
            return start;
        return -1;
    }

    //using two pointer
    public int canCompleteCircuitSimple(final int[] gas, final int[] dist) {
        // TODO: check A==null || B==null || A.size()!=B.size()
        int n = gas.length;
        int start = 0;
        int end = 0;
        int total = 0;
        int curr_gas = 0;

        while (end < n) {
            curr_gas += gas[end] - dist[end];
            total += gas[end] - dist[end];
            if (curr_gas >= 0) {
                end++;
            } else {
                start = end + 1;
                end++;
                curr_gas = 0;
            }
        }

        if (total < 0)
            return -1;
        else return start;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] dist = {3, 4, 5, 1, 2};
        GasStation station = new GasStation();
        System.out.println(station.canCompleteCircuitSimple(gas, dist));
    }
}

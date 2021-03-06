/*
 * *
 *  * Minimum Number Of Cars.java
 *  * Created by Rafsan Ahmad on 1/24/22, 1:58 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Codility;

import java.util.Arrays;

public class MinimumNumberOfCars {

    /*A group of friends is going on holiday together. They come to a meeting point(the start of the journey)
    using N cars. There are P[K] people and S[K] seats in the K-th car for K in range [0..N-1]. Some of the seats
    in the cars may be free, so it is possible for some of the friends to change the car they are in. the friends
    have decided that, in order to be ecological, they will leave some cars parked at the meeting point and travel
    with as few cars as possible.

Write a function:
function solution(P, S)

that, given two arrays P and S, consisting of N integers each, returns the minimum number of cars needed to take all
of the friends on holiday.

Examples:

Given P=[1,4,1] and S=[1,5,1], the function should return 2.
A person from car number 0 can travel in car number 1 instead.
This way, car number 0 can be left parked at the meeting point.

Given P=[4,4,2,4] and S=[5,5,2,5] the function should return 3.
One person from car number 2 can travel in car number 0 and
the other person from car number 2 can travel in car number 3.

Given P=[2,3,4,2] and S=[2,5,7,2],
the function should return 2.
Passengers from car number 0 can travel in car number 1 and
passengers from car number 3 can travel in car number 2.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of arrays P, S is an integer within the range [1..9];
every friend had a seat in the care they came in; that is, P[K] <= S[K] for each K within the range [0..N-1].*/

    //Minimum number of cars required to sit every person

    public int minNumberOfCars(int[] P, int[] S) {
        int minNumberOfCars = 0;
        Arrays.sort(S);
        Arrays.sort(P);
        boolean fillSeat = true;
        int pj = P.length - 1;
        for (int i = S.length - 1; i >= 0; i--) {
            int seatCount = S[i];
            while (seatCount >= 0 && fillSeat && pj >= 0) {
                int person = P[pj];
                if (seatCount >= person) {
                    seatCount = seatCount - person;
                    pj--;
                } else {
                    //Can not be filled
                    fillSeat = false;
                }
            }
            minNumberOfCars++;
            if (pj == 0) return minNumberOfCars + 1;
            else if (pj < 0) return minNumberOfCars;
        }

        return minNumberOfCars;
    }

    public static void main(String[] args) {
        MinimumNumberOfCars cars = new MinimumNumberOfCars();
        int[] P = {1, 2, 2, 6};
        int[] S = {1, 10, 1, 10};
        System.out.println(cars.minNumberOfCars(P, S));

        int[] P1 = {1, 0, 0, 4};
        int[] S1 = {2, 0, 0, 6};
        System.out.println(cars.minNumberOfCars(P1, S1));

        int[] P2 = {10, 11, 12, 13};
        int[] S2 = {11, 12, 13, 14};
        System.out.println(cars.minNumberOfCars(P2, S2));

        int[] P3 = {1, 11, 4, 3};
        int[] S3 = {2, 40, 4, 3};
        System.out.println(cars.minNumberOfCars(P3, S3));

        int[] P4 = {1, 4, 1};
        int[] S4 = {1, 5, 1};
        System.out.println(cars.minNumberOfCars(P4, S4));

        int[] P5 = {4, 4, 2, 4};
        int[] S5 = {5, 5, 2, 5};
        System.out.println(cars.minNumberOfCars(P5, S5)); //Wrong ans

        int[] P6 = {2, 3, 4, 2};
        int[] S6 = {2, 5, 7, 2};
        System.out.println(cars.minNumberOfCars(P6, S6)); //Wrong ans
    }
}

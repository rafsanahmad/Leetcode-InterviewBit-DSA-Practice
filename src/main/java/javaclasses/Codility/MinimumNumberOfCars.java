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

    //Time Complexity: O(N log N) due to sorting
    //Space Complexity: O(1) extra (ignoring the input arrays)
    public int minNumberOfCars(int[] P, int[] S) {
        int totalPeople = 0;
        for (int p : P) totalPeople += p;

        Arrays.sort(S); // ascending

        int carsUsed = 0;
        int seatsAvailable = 0;
        int i = S.length - 1;
        // take cars with most seats first
        while (i >= 0 && seatsAvailable < totalPeople) {
            seatsAvailable += S[i];
            carsUsed++;
            i--;
        }

        return carsUsed;
    }

    /*O(N) Approach Idea
Calculate total people: totalPeople = sum(P).
Count cars by their seat numbers. Since S[i] is 1..9, we can have an array countSeats[10] to
store how many cars have 1,2,…9 seats.
Now, we can start using cars from largest seat count to smallest, subtracting seats from total
people, until totalPeople <= 0.
Number of cars used = minimum number of cars.*/

    //Time: O(N) (traverse arrays once + fixed loop 1..9)
    //Space: O(1) (small counting array)
    public int minNumberOfCarsOptimized(int[] P, int[] S) {
        int totalPeople = 0;
        int[] countSeats = new int[10]; // S[i] in 1..9

        for (int i = 0; i < P.length; i++) {
            totalPeople += P[i];
            countSeats[S[i]]++;
        }

        int carsUsed = 0;
        // Start from largest seats
        for (int seat = 9; seat >= 1; seat--) {
            while (countSeats[seat] > 0 && totalPeople > 0) {
                totalPeople -= seat;
                countSeats[seat]--;
                carsUsed++;
            }
            if (totalPeople <= 0) break;
        }

        return carsUsed;
    }

    public static void main(String[] args) {
        MinimumNumberOfCars cars = new MinimumNumberOfCars();
        int[] P = {1, 2, 2, 6};
        int[] S = {1, 9, 1, 9};
        System.out.println(cars.minNumberOfCars(P, S));
        System.out.println(cars.minNumberOfCarsOptimized(P, S));

        int[] P1 = {1, 0, 0, 4};
        int[] S1 = {2, 0, 0, 6};
        System.out.println(cars.minNumberOfCars(P1, S1));
        System.out.println(cars.minNumberOfCarsOptimized(P1, S1));

        int[] P4 = {1, 4, 1};
        int[] S4 = {1, 5, 1};
        System.out.println(cars.minNumberOfCars(P4, S4));
        System.out.println(cars.minNumberOfCarsOptimized(P4, S4));

        int[] P5 = {4, 4, 2, 4};
        int[] S5 = {5, 5, 2, 5};
        System.out.println(cars.minNumberOfCars(P5, S5)); //Wrong ans
        System.out.println(cars.minNumberOfCarsOptimized(P5, S5));

        int[] P6 = {2, 3, 4, 2};
        int[] S6 = {2, 5, 7, 2};
        System.out.println(cars.minNumberOfCars(P6, S6)); //Wrong ans
        System.out.println(cars.minNumberOfCarsOptimized(P6, S6));
    }
}

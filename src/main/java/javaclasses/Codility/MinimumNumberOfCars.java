/*
 * *
 *  * MinimumNumberOfCars.java
 *  * Created by Rafsan Ahmad on 1/24/22, 1:58 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Codility;

import java.util.Arrays;

public class MinimumNumberOfCars {

    //Minimum number of cars required to sit every person
    public int minNumberOfCars(int[] P, int[] S) {
        int totalSeats = 0;
        int totalPeople = 0;
        int minNumberOfCars = 0;
        for (int i = 0; i < P.length; i++) {
            totalPeople += P[i];
        }
        for (int i = 0; i < S.length; i++) {
            totalSeats += S[i];
        }

        if (totalPeople == totalSeats) {
            return S.length;
        }

        int diff = totalSeats - totalPeople;
        Arrays.sort(P);

        int i = 0;
        minNumberOfCars = S.length;
        while (diff > 0) {
            //FIX LOGIC HERE
            if (P[i] <= diff) {
                diff = diff - P[i];
                minNumberOfCars--;
                i++;
            } else {
                break;
            }
        }

        return minNumberOfCars;
    }

    public static void main(String[] args) {
        MinimumNumberOfCars cars = new MinimumNumberOfCars();
        int[] P = {1, 2, 2, 6};
        int[] S = {1, 10, 1, 10};
        System.out.println(cars.minNumberOfCars(P, S));
    }
}

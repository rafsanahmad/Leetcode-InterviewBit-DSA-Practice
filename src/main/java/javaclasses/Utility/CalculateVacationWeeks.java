/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class CalculateVacationWeeks {

    //System.out.println(numberPasswordValidation.calculateVacation(2014, "April", "May", "Wednesday"));
    public int calculateVacation(int Y, String A, String B, String W) {
        // write your code in Java SE 8
        int result = 0;
        int beginMonth = -1;
        int endMonth = -1;
        switch (A) {
            case "January":
                beginMonth = 1;
                break;
            case "February":
                beginMonth = 2;
                break;
            case "March":
                beginMonth = 3;
                break;
            case "April":
                beginMonth = 4;
                break;
            case "May":
                beginMonth = 5;
                break;
            case "June":
                beginMonth = 6;
                break;
            case "July":
                beginMonth = 7;
                break;
            case "August":
                beginMonth = 8;
                break;
            case "September":
                beginMonth = 9;
                break;
            case "October":
                beginMonth = 10;
                break;
            case "November":
                beginMonth = 11;
                break;
            case "December":
                beginMonth = 12;
                break;
        }
        switch (B) {
            case "January":
                endMonth = 1;
                break;
            case "February":
                endMonth = 2;
                break;
            case "March":
                endMonth = 3;
                break;
            case "April":
                endMonth = 4;
                break;
            case "May":
                endMonth = 5;
                break;
            case "June":
                endMonth = 6;
                break;
            case "July":
                endMonth = 7;
                break;
            case "August":
                endMonth = 8;
                break;
            case "September":
                endMonth = 9;
                break;
            case "October":
                endMonth = 10;
                break;
            case "November":
                endMonth = 11;
                break;
            case "December":
                endMonth = 12;
                break;
        }

        //Process Start date
        YearMonth begin = YearMonth.of(Y, beginMonth);
        int daysInBeginMonth = begin.lengthOfMonth();
        String firstDay_begin = begin.atDay(1).getDayOfWeek().name();
        String lastDay_begin = begin.atEndOfMonth().getDayOfWeek().name();

        String bMonth = Integer.toString(beginMonth);
        if (beginMonth < 10) {
            bMonth = "0" + bMonth;
        }
        String startDateString = Y + "-" + bMonth + "-" + "01";
        LocalDate startDate = LocalDate.parse(startDateString);

        //System.out.println(firstDay_begin + " " + lastDay_begin + " " + daysInBeginMonth + " " );

        //Process End date
        YearMonth end = YearMonth.of(Y, endMonth);
        int daysInEndMonth = end.lengthOfMonth();
        String firstDay_end = end.atDay(1).getDayOfWeek().name();
        String lastDay_end = end.atEndOfMonth().getDayOfWeek().name();

        String eMonth = Integer.toString(endMonth);
        if (endMonth < 10) {
            eMonth = "0" + eMonth;
        }
        String endDateString = Y + "-" + eMonth + "-" + daysInEndMonth;
        LocalDate endDate = LocalDate.parse(endDateString);

        //Number of days between A & B
        long noOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        result = (int) noOfDays / 7;

        //Check week overlap between two months
        if (!lastDay_end.equals("Sunday")) {
            --result;
        }
        //System.out.println(firstDay_end + " " + lastDay_end + " " + daysInEndMonth + " ");
        return result;
    }
}

/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

public class MobileBill {

    //Prints Total bill
    //System.out.println(MobileBill.mobileBill("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090"));
    public int mobileBill(String S) {
        long bill = 0;
        Integer freeNumber = Integer.MAX_VALUE;
        int billLessthan5Min = 3;
        int billmoreThan5Min = 150;

        String[] phoneNumbers = S.split("\\n");
        LinkedHashMap<Integer, Long> data = new LinkedHashMap<>();  //Key = Phone Number, Value = Duration (Seconds)
        for (String phoneNumber : phoneNumbers) {
            String[] parts = phoneNumber.split(",");
            if (parts.length == 2) {
                try {
                    String numberString = parts[1].replaceAll("[\\s\\-]", "");
                    Integer number = Integer.parseInt(numberString);
                    DateFormat format = new SimpleDateFormat("hh:mm:ss");
                    format.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date date = format.parse(parts[0]);
                    long seconds = date.getTime() / 1000L;

                    if (data.containsKey(number)) {
                        data.put(number, data.get(number) + seconds);
                    } else {
                        data.put(number, seconds);
                    }
                } catch (Exception e) {

                }
            }
        }

        //Sort map by value
        data = sortMap(data);

        //For key ascending case
        Long highestDuration = data.entrySet().stream().findFirst().get().getValue();
        for (Map.Entry<Integer, Long> entry : data.entrySet()) {
            Long duration = entry.getValue();
            //if there is more than one higest duration get the minimum number
            if (duration.equals(highestDuration)) {
                if (entry.getKey() < freeNumber) {
                    freeNumber = entry.getKey();
                }
            }
        }

        for (Map.Entry<Integer, Long> entry : data.entrySet()) {
            Integer number = entry.getKey();
            if (!number.equals(freeNumber)) {
                //discard free number bill
                Long val = entry.getValue();
                if (val < 300) {
                    bill = bill + (val * billLessthan5Min);  //3 cent
                } else {
                    int min = (int) Math.round(val / 60);
                    boolean check = isEvenlyDivisable(val, 60);
                    if (!check) {
                        min = min + 1;
                    }
                    bill = bill + ((long) min * billmoreThan5Min);  //150 cent
                }
            }
        }

        return Math.toIntExact(bill);
    }

    public boolean isEvenlyDivisable(long a, int b) {
        return a % b == 0;
    }

    public LinkedHashMap<Integer, Long> sortMap(LinkedHashMap<Integer, Long> hashMap) {
        LinkedHashMap<Integer, Long> sortedMap = new LinkedHashMap<>();
        hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }

    public static void main(String[] args) {
        MobileBill bill = new MobileBill();
        String phoneNumbers = "00:01:07,400-234-090\n" +
                "00:05:01,701-080-080\n" +
                "00:05:00,400-234-090";
        System.out.println(bill.mobileBill(phoneNumbers));
    }
}

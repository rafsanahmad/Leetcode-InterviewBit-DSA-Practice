/*
 * *
 *  * Pass By Value Reference Output.java
 *  * Created by Rafsan Ahmad on 1/13/22, 3:43 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.OutputQuiz;

public class PassByValueReferenceOutput {

    public static void changeTheString(String weather) {
        weather = "sunny";
    }

    public static void changeTheArray(String[] rainyDays) {
        rainyDays[1] = "Sunday";
    }

    public static void changeTheObject(Forecast forecast) {
        forecast.temperature = 35;
    }

    public static void main(String[] args) {
        String weather = "rainy";
        changeTheString(weather);
        System.out.println("The Weather is " + weather);

        String[] rainyDays = new String[]{"Monday", "Friday"};
        changeTheArray(rainyDays);
        System.out.println("The rainy days were on " + rainyDays[0] + " and " + rainyDays[1]);

        Forecast forecast = new Forecast();
        forecast.pressure = 700;
        forecast.temperature = 20;
        changeTheObject(forecast);
        System.out.println("The temperature is " + forecast.temperature + "C");
    }
}

class Forecast extends PassByValueReferenceOutput {
    public int temperature;
    public int pressure;
}

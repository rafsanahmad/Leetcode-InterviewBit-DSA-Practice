/*
 * *
 *  * Created by Rafsan Ahmad on 11/13/21, 1:58 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReadData {

    //Java program to read data from URL connection input stream
    public static void main(String[] args) {
        String result;
        try {
            URL url = new URL("https://coderbyte.com/api/challenges/json/age-counting");
            try {
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                //System.out.println(inputStream);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        inputStream));
                StringBuilder builder = new StringBuilder();
                while ((result = in.readLine()) != null) {
                    builder.append(result);
                }
                result = builder.toString();
                in.close();
            } catch (IOException ioEx) {
                System.out.println(ioEx);
                result = "{\"data\":\"key=IAfpK, age=58, key=WNVdi, age=64, key=jp9zt, age=47\"}";
            }
        } catch (MalformedURLException malEx) {
            System.out.println(malEx);
            result = "{\"data\":\"key=IAfpK, age=58, key=WNVdi, age=64, key=jp9zt, age=47\"}";
        }
        result = result.replace("{", "");
        result = result.replace("}", "");
        String[] str = result.split(":");
        String[] ages = str[1].split(",");
        int count = 0;
        for (int i = 0; i < ages.length; i++) {
            if (ages[i].contains("age")) {
                String[] split = ages[i].split("=");
                if (split.length == 2) {
                    String strNum = split[1].trim();
                    strNum = strNum.replace("\"", "");
                    int num = Integer.parseInt(strNum);
                    if (num >= 50) {
                        ++count;
                    }
                }
            }
        }
        //Prints 2 - As 2 ages are greater or equal to 50
        System.out.println(count);
    }
}

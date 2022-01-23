/*
 * *
 *  * CropMessage.java
 *  * Created by Rafsan Ahmad on 1/24/22, 1:51 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Codility;

public class CropMessage {

    //K Characters limit on a Forum Message
    public String cropMessage(String message, int K) {
        StringBuilder builder = new StringBuilder("");
        int currentLength = 0;
        String[] splitted = message.split("\\s+");
        for (String str : splitted) {
            currentLength += str.length() + 1; //for space
            if (currentLength <= K || currentLength - 1 == K) {
                builder.append(str).append(" ");
            }
        }
        String result = builder.toString();
        result = result.replaceAll("\\s+$", ""); //remove ending space
        return result;
    }

    public static void main(String[] args) {
        CropMessage c = new CropMessage();
        String message = "Codility we test coders";
        System.out.println(c.cropMessage(message, 14));
        String message2 = "The quick brown fox jumps over the lazy dog";
        System.out.println(c.cropMessage(message2, 39));
        String message3 = "APP B CD E";
        System.out.println(c.cropMessage(message3, 6));
    }
}

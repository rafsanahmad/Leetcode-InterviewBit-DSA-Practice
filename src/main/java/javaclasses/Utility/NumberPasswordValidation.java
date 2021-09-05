package javaclasses.Utility;

import java.util.regex.Pattern;

public class NumberPasswordValidation {

    public boolean solution(String S) {
        // write your code in Java SE 8

        //check only digits
        if (!S.matches("[0-9-]+")) {
            return false;
        }

        //check length
        if (!(S.length() == 11)) {
            return false;
        }

        //check number of segments
        String[] parts = S.split("-");
        if (!(parts.length == 3)) {
            return false;
        }

        //check individual segment length
        for (int i = 0; i < parts.length; i++) {
            if (!(parts[i].length() == 3)) {
                return false;
            }
        }
        return true;
    }

    public boolean passwordCheck(String S) {
        if (S.length() < 6) {
            return false;
        }

        if (S.contains(" ")) {
            return false;
        }

        Pattern upperCase = Pattern.compile(".*[A-Z].*");
        if (!upperCase.matcher(S).matches()) {
            return false;
        }

        Pattern lowerCase = Pattern.compile(".*[a-z].*");
        if (!lowerCase.matcher(S).matches()) {
            return false;
        }

        Pattern digit = Pattern.compile(".*\\d.*");
        if (!digit.matcher(S).matches()) {
            return false;
        }

        String specialCharacters = "!@#$%^&*()_";
        Pattern special = Pattern.compile(".*[" + specialCharacters + "].*");
        if (!special.matcher(S).matches()) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        NumberPasswordValidation numberPasswordValidation = new NumberPasswordValidation();
        //System.out.println(Math.round(361 / 60));
        System.out.println(numberPasswordValidation.solution("000 34 32232"));
        System.out.println(numberPasswordValidation.passwordCheck("FooBar123!"));

        char ch = (char) ('a' + 4);
        System.out.println(ch);
        int d = 10;
    }
}

package javaclasses.Utility;

public class PhoneNumValidation {

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

    public static void main(String[] args) {
        PhoneNumValidation phoneNumValidation = new PhoneNumValidation();
        System.out.println(phoneNumValidation.solution("000 34 32232"));
    }
}

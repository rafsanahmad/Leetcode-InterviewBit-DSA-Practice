package javaclasses.Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
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

    public String mobileKeyboard(String S) {
        StringBuilder stringBuilder = new StringBuilder("");
        boolean capsLockStatus = false;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == 'C' || ch == 'B') {
                switch (ch) {
                    case 'C':
                        capsLockStatus = !capsLockStatus;
                        break;
                    case 'B':
                        if (stringBuilder.length() > 0) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }
                        break;

                }
                continue;
            }
            if (capsLockStatus) {
                stringBuilder.append(Character.toUpperCase(ch));
            } else {
                stringBuilder.append(Character.toLowerCase(ch));
            }
        }
        return stringBuilder.toString();
    }

    public int mobileBill(String S) {
        // write your code in Java SE 8
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
                    bill = bill + (min * billmoreThan5Min);  //150 cent
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
        NumberPasswordValidation numberPasswordValidation = new NumberPasswordValidation();
        //System.out.println(Math.round(361 / 60));
        System.out.println(numberPasswordValidation.solution("000 34 32232"));
        System.out.println(numberPasswordValidation.passwordCheck("FooBar123!"));
        System.out.println(numberPasswordValidation.mobileKeyboard("CrCellBax"));
        System.out.println(numberPasswordValidation.mobileBill("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090"));
    }
}

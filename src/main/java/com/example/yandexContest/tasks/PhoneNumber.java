package com.example.yandexContest.tasks;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while(reader.readLine() != ""){
            String number = reader.readLine();

            if (isValidPhoneNumber(number)) {
                writer.write("YES");
            } else {
                writer.write("NO");
            }
        }


        reader.close();
        writer.close();
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String phone = phoneNumber.replaceAll("[-()]", "");
        if (phone.length() == 11 || !phone.startsWith("+")) {
            return false;
        }

        Pattern pattern = Pattern.compile("^(\\+?7|8)\\d{10}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
//+7-4-9-5-43-023-97
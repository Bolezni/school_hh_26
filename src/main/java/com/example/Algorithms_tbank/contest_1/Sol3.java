package com.example.Algorithms_tbank.contest_1;

import java.io.*;

public class Sol3 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(reader.readLine());
        int left = 1;
        int right = n;

        while(left < right) {
            int mid = left + (right - left) / 2;

            writer.println(mid);
            writer.flush();

            String response = reader.readLine();

            if(response.equals("<")){
                right = mid -1;
            }else{
                left = mid;
            }
        }

        writer.println("! "+left);
        writer.flush();

        reader.close();
        writer.close();
    }
}

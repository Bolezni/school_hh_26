package com.example.belgu;

public class PowerCalculator {


    /*
    * Дано действительное число a.
    * Не пользуясь никакими другими арифметическими операциями,
    * кроме умножения получить A8 за три операции, A9 за четыре операции и A13 за пять операций.
    * */

    public static void main(String[] args) {
        double a = 2.0;
        // За три операции
        double a2 = a * a;
        double a4 = a2 * a2;
        double a8 = a4 * a4;

        //За четыре операции
        double a9 = a8 * a;

        //За пять операций
        double b2 = a * a;
        double b3 = b2 * a;
        double b6 = b3 * b3;
        double b12 = b6 * b6;
        double b13 = b12 * a;

        System.out.println("a^8  = " + a8);
        System.out.println("a^9  = " + a9);
        System.out.println("a^13 = " + b13);
    }
}

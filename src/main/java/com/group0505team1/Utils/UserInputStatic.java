package com.group0505team1.Utils;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInputStatic {

    static Scanner scanner = new Scanner(System.in);

    public static String inputText(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    public static int inputInt(String message){
        System.out.println(message);
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static double inputDouble(String message){
        System.out.println(message);
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    public static LocalDate inputDate(String message){
        while(true){
            System.out.println(message);
            String date = scanner.nextLine().trim();
            if (date.isEmpty()){
                System.out.println("Date is empty");
                continue;
            }
            try {
                return LocalDate.parse(date);
            }catch (Exception e){
                System.out.println("Invalid date format . Use YEAR-MONTH-DAY");
            }
        }
    }

    public static void close(){
        scanner.close();
    }

}

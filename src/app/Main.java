package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.out.println("Welcome to console bank");
        while(running){
            System.out.println("""
                1} Open Account,
                2} Transfer,
                3} Deposit,
                4} Withdraw,
                5} Bank Statement,
                6} List Account,
                7} Search Account By Customer Name,
                0} Exit
                """);
            System.out.println("Choose:");
            String choice =sc.nextLine().trim();
            System.out.println("Choice:" + choice);

            switch (choice){
                case "0"-> running=false;
            }
        }


    }
}

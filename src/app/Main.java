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
                2} Deposit,
                3} Withdraw,
                4} Transfer,
                5} Account Statement,
                6} List Account,
                7} Search Account By Customer Name,
                0} Exit
                """);
            System.out.println("Choose:");
            String choice =sc.nextLine().trim();
            System.out.println("Choice:" + choice);

            switch (choice){
                case "1" -> openAccount(sc);
                case "2" -> deposit(sc);
                case "3" -> withdraw(sc);
                case "4" -> transfer(sc);
                case "5" -> accountStatement(sc);
                case "6" -> listAccounts(sc);
                case "7" ->searchAccounts(sc);
                case "0"-> running=false;
            }
        }


    }

    private static void openAccount(Scanner sc) {
        System.out.println("Customer Name:");
        String name =sc.nextLine().trim();
        System.out.println("Customer Email:");
        String email = sc.nextLine();
        System.out.println("Account type ? Saving.Current");
        String type =sc.nextLine().trim();
        System.out.println("Initial Deposit (Optional ,0 for no deposit)");
        String amountStr =sc.nextLine().trim();
        Double initial = Double.valueOf(amountStr);
    }

    private static void deposit(Scanner sc) {
    }

    private static void withdraw(Scanner sc) {
    }

    private static void transfer(Scanner sc) {
    }

    private static void accountStatement(Scanner sc) {
    }

    private static void listAccounts(Scanner sc) {
    }

    private static void searchAccounts(Scanner sc) {
    }

}

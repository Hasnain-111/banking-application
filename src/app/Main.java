package app;

import service.BankService;
import service.BankServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankServiceImpl();
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
                case "1" -> openAccount(sc,bankService);
                case "2" -> deposit(sc);
                case "3" -> withdraw(sc);
                case "4" -> transfer(sc);
                case "5" -> accountStatement(sc);
                case "6" -> listAccounts(sc,bankService);
                case "7" ->searchAccounts(sc);
                case "0"-> running=false;
            }
        }


    }

    private static void openAccount(Scanner sc,BankService bankService) {
        System.out.println("Customer Name:");
        String name =sc.nextLine().trim();

        System.out.println("Customer Email:");
        String email = sc.nextLine();

        System.out.println("Account type ? Saving.Current");
        String type =sc.nextLine().trim();

        System.out.println("Initial Deposit (Optional ,0 for no deposit)");
        String amountStr =sc.nextLine().trim();
        Double initial = Double.valueOf(amountStr);

        bankService.openAccount(name,email,type);
    }

    private static void deposit(Scanner sc) {
    }

    private static void withdraw(Scanner sc) {
    }

    private static void transfer(Scanner sc) {
    }

    private static void accountStatement(Scanner sc) {
    }

    private static void listAccounts(Scanner sc,BankService bankService) {
        bankService.listAccount().forEach(account -> {
            System.out.println(account.getAccountNumber() +"|"
                    +account.getAccountType() +
                    "|" +account.getBalance());
        });
    }

    private static void searchAccounts(Scanner sc) {
    }

}

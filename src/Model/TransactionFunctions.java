package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TransactionFunctions {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Transaction> AllMyTransaction;
    HashMap<String, ArrayList<Transaction>> FinalTransactions;

    public TransactionFunctions() {
        AllMyTransaction = new ArrayList<>();
        FinalTransactions = new HashMap<>();
    }

    //Method to add Transactions

    public void add() {
        System.out.print("Enter the Amount: Rs.");
        float amount = scanner.nextFloat();

        System.out.print("Enter the date of Transaction (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        while (date.isEmpty())
            date =  scanner.nextLine();  //while the date is empty

        System.out.print("""
                \nSelect the type of Transaction:\s
                1. Income
                2. Expense\s""");

        System.out.print("\n\nEnter your option: ");
        int choice = scanner.nextInt();

        System.out.print("Enter the note for your Transaction: ");
        String note = scanner.nextLine();
        while (note.isEmpty())
            note = scanner.nextLine();

        Transaction transaction = new Transaction(date, choice - 1, amount, note);
        AllMyTransaction.add(transaction);
        LocalDate localDate = LocalDate.parse(transaction.date, DateTimeFormatter.ISO_DATE);

//        Key for hashmap
        String key = localDate.getMonth().toString() + " " + localDate.getYear();

//        If particular month of a year already has previous transactions
        if (FinalTransactions.containsKey(key)) {
            FinalTransactions.get(key).add(transaction);
        }
//        If I am making first Transaction of a particular month
        else {
            ArrayList<Transaction> newTransaction = new ArrayList<>();
            newTransaction.add(transaction);
            FinalTransactions.put(key, newTransaction);
        }
        System.out.println(Colors.GREEN + "Transaction successfully Added." + Colors.RESET);
    }

    //Filtering Transactions Monthly
    public void filter() {
        System.out.print("Enter month and year (Format: MAY 2019): ");
        String key = scanner.nextLine();
        while (key.isEmpty())
            key = scanner.nextLine();
        float expense = 0;
        float income = 0;

        ArrayList<Transaction> trans = FinalTransactions.get(key);

        //total spends and earnings
        for (Transaction tran : trans) {
            if (tran.transactionType == TransactionType.TYPE_INCOME) {
                income += tran.amount;
            } else {
                expense += tran.amount;
            }
        }
        float savings = income - expense;
        System.out.println(Colors.GREEN + "Total Earned: Rs." + income + Colors.RESET);
        System.out.println(Colors.RED + "Total Spent: Rs." + expense + Colors.RESET);

        if(savings < 0){
            System.out.println(Colors.RED + "\nSavings: Rs." + savings + Colors.RESET);
        }
        else if(savings == 0){
            System.out.println(Colors.YELLOW + "\nSavings: Rs." + savings + Colors.RESET);
        }
        else if(savings > 0){
            System.out.println(Colors.GREEN + "\nSavings: Rs." + savings + Colors.RESET);

        }
    }

    //    To edit a Transaction
    public void editTransaction(){
        System.out.print("Enter the month and the year (Format MAY 2019): ");
        String key = scanner.nextLine();
        while(key.isEmpty())
            key = scanner.nextLine();
        ArrayList<Transaction> trans = FinalTransactions.get(key);

        //Printing all the transactions of the month serial wise
        for(int i = 0; i<trans.size(); i++){
            System.out.println((i + 1) + ". " + trans.get(i));
        }
        System.out.print("Choose from above Transactions: ");
        int choice = scanner.nextInt();

        Transaction transaction = trans.get(choice-1);
        String menu = """

                \nChoose the entity you want to edit:\s
                0. Go Back
                1. Amount
                2. Date
                3. Note
                Enter your choice:\s""";

        int option = 1;
        while(option!=0){
            System.out.print(menu);
            option = scanner.nextInt();

//            to edit the Amount
            if(option == 1){
                System.out.print("\n\nEnter the new Amount: Rs.");
                int newAmount = scanner.nextInt();
                transaction.amount = newAmount;
                System.out.println(Colors.GREEN + "Amount updated to " + newAmount + " Successfully" + Colors.RESET);
            }

//            to edit date
            else if(option == 2){
                System.out.print("Enter new Date: ");
                String newDate = scanner.nextLine();
                while(newDate.isEmpty())
                    newDate = scanner.nextLine();
                transaction.date = newDate;
                System.out.println(Colors.GREEN + "Date updated to " + newDate + " Successfully" + Colors.RESET);
            }
            else if(option == 3){
                System.out.println("Enter the new Note: ");
                String newNote = scanner.nextLine();
                while (newNote.isEmpty())
                    newNote = scanner.nextLine();
                transaction.Note = newNote;
                System.out.println(Colors.GREEN + "Note updated to " + newNote + " Successfully" + Colors.RESET);
            }
            else if(option != 0)
                System.out.println(Colors.RED + "Incorrect choice, choose from 0 - 3. " + Colors.RESET);
        }
    }

    //    Delete a Transaction
    public void deleteTransaction(){
        System.out.println("Enter the transaction month and the year (Format: MAY 20XX): ");
        String key = scanner.nextLine();
        while (key.isEmpty())
            key = scanner.nextLine();
        if(!FinalTransactions.containsKey(key)){
            System.out.println(Colors.RED + "You don't have any Transaction in the given month of the year" + Colors.RESET);
        }
        ArrayList<Transaction> trans = FinalTransactions.get(key);
        for(int i = 0; i < trans.size(); i++){
            System.out.println((i + 1) + ". "  + trans.get(i));
        }
        System.out.println("Choose from above Transactions: ");

        int choice = scanner.nextInt();
        Transaction transaction = trans.get(choice - 1);
        trans.remove(transaction);
        System.out.println(Colors.GREEN + "Transaction removed Successfully" + Colors.RESET);
    }

    //        TO show all the transaction of the user
    public void showTransaction(){
        for (int i = 0; i < AllMyTransaction.size() ; i++){
            System.out.println((i + 1) + ". " + AllMyTransaction.get(i));
        }
    }

    //    TO show the transactions monthly
    public void showTransactionsByMonth(){
        System.out.println("Enter the month adn the year (Format: MAY 2019): ");
        String key = scanner.nextLine();
        while (key.isEmpty())
            key = scanner.nextLine();
        if(!FinalTransactions.containsKey(key))
            System.out.println(Colors.RED + "Sorry! You don't have any Transaction of the given month." + Colors.RESET);
        ArrayList<Transaction> trans = FinalTransactions.get(key);
        for(int i = 0 ; i< trans.size() ; i++){
            System.out.println((i+1) + ". " + trans.get(i));
        }
    }
}

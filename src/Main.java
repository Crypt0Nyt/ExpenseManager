import Model.TransactionFunctions;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionFunctions transactionManager = new TransactionFunctions();
        Scanner sc = new Scanner(System.in);
        String menu = "\nChoose from the following: "
                + "\n0: Exit from application"
                + "\n1: Add New Transaction"
                + "\n2: Delete a Transaction"
                + "\n3: Edit a Transaction"
                + "\n4: Get Expenditure of Month"
                + "\n5: Show Transactions by Month"
                + "\n6: Show all Transactions"
                + "\n\nEnter your choice : ";

        while (true){
            System.out.print(menu);
            int choice = sc.nextInt();
            switch (choice){
                case 0:
                    System.out.println("Thank You!");
                    return;

                case 1:
                    transactionManager.add();
                    break;

                case 2:
                    transactionManager.deleteTransaction();
                    break;

                case 3:
                    transactionManager.editTransaction();
                    break;

                case 4:
                    transactionManager.filter();
                    break;

                case 5:
                    transactionManager.showTransactionsByMonth();
                    break;

                case 6:
                    transactionManager.showTransaction();
                    break;

                default:
                    System.out.println("Incorrect choice");
                    break;
            }
        }
    }
}



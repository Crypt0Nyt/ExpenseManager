package Model;

import java.lang.reflect.Type;

public class Transaction {
    public String date;                       //Date of the Transaction
    public int transactionType;               //Type of the transaction ( Income and Expense)
    public float amount;                      // Amount of the transaction
    public String Note;                       // Note or Summary for the Transaction

    //Parameterised constructor for transaction
    public Transaction(String date, int transactionType, float amount, String note) {
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
        this.Note = note;
    }

    public int getTransactionType() {
        return transactionType;
    }

    @Override
    public String toString() {
        String s;
        if (transactionType == TransactionType.TYPE_INCOME) {
            return (Colors.GREEN + "Income:    Rs." + amount + "     " + date + "     " + Note + "     " + Colors.RESET);
        } else {
            return (Colors.RED + "Expense:     Rs." + amount + "     " + date + "     " + Note + "     " + Colors.RESET);


        }
    }
}

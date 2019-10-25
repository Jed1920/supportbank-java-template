package training.supportbank;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {
    List<Transaction> fromTransactions = new ArrayList<>();
    List<Transaction> toTransactions = new ArrayList<>();
    Double balance = 0.0;

    public Account(String name, List<Transaction> allTransactions) {
        // Loops through each transaction
        for (Transaction item : allTransactions) {
            // Adds the transaction to the fromTransactions field if the name is in the From field of the transactions
            // Adds the Amount of the transaction to the balance
            if (name.equals(item.getFrom())) {
                fromTransactions.add(item);
                balance += item.getAmount();
            }
            // Adds the transaction to the toTransactions field if the name is in the To field of the transactions
            // Subtracts the Amount of the transaction from the balance
            if (name.equals(item.getTo())) {
                toTransactions.add(item);
                balance -= item.getAmount();
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        balance = Double.valueOf(df.format(balance));
    }

    public List<Transaction> getFromTransactions() {
        return fromTransactions;
    }

    public List<Transaction> getToTransactions() {
        return toTransactions;
    }

    public Double getBalance() {
        return balance;
    }
}
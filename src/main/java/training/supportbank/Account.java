package training.supportbank;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Account {
    List<Transaction> fromTransactions = new ArrayList<>();
    List<Transaction> toTransactions = new ArrayList<>();
    Double balance = 0.0;

    public Account(String name, List<Transaction> allTransactions) {
        for (Transaction item : allTransactions) {
            if (name.equals(item.getFrom())) {
                fromTransactions.add(item);
                balance += item.getAmount();
            }
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
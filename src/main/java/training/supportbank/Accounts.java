package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Accounts {

    private List<Transaction> fromTransactions = new ArrayList<>();
    private List<Transaction> toTransactions = new ArrayList<>();
    private Double balance = 0.0;

    public Accounts(String name, List<Transaction> transactionList){

        for (Transaction indivTrans:transactionList) {
            if (name.equals(indivTrans.getFrom())) {
                fromTransactions.add(indivTrans);
                balance += indivTrans.getAmount();
            }
            if (name.equals(indivTrans.getTo())) {
                toTransactions.add(indivTrans);
                balance -= indivTrans.getAmount();
            }
        }

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

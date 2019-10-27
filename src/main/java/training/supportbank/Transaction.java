package training.supportbank;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Transaction {

    private String date;
    private String from;
    private String to;
    private String narrative;
    private Double amount;

    String splitTransactions [];
    public Transaction (String strIndivTrans){

        String splitTransactions [] = strIndivTrans.split(",");

        date = splitTransactions[0];
        from = splitTransactions[1];
        to = splitTransactions[2];
        narrative = splitTransactions[3];
        try {
            amount = Double.parseDouble(splitTransactions[4]);
        } catch (NumberFormatException e) {
            amount = 0.0;
            }
    }
    @Override
    public String toString(){
        String print = String.format("| %-10s | %-10s |%-10s | %-35s | %-5s | " , date, from,to,narrative, Double.toString(amount));
        return print;
    }

   public String getDate() {
       return date;
   }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getNarrative() {
        return narrative;
    }

    public Double getAmount() {
        return amount;
    }
}
package training.supportbank;

import java.text.DecimalFormat;
import java.util.List;

public class Transaction {

    private String date;
    private String from;
    private String to;
    private String narrative;
    private Double amount;

    public Transaction (String singletrans){
        String [] splittransactions = singletrans.split(",");
        date = splittransactions[0];
        from  = splittransactions[1];
        to = splittransactions[2];
        narrative = splittransactions[3];

        amount = Double.parseDouble(splittransactions[4]);
    }

    @Override
    public String toString () {

        String printout = date + " " + from + " " + to + " " + narrative + " " + amount + "\n";
        return printout;
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

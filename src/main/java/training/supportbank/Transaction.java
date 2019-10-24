package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Transaction {

    private String date;
    private String from;
    private String to;
    private String narrative;
    private Double amount;

    Logger logger = LogManager.getLogger();

    public Transaction (String singletrans) {
        String[] splittransactions = singletrans.split(",");
        date = splittransactions[0];
        from = splittransactions[1];
        to = splittransactions[2];
        narrative = splittransactions[3];
        try {
            amount = Double.parseDouble(splittransactions[4]);
        } catch (Exception e) {
            logger.debug("Value in the amount column not registered as digit");
            amount = 0.0;

        }
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

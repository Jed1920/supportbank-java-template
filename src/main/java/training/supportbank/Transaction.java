package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Transaction {

    private String date;
    private String from;
    private String to;
    private String narrative;
    private Double amount;

    Logger logger = LogManager.getLogger();

    public Transaction(String singletrans) throws ParseException, NumberFormatException {
        // Splits the string of a transaction into each element by separating at each comma
        String[] splittransactions = singletrans.split(",");

        // from, to and narrative are nice and boring.
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        from = splittransactions[1];
        to = splittransactions[2];
        narrative = splittransactions[3];

        try {
            Date date1 = formatter.parse(splittransactions[0]);
            date = formatter.format(date1);
        } catch (ParseException e) {
            logger.warn(" '" + splittransactions[0] + "' - " + " in incorrect format so could not be converted to date, is null");
            date = null;
        }

        try {
            amount = Double.parseDouble(splittransactions[4]);
        } catch (NumberFormatException e) {
            logger.error(" '" + splittransactions[4] + "' - " + " in incorrect format so could not be converted to an amount");
            throw e;
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

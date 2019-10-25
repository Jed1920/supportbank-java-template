package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String args[]) {

        Logger logger = LogManager.getLogger();
        logger.info("SupportBank program began running");


        List<String> csvTransString = new ArrayList<>();

        try {
            //Imports the csv file and creates a string line by line of the csv file
            Path filePath = Paths.get("DodgyTransactions2015.csv");
            csvTransString = Files.readAllLines(filePath);

        } catch (Exception e) {
            logger.error("File could not be read");
            System.exit(0);
        }

        int i = 1;
        List<Transaction> transactionList = new ArrayList<>();
        //Loops through each line of the string and creates a Transaction object with Date, From, To, Narrative
        // and Amount fields
        for (String singletrans : csvTransString) {
            try {
                Transaction sortedTrans = new Transaction(singletrans);
                transactionList.add(sortedTrans);
            } catch (Exception e){
                logger.warn("Transaction on line  " + i +" could not be processed");
            }
            i++;
        }

        // Loops through each individual transaction and creates a Set containing each name in the From and To section
        Set<String> nameSet = new HashSet<>();
        for (Transaction indivTrans : transactionList) {
            nameSet.add(indivTrans.getFrom());
            nameSet.add(indivTrans.getTo());
        }

        // Converts this set into a usable list
        List<String> nameList = new ArrayList<>(nameSet);

        // Creates a two Hashmaps connecting a name to an account or a total balance they owe or are owed
        Map<String, Account> listOfAcc = new HashMap<String, Account>();
        Map<String, Double> balanceList = new HashMap<String, Double>();

        // Loops through each name and creates an Account object
        for (String name : nameList) {
            Account holder = new Account(name, transactionList);
            listOfAcc.put(name, holder);
            balanceList.put(name, listOfAcc.get(name).getBalance());
        }
        // Asks for the user to specify whose list of accounts to be viewed
        Scanner askName = new Scanner(System.in);
        String accountName;
        System.out.println("Enter account name");
        accountName = askName.nextLine();
        System.out.println((listOfAcc.get(accountName)).getFromTransactions());
        System.out.println((listOfAcc.get(accountName)).getToTransactions());

        System.out.println(balanceList);
    }
}



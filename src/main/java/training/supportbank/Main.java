package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String args[]) {

        Logger logger = LogManager.getLogger();
        logger.info("SupportBank program run");


        List<String> csvTransString = new ArrayList<>();

        try {
            //Imports the csv file and creates a string line by line of the csv file
            Path filePath = Paths.get("DodgyTransactions2015.csv");
            csvTransString = Files.readAllLines(filePath);

        } catch (Exception e) {
            logger.error("File could not be read", e);
            System.exit(0);
        }

        List<Transaction> transactionList = new ArrayList<>();
        Set<String> unknownBalances = new HashSet<>();
        for (int lineNumber = 1; lineNumber < csvTransString.size(); lineNumber++) {
            String singletrans = csvTransString.get(lineNumber);

            try {
                Transaction sortedTrans = new Transaction(singletrans);
                transactionList.add(sortedTrans);
            } catch (Exception e) {
                String[] splittransactions = singletrans.split(",");
                unknownBalances.add(splittransactions[1]);
                unknownBalances.add(splittransactions[2]);
                logger.error(String.format("Failed to create transaction from line %d.  Transaction skipped.", lineNumber), e);
            }
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

        Map<String, String> unknownBalanceList = new HashMap<String, String>();

        // Loops through each name and creates an Account object
        for (String name : nameList) {
            Account holder = new Account(name, transactionList);
            listOfAcc.put(name, holder);
            if (!unknownBalances.contains(name)) {
                balanceList.put(name, listOfAcc.get(name).getBalance());
            } else {
                unknownBalanceList.put(name, "??? " + String.valueOf(listOfAcc.get(name).getBalance()) + " ???");
            }
        }
        // Asks for the user to specify whose list of accounts to be viewed
        Scanner askName = new Scanner(System.in);
        String accountName;
        System.out.println("Enter account name");
        accountName = askName.nextLine();

        System.out.println((listOfAcc.get(accountName)).getFromTransactions());
        System.out.println((listOfAcc.get(accountName)).getToTransactions());

        System.out.println(balanceList);
        System.out.println(unknownBalanceList);
        logger.warn("Balances of " + unknownBalances + " not produced due to issues with amount format from file");
        logger.info(transactionList.size() + " of " +  (csvTransString.size()-1) + " transactions processed");
    }
}



package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {

        Logger logger = LogManager.getLogger();


        Path filePath = Paths.get("DodgyTransactions2015.csv");
        List<String> csvTransString = Files.readAllLines(filePath);

        int i = 1;

        List<Transaction> transactionList = new ArrayList<>();
        for (String singletrans : csvTransString) {
            try {
                Transaction sortedTrans = new Transaction(singletrans);
                transactionList.add(sortedTrans);
            } catch (Exception e){
                logger.debug("Transaction " + i +" could not be processed");
            }

            i++;
        }


        Set<String> nameSet = new HashSet<>();
        for (Transaction indivTrans : transactionList) {
            nameSet.add(indivTrans.getFrom());
            nameSet.add(indivTrans.getTo());
        }

        List<String> nameList = new ArrayList<>(nameSet);
        HashMap<String, Double> balance = new HashMap<String, Double>();

        Map<String, Account> listOfAcc = new HashMap<String, Account>();
        Map<String, Double> balanceList = new HashMap<String, Double>();
        for (String name : nameList) {
            Account holder = new Account(name, transactionList);
            listOfAcc.put(name, holder);
            balanceList.put(name, listOfAcc.get(name).getBalance());
        }

        Scanner askName = new Scanner(System.in);
        String accountName;
        System.out.println("Enter account name");
        accountName = askName.nextLine();
        System.out.println((listOfAcc.get(accountName)).getFromTransactions());
        System.out.println((listOfAcc.get(accountName)).getToTransactions());

//        }
//        public static void balanceOwed() {
        System.out.println(balanceList);

        System.out.println(balance);
        //System.out.println(nameList);
    }
}



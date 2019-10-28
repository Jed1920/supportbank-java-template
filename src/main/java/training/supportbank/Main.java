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
        logger.info("SupportBank program run");

//        try {
            Path filePath = Paths.get("Transactions2014.csv");
            List<String> strCSVTrans = Files.readAllLines(filePath);
//        } catch (Exception e){
//            logger.fatal("Could not load file" + e );
//        }

        List <Transaction> transactionList = new ArrayList<>();
        Set<String> namelist = new HashSet<>();
        HashMap<String, Accounts> accounts = new HashMap<>();

        for (String strIndivTrans: strCSVTrans){
            Transaction orderedIndivTrans = new Transaction(strIndivTrans);
            transactionList.add(orderedIndivTrans);

            namelist.add(orderedIndivTrans.getFrom());
            namelist.add(orderedIndivTrans.getTo());
        }
        for (String name:namelist){
              Accounts indivAccount = new Accounts(name, transactionList);
                  accounts.put(name,indivAccount);
                  System.out.println(String.format("| %-10s = %#5.2f |", name,indivAccount.getBalance()));
            }

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a Name ");
        String selectedName = reader.nextLine();

        System.out.println("Sent Transactions");
        for(Transaction transactions:(accounts.get(selectedName).getFromTransactions())) {
            System.out.println(transactions);
        }
        System.out.println("Received Transactions");
        for(Transaction transactions:(accounts.get(selectedName).getToTransactions())) {
            System.out.println(transactions);
        }

    }
}

package training.supportbank;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String args[]) throws IOException {
        // Your code here!
        Path filePath = Paths.get("Transactions2014.csv");
        List<String> strCSVTrans = Files.readAllLines(filePath);
        List <Transaction> transactionList = new ArrayList<>();
        Set<String> namelist = new HashSet<>();

        for (String strIndivTrans: strCSVTrans){
            Transaction orderedIndivTrans = new Transaction(strIndivTrans);
            transactionList.add(orderedIndivTrans);

            namelist.add(orderedIndivTrans.getFrom());
            namelist.add(orderedIndivTrans.getTo());

//            System.out.println(orderedIndivTrans);
        }
        for (String name:namelist){

        }



        System.out.println(transactionList);
    }
}

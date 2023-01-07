package ProductsInWorld;

import com.opencsv.*;

import java.io.*;
import java.util.*;


public class Parser {
    public List<Products> SalesOfProductsList = new ArrayList<>();
    public Parser(String path) throws FileNotFoundException {
        FileReader filereader = new FileReader(path);
        try (CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build()){
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                for (String cell : row){
                    System.out.print(cell + ", ");
                }
                System.out.println();
                Products products = new Products(row);
                SalesOfProductsList.add(products);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}



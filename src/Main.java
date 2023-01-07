import ProductsInWorld.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException {
        String csvPath = "files\\SalesOfProducts.csv";
        String sqlPath = "jdbc:sqlite:files/SalesOfProducts.s3db";
        Parser CSVParser = new Parser(csvPath);

        Database.createAndFillTable(CSVParser, sqlPath);


        Requests.firstTask(sqlPath);
        System.out.println(Requests.secondTask(sqlPath));
        System.out.println(Requests.thirdTask(sqlPath));
    }
}
package ProductsInWorld;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import java.sql.*;


public class Requests {
    public static void firstTask(String sqlPath) throws SQLException, ClassNotFoundException {
        String query = "select region, count(*) as result from salesofproducts group by region";

        JDBCCategoryDataset dataset = new JDBCCategoryDataset(Database.ConnectToDB(sqlPath), query);
        JFreeChart chart = ChartFactory.createLineChart("график", "x", "y", dataset);
        ChartFrame frame = new ChartFrame("Query Chart", chart);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }


    public static String secondTask(String sqlPath) throws SQLException, ClassNotFoundException {
        Database.ConnectToDB(sqlPath);

        ResultSet resultsFromDB = Database.sttm.executeQuery("select country from salesofproducts where (region = 'Europe' or region = 'Asia') order by totalprofit desc");
        String result = String.format("ответ на второе задание: %s", resultsFromDB.getString(1));

        Database.Disconnect();
        return result;
    }

    public static String thirdTask(String sqlPath) throws SQLException, ClassNotFoundException {
        Database.ConnectToDB(sqlPath);

        ResultSet resultsFromDB = Database.sttm.executeQuery("select country from salesofproducts" +
                " where (totalprofit >= 420000.0 and totalprofit <= 440000.0)" +
                " and (region = 'Middle East and North Africa' or region = 'Sub-Saharan Africa') order by totalprofit desc");
        String result = String.format("ответ на третье задание: %s", resultsFromDB.getString(1));

        Database.Disconnect();
        return result;
    }


}
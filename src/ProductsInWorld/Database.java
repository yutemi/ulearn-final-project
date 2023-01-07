package ProductsInWorld;

import java.sql.*;

public class Database {
    private static Connection conn;
    protected static Statement sttm;

    public static Connection ConnectToDB(String sqlPath) throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(sqlPath);
        sttm = conn.createStatement();

        return conn;
    }

    public static void Disconnect() throws SQLException {
        conn.close();
        sttm.close();
    }

    public static void createAndFillTable(Parser products, String sqlPath) throws SQLException, ClassNotFoundException {
        ConnectToDB(sqlPath);
        String request = """
                CREATE TABLE IF NOT EXISTS SalesOfProducts (
                region TEXT,
                country TEXT,
                itemType TEXT,
                salesChannel TEXT,
                orderPriority TEXT,
                orderDate TEXT,
                unitsSold INTEGER,
                totalProfit REAL);
                """;
        sttm.execute(request);

        try (PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO 'SalesOfProducts' ('region', 'country', 'itemType', 'salesChannel', 'orderPriority', 'orderDate', 'unitsSold' , 'totalProfit' ) VALUES (?,?,?,?,?,?,?,?);")) {
            products.SalesOfProductsList.forEach(x -> {
                try {
                    statement.setObject(1, x.region);
                    statement.setObject(2, x.country);
                    statement.setObject(3, x.itemType);
                    statement.setObject(4, x.salesChannel);
                    statement.setObject(5, x.orderPriority);
                    statement.setObject(6, x.orderDate);
                    statement.setObject(7, x.unitsSold);
                    statement.setObject(8, x.totalProfit);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
// Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Disconnect();
    }
}

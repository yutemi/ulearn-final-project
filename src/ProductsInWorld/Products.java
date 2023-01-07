package ProductsInWorld;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Products {
    public String region;
    public String country;
    public String itemType;
    public String salesChannel;
    public Character orderPriority;
    public Date orderDate;
    public int unitsSold;
    public Float totalProfit;

    public Products(String[] values) throws ParseException {
        this.region = values[0];
        this.country = values[1];
        this.itemType = values[2];
        this.salesChannel = values[3];
        this.orderPriority = values[4].charAt(0);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        this.orderDate = formatter.parse(values[5].replace('.', '/'));
        this.unitsSold = Integer.parseInt(values[6]);
        this.totalProfit = Float.parseFloat(values[7]);
    }
}

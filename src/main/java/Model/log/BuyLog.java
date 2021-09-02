package Model.log;

import Model.Good.Good;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log{
    private String sellerName;

    public BuyLog(String logId, Date date, double moneyExchanged, double discountAmount, ArrayList<Good> goodsExchanged, ShipmentState shipmentState , String sellerName) {
        super(logId, date, moneyExchanged, discountAmount, goodsExchanged, shipmentState);
        this.sellerName = sellerName;
    }

    public String getSellerName() {
        return sellerName;
    }
}

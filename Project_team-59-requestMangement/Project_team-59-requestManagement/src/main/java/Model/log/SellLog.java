package Model.log;

import Model.Good.Good;

import java.util.ArrayList;
import java.util.Date;

public class SellLog extends Log{
    private String buyerName;

    public SellLog(String logId, Date date, double moneyExchanged, double discountAmount, ArrayList<Good> goodsExchanged, ShipmentState shipmentState, String buyerName) {
        super(logId, date, moneyExchanged, discountAmount, goodsExchanged, shipmentState);
        this.buyerName = buyerName;
    }

    public String getBuyerName() {
        return buyerName;
    }
}

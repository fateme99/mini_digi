package Model.log;

import Model.Good.Good;

import java.util.ArrayList;
import java.util.Date;

public class Log {
    private String logId;
    private Date date;
    private double moneyExchanged;
    private double discountAmount;
    private ArrayList<Good> goodsExchanged;
    private ShipmentState shipmentState;

    public Log(String logId, Date date, double moneyExchanged, double discountAmount, ArrayList<Good> goodsExchanged, ShipmentState shipmentState) {
        this.logId = logId;
        this.date = date;
        this.moneyExchanged = moneyExchanged;
        this.discountAmount = discountAmount;
        this.goodsExchanged = goodsExchanged;
        this.shipmentState = shipmentState;
    }

    public String getLogId() {
        return logId;
    }

    public Date getDate() {
        return date;
    }

    public double getMoneyExchanged() {
        return moneyExchanged;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public ArrayList<Good> getGoodsExchanged() {
        return goodsExchanged;
    }

    public ShipmentState getShipmentState() {
        return shipmentState;
    }
}

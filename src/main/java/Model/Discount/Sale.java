package Model.Discount;

import Model.Account.Seller;
import Model.Good.Good;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
    private static int idCounter = 0;
    private int saleId;
    private ArrayList<Good> inSaleGoods;
    private SaleState saleState;
    private Date startingDate;
    private Date endingDate;
    private double offPercent;
    private Seller seller;

    public Sale( ArrayList<Good> inSaleGoods , Date startingDate, Date endingDate, double offPercent , Seller seller ) {
        this.saleId = idCounter++;
        this.inSaleGoods = inSaleGoods;
        this.saleState = SaleState.PENDING;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.offPercent = offPercent;
        this.seller = seller;
    }

    public int getSaleId() {
        return saleId;
    }

    public ArrayList<Good> getInSaleGoods() {
        return inSaleGoods;
    }

    public SaleState getSaleState() {
        return saleState;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public double getOffPercent() {
        return offPercent;
    }

    public void addInSaleGood(Good good) {
        this.inSaleGoods.add(good);
    }

    public void removeInSaleGood(Good good) {
        this.inSaleGoods.remove(good);
    }

    public void setSaleState(SaleState saleState) {
        this.saleState = saleState;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setOffPercent(double offPercent) {
        this.offPercent = offPercent;
    }
}

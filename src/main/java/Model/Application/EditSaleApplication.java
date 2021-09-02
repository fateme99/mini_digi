package Model.Application;

import Model.Discount.Sale;
import Model.Good.Good;

import java.util.ArrayList;
import java.util.Date;

public class EditSaleApplication extends Application{
    private Sale sale;
    private Date newStartingDate;
    private Date newEndingDate;
    private ArrayList<Good> goodsToBeRemoved;
    private ArrayList<Good> goodsToBeAdded;
    private double newOffPercent;

    public EditSaleApplication(Sale sale) {
        super(ApplicationType.EDIT_OFF);
        this.sale = sale;
        goodsToBeAdded = new ArrayList<>();
        goodsToBeRemoved = new ArrayList<>();
    }

    public Sale getSale() {
        return sale;
    }

    public Date getNewStartingDate() {
        return newStartingDate;
    }

    public Date getNewEndingDate() {
        return newEndingDate;
    }

    public ArrayList<Good> getGoodsToBeRemoved() {
        return goodsToBeRemoved;
    }

    public ArrayList<Good> getGoodsToBeAdded() {
        return goodsToBeAdded;
    }

    public double getNewOffPercent() {
        return newOffPercent;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setNewStartingDate(Date newStartingDate) {
        this.newStartingDate = newStartingDate;
    }

    public void setNewEndingDate(Date newEndingDate) {
        this.newEndingDate = newEndingDate;
    }

    public void setNewOffPercent(double newOffPercent) {
        this.newOffPercent = newOffPercent;
    }

    public void addGoodToAddGoodList(Good good){
        goodsToBeAdded.add(good);
    }

    public void addGoodToRemoveGoodList(Good good){
        goodsToBeRemoved.add(good);
    }
}



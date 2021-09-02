package Model.Account;

import Model.Discount.Sale;
import Model.Good.Good;
import Model.log.SellLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends Account {
    private HashMap <Good , Integer> goodsBeingOffered;
    private ArrayList <Sale> saleList;
    private ArrayList <SellLog> sellLog;
    private String companyInformation;
    //TODO if seller sets its own price for goods....

    public Seller(AccountInformation accountInformation, Role role , String companyInformation ) {
        super(accountInformation, role);
        this.goodsBeingOffered = new HashMap<>();
        this.saleList = new ArrayList<>();
        this.sellLog = new ArrayList<>();
        this.companyInformation = companyInformation;
    }

    public void setCompanyInformation(String companyInformation) {
        this.companyInformation = companyInformation;
    }

    public String getCompanyInformation() {
        return companyInformation;
    }

    public HashMap<Good, Integer> getGoodsBeingOffered() {
        return goodsBeingOffered;
    }

    public ArrayList<Sale> getSaleList() {
        return saleList;
    }

    public ArrayList<SellLog> getSellLog() {
        return sellLog;
    }

    public void updateGoodsAmount( Good good , int amount ){
        for (Good good1 : goodsBeingOffered.keySet()) {
            if ( good.equals(good1) ){
                goodsBeingOffered.put(good , amount);
            }
        }
        //TODO throw exception
    }

    public void removeGood( Good good ){
        for (Good good1 : goodsBeingOffered.keySet()) {
            if(good.equals(good1)){
                goodsBeingOffered.remove(good);
            }
        }
        //TODO throw exception
    }

    public void addGood( Good good , int amount ){
        //TODO throw exception   if good exists
        goodsBeingOffered.put( good , amount );
    }
}

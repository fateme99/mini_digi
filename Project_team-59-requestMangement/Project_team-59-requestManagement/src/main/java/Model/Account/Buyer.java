package Model.Account;

import Model.Good.Good;
import Model.log.BuyLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Account {
    private HashMap<Good , Integer> cart;
    private ArrayList<BuyLog> buyLog;

    public Buyer(AccountInformation accountInformation, Role role ) {
        super(accountInformation, role);
        this.cart = new HashMap<>();
        this.buyLog = new ArrayList<>();
    }

    public HashMap<Good , Integer> getCart() {
        return cart;
    }

    public ArrayList<BuyLog> getBuyLog() {
        return buyLog;
    }

    public double getCartValue(){
        double totalPrice = 0;
        for (Good good : cart.keySet()) {
            totalPrice += good.getPrice()*cart.get(good);
        }
        return totalPrice;
    }

    public void addProduct(Good good){
        cart.put(good , 1);
    }

    public void removeProduct(Good wantedGood){
        for (Good good : cart.keySet()) {
            if ( wantedGood.equals(good) ){
                cart.remove(good);
                return;
            }
        }
        //TODO throw exception....
    }

    public void increaseGoodInCart( Good wantedGood ){
        for (Good good : cart.keySet()) {
            if ( wantedGood.equals(good) ){
                cart.put(good, cart.get(good) + 1);
            }
        }
    }

    public void decreaseGoodInCart( Good wantedGood ){
        for (Good good : cart.keySet()) {
            if ( wantedGood.equals(good) ){
                cart.put(good, cart.get(good) - 1);
            }
        }
    }

    public BuyLog getBuyLogById(String buyLogId){
        for (BuyLog log : buyLog) {
            if( log.getLogId().equals(buyLogId))
                return log;
        }
        //TODO throw exception instead of down return statement
        return null;
    }

    public void addItemsToCart(HashMap<Good , Integer> cartItems){
        this.cart.putAll(cartItems);
    }
}

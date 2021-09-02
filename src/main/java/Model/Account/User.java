package Model.Account;

import Model.Good.Good;

import java.util.HashMap;


public class User {
    private HashMap<Good , Integer> cart;

    public User() {
        this.cart = new HashMap<>();
    }

    public HashMap<Good, Integer> getCart() {
        return cart;
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
}

package Controller;

import Model.Account.Seller;
import Model.Good.Good;

import java.util.ArrayList;

public class UserController {

    public void creatAccount( ArrayList<String> userInputs ){
        //TODO sends application to manager
    }

    public void logIn(){
        //TODO opens login page then ....
    }

    public void digest( Good good ){
        //TODO prints good's related information and enables ability to add to cart and select seller
    }

    public void addToCart(Good good , Seller seller , int amount ){
        //TODO adds to user/buyers cart with seller and amount
    }

    public void selectSeller( Good good ){
        //TODO check if seller exists then weather seller sells product, each need dedicated function
    }

    public void showAttributes( Good good ){
        //TODO print goods attributes
    }

    public void compare( Good good1 , String good2ProductId ){
        //TODO print two goods attributes for comparison
    }

    public void addComment( String title , String insidetTxt , Good good ){
        //TODO sends an application to manager then can be added to goods comments
    }

    public void viewCart(){
        //TODO shows buyer's cart goods
    }

    public void showOrder(){
        //TODO shows buyer's cart goods // not sure
    }

    public void purchase(){
        //TODO user signs in and then buyer purchases ...
    }

    public void showTotalPrice(){
        //TODO prints total price of buyers cart
    }

    public void increaseProduct( String productId ){
        //TODO increases product in user/buyer cart
    }

    public void decreaseProduct( String productId ){
        //TODO decreases product in user/buyer cart
    }

}

package View.Menus;

import Controller.BuyerController;
import Model.Account.Buyer;

public class PaymentMenu extends Menu {
    public PaymentMenu(Menu headMenu) {
        this.headMenu = headMenu;
    }

    public void run(double totalValue , Buyer buyer){
        if(buyer.getBalance() < totalValue){
            System.out.println("your balance is not enough");
        }
        else if ( buyer.getBalance() >= totalValue ){
            BuyerController.purchase(buyer , totalValue);
        }
    }
}

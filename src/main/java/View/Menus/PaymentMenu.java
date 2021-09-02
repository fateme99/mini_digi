package View.Menus;

import Controller.BuyerController;
import Controller.Controller;
import Model.Account.Buyer;
import Model.Good.Good;

import java.util.Collection;

public class PaymentMenu extends Menu {
    private static final Controller controller = Controller.getInstance();
    public PaymentMenu(Menu headMenu) {
        this.headMenu = headMenu;

    }

    public void run(double totalValue , Buyer buyer) throws Exception {
        if(buyer.getBalance() < totalValue){
            System.out.println("your balance is not enough");
        }
        else if ( buyer.getBalance() >= totalValue ){
            BuyerController.purchase(buyer , totalValue);
        }
    }
}

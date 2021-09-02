package View.Menus;

import Controller.Controller;
import Model.Account.Account;
import Model.Account.Buyer;
import Model.Account.Manager;
import Model.Account.Seller;
import View.Requests.UserRequest;

public class LogInView extends Menu{
    private UserRequest userRequest;
    private boolean purchaseIsHappening;

    public LogInView(Menu menu , boolean purchaseIsHappening) {
        this.headMenu = menu;
        this.purchaseIsHappening = purchaseIsHappening;
    }

    public void run( String[] splitInput ){
        try {
        if ( !Controller.usernameExists(splitInput[1]) ){
            System.out.println("no user with such username exists");
            this.headMenu.run();
        }
        System.out.println("please enter your password: ");
        String input = Menu.getInputFromUser();
            Account account = Manager.getAccountByUsername(splitInput[1]);
            if (account.passwordIsCorrect(input)) {
                goToAccountsPage(account);
            } else {
                System.out.println("password is incorrect");
                this.headMenu.run();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void goToAccountsPage(Account account){
        Controller.setCurrentAccount(account);
        if ( account instanceof Buyer ){
            addUserCartToAccount( account );
            if(!purchaseIsHappening){
                new BuyerView(this.headMenu , (Buyer)account).run();
            }
            purchaseIsHappening = false;
        }
        else if ( account instanceof Seller){
            new SellerView( this.headMenu , (Seller) account ).run();
        }
        else {
            new ManagerView( this.headMenu ).run();
        }
    }

    private void addUserCartToAccount(Account account){
        Buyer buyer = (Buyer)account;
        buyer.addItemsToCart(Controller.getCurrentUser().getCart());
    }

    private void help(){
        //TODO
    }
}

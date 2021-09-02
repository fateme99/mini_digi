package Controller;

import Model.Account.Account;

public abstract class AccountController {
    private Account loggedInAccount;

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public AccountController(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    public void printAccountInfo(Account accont ){
        //TODO print accounts info
    }

    public void editAccountInfo( String field ){
        //TODO edits users account info, field is anything inside of accountInformation besides username
    }

    public void viewBalance(){
        //TODO prints account's balance
    }
}

package Model.Good;

import Model.Account.Account;

public class Rating {
    private Account account;
    private int rating;
    private Good good;

    public Rating(Account account, int rating, Good good) {
        this.account = account;
        this.rating = rating;
        this.good = good;
    }

    public Account getAccount() {
        return account;
    }

    public int getRating() {
        return rating;
    }

    public Good getGood() {
        return good;
    }
}

package Model.Discount;

import Model.Account.Account;

import java.util.ArrayList;
import java.util.Date;

public class OffTicket {
    private String offTicketId;
    private Date startingDate;
    private Date endingDate;
    private double offPercent;
    private double offAmount;
    private int timesCanBeUsed;
    private ArrayList<Account> accountsInvolved;

    public OffTicket( Date startingDate, Date endingDate, double offPercent, double offAmount, int timesCanBeUsed, ArrayList<Account> accountsInvolved) {
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.offPercent = offPercent;
        this.offAmount = offAmount;
        this.timesCanBeUsed = timesCanBeUsed;
        this.accountsInvolved = accountsInvolved;
    }

    public String getOffTicketId() {
        return offTicketId;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public double getOffPercent() {
        return offPercent;
    }

    public double getOffAmount() {
        return offAmount;
    }

    public int getTimesCanBeUsed() {
        return timesCanBeUsed;
    }

    public ArrayList<Account> getAccountsInvolved() {
        return accountsInvolved;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setOffPercent(double offPercent) {
        this.offPercent = offPercent;
    }

    public void setOffAmount(double offAmount) {
        this.offAmount = offAmount;
    }

    public void setTimesCanBeUsed(int timesCanBeUsed) {
        this.timesCanBeUsed = timesCanBeUsed;
    }

    public void addAccountInvolved(Account accountInvolved) {
        this.accountsInvolved.add(accountInvolved);
    }

    public void removeAccountInvolved(Account accountInvolved) {
        this.accountsInvolved.remove(accountInvolved);
    }
}

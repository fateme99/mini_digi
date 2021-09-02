package Model.Discount;

import Model.Account.Account;

import java.util.ArrayList;
import java.util.Date;

public class OffTicket {
    private static int idCounter=0;
    private String offTicketId;
    private String startingDate;
    private String endingDate;
    private double offPercent;
    private double offAmount;
    private int timesCanBeUsed;
    private ArrayList<String> accountIdsInvolved;

    public OffTicket( String startingDate, String endingDate, double offPercent, double offAmount, int timesCanBeUsed, ArrayList<String> accountsInvolved) {
        this.offTicketId=""+idCounter++;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.offPercent = offPercent;
        this.offAmount = offAmount;
        this.timesCanBeUsed = timesCanBeUsed;
        this.accountIdsInvolved = accountsInvolved;
    }

    public String getOffTicketId() {
        return offTicketId;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setAccountIdsInvolved(ArrayList<String> accountIdsInvolved) {
        this.accountIdsInvolved = accountIdsInvolved;
    }

    public String getEndingDate() {
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

    public ArrayList<String> getAccountIdsInvolved() {
        return accountIdsInvolved;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(String endingDate) {
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

    public void addAccountInvolved(String accountInvolved) {
        this.accountIdsInvolved.add(accountInvolved);
    }

    public void removeAccountInvolved(String accountInvolved) {
        this.accountIdsInvolved.remove(accountInvolved);
    }
}

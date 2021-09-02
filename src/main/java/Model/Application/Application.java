package Model.Application;

import Model.Account.Account;
import Model.Account.ManagerRequest;
import Model.Account.User;
import Model.Discount.OffTicket;
import Model.Good.Category;
import Model.Good.Comment;
import Model.Good.Good;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class Application {
    private static Application ourInstance=new Application();
    private Map<String, Good>goods;
    private Map<String,Account>accounts;
    private Map<String, OffTicket>offTickets;
    private Map<String, ManagerRequest>requests;
    private Map<String, Category>categoories;
    private Map<String , User>users;
    private Map<Good, Comment>comments;

    private Application(){
        goods=new HashMap<>();
        accounts=new HashMap<>();
        offTickets=new HashMap<>();
        requests=new HashMap<>();
        categoories=new HashMap<>();
        users=new HashMap<>();
        comments=new HashMap<>();
    }
    public Map<String,Category>getCategoories(){
        return categoories;
    }
    public Map<String , User>getUsers(){
        return users;
    }
    public Map<String,OffTicket>getOffTickets(){
        return offTickets;
    }
    public Map<String,ManagerRequest>getRequests(){
        return requests;
    }
    public Map<String,Good>getGoods(){
        return goods;
    }
    public Map<Good,Comment>getComments(){ return comments;}
    public Map<String,Account>getAccounts(){
        return accounts;
    }
    private static int requestNumber = 0;
    private int requestId;
    private ArrayList<String> requestDescription;
    private ApplicationState applicationState;
    private ApplicationType applicationType;

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }
    public static Application getInstance() {
        return ourInstance;
    }
    public Application( ApplicationType applicationType) {
        this.requestId = requestNumber++;
        this.applicationState = ApplicationState.TO_BE_APPROVED;
        this.applicationType = applicationType;
    }

    public int getRequestId() {
        return requestId;
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }
}

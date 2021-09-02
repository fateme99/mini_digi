package Controller;

import Model.Account.*;
import Model.Application.Application;
import Model.Application.ApplicationType;
import Model.Application.CreatAccountApplication;
import Model.Discount.OffTicket;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Comment;
import Model.Good.Good;
import Model.log.BuyLog;
//import View.Menus.BuyerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Controller {
    private static Manager coreManager;
    private static Account currentAccount;
    private static User currentUser;
    private boolean filteringIsPossible;//not sure
    private boolean hasDigestHappened;//not sure
    private Application application;
    private static Controller instance = new Controller();

    public Controller() {
        application = Application.getInstance();
    }

    public static Controller getInstance() {
        return instance;
    }

    public static void initializer() {
        //TODO initializes core manager
        //TODO sets up current user
    }

    public static void creatAdmin(AccountInformation accountInformation) {
        coreManager = new Manager(accountInformation, Role.MANAGER);
        currentUser = new User();
    }

    public static void purchase() throws Exception {
        if (currentAccount == null) {
            throw new Exception("you must log in first in order to purchase");
        }
        if (!(currentAccount instanceof Buyer)) {
            throw new Exception("only buyer accounts can purchase");
        } else {
           // new BuyerView(null, (Buyer) currentAccount);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void sendCreatAccountApplication(String userName, String name, String lastName, String email, String phoneNumber, String password, Role role) {
        Manager.addApplication(new CreatAccountApplication(ApplicationType.CREAT_ACCOUNT, userName, name, lastName, email, phoneNumber, password, role));
    }

    public static double useOffTicket(String offTicketId, Buyer buyer) throws Exception {
        OffTicket offTicket;
        for (OffTicket temporaryOffTicket : buyer.getOffTickets()) {
            if (temporaryOffTicket.getOffTicketId().equalsIgnoreCase(offTicketId)) {
                offTicket = temporaryOffTicket;
                return useOffThicketIfPossible(buyer, offTicket);
            } else {
                throw new Exception("you dont owe such off ticket");
            }
        }
        return buyer.getCartValue();
    }

    public static double useOffThicketIfPossible(Buyer buyer, OffTicket offTicket) throws Exception {
        if (offTicket.getEndingDate().compareTo(new String()) < 0) {
            throw new Exception("off ticket is expired");
        } else if (offTicket.getTimesCanBeUsed() < 1) {
            throw new Exception("no more usages are available for this off ticket");
        } else {
            offTicket.setTimesCanBeUsed(offTicket.getTimesCanBeUsed() - 1);
            double totalValue = (1 - (offTicket.getOffAmount() / 100)) * buyer.getCartValue();
            if (offTicket.getOffAmount() >= (offTicket.getOffAmount() / 100) * buyer.getCartValue()) {
                return totalValue;
            }
            return buyer.getCartValue() - offTicket.getOffAmount();
        }
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public static boolean usernameExists(String username) {
        return true;
    }

    public static void terminator() {
        //TODO terminates program by saving all data
    }

    public void goToOffsPage() {
        //TODO opens offs page
    }

    public void goToProductPage() {
        //TODO opens products page
    }

    public void showCategories() {
        //TODO prints categories
    }

    public void showProduct(String productId) {
        //TODO opens products page and increase goods times visited
    }

    public void showCurrentFilters() {
        //TODO prints current filters
    }

    public void filtering() {
        //TODO ?
    }

    public void showAvailableFilters() {
        //TODO prints possible filters
    }

    public void filter(Characteristic characteristic) {
        //TODO filters by characteristic and adds it to filters
    }

    public void disableFilter(Characteristic characteristic) {
        //TODO disables filter
    }

    public void showCurrentSorts() {
        //TODO prints currentSorts
    }

    public void sorting() {
        //TODO ?
    }

    public void showAvailableSorts() {
        //TODO prints possible sorts
    }

    public void sorter(Characteristic characteristic) {
        //TODO sorts by characteristic
    }

    public void disableSort(Characteristic characteristic) {
        //TODO disables sort and resets it to "times visited"
    }

    public void showProducts(ArrayList<Characteristic> filters, Characteristic sort) {
        //TODO filters then sorts then prints products
    }

    public Collection<OffTicket> getoffTickets() {
        return application.getOffTickets().values();
    }

    public Collection<Good> getgoods() {
        return application.getGoods().values();
    }

    public boolean isbuy(Good good , Buyer currentAccount){
        ArrayList<BuyLog> buyLogs=currentAccount.getBuyLog();
        for (BuyLog buyLog:buyLogs) {
            ArrayList<Good> goods=new ArrayList<>();
            goods=buyLog.getGoodsExchanged();
            for (Good good1:goods) {
                if (good1.getProductId().equalsIgnoreCase(good.getProductId()))
                    return true;
            }
        }
        return false;
    }
    public void addRate(double Rate , Good good){
        double result=good.getAverageRate()*good.getNumOfRate()+Rate/good.getNumOfRate()+1;
        good.setAverageRate(result);
        good.setNumOfRate(good.getNumOfRate()+1);
    }
    public void removeDiscount(String offTicketId) {
        OffTicket offTicket = application.getOffTickets().get(offTicketId);
        application.getOffTickets().remove(offTicket);
    }
    public boolean isManager(){
        ArrayList<Manager>managers= Manager.getAllManagers();
        if (managers.size()==0)
            return false;
        return true;
    }
    public Collection<ManagerRequest> getRequests() {
        return application.getRequests().values();
    }

    public Collection<Category> getCategories() {
        return application.getCategoories().values();
    }

    public Collection<User> getUsers() {
        return application.getUsers().values();
    }

    public Collection<Account> getAccounts() {
        return application.getAccounts().values();
    }

    public ManagerRequest viewRequest(String requestId) {
        ManagerRequest managerRequest = application.getRequests().get(requestId);
        return managerRequest;
    }

    public Collection<Comment> getcomments() {
        return application.getComments().values();
    }

    public Good viewProduct(String productId) {
        Good good = application.getGoods().get(productId);
        return good;
        //TODO show product
    }

    public void removeProduct(Good product) {
        application.getGoods().remove(product);

    }
    public void createManager(String username , String passWord){
        Manager manager=new Manager(new AccountInformation(username,null,null,null,null,passWord),null);
    }
    public void removeUser(Account account) {
        application.getAccounts().remove(account);
    }

    public void removeCategory(Category category) {
        application.getCategoories().remove(category);
    }

    public Category getCategory(String name) {
        Category category = application.getCategoories().get(name);
        return category;
    }

    public User getUser(String username) {
        User user = application.getUsers().get(username);
        return user;
    }

    public Account getAccount(String username) {
        Account account = application.getAccounts().get(username);
        return account;

    }

    public void addGood(Good good) {
        application.getGoods().put(good.getProductId(), good);
    }

    public void addOffticket(OffTicket offTicket) {
        application.getOffTickets().put(offTicket.getOffTicketId(), offTicket);
    }

    public void addComment(Comment comment, Good product) {
        application.getComments().put(product, comment);
    }

    public void addAccounts(Account account) {
        application.getAccounts().put(account.getAccountInformation().getUsername(), account);

    }

    public void addcategory(Category category) {
        application.getCategoories().put(category.getCategoryName(), category);

    }

    public Good getGood(String productId) {
        Good good = application.getGoods().get(productId);
        return good;
    }

    public OffTicket getOffticket(String offticketId) {
        OffTicket offTicket = application.getOffTickets().get(offticketId);
        return offTicket;
    }

    public boolean checkNoDiscountCode(OffTicket offTicket) {
        String offticketId = offTicket.getOffTicketId();
        OffTicket offTicket1 = application.getOffTickets().get(offticketId);
        if (offTicket1 == null)
            return true;
        else
            return false;
    }

    public Collection<Good> sortbyPrice(Collection<Good> goods) {
        ArrayList<Good> sorted=new ArrayList<>();
        double max = 0;
        while (true) {
            max = 0;
            if (goods.size()==0)
                return sorted;
            else {
                for (Good product : goods) {
                    if (product.getPrice() > max)
                        max = product.getPrice();

                }
                for (Good product : goods) {
                    if (product.getPrice() == max) {
                        sorted.add(product);
                        goods.remove(product);
                        break;
                    }

                }
            }


        }

    }
    public Collection<Good>sortbyVisit(Collection<Good>goods){

        ArrayList<Good> sorted=new ArrayList<>();
        Collection<Good> notsorted=goods;

        double max = 0;
        while (true) {
            max = 0;
            if (notsorted.size()==0)
                return sorted;
            else {
                for (Good product : notsorted) {
                    if (product.getPrice() > max)
                        max = product.getTimesVisited();

                }
                for (Good product : notsorted) {
                    if (product.getTimesVisited() == max) {
                        sorted.add(product);
                        notsorted.remove(product);
                        break;
                    }

                }
            }


        }
    }
    public Collection<Good>category(Collection<Good>goods,String categoryValue){
        ArrayList<Good> sorted=new ArrayList<>();
        Collection<Good>goods1=goods;
        if (categoryValue.contains("Nothing"))
            return goods1;

        while (true){
            if (goods1.size()==0)
                return sorted;
            else {
                for (Good product: goods1) {
                    if (categoryValue.contains(product.getCategory().getCategoryName())){
                        sorted.add(product);
                        goods1.remove(product);
                        break;
                    }
                    else {
                        goods1.remove(product);
                        break;
                    }
                }
            }
        }
    }
    public Collection<Good> sortbypriceIn(Collection<Good> goods) {
        ArrayList<Good> sorted=new ArrayList<>();
        double min;
        while (true) {
            min = Double.MAX_VALUE;
            if (goods.size()==0)
                return sorted;
            else {
                for (Good product : goods) {
                    if (product.getPrice() <min)
                        min = product.getPrice();

                }
                for (Good good:goods) {
                    if (good.getPrice() == min) {
                        sorted.add(good);
                        goods.remove(good);
                        break;
                    }

                }
            }


        }
    }

}/*public boolean createDiscountCode(OffTicket offTicket){
    String offticketId=offTicket.getOffTicketId();

    application.getOffTickets().put(offticketId,offTicket);

}*/

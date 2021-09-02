package Model.Account;

import Model.Application.Application;
import Model.Discount.OffTicket;
import Model.Discount.Sale;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Good;
//import View.Requests.ManagerRequest;

import java.util.ArrayList;

public class Manager extends Account{
    private static ArrayList<OffTicket> offTicketList = new ArrayList<>();
    private static  ArrayList<Account> accountList = new ArrayList<>();
    private static ArrayList<Category> categoryList = new ArrayList<>();
    private static ArrayList<Good> allGoodsList = new ArrayList<>();

    private static ArrayList<Application> applications = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();
    private static ArrayList<Sale> allSales = new ArrayList<>();


    public Manager(AccountInformation accountInformation, Role role) {
        super(accountInformation, Role.MANAGER);
        allManagers.add(this);
        accountList.add(this);
    }

    public static ArrayList<Manager> getAllManagers() {
        return allManagers;
    }

    public static Account getAccountByUsername(String username) throws Exception{
        for (Account account : accountList) {
            if (account.getAccountInformation().getUsername().equals(username)){
                return account;
            }
        }
        throw new Exception("user not found with this username");
    }

    public static Good getGoodByName(String productName) throws Exception{
        for (Good good : allGoodsList) {
            if(good.getProductName().equals(productName)){
                return good;
            }
        }
        throw new Exception("no good exists with this name");
    }

    public static ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public static Good getGoodById(String productId) throws Exception{
        for (Good good : allGoodsList) {
            if ( good.getProductId().equalsIgnoreCase(productId) ){
                return good;
            }
        }
        throw new Exception("product not found");
    }

    public Account getSellerById(String sellerUsername) throws Exception{
        for (Account account : accountList) {
            if( account.getAccountInformation().getUsername().equals(sellerUsername) && account instanceof Seller )
                return account;
        }
        throw new Exception("seller not found");
    }

    public OffTicket getOffTicketById( String offTicketId ){
        for (OffTicket offTicket : offTicketList) {
            if ( offTicket.getOffTicketId().equalsIgnoreCase(offTicketId) )
                return offTicket;
        }
        //TODO throw exception instead of down return statement
        return null;
    }

    public static Category getCategoryByName(String categoryName) throws Exception{
        for (Category category : categoryList) {
            if(category.getCategoryName().equals(categoryName)){
                return category;
            }
            for (Category subCategory : category.getSubCategories()) {
                if(subCategory.getCategoryName().equals(categoryName)){
                    return subCategory;
                }
            }
        }
        throw new Exception("category not found");
    }

    public static Category getCategoryOfSubCategoryByName(String name){
        for (Category category : categoryList) {
            if ( category.getSubCategories().size() != 0) {
                for (Category subCategory : category.getSubCategories()) {
                    if (subCategory.getCategoryName().equals(name)) {
                        return category;
                    }
                }
            }
        }
        return null;
    }

    public static void addSale(Sale sale){
        allSales.add(sale);
    }

    public static Sale getSaleByID(String idAsString) throws Exception{
        int id;
        try {
            id = Integer.parseInt(idAsString);
        }
        catch (Exception e){
            throw new Exception("id has invalid format");
        }
        for (Sale sale : allSales) {
            if(sale.getSaleId() == id){
                return sale;
            }
        }
        throw new Exception("sale not fount with this id");
    }

    public static void addApplication( Application application ){
        applications.add( application );
    }

    public static boolean categoryExists(String categoryName){
        for (Category category : categoryList) {
            if ( category.getCategoryName().equals(categoryName) ){
                return true;
            }
        }
        return false;
    }

    /*public static void creatCategory(String categoryName, ArrayList<Characteristic> characteristics , ArrayList<Good> goodsInCategory){
        categoryList.add( new Category(categoryName , characteristics , goodsInCategory) );
    }*/

    public static boolean goodExistsInCategory(Category category , Good good) throws Exception{
        if(category.getGoodsInCategory().contains(good)){
            return true;
        }
        throw new Exception("good does not exist in parent category");
    }

}

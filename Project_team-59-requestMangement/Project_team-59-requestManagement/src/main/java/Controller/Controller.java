package Controller;

import Model.Account.*;
import Model.Application.ApplicationType;
import Model.Application.CreatAccountApplication;
import Model.Discount.OffTicket;
import Model.Good.Characteristic;
import View.Menus.BuyerView;
import View.Menus.LogInView;
import View.Menus.Menu
import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private static Manager coreManager;
    private static Account currentAccount;
    private static User currentUser;
    private boolean filteringIsPossible;//not sure
    private boolean filterByCategoryIsAble ;
    private boolean filterByCharacteristicIsAble ;
    private boolean filterByNameIsable;
    private boolean filterByPriceIsable;
    private boolean filterByExistence ;
    private int lowPrice , int highPrice ;
    private String filteredName ;
    private Category filteredCategory ;
    private Characteristic filteredCharacteristic ;    
    private ArrayList<Good> filteredGoods;
    private boolean hasDigestHappened;//not sure

    public Controller() {
    }

    public static void initializer(){
        //TODO initializes core manager
        //TODO sets up current user
    }

    public static void creatAdmin( AccountInformation accountInformation ){
        coreManager = new Manager( accountInformation , Role.MANAGER );
        currentUser = new User();
    }

    public static void purchase() throws Exception{
        if(currentAccount == null){
            throw new Exception("you must log in first in order to purchase");
        }
        if(!(currentAccount instanceof Buyer)){
            throw new Exception("only buyer accounts can purchase");
        }
        else {
            new BuyerView(null, (Buyer) currentAccount);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void sendCreatAccountApplication(String userName , String name , String lastName , String email , String phoneNumber , String password , Role role ){
        Manager.addApplication(new CreatAccountApplication( ApplicationType.CREAT_ACCOUNT , userName, name, lastName, email, phoneNumber, password, role ));
    }

    public static double useOffTicket(String offTicketId , Buyer buyer)throws Exception{
        OffTicket offTicket;
        for (OffTicket temporaryOffTicket : buyer.getOffTickets()) {
            if(temporaryOffTicket.getOffTicketId().equals(offTicketId)){
                offTicket = temporaryOffTicket;
                return useOffThicketIfPossible(buyer , offTicket);
            }
            else{
                throw new Exception("you dont owe such off ticket");
            }
        }
        return buyer.getCartValue();
    }

    public static double useOffThicketIfPossible(Buyer buyer , OffTicket offTicket) throws Exception{
        if(offTicket.getEndingDate().compareTo(new Date()) < 0){
            throw new Exception("off ticket is expired");
        }
        else if(offTicket.getTimesCanBeUsed() < 1){
            throw new Exception("no more usages are available for this off ticket");
        }
        else {
            offTicket.setTimesCanBeUsed(offTicket.getTimesCanBeUsed()-1);
            double totalValue = (1 - (offTicket.getOffAmount() / 100)) * buyer.getCartValue();
            if(offTicket.getOffAmount() >= (offTicket.getOffAmount() / 100) * buyer.getCartValue()){
                return totalValue;
            }
            return buyer.getCartValue()-offTicket.getOffAmount();
        }
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }
    public static Account getCurrentAccount() {
       return currentAccount ;
    }
    

    public static boolean usernameExists(String username){
        return true;
    }

    public static void terminator(){
        //TODO terminates program by saving all data
    }

    public void goToOffsPage(){
        //TODO opens offs page
    }

    public void goToProductPage(){
        //TODO opens products page
    }

    public void showCategories(){
        //TODO prints categories
    }

    public void showProduct( String productId ){
    	 ArrayList<Good> GoodsToShow =Manager.getAllGoodsList () ;
    	if ( filterByCategoryIsAble ) {
    		for (Good good : GoodsToShow) {
    			if (!(good.getCategory.equals(filteredCategory)))
     				GoodsToShow.remove (good) ;
    		}
    	}
   		
    	 if (filterByCharacteristicIsAble )   {
    		 for (Good good : GoodsToShow) {
    			 ArrayList<Characteristic> characteristics =good.getCharacteristics() ;
    			 for (Characteristic characteristic : characteristics) {
    				 if (!(characteristic.equals(filteredCharacteristic)))
    				 { GoodsToShow.remove (good) ;
    				   break ;
    				 }
    			 }
     		}
    	 }
   		 
    	 if (filterByNameIsable) {
    		 for (Good good : GoodsToShow) {
     			if(!(good.getProductName()startsWith(filteredName)))
     				GoodsToShow.remove (good) ;
     		}
    	 }
   		
    	 if (filterByPriceIsable) {
    		 for (Good good : GoodsToShow) {
     			if (!(good.getPrice <= highPrice && good.getPrice >= lowPrice))
     				GoodsToShow.remove (good) ;
     		}
    	 }
   		
    	 if (filterByExistence ) {
    		 for (Good good : GoodsToShow) {
     			if (!(good.isAvailable())) {
     				GoodsToShow.remove (good) ;
     			}
     		}
    	 }
        //TODO opens products page and increase goods times visited
    }

    public void showCurrentFilters(){
        //TODO prints current filters
    	 if ( filterByCategoryIsAble )
    		 System.out.println("Filtered by category");
    	 if (filterByCharacteristicIsAble )   
    		 System.out.println("Filtered by characteristic");
    	 if (filterByNameIsable)
    		 System.out.println("Filtered by name");
    	 if (filterByPriceIsable)
    		 System.out.println("Filtered by price");
    	 if (filterByExistence)
    		 System.out.println("Filtered by existence");
    }

    public void callAppropriatefilteringFunction(String input){
    	String type = input.toLowerCase() ;
    	if (type.equals("characteristic")) {
    		System.out.println("please enter characteristic name") ;
    		input = Menu.getInputFromUser();
    		filterByCharacteristic(input.toLowerCase());
    	}
    	else if (type.equals("category")) {
    		filterByCategory () ;
    	}
         else if (type.equals("price")) {
        	 System.out.println("please enter price range") ;
     		input = Menu.getInputFromUser();
     	    String input2 =Menu.getInputFromUser();
     		filterByPrice(Integer.parseInt(input ), Integer.parseInt(input1 ));
    		
    	}
         else if (type.equals("existence")) {
        	 filterByExistence = true ;
     	}
         else if (type.equals("name")) {
        	 System.out.println("please enter the name") ;
     		input = Menu.getInputFromUser();
     		filterByName(input.toLowerCase());
     		
     	}
        //TODO ?
    }

    public void showAvailableFilters(){
    	System.out.println("Filter by :");
    	System.out.println("characteristic");
    	System.out.println("category");
    	System.out.println("name");
    	System.out.println("price");
    	System.out.println("existence");
        //TODO prints possible filters
    }

    public void filterByCharacteristic(String characteristicName){
    	filterByCharacteristicIsAble = true ;
        filteredCharacteristic = Characteristic.getCharacteristicByName(characteristicName) ;
        //TODO filters by characteristic and adds it to filters
    }

    public void filterByName (String name)
    {
    	filterByNameIsable = true ;
    	filteredName = name ;
    }

    public void filterByCategory(Category category)
    {
    	System.out.println ("Please enter category's name") ;
    	String name = Menu.getInputFromUser().trim().toLoweCase() ;
    	Category category =Manager.getCategoryByname (name) ;
    	filterByCategoryIsAble = true ;
    	filteredCategory = category ;    	
    }

    public void filterByPrice(int low , int high) 
    {
    	filterByPriceIsable = true ;	
    	highPrice = high ;
    	lowPrice = low ;
    }

   
    public void disableFilter( ){
    	System.out.println("enter the field to disable filter") ;
    	 String input;
         do{
             input = Menu.getInputFromUser().toLowerCase();
             if (input.equals("price"))
            	 filterByPriceIsable = false ;
             if (input.equals("category"))   
            	 filterByCategoryIsAble = false ;
             if (input.equals("exsitence"))
            	 filterByExistence= false ;
             if (input.equals("name"))
            	 filterByNameIsable = false ;
             if (input.equals("characteristic"))
            	 filterByCharacteristicIsAble = false ;

         }while(!input.trim().equalsIgnoreCase("back"));
        //TODO disables filter
    }

    public void showCurrentSorts(){
        //TODO prints currentSorts
    }

    public void sorting(){
        //TODO ?
    }

    public void showAvailableSorts(){
        //TODO prints possible sorts
    }

    public void sorter(Characteristic characteristic){
        //TODO sorts by characteristic
    }

    public void disableSort( Characteristic characteristic ){
        //TODO disables sort and resets it to "times visited"
    }

    public void showProducts( ArrayList<Characteristic> filters , Characteristic sort ){
        //TODO filters then sorts then prints products
    }

    public void viewProduct( String productId ){
        //TODO show product
    }
}

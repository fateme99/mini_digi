package View.Menus;

import Model.Account.Buyer;
import View.Requests.BuyerRequest;
import View.Requests.UserRequest;
import Controller.Controller;
import Controller.BuyerController;

public class BuyerView extends Menu {
    private BuyerRequest buyerRequest;
    private UserRequest userRequest;
    private Buyer buyer;
    private BuyerController buyerController ;


    public BuyerView( Menu menu , Buyer buyer ) {
        this.headMenu = menu;
        this.buyerController = new BuyerController (Controller.getCurrentAccount(),buyer) ;
        this.buyer = buyer;
    }

    public void run(){
        String input;
        do{
            input = Menu.getInputFromUser().trim();
            getRequestType(input.toLowerCase());
            callAppropriateFunction();
        }while(!input.toLowerCase().equals("back"));
    }

    private void getRequestType(String command){
        if(command.equals("view cart")){
            buyerRequest = BuyerRequest.VIEW_CART;
        }
        else if(command.equals("view balance")){
            buyerRequest = BuyerRequest.VIEW_BALANCE;
        }
        else if(command.equals("view discount codes")){
            buyerRequest = BuyerRequest.VIEW_DISCOUNT_CODES;
        }

        else if(command.equals("view orders")){
            buyerRequest = BuyerRequest.VIEW_ORDERS;
        }
        
        
        
       
    }
    private void viewOrders () {
    	 String input;
         do{
             command = Menu.getInputFromUser().trim();
             getOrdersRequestType(input.toLowerCase());
             callAppropriateOrdersFunction(input.toLowerCase());
         }while(!input.toLowerCase().equals("back"));
         
    	
    }
    private void getOrdersRequestType (String command)
    {
    	 if(command.startsWith("show order"){
             buyerRequest = BuyerRequest.SHOW_ORDER;
         }
    	 else if(command.startsWith("rate"){
             buyerRequest = BuyerRequest.RATE_PRODUCT;
         }
    	
    	
    }
    private void callAppropriateOrdersFunction (String input) {
    	 String[] inputSplit = input.split(" ");
    	 if(buyerRequest.equals(BuyerRequest.RATE_PRODUCT)){
    		 buyerController.rateProduct(inputsplit [1] , inputsplit [2]) ;
         }
    	 else if(buyerRequest.equals(BuyerRequest.RATE_PRODUCT)){
    		 buyerController.viewOrder(inputsplit [1] ) ;
         }
    }

    private void callAppropriateFunction(){
        if(buyerRequest.equals(BuyerRequest.VIEW_CART)){
            viewCart();
        }
        else if(buyerRequest.equals(BuyerRequest.VIEW_BALANCE)){
            viewDiscountCodes () ;
        }
        else if(buyerRequest.equals(BuyerRequest.VIEW_DISCOUNT_CODES)){
            viewBalance () ;
        }
        else if(buyerRequest.equals(BuyerRequest.VIEW_ORDERS)){
        	buyerController.viewOrders () ;
            viewOrders( () ;
        }
        
        
        
    }
    private void viewBalance(){

    	System.out.println (Controller.getCurrentAccount().getBalance () ) ;
    }
    private void viewDiscountCodes() {
    	Account account = Controller.getCurrentAccount () ;
    	ArrayList<OffTickets> offTickets = account.getOffTickets() ;
    	for (OffTicket offTicket : offTickets) {
    		System.out.println(offTicket.getOffTicketId) ;
    	}
    }

    private void viewCart(){
        String input;
        do{
            input = Menu.getInputFromUser();
            getViewCartRequest(input.toLowerCase());
            callAppropriateViewCartFunction();
        }while(!input.equals("back"));
    }

    private void getViewCartRequest(String command){
        if(command.equals("purchase")){
            buyerRequest = BuyerRequest.PURCHASE;
        }
    }

    private void callAppropriateViewCartFunction(){
        if(buyerRequest.equals(BuyerRequest.PURCHASE)){
            purchase();
        }
    }

    private void purchase(){
        new InformationReceiver(this).run(buyer);
    }

    private void help(){
        //TODO
    }
}

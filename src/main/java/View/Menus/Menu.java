package View.Menus;

import java.util.Scanner;
import Controller.Controller;
import Model.Account.Account;
import View.Requests.UserRequest;

import static Controller.Controller.*;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    protected Menu headMenu;
    private String input;
    private static UserRequest userRequest1 ;
    public Menu() {
    }

    public void run(){

    }

    public static String getInputFromUser(){
         String input = scanner.nextLine().trim();
        getRequestType (input.trim ().toLowerCase()) ;
        callAppropriateFunction( input.trim ().toLowerCase()) ;
        return input ;
        
    }
    
    private static void callAppropriateFunction( String input ){
        if ( userRequest1.equals(UserRequest.LOG_OFF) ){
            logOff () ;
        }
        if ( userRequest1.equals(UserRequest.VIEW_ACCOUNT_INFO) ){
            viewPersonalInfo (getCurrentAccount ()) ;
        }
        if ( userRequest1.equals(UserRequest.EDIT_ACCOUNT_INFO) ){
        	String [] splitInput = input.split(" ") ;
            editPersonalInfo (splitInput [1].toLowerCase()) ;
        }
      
    }
    private static void logOff ()
    {
    	setCurrentAccount(null) ;
    }
    private static void viewPersonalInfo (Account account )
    {
    	getCurrentAccount ().getAccountInformation ().printAccountInformation () ;
    }
    
    private static void editPersonalInfo (String field)  {
    	if (field.equals("username"))
    		System.out.println("you cannot change your username") ;
    	else if (field.equals("name") || field.equals("lastname") || field.equals("password") || field.equals("email")|| field.equals("phonenumber") || field.equals("phone") ) ;
    	{
    		System.out.println ("Please enter your new" + field ) ;
    		String input = Menu.getInputFromUser() ;
    		if (field.equals("name") )
    			getCurrentAccount ().getAccountInformation().setName (input) ;
    		if (field.equals("lastname") )
    			getCurrentAccount ().getAccountInformation ().setLastName (input) ;
    		if (field.equals("password") )
    			getCurrentAccount ().getAccountInformation().setPassWord (input) ;
    		if (field.equals("email") )
    			getCurrentAccount ().getAccountInformation ().setEmail (input) ;
    		if (field.equals("phone") || field.equals("phonenumber"))
    			getCurrentAccount ().getAccountInformation().setPhoneNumber (input) ;
    	}
    	
    	System.out.println ("The field does not exist") ;
    	
    }
    private static void getRequestType( String input ){
        String command = input.toLowerCase().trim();
        if ( command.startsWith("log off")){
            userRequest1 = UserRequest.LOG_OFF;
        }
        else if ( command.startsWith("view personal info")){
            userRequest1 = UserRequest.VIEW_ACCOUNT_INFO;
        }
        else if ( command.startsWith("edit ")){
            userRequest1 = UserRequest.EDIT_ACCOUNT_INFO;
        }
    }
}

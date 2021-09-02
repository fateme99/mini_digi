package View.Menus;

import java.util.Scanner;
import Controller.Controller;
public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    protected Menu headMenu;
    private String input;
    private static userRequest userRequest1 ;
    public Menu() {
    }

    public void run(){

    }

    public static String getInputFromUser(){
         input = scanner.nextLine().trim();
        getRequestType (input.trim ().toLowerCase()) ;
        
    }
    
    private void callAppropriateFunction( String input ){
        if ( userRequest1.equals(UserRequest.LOG_OFF) ){
            logOff () ;
        }
        if ( userRequest1.equals(UserRequest.VIEW_PERSONAL_INFO) ){
            viewPersonalInfo (Controller.getCurrentAccount ()) ;
        }
        if ( userRequest1.equals(UserRequest.EDIT_PERSONAL_INFO) ){
        	String [] splitInput = input.split(" ") ;
            editPersonalInfo (splitInput [1].toLowerCase()) ;
        }
      
    }
    private static void logOff ()
    {
    	Controller.setCurrentAccount(null) ;
    }
    private static void viewPersonalInfo (Account account )
    {
    	Controller.getCurrentAccount ().getAccountInformationcountInformation ().printAccountInformation () ;
    }
    
    private static void editPersonalInfo (String field)  {
    	if (field.equals("username"))
    		System.out.println("you cannot change your username") ;
    	else if (field.equals("name") || field.equals("lastname") || field.equals("password") || field.equals("email")|| field.equals("phonenumber") || field.equals("phone") ) ;
    	{
    		System.out.println ("Please enter your new" + field ) ;
    		input = Menu.getInputFromUser() ;
    		if (field.equals("name") )
    			Controller.getCurrentAccount ().getAccountInformationcountInformation ().setName (input) ;
    		if (field.equals("lastname") )
    			Controller.getCurrentAccount ().getAccountInformationcountInformation ().setLastName (input) ;
    		if (field.equals("password") )
    			Controller.getCurrentAccount ().getAccountInformationcountInformation ().setPassWord (input) ;
    		if (field.equals("email") )
    			Controller.getCurrentAccount ().getAccountInformationcountInformation ().setEmail (input) ;
    		if (field.equals("phone") || field.equals("phonenumber"))
    			Controller.getCurrentAccount ().getAccountInformationcountInformation ().setPhoneNumber (input) ;
    	}
    	else 
    	System.out.println ("The field does not exist") ;
    	
    }
    private void getRequestType( String input ){
        String command = input.toLowerCase().trim();
        if ( command.startsWith("log off")){
            userRequest1 = UserRequest.LOG_OFF;
        }
        else if ( command.startsWith("view personal info")){
            userRequest1 = UserRequest.VIEW_PERSONAL_INFO;
        }
        else if ( command.startsWith("edit ")){
            userRequest1 = UserRequest.EDIT_PERSONAL_INFO;
        }
    }
}

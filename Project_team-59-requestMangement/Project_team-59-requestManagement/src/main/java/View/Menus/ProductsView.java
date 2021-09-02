package View.Menus;

import Controller.Controller;
import View.Requests.UserRequest;

public class ProductsView extends Menu{
    private UserRequest userRequest;
    private Controller controller ;
    public ProductsView(Menu menu) {
        this.headMenu = menu;
    }

    public void run(){
        String input;
        do{
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callAppropriateUserFunction(input);

        }while(!input.trim().equalsIgnoreCase("back"));
    }

    private void callAppropriateUserFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
       else if ( userRequest.equals(UserRequest.LOG_IN) ){
            new LogInView(this , true).run(input.split(" "));
        }
       else if ( userRequest.equals(UserRequest.PURCHASE)){
            purchase();
        }
       else if ( userRequest.equals(UserRequest.FILTERING)){
           filtering();
       }
    }
    private void filtering(){
    	  String input;
          do{
              input = Menu.getInputFromUser();
              getFilteringRequestType(input.trim().toLowerCase());
              callAppropriateFilteringFunction(input);

          }while(!input.trim().equalsIgnoreCase("back"));
    }
    private void getFilteringRequestType (String command)
    {
    	if ( command.startsWith("show availabe filters")){
            userRequest = UserRequest.SHOW_AVAILABLE_FILTERS;
        }
    	
    	else if ( command.startsWith("current filters")){
            userRequest = UserRequest.SHOW_CURRENT_FILTERS;
        }
    	else if ( command.startsWith("filter")){
            userRequest = UserRequest.FILTER;
        }
    	else if ( command.startsWith("disable filter")){
            userRequest = UserRequest.DISABLE_FILTER;
        }

    	
    	
    }
    private void callAppropriateFilteringFunction(String input)
    {
    	ArrayList <String> splitedInput =input.split(" ") ;
    	 if ( userRequest.equals(UserRequest.SHOW_AVAILABLE_FILTERS)){
             controller.showAvailableFilters() ;
         }
    	 else if ( userRequest.equals(UserRequest.SHOW_CURRENT_FILTERS)){
             controller.showCurrentFilters() ;
         }
    	 else if ( userRequest.equals(UserRequest.FILTER)){
             controller.filteringType(splitedInput [2]) ;
         }
    	 else if ( userRequest.equals(UserRequest.DISABLE_FILTERS)){
             controller.disableFilters() ;
         }
    	 
    	 
    	 
    	
    }
    Private

    private void purchase(){
        try {
            Controller.purchase();
        }
        catch ( Exception e ){
            System.out.println(e.getMessage());
            new LogInView(this , true);
        }
    }

    private void getRequestType( String input ){
        String command = input.toLowerCase().trim();
        if ( command.startsWith("creat account")){
            userRequest = UserRequest.CREAT_ACCOUNT;
        }
        else if ( command.startsWith("login")){
            userRequest = UserRequest.LOG_IN;
        }
        else if ( command.equals("purchase")){
            userRequest = UserRequest.PURCHASE;
        }
        else if ( command.equals("filtering")){
            userRequest = UserRequest.FILTERING;
        }
        
    }

    private void help(){
        //TODO
    }
}

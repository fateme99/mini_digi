package View.Menus;

import View.Requests.BuyerRequest;
import View.Requests.UserRequest;

public class ProductMenu extends Menu{
    private BuyerRequest buyerRequest;
    private UserRequest userRequest;

    public ProductMenu(Menu menu) {
        this.headMenu = menu;
    }

    public void run(){
        String input;
        while(true){
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            if( userRequest != null ) {
                callAppropriateUserFunction(input);
            }
            else{
                //TODO
            }
        }
    }

    private void callAppropriateUserFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
        if ( userRequest.equals(UserRequest.LOG_IN) ){
            //erordare
            //new LogInView(this).run(input.split(" "));
        }
    }

    private void callAppropriateBuyerFunction(){
        //TODO
    }

    private void getRequestType( String input ){
        String command = input.toLowerCase().trim();
        if ( command.startsWith("creat account")){
            userRequest = UserRequest.CREAT_ACCOUNT;
        }
        else if ( command.startsWith("login")){
            userRequest = UserRequest.LOG_IN;
        }
    }


    private void help(){
        //TODO
    }
}

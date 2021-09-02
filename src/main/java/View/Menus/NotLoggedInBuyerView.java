package View.Menus;

import View.Requests.NotLoggedInBuyerRequest;
import View.Requests.UserRequest;

public class NotLoggedInBuyerView extends Menu{
    private NotLoggedInBuyerRequest notLoggedInBuyerRequest;
    private UserRequest userRequest;


    public NotLoggedInBuyerView( Menu menu ) {
        this.headMenu = menu;
    }

    public void run(){
        String input;
        while(true){
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callAppropriateFunction( input );
        }
    }

    private void callAppropriateFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
        if ( userRequest.equals(UserRequest.LOG_IN) ){
            //eror dare
           // new LogInView(this).run(input.split(" "));
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
    }

    private void getRequestType(){
        //TODO
    }

    private void help(){
        //TODO
    }
}

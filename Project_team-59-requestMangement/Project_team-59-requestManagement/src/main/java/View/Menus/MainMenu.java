package View.Menus;

import Controller.Controller;
import View.Requests.UserRequest;

import java.util.ArrayList;

public class MainMenu extends Menu{
    private UserRequest userRequest;
    Controller controller;

    public MainMenu( Controller controller ) {
        this.controller = controller;
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
            new LogInView(this).run(input.split(" "));
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

    private void help(){
        //TODO
    }
}

package Main;

import Controller.Controller;
import Model.Account.AccountInformation;
import Model.Account.Buyer;
import Model.Account.Role;
import Model.Discount.OffTicket;
import Model.Good.Good;
import View.GUIMenu.*;
import View.Menus.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {

        HashMap<Good, Integer> cart = new HashMap<>();
        cart.put(new Good("",null,20.0, "bnana",null,null,null), 5);
        cart.put(new Good(null,null,20.0, "orange",null,null,null), 1);
        cart.put(new Good(null,null,15.0, "sad",null,null,null), 10);
        cart.put(new Good(null,null,0.9, "asdasd",null,null,null), 12);

        Buyer buyer = new Buyer(new AccountInformation("iman", "iman", "alipour", "asdasd@asd.ads", "2131231", "a"), Role.BUYER);
        buyer.addItemsToCart(cart);

        ArrayList<String> bu = new ArrayList<>();
        bu.add("iman");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        buyer.getOffTickets().add(new OffTicket(dateFormat.format(new Date()), dateFormat.format(new Date()), 10, 20, 2, bu));
        buyer.getOffTickets().add(new OffTicket(dateFormat.format(new Date()), dateFormat.format(new Date()), 10, 20, 2, bu));
        buyer.getOffTickets().add(new OffTicket(dateFormat.format(new Date()), dateFormat.format(new Date()), 10, 20, 2, bu));


        new LoginAndSignUp().display();

    }

    public static void startProgram(Stage window){
        Controller controller = new Controller();
          if ( !new File("database.ifs").exists() ){
            new RegistraitionMenuGUI(true); // todo convert into main menu
        }
        else{
            Controller.initializer();
        }
    }
}

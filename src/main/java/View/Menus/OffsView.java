package View.Menus;

import Controller.Controller;
import Model.Good.Category;
import Model.Good.Good;
import View.Requests.ConsoleCommandOff;
import View.Requests.UserRequest;

import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;

public class OffsView extends Menu{
    private UserRequest userRequest;

    public OffsView(Menu menu) {
        this.headMenu = menu;
    }
    String command;
    private static final Controller controller = Controller.getInstance();
    public void run(){

        while (true){
            System.out.println("**** Off view ****");
            Scanner scanner=new Scanner(System.in);
            command=scanner.nextLine().trim();
            if (ConsoleCommandOff.BACK.getStringMatcher(command).matches()){
                break;
            }
            else if (ConsoleCommandOff.HELP.getStringMatcher(command).matches()){
                System.out.println("for back use : back");
                System.out.println("for see offs use : offs");
                System.out.println("for see product use : show product productId");
            }
            else if (ConsoleCommandOff.OFFS.getStringMatcher(command).matches()){
                printoffs();
            }
            else if (ConsoleCommandOff.SHOW_PRODUCT.getStringMatcher(command).matches()){
                Matcher matcher=ConsoleCommandOff.SHOW_PRODUCT.getStringMatcher(command);

                if (matcher.find()){
                    String goodId=matcher.group(1);
                    Good good=controller.getGood(goodId);
                    if (good==null)
                        System.out.println("INVALID ID");
                    else {
                        new ProductView(new Menu(),good);
                    }

                }
            }
            else{
                System.out.println("In valid command ->  help  <-");
            }
        }
        /*String input;
        while(true){
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callAppropriateFunction( input );
        }*/
    }
    public void printoffs(){
        Collection<Good>goods=controller.getgoods();
        System.out.print("+-----+-----------+-----------------------+-----------------+-------------------+\n");
        System.out.print("| id  |   Name    |   price without off   |  price with off |    off details    |\n");
        System.out.print("+-----+-----------+-----------------------+-----------------+-------------------+\n");
        for (Good good:goods) {
            if (good.getOffpercent()!=0){
                double newPrice=good.getPrice()-(good.getPrice()*good.getOffpercent());
                System.out.format("| %-3s |%-10s |  %-20.2f | %-15.2f |  %-16s |\n", good.getProductId(),good.getProductName(),good.getPrice(),newPrice,"null" );
                System.out.println("+-----+-----------+-----------------------+-----------------+-------------------+");
            }

        }


    }
    public void printProducts(){
        Collection<Good>goods=controller.getgoods();
        System.out.print("+--------------+\n");
        System.out.print("|  good name   |\n");
        System.out.print("+--------------+\n");

        for (Good good : goods) {
            System.out.format("| %-12s |\n", good.getProductName());
            System.out.println("+--------------+");
        }


    }

    /*private void callAppropriateFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
        if ( userRequest.equals(UserRequest.LOG_IN) ){
            //eror dare
            //new LogInView(this).run(input.split(" "));
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
    }*/
}

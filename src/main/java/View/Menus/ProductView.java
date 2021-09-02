package View.Menus;

import Controller.Controller;
import Model.Account.Seller;
import Model.Good.Comment;
import Model.Good.Good;
import View.Requests.ConsoleCommandProduct;
import View.Requests.UserRequest;

import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProductView extends Menu {
    private static final Controller controller = Controller.getInstance();
    private UserRequest userRequest;
    private Good product;

    public ProductView(Menu menu, Good good) {
        this.headMenu = menu;
        this.product = good;
        run();
    }

    String command;

    public void run() {
        while (true) {
            System.out.println("**** product view ****");
            Scanner scanner=new Scanner(System.in);
            command = scanner.nextLine().trim();
            if (ConsoleCommandProduct.BACK.getStringMatcher(command).matches()) {
                break;
            } else if (ConsoleCommandProduct.DIGEST.getStringMatcher(command).matches()) {
                show_digest();
                System.out.println("ENTER COAMMAND : ");
                while (true) {
                    String subcommand = scanner.nextLine();
                    if (ConsoleCommandProduct.BACK.getStringMatcher(subcommand).matches())
                        break;
                    else if (ConsoleCommandProduct.ADD_TO_CARD.getStringMatcher(subcommand).matches()) {
                        //if (controller.getCurrentAccount().isLogin()) {
                            addProductTocart(product,getCurrentUsername());
                            new PaymentMenu(new Menu());
                        //} else {
                            System.out.println("PLEASE LOGIN");
                            // inja mishe bere safhe login
                        //}
                    }
                    else if (ConsoleCommandProduct.SELECT_SELLER.getStringMatcher(subcommand).matches()) {

                    }
                    else if (ConsoleCommandProduct.HELP.getStringMatcher(subcommand).matches()) {
                        System.out.println("for back use : back");
                        System.out.println("for buy product use : add to card");
                        System.out.println("for select seller use: select seller [seller_username] ");
                    }
                    else {
                        System.out.println("INVALID COMMAND   ->   HELP   <-");
                    }
                }
            }
            else if (ConsoleCommandProduct.ATTRIBUTES.getStringMatcher(command).matches()) {
                printCompleteProduct();

            }
            else if (ConsoleCommandProduct.COMPARE_PRODUCT.getStringMatcher(command).matches()) {
                Matcher matcher=ConsoleCommandProduct.COMPARE_PRODUCT.getStringMatcher(command);
                if (matcher.find()){
                    String id=matcher.group(1);
                    compare(id);
                }

            } else if (ConsoleCommandProduct.COMMENTS.getStringMatcher(command).matches()) {
                printComments();
                System.out.println("DO YOU WANT TO ADD COMMENT : add comment");
                String input=scanner.nextLine();
                if (ConsoleCommandProduct.ADD_COMMENTS.getStringMatcher(input).matches()){
                    addComment();
                }



            } else if (ConsoleCommandProduct.HELP.getStringMatcher(command).matches()) {
                System.out.println("for back use : back");
                System.out.println("for see a little information about product use : digest");
                System.out.println("for see complete information about product : attributes");
                System.out.println("for compare two product use : compare [productID]");
                System.out.println("for see comments about product use : comments");
            } else {
                System.out.println("In valid command ->  help  <-");
            }
        }
    }

    public void show_digest() {

        System.out.print("+----------+-------------+--------------+--------------+----------------------------------------------------------------+\n");
        System.out.print("|  price   | off percent |   category   | average rate |                            explanation                         | \n");
        System.out.print("+----------+-------------+--------------+--------------+----------------------------------------------------------------+\n");
        System.out.format("| %-8.1f |%-12.2f |  %-11s | %-12.2f | %-62s |\n", product.getPrice(), product.getOffpercent(), product.getCategory(), product.getAverageRate(), product.getCharacteristics());
        System.out.println("+----------+-------------+--------------+--------------+----------------------------------------------------------------+");
        printSeller();
    }
    public void printCompleteProduct(){
        System.out.print("+----------+-------------+--------------+--------------+----------------+-------------------------------------------+\n");
        System.out.print("|  price   | off percent |   category   | average rate |  product state |              explanation                  | \n");
        System.out.print("+----------+-------------+--------------+--------------+----------------+-------------------------------------------+\n");
        System.out.format("| %-8.1f |%-12.2f |  %-11s | %-12.2f | %-14s |  %-40s |\n", product.getPrice(), product.getOffpercent(), product.getCategory(), product.getAverageRate(), product.getProductState().toString(),product.getCharacteristics());
        System.out.println("+----------+-------------+--------------+--------------+----------------+-------------------------------------------+");
        printSeller();
        printComments();
    }
    public void printSeller() {
        Collection<Seller> sellers = product.getSellers();
        System.out.println("+-----------------------+");
        System.out.print("|  seller username      |\n");
        System.out.println("+-----------------------+");
        if (sellers == null) {

        } else {
            for (Seller seller : sellers) {

                if (seller==null){

                }
                else {
                    System.out.format("|  %-21s |\n", seller.getAccountInformation().getUsername());
                    System.out.print("+-----------------------+");
                }



            }
        }

    }
    public void addProductTocart(Good product , String username){
        controller.getUser(username).addProduct(product);
    }
    public String getCurrentUsername(){
        return controller.getCurrentAccount().getAccountInformation().getUsername();
    }
    public void printComments(){
        Collection<Comment>comments=controller.getcomments();
        System.out.println("+--------------+---------------------------------------------------------+");
        System.out.println("|    title     |                          content                        |");
        System.out.println("+--------------+---------------------------------------------------------+");
        if (comments==null){

        }
        else {
            for (Comment comment:comments) {
                if (comment==null){

                }
                else {

                    System.out.format("| %-12s | %-55s | \n" , comment.getTitle(),comment.getContent());
                    System.out.println("+--------------+---------------------------------------------------------+");
                }


            }
        }

    }
    public void compare(String id){
        product=controller.getGood(product.getProductId());
        Good good=controller.getGood(id);
        System.out.print("+----------+----------+-------------+--------------+--------------+----------------------------------------------------------------+\n");
        System.out.print("|   name   |  price   | off percent |   category   | average rate |                            explanation                         | \n");
        System.out.print("+----------+----------+-------------+--------------+--------------+----------------------------------------------------------------+\n");
        System.out.format("| %-8s | %-8.1f |%-12.2f |  %-11s | %-12.2f | %-62s |\n",product.getProductName(), product.getPrice(), product.getOffpercent(), product.getCategory(), product.getAverageRate(), product.getCharacteristics());
        System.out.println("+----------+----------+-------------+--------------+--------------+----------------------------------------------------------------+");
        System.out.format("| %-8s | %-8.1f |%-12.2f |  %-11s | %-12.2f | %-62s |\n",good.getProductName(), good.getPrice(), good.getOffpercent(), good.getCategory(), good.getAverageRate(), good.getCharacteristics());
        System.out.println("+----------+----------+-------------+--------------+--------------+----------------------------------------------------------------+");
    }
    public void addComment(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Title : ");
        String title=scanner.nextLine();
        System.out.println("Content : ");
        String content=scanner.nextLine();
        Comment comment=new Comment(title,content);
        controller.addComment(comment,product);
        System.out.println("COMMENT ADDED");

    }
}

package View.Menus;

import Controller.Controller;
import Model.Good.Category;
import Model.Good.Good;

import View.Requests.ConsoleCommandForProducts;
import View.Requests.UserRequest;

import java.util.*;
import java.util.regex.Matcher;

public class ProductsView extends Menu {
    private static final Controller controller = Controller.getInstance();
    private UserRequest userRequest;
    private String filter;
    private int i = 0;
    private Scanner scanner ;
    private String sort;

    public ProductsView(Menu menu) {
        this.headMenu = menu;
    }

    String command;

    public void run() {


        while (true) {
            System.out.println("**** products view ****");
            command = scanner.nextLine().trim();
            if (ConsoleCommandForProducts.BACK.getStringMatcher(command).matches()) {
                break;
            } else if (ConsoleCommandForProducts.PRODUCTS.getStringMatcher(command).matches()) {
                printProducts();
            } else if (ConsoleCommandForProducts.VIEW_CATEGORIES.getStringMatcher(command).matches()) {
                printCategories();
            } else if (ConsoleCommandForProducts.FILTERING.getStringMatcher(command).matches()) {

                String subcommand;
                while (true) {
                    System.out.println("*** filtering ***");
                    subcommand = scanner.nextLine();
                    if (ConsoleCommandForProducts.BACK.getStringMatcher(subcommand).matches()) {
                        break;
                    } else if (ConsoleCommandForProducts.HELP.getStringMatcher(subcommand).matches()) {
                        System.out.println("for back use : back");
                        System.out.println("for show available filter use : show available filters");
                        System.out.println("for show products with the filter use : filter [an available filter]");
                        System.out.println("for see the current filter use : current filters");
                        System.out.println("for disable a filter use : disable filter");
                    } else if (ConsoleCommandForProducts.SHOW_AVAILABLE_FILTER.getStringMatcher(subcommand).matches()) {
                        showAvailableFilter();
                    } else if (ConsoleCommandForProducts.FILTER_AVAILABLE_FILTER.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForProducts.FILTER_AVAILABLE_FILTER.getStringMatcher(subcommand);
                        if (matcher.find()) {
                            String input = matcher.group(1);
                            filters(input);
                        }

                    } else if (ConsoleCommandForProducts.CURRENT_FILTER.getStringMatcher(subcommand).matches()) {
                        System.out.println(filter);
                    } else if (ConsoleCommandForProducts.DISABLE_FILTER.getStringMatcher(subcommand).matches()) {
                        filter = "nothing";


                    } else {
                        System.out.println("Invalid command ->   help   <-");
                    }
                }
            } else if (ConsoleCommandForProducts.SORTING.getStringMatcher(command).matches()) {
                String subcommand;
                while (true) {
                    subcommand = scanner.nextLine();
                    if (ConsoleCommandForProducts.BACK.getStringMatcher(subcommand).matches()) {
                        break;
                    } else if (ConsoleCommandForProducts.HELP.getStringMatcher(subcommand).matches()) {
                        System.out.println("for back use : back");
                        System.out.println("for show available sorts use : show available sorts");
                        System.out.println("for show products with the sort use : sort [an available sort]");
                        System.out.println("for see the current sort use : current sort");
                        System.out.println("for disable a sort use : disable sort");

                    } else if (ConsoleCommandForProducts.SHOW_AVAILABLE_SORTS.getStringMatcher(subcommand).matches()) {
                        showAvailableSorts();
                    } else if (ConsoleCommandForProducts.SORT_AVAILABLE_SORT.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForProducts.SORT_AVAILABLE_SORT.getStringMatcher(subcommand);
                        if (matcher.find()) {
                            String sort = matcher.group(1);
                            int a = Integer.parseInt(sort);
                            if (a == 1) {
                                showMostExpensiveProduct();
                            } else if (a == 2) {
                                showMostCheapProduct();
                            } else if (a == 3) {
                                showByVisitProduct();
                            } else {
                                System.out.println("INVALID SORTID");
                            }
                        }

                        //sortIt(sort);
                    } else if (ConsoleCommandForProducts.CURRENT_SORT.getStringMatcher(subcommand).matches()) {
                        getcurrentsort();
                    } else if (ConsoleCommandForProducts.DISABLE_SORT.getStringMatcher(subcommand).matches()) {
                        sort = "nothing";
                        //disableSort();
                    } else {
                        System.out.println("INVALID COMMAND");
                    }
                }
            } else if (ConsoleCommandForProducts.SHOW_PRODUCTS.getStringMatcher(command).matches()) {
                //showproducts();
            } else if (ConsoleCommandForProducts.SHOW_PRODUCT.getStringMatcher(command).matches()) {
                Matcher matcher = ConsoleCommandForProducts.SHOW_PRODUCT.getStringMatcher(command);
                if (matcher.find()) {
                    String productId = matcher.group(1);

                    showproduct(productId);
                }

            } else if (ConsoleCommandForProducts.HELP.getStringMatcher(command).matches()) {
                System.out.println("for see products use : products ");
                System.out.println("for see categories use : view categories ");
                System.out.println("for enable filtering use : filtering ");
                System.out.println("for enable sorting use : sorting ");
                System.out.println("for see a product use : show product [productId] ");
                System.out.println("for back use : back");

            } else {
                System.out.println("In valid command ->  help  <-");
            }
        }





        /*String input;
        do{
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callAppropriateUserFunction(input);

        }while(!input.trim().equalsIgnoreCase("back"));*/
    }

    /*private void callAppropriateUserFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
       else if ( userRequest.equals(UserRequest.LOG_IN) ){
            new LogInView(this , true).run(input.split(" "));
        }
       else if ( userRequest.equals(UserRequest.PURCHASE)){
            purchase();
        }
    }

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
    }

    private void help(){
        //TODO
    }*/

    public void getcurrentsort() {
        System.out.println("sort by : " + sort);
    }

    public void showMostExpensiveProduct() {
        sort = "the most expensive";
        Collection<Good> goods = controller.getgoods();

        goods = controller.sortbyPrice(goods);
        System.out.print("+--------------+\n");
        System.out.print("|  good name   |\n");
        System.out.print("+--------------+\n");

        for (Good good : goods) {
            System.out.format("| %-12s |\n", good.getProductName());
            System.out.println("+--------------+");
        }


    }

    public void showMostCheapProduct() {
        Collection<Good> goods = controller.getgoods();
        sort = "cheapest";
        goods = controller.sortbypriceIn(goods);
        System.out.print("+--------------+\n");
        System.out.print("|  good name   |\n");
        System.out.print("+--------------+\n");

        for (Good good : goods) {
            System.out.format("| %-12s |\n", good.getProductName());
            System.out.println("+--------------+");
        }


    }


    public void showByVisitProduct() {
        Collection<Good> goods = controller.getgoods();
        sort = "total visited";
        goods = controller.sortbyVisit(goods);
        System.out.print("+--------------+\n");
        System.out.print("|  good name   |\n");
        System.out.print("+--------------+\n");

        for (Good good : goods) {
            System.out.format("| %-12s |\n", good.getProductName());
            System.out.println("+--------------+");
        }


    }

    public void showAvailableSorts() {
        System.out.println("the most expensive : 1");
        System.out.println("cheapest : 2");
        System.out.println("total visit : 3");
    }

    public void showproduct(String id) {
        Good good = controller.viewProduct(id);
        if (good == null)
            System.out.println("INVALID ID");
        else
            new ProductView(new Menu(), good);
    }

    /*public void disableFilter(String filter){
        filters.remove(filter);
    }
    public void getfilter(){
        for (String filter : filters)
            System.out.println(filter);
    }*/
    public void filters(String field) {


        if (field.equalsIgnoreCase("name")) {
            System.out.print("enter name : ");
            String input = scanner.nextLine();
            filter = "name";
            Collection<Good> goods = controller.getgoods();


            System.out.print("+--------------+\n");
            System.out.print("|  good name   |\n");
            System.out.print("+--------------+\n");

            for (Good good : goods) {
                if (good.getProductName().equalsIgnoreCase(input)) {
                    System.out.format("| %-12s |\n", good.getProductName());
                    System.out.println("+--------------+");
                }

            }

        } else if (field.equalsIgnoreCase("price")) {
            System.out.print("enter price : ");
            double input = scanner.nextDouble();
            filter = "price";
            Collection<Good> goods = controller.getgoods();


            System.out.print("+--------------+\n");
            System.out.print("|  good name   |\n");
            System.out.print("+--------------+\n");

            for (Good good : goods) {
                if (good.getPrice() == (input)) {
                    System.out.format("| %-12s |\n", good.getProductName());
                    System.out.println("+--------------+");
                }

            }

        } else if (field.equalsIgnoreCase("productstate")) {
            System.out.print("enter product state : ");
            String input = scanner.nextLine();
            filter = "productstate";
            Collection<Good> goods = controller.getgoods();


            System.out.print("+--------------+\n");
            System.out.print("|  good name   |\n");
            System.out.print("+--------------+\n");

            for (Good good : goods) {
                if (good.getProductState().equals(input)) {
                    System.out.format("| %-12s |\n", good.getProductName());
                    System.out.println("+--------------+");
                }

            }
        } else if (field.equalsIgnoreCase("isavailable")) {
            System.out.print("enter is available : ");
            boolean input = scanner.nextBoolean();
            filter = "is available";
            Collection<Good> goods = controller.getgoods();


            System.out.print("+--------------+\n");
            System.out.print("|  good name   |\n");
            System.out.print("+--------------+\n");

            for (Good good : goods) {
                if (good.isAvailable() == (input)) {
                    System.out.format("| %-12s |\n", good.getProductName());
                    System.out.println("+--------------+");
                }

            }
        } else {
            System.out.println("INVALID FILTER");
        }


    }

    public void filterIt(String[][] filters) {
        Collection<Good> goods = controller.getgoods();
        for (Good good : goods) {
            for (int i = 0; i < 10; i++) {

            }
        }

    }

    public void printProducts() {
        Collection<Good> goods = controller.getgoods();


        System.out.print("+--------------+\n");
        System.out.print("|  good name   |\n");
        System.out.print("+--------------+\n");

        for (Good good : goods) {
            System.out.format("| %-12s |\n", good.getProductName());
            System.out.println("+--------------+");
        }


    }

    public void printCategories() {
        Collection<Category> categories = controller.getCategories();
        System.out.print("+------------------+\n");
        System.out.print("|   category name  |\n");
        System.out.print("+------------------+\n");

        for (Category category : categories) {
            if (category.getParentCategory() == null) {
                System.out.format("| %-16s |\n", category.getCategoryName());
                System.out.println("+------------------+");
            }

        }

    }

    public void showAvailableFilter() {
        System.out.println("Name");
        System.out.println("price");
        System.out.println("ProductState");
        System.out.println("isAvailable");

    }
}

package View.Menus;

import Controller.Controller;
import Controller.ManagerController;
import Model.Account.Account;
import Model.Account.AccountInformation;
import Model.Account.Manager;
import Model.Account.User;
import Model.Discount.OffTicket;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Good;
import View.Requests.ConsoleCommandForManager;
import View.Requests.ManagerRequest;
import View.Requests.SellerRequest;
import View.Requests.UserRequest;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import View.Menus.CreatAccountMenu;

public class ManagerView extends Menu {
    private ManagerRequest managerRequest;
    private UserRequest userRequest;
    private static Scanner scanner ;

    String command;
    private static final Controller controller = Controller.getInstance();

    public ManagerView(Menu menu) {
        this.headMenu = menu;
    }

    public void run() {
        while (true) {
            System.out.println("*****Manager View*****");
            command = scanner.nextLine().trim();
            if (ConsoleCommandForManager.BACK.getStringMatcher(command).matches()) {
                break;
            }
            else if (ConsoleCommandForManager.VIEW_PERSONAL_INFO.getStringMatcher(command).matches()) {
                printpersonalInfo();
            }
            else if (ConsoleCommandForManager.EDIT_FIELD.getStringMatcher(command).matches()) {
                Matcher matcher=ConsoleCommandForManager.EDIT_FIELD.getStringMatcher(command);

                if (matcher.find()){
                    String field=matcher.group(1);

                    editField(field);
                }

            }
            else if (ConsoleCommandForManager.MANAGE_USERS.getStringMatcher(command).matches()) {
                printUsers();
                String subcommand=scanner.nextLine();
                while (true){
                    subcommand=scanner.nextLine();
                    if (ConsoleCommandForManager.BACK.getStringMatcher(subcommand).matches())
                        break;
                    else if (ConsoleCommandForManager.HELP.getStringMatcher(subcommand).matches()){
                        System.out.println("for back use : back");
                        System.out.println("for view username use : view [username]");
                        System.out.println("for delete user use : delete user [username]");
                        System.out.println("for create manager profile use : create manager profile");
                    }
                    else if (ConsoleCommandForManager.VIEW_USERNAME.getStringMatcher(subcommand).matches()){
                        Matcher matcher=ConsoleCommandForManager.VIEW_USERNAME.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String username=matcher.group(1);
                            viewUser(username);
                        }

                    }
                    else if (ConsoleCommandForManager.DELETE_USER.getStringMatcher(subcommand).matches()){
                        Matcher matcher=ConsoleCommandForManager.DELETE_USER.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String username=matcher.group(1);
                            deleteUser(username);
                        }


                    }
                    else if (ConsoleCommandForManager.CREATE_MANAGER_PROFILE.getStringMatcher(subcommand).matches()){
                        createManager();
                    }
                    else {
                        System.out.println("Invalid command");
                    }
                }

            }
            else if (ConsoleCommandForManager.MANAGE_ALL_PRODUCT.getStringMatcher(command).matches()) {
                String subcommand;
                while (true) {
                    subcommand = scanner.nextLine().trim();
                    if (ConsoleCommandForManager.REMOVE_PRODUCT.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForManager.REMOVE_PRODUCT.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String productId = matcher.group(1);
                            removeProduct(productId);
                        }

                    } else if (ConsoleCommandForManager.HELP.getStringMatcher(subcommand).matches()) {

                        System.out.println("for remove a good you can use this command : remove[productId]");
                        System.out.println("for back to last page you can use this command : back");
                    } else if (ConsoleCommandForManager.BACK.getStringMatcher(subcommand).matches()) {
                        break;
                    } else {
                        System.out.println("in valid command ->  help  <-");
                    }
                }

            }
            else if (ConsoleCommandForManager.CREATE_DISCOUNT_CODE.getStringMatcher(command).matches()) {
                createDiscountCode();
                command=scanner.nextLine();
            }
            else if (ConsoleCommandForManager.VIEW_DISCOUNT_CODES.getStringMatcher(command).matches()) {
                printDiscountCodeList();
                String subcommand ;
                while (true){
                    subcommand = scanner.nextLine();
                    if (ConsoleCommandForManager.BACK.getStringMatcher(subcommand).matches()) {
                        break;

                    } else if (ConsoleCommandForManager.HELP.getStringMatcher(subcommand).matches()) {
                        System.out.println("for back use this command : back");

                        System.out.println("for view information of a code use :  view discount code[code]");
                        System.out.println("for edit a code information use : edit discount code[code] ");
                        System.out.println("for remove a code use : remove discount code[code]");
                    } else if (ConsoleCommandForManager.VIEW_DISCOUNT_CODE.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForManager.VIEW_DISCOUNT_CODE.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String discountId = matcher.group(1);
                            printDiscountCode(discountId);
                        }

                    } else if (ConsoleCommandForManager.EDIT_DISCOUNT_CODE.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForManager.EDIT_DISCOUNT_CODE.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String discountId = matcher.group(1);
                            editDiscount(discountId);
                            command=scanner.nextLine();
                        }

                    } else if (ConsoleCommandForManager.REMOVE_DISCOUNT_CODE.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForManager.REMOVE_DISCOUNT_CODE.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String offTicketId = matcher.group(1);
                            removeOffTicket(offTicketId);
                        }

                    } else {
                        System.out.println("In valid command ->  help  <-");
                    }
                }

            }
            else if (ConsoleCommandForManager.MANAGE_REQUESTS.getStringMatcher(command).matches()) {
                printRequests();
                String subcommand ;
                while (true){
                    subcommand = scanner.nextLine();
                    if (ConsoleCommandForManager.BACK.getStringMatcher(subcommand).matches()) {
                        break;
                    }
                    else if (ConsoleCommandForManager.HELP.getStringMatcher(subcommand).matches()) {

                        System.out.println("for back use this command : back");
                        System.out.println("for view detail of a requst use : details [requestId]");
                        System.out.println("for accept a request use  : accept [requestId] ");
                        System.out.println("for decline a request use : decline[requestId]");
                    }
                    else if (ConsoleCommandForManager.DETAIL_REQUEST.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForManager.DETAIL_REQUEST.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String requestId = matcher.group(1);
                            details(requestId);
                        }

                    }
                    else if (ConsoleCommandForManager.ACCEPT_REQUEST.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForManager.ACCEPT_REQUEST.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String requestId = matcher.group(1);
                            acceptRequest(requestId);
                        }

                    }
                    else if (ConsoleCommandForManager.DECLINE_REQUEST.getStringMatcher(subcommand).matches()) {
                        Matcher matcher = ConsoleCommandForManager.DECLINE_REQUEST.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String requestId = matcher.group(1);
                            declineRequest(requestId);
                        }

                    }
                    else {
                        System.out.println("In valid command ->  help  <-");
                    }

                }

            }

            else if (ConsoleCommandForManager.MANAGE_CATEGORIES.getStringMatcher(command).matches()) {
                printcategories();
                String subcommand;
                while (true) {
                    subcommand = scanner.nextLine().trim();

                    if (ConsoleCommandForManager.EDIT_CATEGORY.getStringMatcher(subcommand).matches()) {
                        Matcher matcher=ConsoleCommandForManager.EDIT_CATEGORY.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String categoryName=matcher.group(1);
                            editCategory(categoryName);
                        }


                    } else if (ConsoleCommandForManager.ADD_CATEGORY.getStringMatcher(subcommand).matches()) {
                        Matcher matcher=ConsoleCommandForManager.ADD_CATEGORY.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String categoryName=matcher.group(1);
                            Category category=getCategory(categoryName);
                            addCategory(category);
                            System.out.println("CATEGORY ADDED");
                        }


                    }
                    else if (ConsoleCommandForManager.REMOVE_CATEGORY.getStringMatcher(subcommand).matches()) {
                        Matcher matcher=ConsoleCommandForManager.REMOVE_CATEGORY.getStringMatcher(subcommand);
                        if (matcher.find()){
                            String categoryName=matcher.group(1);
                            removeCategory(categoryName);
                            System.out.println("REMOVED");
                        }



                    }
                    else if (ConsoleCommandForManager.BACK.getStringMatcher(subcommand).matches())
                        break;
                    else if (ConsoleCommandForManager.HELP.getStringMatcher(subcommand).matches()) {
                        System.out.println("for help use : help");
                        System.out.println("for edit category use : edit[category]");
                        System.out.println("for add category use : add[category]");
                        System.out.println("for remove category use : remove[category]");
                        System.out.println("for back use : back");
                    } else {
                        System.out.println("In valid command ->  help  <-");
                    }
                }
            }
            else if (ConsoleCommandForManager.HELP.getStringMatcher(command).matches()) {
                System.out.println("for back use : back");
                System.out.println("for view personal information use : view personal info");
                System.out.println("for edit information use : edit [field]");
                System.out.println("for manage users use : manage users");
                System.out.println("for manage all product use : manage all products");
                System.out.println("for create discount code use : create discount code");
                System.out.println("for view discount code use : view discount code ");
                System.out.println("for manage requests use : manage requests");
                System.out.println("for manage categories use : manage categoies");


            }
            else {
                System.out.println("In valid command ->  help  <-");
            }
        }

    }



        /*printCategories();
        String input;
        while(!((input = Menu.getInputFromUser()).trim().equalsIgnoreCase("back"))){
            getRequestType(input);
            callAppropriateMethod();
        }*/

    private static void createDiscountCode(){

        System.out.print("enter start Date : ");
        String startingDate = scanner.nextLine();
        System.out.print("enter end Date : ");
        String endingDate = scanner.nextLine();
        System.out.print("enter off percent : ");
        double offpercent = scanner.nextDouble();
        System.out.print("enter max off amount : ");
        double offamount = scanner.nextDouble();
        System.out.print("enter times can be use : ");
        int timesCanBeUse = scanner.nextInt();
        ArrayList<String> accountIds = new ArrayList<String>();
        Account account;
        System.out.println("enter accountId that can use this off,when finish press \"ok\"");
        String accountId="" ;
        accountId=scanner.next();
        while (true) {
            if (accountId.equalsIgnoreCase("ok"))
                break;
            else {

                account = controller.getAccount(accountId);
                if (account==null)
                    System.out.println("Invalid account id");
                else
                    accountIds.add(accountId);
                accountId = scanner.next();
            }


        }

        OffTicket offTicket = new OffTicket(startingDate, endingDate, offpercent, offamount, timesCanBeUse, accountIds);
        if (controller.checkNoDiscountCode(offTicket)) {
            controller.addOffticket(offTicket);

        }
        else{

            System.out.println("This offTicket is in use ");
            System.out.println("try again");
        }

    }
    private static void removeProduct(String productId){
        Good good=controller.getGood(productId);
        if (good==null)
            System.out.println("This productId is Invalid");
        else {
            controller.removeProduct(good);
        }

    }
    private static void printpersonalInfo(){
        Account account=controller.getCurrentAccount();

        PrintStatus(account);

    }

    private static void createManager(){
        CreatAccountMenu create=new CreatAccountMenu(new Menu());
        create.creatAdmin();

    }
    private static void deleteUser(String username){
        Account account=controller.getAccount(username);
        if (account==null)
            System.out.println("This username is Invalid");
        else {
            controller.removeUser(account);
        }


    }
    private static void printUsers(){
        Collection<Account>accounts=controller.getAccounts();
        System.out.print("+-----------+\n");
        System.out.print("| user name |\n");
        System.out.print("+-----------+\n");

        for (Account account : accounts) {
            System.out.format("| %-12s | %n",account.getAccountInformation().getUsername());

        }
        System.out.print("+-----------+");

    }
    private static void viewUser(String username){
        Account account=controller.getAccount(username);
        if (account==null)
            System.out.println("this username is invalid");
        else {
            PrintStatus(account);
        }

    }

    private static void PrintStatus(Account account) {
        System.out.print("+-----------+----------+-------+------+-----------+--------------+\n");
        System.out.print("| user name | password | email | name | last name | phone number |\n");
        System.out.print("+-----------+----------+-------+------+-----------+--------------+\n");
        System.out.format("| %-10s | %-8s | %-5s | %-4s | %-9s | %-12s | ", account.getAccountInformation().getUsername(), account.getAccountInformation().getPassWord(),account.getAccountInformation().getEmail(),account.getAccountInformation().getName(),account.getAccountInformation().getLastname(),account.getAccountInformation().getPhoneNumber());
        System.out.println("+-----------+----------+-------+------+-----------+--------------+");
    }

    private static void editField(String field){


            if (field.equalsIgnoreCase("username")){
                System.out.println("you cant edit this field ");
                System.out.println("*******************");
                System.out.println("YOU CAN EDIT THESE :  email , password , name , lastname , phonenumber ");
            }
            else if (field.equalsIgnoreCase("password")){
                System.out.print("PLEASE ENTER PASSWORD : ");
                String password=scanner.next();
                Account account=controller.getCurrentAccount();
                account.getAccountInformation().setPassWord(password);
            }
            else if (field.equalsIgnoreCase("email")){
                System.out.print("PLEASE ENTER EMAIL : ");
                String email=scanner.next();
                // mishe inja check kard k email valid hast ya na
                Account account=controller.getCurrentAccount();
                account.getAccountInformation().setEmail(email);
            }
            else if (field.equalsIgnoreCase("name")){
                System.out.print("PLEASE ENTER NAME : ");
                String name=scanner.next();
                Account account=controller.getCurrentAccount();
                account.getAccountInformation().setName(name);
            }
            else if (field.equalsIgnoreCase("lastname")){
                System.out.print("PLEASE ENTER LASTNAME : ");
                String lastname=scanner.next();
                Account account=controller.getCurrentAccount();
                account.getAccountInformation().setLastName(lastname);
            }
            else if (field.equalsIgnoreCase("phonenumber")){
                System.out.print("PLEASE ENTER PHONENUMBER : ");
                String phonenumber=scanner.next();
                // mishe check kard valid boodanesho
                Account account=controller.getCurrentAccount();
                account.getAccountInformation().setPhoneNumber(phonenumber);
            }
            else{
                System.out.println("Invalid field  ");
                System.out.println("*******************");
                System.out.println("YOU CAN EDIT THESE :  email , password , name , lastname , phonenumber ");

            }




    }
    private static void removeCategory(String name){
        Category category=controller.getCategory(name);
        controller.removeCategory(category);
    }
    private static void addCategory(Category category){
        controller.addcategory(category);

    }
    private static Good getGood(){
        System.out.print("enter good name : ");
        String name=scanner.nextLine();
        System.out.println("enter price : ");
        double price=scanner.nextDouble();
        System.out.println("enter characteristic : ");
        String characteristic=scanner.nextLine();
        Good good=new Good(name,null,price,characteristic,null , null ,null);
        return good;

    }
    private static Good getGood(String name){

        System.out.println("enter characteristic : ");
        String characteristic=scanner.nextLine();
        System.out.println("enter price : ");
        double price=scanner.nextDouble();
        Good good=new Good(name,null,price,characteristic,null , null,null);

        System.out.println("GOOD ADDED");
        return good;
    }
    private static Category getCategory(String name){



        String parentCategoryName;

        System.out.print("enter parent Category : ");
        parentCategoryName=scanner.nextLine();
        Category parentCategory=controller.getCategory(parentCategoryName);
        System.out.print("enter characteristic : ");
        String characteristic=scanner.nextLine();
        System.out.println("enter subCategory , when finish press\"ok\"");
        ArrayList<Category>subcategories=new ArrayList<>();
        String input1=scanner.nextLine();
        while (true){
            if (input1.equalsIgnoreCase("ok"))
                break;
            else {
                System.out.println("ENTER SUBCATEGORY ");
                subcategories.add(getCategory(input1));
                input1=scanner.nextLine();
            }

        }
        System.out.println("enter goodnames , when finish press\"ok\"");
        ArrayList<Good>goods=new ArrayList<>();
        String input=scanner.nextLine();
        while (true){
            if (input.equalsIgnoreCase("ok")){
                break;
            }
            else {
                goods.add(getGood(input));
                System.out.println("enter goodnames , when finish press\"ok\"");
                input=scanner.nextLine();
            }

        }
        Category category=new Category(name,characteristic,subcategories,goods,parentCategory);
        return category;
    }
    private static Category getCategory(){


        String name;
        System.out.print("enter category name : ");
        name=scanner.next();
        String parentCategoryName;

        System.out.print("enter parent Category : ");
        scanner.nextLine();
        parentCategoryName=scanner.nextLine();
        Category parentCategory=controller.getCategory(parentCategoryName);
        System.out.print("enter characteristic : ");
        String characteristic=scanner.nextLine();
        System.out.println("enter subCategory , when finish press\"ok\"");
        ArrayList<Category>subcategories=new ArrayList<>();
        String input1=scanner.nextLine();
        while (true){
            if (input1.equalsIgnoreCase("ok"))
                break;
            else {
                subcategories.add(getCategory(input1));
                input1=scanner.nextLine();
            }

        }
        System.out.println("enter goods , when finish press\"ok\"");
        ArrayList<Good>goods=new ArrayList<>();
        String input=scanner.nextLine();
        while (true){
            if (input.equalsIgnoreCase("ok"))
                break;
            else {
                goods.add(getGood());
                input=scanner.nextLine();
            }

        }
        Category category=new Category(name,characteristic,subcategories,goods,parentCategory);
        return category;
    }
    private static void details(String requestId) {
        Model.Account.ManagerRequest request=controller.viewRequest(requestId);
        if (request==null)
            System.out.println("DONT HAVE THIS REQUEST ID");
        else {
            System.out.print("+------------+----------------+----------------------+\n");
            System.out.print("| Request Id | Request Status | Request explanation  |\n");
            System.out.print("+------------+----------------+----------------------+\n");
            System.out.format("| %-13s | %-14b |%-20s |\n",request.getRequestId(), request.isRequestStatus() , request.getRequestExplanation() );
            System.out.println("+------------+----------------+----------------------+\n");
        }

    }
    private static void printcategories(){
        Collection<Category>categories=controller.getCategories();
        System.out.print("+---------------+\n");
        System.out.print("| category name |\n");
        System.out.print("+---------------+\n");

        for (Category category : categories) {
            System.out.format("| %-13s | %n",category.getCategoryName());
            System.out.println("+---------------+");
        }

    }
    private static void editCategory(String name){
        Category category2=controller.getCategory(name);
        if (category2==null)
            System.out.println("INVALID NAME");
        else {
            Category category=getCategory();
            Collection<Category> categories = controller.getCategories();

            for (Category category1 : categories) {
                if (category1.getCategoryName().equalsIgnoreCase(name)) {
                    category1.setCharacteristics(category.getCharacteristics());
                    category1.setCategoryName(category.getCategoryName());
                    category1.setGoodsInCategory(category.getGoodsInCategory());
                    category1.setParentCategory(category.getParentCategory());
                    category1.setSubCategories(category.getSubCategories());
                    break;
                }

            }
            System.out.println("EDITTED");
        }

    }
    private static void printRequests() {
        Collection<Model.Account.ManagerRequest> requests=controller.getRequests();
        System.out.print("+------------+\n");
        System.out.print("| Request Id |\n");
        System.out.print("+------------+\n");

        for (Model.Account.ManagerRequest request : requests) {
            System.out.format("| %-13s | %n",request.getRequestId());
            System.out.print("+--------------+");
        }



    }

    private static void printDiscountCodeList() {
        Collection<OffTicket> offTickets = controller.getoffTickets();
        System.out.print("+--------------+\n");
        System.out.print("| offTicket Id |\n");
        System.out.print("+--------------+\n");

        for (OffTicket offTicket : offTickets) {
            System.out.format("| %-12s |%n", offTicket.getOffTicketId());
            System.out.print("+--------------+\n");
        }


    }

    private static void printDiscountCode(String offTicketId) {
        Collection<OffTicket> offTickets = controller.getoffTickets();


        for (OffTicket offTicket : offTickets) {
            if (offTicket.getOffTicketId().equalsIgnoreCase(offTicketId)) {
                System.out.print("+---------------+------------+------------+-------------+------------+------------------+\n");
                System.out.print("| Off Ticket Id |  starting  |   ending   | off percent | off amount | times Can Be use |\n");
                System.out.print("+---------------+------------+------------+-------------+------------+------------------+\n");
                System.out.format("| %-13s | %-10s | %-10s | %-11.2f | %-10.2f | %-16d | \n", offTicket.getOffTicketId(), offTicket.getStartingDate(), offTicket.getEndingDate(), offTicket.getOffPercent(), offTicket.getOffAmount(), offTicket.getTimesCanBeUsed() );
                System.out.println("+---------------+------------+------------+-------------+------------+------------------+");
                break;
            }

        }
        System.out.println("DONT HAVE THIS DISCOUNT CODE");


    }

    public static void editDiscount(String discountId) {
        System.out.print("enter starting Date : ");
        String startingDate = scanner.nextLine();
        System.out.print("enter ending Date : ");
        String endingDate = scanner.nextLine();
        System.out.print("enter off percent : ");
        double offPercent = scanner.nextDouble();
        System.out.print("enter off amount : ");
        double offamount = scanner.nextDouble();
        System.out.print("times can be use : ");
        int times = scanner.nextInt();
        ArrayList<String> accountIds = new ArrayList<>();
        Account account;
        System.out.println("enter accountId that can use this off,when finish press \"ok\"");
        String accountId="" ;
        accountId=scanner.next();
        while (true) {
            if (accountId.equalsIgnoreCase("ok"))
                break;
            else {

                account = controller.getAccount(accountId);
                if (account==null)
                    System.out.println("Invalid account id");
                else
                    accountIds.add(accountId);
                accountId = scanner.next();
            }


        }
        Collection<OffTicket> offTickets = controller.getoffTickets();

        for (OffTicket offTicket : offTickets) {
            if (offTicket.getOffTicketId().equalsIgnoreCase(discountId)) {
                offTicket.setStartingDate(startingDate);
                offTicket.setEndingDate(endingDate);
                offTicket.setOffPercent(offPercent);
                offTicket.setOffAmount(offamount);
                offTicket.setTimesCanBeUsed(times);
                offTicket.setAccountIdsInvolved(accountIds);
                break;
            }

        }
        System.out.println("EDIT DONE");
    }

    public static void removeOffTicket(String offTicketId) {
        Collection<OffTicket>offTickets=controller.getoffTickets();
        OffTicket offTicket=controller.getOffticket(offTicketId);
        offTickets.remove(offTicket);
        System.out.println("REMOVE DONE");
    }

    public static void acceptRequest(String requestId){
        Model.Account.ManagerRequest request = controller.viewRequest(requestId);
        if (request==null)
            System.out.println("INVALID REQUEST ID");
        else {
            request.setRequestStatus(true);
            System.out.println("ACCEPTED");
        }


}
    public static void declineRequest(String requestId){
        Model.Account.ManagerRequest request = controller.viewRequest(requestId);
        if (request==null)
            System.out.println("INVALID REQUEST ID");
        else {
            request.setRequestStatus(false);
            System.out.println("DECLINED");
        }


    }

    /*public void manageCategories(){
        String input;
        while(!((input = Menu.getInputFromUser()).trim().equalsIgnoreCase("back"))){
            getRequestManageRequestType(input);
            callAppropriateManageCategoryMethod(input);
        }
    }

    private void callAppropriateManageCategoryMethod(String input){
        String[] inputSplit = input.split(" ");
        if (managerRequest.equals(ManagerRequest.REMOVE_CATEGORY)){
            try {
                ManagerController.removeCategory(inputSplit[1]);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        else if(managerRequest.equals(ManagerRequest.ADD_CATEGORY)){
            creatCategory(inputSplit[1]);
        }
        else if (managerRequest.equals(ManagerRequest.EDIT_CATEGORY)){
            editCategory(inputSplit[1]);
        }

    }

    private void editCategory(String categoryName){
        String input;
        Category category;
        try {
            category = Manager.getCategoryByName(categoryName);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }
        do{
            printCategoryInfo(category);
            System.out.println("please enter an edit command, you can delete|add a characteristic, delete|add a product or change the name of category");
            input = Menu.getInputFromUser();
            getEditCommandType(input);
            callEditCategoryFunction(category , input);
            System.out.println("if you want to continue, press 'y', any other input counts as no");
        }while(!input.trim().toLowerCase().equals("y"));
    }

    private void callEditCategoryFunction(Category category , String input){
        String[] inputSplit = input.trim().split(" ");
        if(managerRequest.equals(ManagerRequest.ADD_CHARACTERISTIC)){
            Characteristic characteristic = getCharacteristicFromUser();
            ManagerController.addCharacteristicToCategory(category , characteristic );
        }
        else if (managerRequest.equals(ManagerRequest.REMOVE_CHARACTERISTIC)){
            ManagerController.removeCharacteristicFromCategory(category , inputSplit[2]);
        }
        else if (managerRequest.equals(ManagerRequest.CHANGE_CATEGORY_NAME)){
            ManagerController.changeCategoryName(category , inputSplit[3]);
        }
        else if ( managerRequest.equals(ManagerRequest.ADD_PRODUCT_TO_CATEGORY)){
            addProductToCategory(category, inputSplit[2]);
        }
        else if ( managerRequest.equals(ManagerRequest.REMOVE_PRODUCT_FROM_CATEGORY)){
            removeProductFromCategory(category , inputSplit[2]);
        }
        else if ( managerRequest.equals(ManagerRequest.ADD_SUBCATEGORY)){
            addSubCategory( category , inputSplit[2]);
        }
        else if ( managerRequest.equals(ManagerRequest.REMOVE_SUBCATEGORY)){
            removeSubCategory(inputSplit[2]);
        }
        else{
            manageCategoryHelp();
        }
    }

    private void removeSubCategory(String subcategoryName){
        try{
            ManagerController.removeSubCategory(subcategoryName);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void addSubCategory(Category parentCategory , String subCategoryName){
        if (Manager.categoryExists(subCategoryName)){
            System.out.println("category already exists with this name.");
            return;
        }
        ArrayList<Characteristic> categoryCharacteristics = getSubCategoryCharacteristics(parentCategory);
        ArrayList<Good> categoryGoods = getSubCategoryGoods(parentCategory);
        ManagerController.addSubCategory(subCategoryName , categoryCharacteristics , parentCategory , categoryGoods );
    }
    public ArrayList<Good> getSubCategoryGoods(Category category){
        ArrayList<Good> categoryGoods = new ArrayList<>();
        String goodsName;
        do{
            System.out.println("please enter goods name from parent category");
            goodsName = Menu.getInputFromUser().trim();
            try {
                if (Manager.goodExistsInCategory(category, Manager.getGoodByName(goodsName))){
                    categoryGoods.add((Manager.getGoodByName(goodsName)));
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("if you want to add another product, enter Y or y, any other input ends this part");
            goodsName = Menu.getInputFromUser();
        }while(goodsName.trim().equalsIgnoreCase("y"));
        return categoryGoods;
    }

    public ArrayList<Characteristic> getSubCategoryCharacteristics(Category category){
        ArrayList<Characteristic> categoryCharacteristics = new ArrayList<>();
        String title;
        do{
            try {
                category.addCharacteristics(getSubCategoryCharacteristicFromUser(category));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("if you want to add another one, enter Y or y, any other input ends this part");
            title = Menu.getInputFromUser();
        }while(title.trim().equalsIgnoreCase("y"));
        return categoryCharacteristics;
    }

    private Characteristic getSubCategoryCharacteristicFromUser(Category category) throws Exception{
        System.out.println("enter parentCategory characteristic title");
        String title = Menu.getInputFromUser().trim();
        return category.getCategoryCharacteristicByTitle(title);
    }

    private void manageCategoryHelp(){
        System.out.println("add characteristic\n"+ "remove characteristic\n" + "add product [productId]\n" + "remove product [productId]\n" + "change name to [new name]" + "add subcategory [subcategory name]" + "remove subcategory [subcategory name]");
    }

    private void addProductToCategory(Category category , String productId){
        try {
            ManagerController.addProductToCategory(category , Manager.getGoodById(productId));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void removeProductFromCategory(Category category , String productId){
        try {
            ManagerController.removeProductFromCategory(category , Manager.getGoodById(productId));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private Characteristic getCharacteristicFromUser(){
        String title;
        String description;
        System.out.println("please enter title of characteristic");
        title = Menu.getInputFromUser();
        System.out.println("please enter description of characteristic");
        description = Menu.getInputFromUser();
        return new Characteristic(title , description);
    }

    private void printCategoryInfo(Category category){
        System.out.println(category.getCategoryName());
        System.out.println("Characteristics : *******");
        for (Characteristic characteristic : category.getCharacteristics()) {
            System.out.println(characteristic.getCharacteristicName());
        }
        System.out.println("Goods : *******");
        for (Good good : category.getGoodsInCategory()) {
            System.out.println(good.getProductName());
        }
    }

    private void getEditCommandType( String input ){
        String command = input.trim().toLowerCase();
        if ( command.startsWith("delete characteristic")){
            managerRequest = ManagerRequest.REMOVE_CHARACTERISTIC;
        }
        else if ( command.startsWith("add characteristic")){
            managerRequest = ManagerRequest.ADD_CHARACTERISTIC;
        }
        else if ( command.startsWith("change name")){
            managerRequest = ManagerRequest.CHANGE_CATEGORY_NAME;
        }
        else if ( command.startsWith("delete product")){
            managerRequest = ManagerRequest.REMOVE_CHARACTERISTIC;
        }
        else if ( command.startsWith("add product")){
            managerRequest = ManagerRequest.ADD_CHARACTERISTIC;
        }
        else if ( command.startsWith("add subcategory")){
            managerRequest = ManagerRequest.ADD_SUBCATEGORY;
        }
        else{
            managerRequest = ManagerRequest.MANAGE_CATEGORY_HELP;
        }
    }

    private void creatCategory(String categoryName){
        if (Manager.categoryExists(categoryName)){
            System.out.println("category already exists with this name.");
            return;
        }
        ArrayList<Characteristic> categoryCharacteristics = getCategoryCharacteristics();
        ArrayList<Good> categoryGoods = getCategoryGoods();
        Manager.creatCategory(categoryName,categoryCharacteristics,categoryGoods);
    }

    public ArrayList<Good> getCategoryGoods(){
        ArrayList<Good> categoryGoods = new ArrayList<>();
        String productId;
        do{
            System.out.println("please enter goods Id");
            productId = Menu.getInputFromUser().trim();
            try {
                Good good = Manager.getGoodById(productId);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("if you want to add another product, enter Y or y, any other input ends this part");
            productId = Menu.getInputFromUser();
        }while(productId.trim().equalsIgnoreCase("y"));
        return categoryGoods;
    }

    public ArrayList<Characteristic> getCategoryCharacteristics(){
        ArrayList<Characteristic> categoryCharacteristics = new ArrayList<>();
        String title;
        String description;
        do{
            categoryCharacteristics.add(getCharacteristicFromUser());
            System.out.println("if you want to add another one, enter Y or y, any other input ends this part");
            title = Menu.getInputFromUser();
        }while(title.trim().equalsIgnoreCase("y"));
        return categoryCharacteristics;
    }

    private void printCategories(){
        ArrayList<Category> categoryList = Manager.getCategoryList();
        for (Category category : categoryList) {
            System.out.println(category.getCategoryName());
            printSubCategories(category);
        }
    }

    private void printSubCategories(Category category){
        ArrayList<Category> categoryList = category.getSubCategories();
        for (Category subCategory : categoryList) {
            System.out.println("\t" + subCategory.getCategoryName());
        }
    }

    public void getRequestManageRequestType(String input){
        if ( input.trim().toLowerCase().startsWith("edit")){
            managerRequest = ManagerRequest.EDIT_CATEGORY;
        }
        else if ( input.trim().toLowerCase().startsWith("add")){
            managerRequest = ManagerRequest.ADD_CATEGORY;
        }
        else if ( input.trim().toLowerCase().startsWith("remove")){
            managerRequest = ManagerRequest.REMOVE_CATEGORY;
        }
    }

    private void getRequestType( String input ){
        if ( input.equalsIgnoreCase("manage categories")){
            managerRequest = ManagerRequest.MANAGE_CATEGORIES;
            manageCategories();
        }
    }

    public void callAppropriateMethod(){
        if ( managerRequest.equals(ManagerRequest.MANAGE_CATEGORIES)){
            manageCategories();
        }
    }

    private void help(){
        //TODO
    }*/
}

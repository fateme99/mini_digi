package View.Menus;

import Controller.SellerController;
import Model.Account.Manager;
import Model.Account.Seller;
import Model.Discount.Sale;
import Model.Discount.SaleState;
import Model.Good.Good;
import Model.Good.Category ;
import View.Requests.SellerRequest;
import View.Requests.UserRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class SellerView extends Menu{
    private Seller seller;
    private SellerRequest sellerRequest;
    private UserRequest userRequest;
    private SellerController sellerController;


    public SellerView(Menu menu , Seller seller ) {
        this.headMenu = menu;
        this.seller = seller;
        this.sellerController = new SellerController(seller);
    }

    public void run(){
        String input;
        do{
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callFunctionAccordingToRequestType();
        }while(!input.trim().equalsIgnoreCase("back"));
    }

    private void callFunctionAccordingToRequestType(){
        if(sellerRequest.equals(SellerRequest.VIEW_SELLERS_SALES)){
            showSellersSales();
        }
        if(sellerRequest.equals(SellerRequest.VIEW_COMPANY_INFORMATION)){
            sellerController.viewCompanyInformation() ;
        }
        if(sellerRequest.equals(SellerRequest.SHOW_CATEGORIES)){
            showCategories();
        }
        if(sellerRequest.equals(SellerRequest.VIEW_BALANCE)){
            viewBalance();
        }
        if(sellerRequest.equals(SellerRequest.VIEW_SALES_HISTORY)){
            sellerController.viewSalesHistory() ;
        }

    }
    private void showCategories ()
    {
        ArrayList<Category> categoryList = Manager.getCategoryList () ;
        for (Category category : categoryList)
        {
            System.out.println(category.getCategoryName ());
        }
    }

    private void  viewBalance ()
    {
        System.out.println (sellerController.getLoggedInSeller().getBalance () ) ;
    }

    private void showSellersSales(){
        ArrayList<Sale> sellersSales = SellerController.getSellersSales(this.seller);
        for (Sale sale : sellersSales) {
            System.out.println(sale.getSaleId());
        }
        String input;
        do{
            input = Menu.getInputFromUser();
            try {
                getViewSalesRequestType(input.trim().toLowerCase());
                callViewSalesFunctionAccordingToRequestType(input.trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(!input.trim().equalsIgnoreCase("back"));
    }

    private void getViewSalesRequestType(String command) throws Exception{
        if( command.startsWith("view")){
            sellerRequest = SellerRequest.VIEW_SALE;
        }
        else if ( command.startsWith("edit")){
            sellerRequest = SellerRequest.EDIT_SALE;
        }
        else if ( command.equals("add off")){
            sellerRequest = SellerRequest.ADD_SALE;
        }
        else if ( command.equals("help")){
            sellerRequest = SellerRequest.VIEW_SALE_HELP;
        }
        else if (command.equals("back") ){
            sellerRequest = SellerRequest.GO_BACK;
        }
        else{
            throw new Exception("command not found");
        }
    }

    private void callViewSalesFunctionAccordingToRequestType(String input){
        String[] inputSplit = input.split(" ");
        if(sellerRequest.equals(SellerRequest.VIEW_SALE)){
            viewSale(inputSplit[2]);
        }
        else if ( sellerRequest.equals(SellerRequest.ADD_SALE)){
            addSaleWithExceptionHandler();
        }
        else if ( sellerRequest.equals(SellerRequest.GO_BACK)){
            goBack();
        }
        else if ( sellerRequest.equals(SellerRequest.VIEW_SALE_HELP)){
            viewSaleHelp();
        }
        else if ( sellerRequest.equals((SellerRequest.EDIT_SALE))){
            getEditSaleInformation(inputSplit[1]);
        }
    }

    private void goBack(){

    }

    private void getEditSaleInformation(String saleId){
        String input;
        try {
            Sale sale = Manager.getSaleByID(saleId);
            do{
                input = Menu.getInputFromUser().trim();
                getEditSaleRequestType(input.toLowerCase());
                callEditSaleFunctionAccordingToRequestType(input , sale);
            }while (!input.toLowerCase().equals("back"));
        }
        catch (Exception e){
            System.out.println("sale not found");
        }
    }

    private void getEditSaleRequestType(String command){
        if(command.equals("help")){
            sellerRequest = SellerRequest.EDIT_SALE_HELP;
        }
        else if (command.startsWith("starting date")){
            sellerRequest = SellerRequest.EDIT_STARTING_DATE;
        }
        else if(command.startsWith("off percent")){
            sellerRequest = SellerRequest.EDIT_SALES_OFF_PERCENT;
        }
        else if (command.startsWith("add products")){
            sellerRequest = SellerRequest.ADD_PRODUCTS_TO_OFF;
        }
        else if (command.startsWith("remove products")){
            sellerRequest = SellerRequest.REMOVE_PRODUCTS_FROM_OFF;
        }
    }

    private void callEditSaleFunctionAccordingToRequestType(String input , Sale sale){
        String[] inputSplit = input.split(" ");
        if ( sellerRequest.equals(SellerRequest.EDIT_SALE_HELP)){
            editSaleHelp();
        }
        else if ( sellerRequest.equals(SellerRequest.EDIT_STARTING_DATE)){
            editStartingDate(inputSplit[2] , sale);
        }
        else if ( sellerRequest.equals(SellerRequest.EDIT_ENDING_DATE)){
            editEndingDate(inputSplit[2] , sale);
        }
        else if ( sellerRequest.equals(SellerRequest.EDIT_SALES_OFF_PERCENT)){
            editSalesOffPercent(inputSplit[2] );
        }
        else if ( sellerRequest.equals(SellerRequest.ADD_PRODUCTS_TO_OFF)){
            addProductsToSale(new ArrayList<>(Arrays.asList(inputSplit)) , sale);
        }
        else if ( sellerRequest.equals(SellerRequest.REMOVE_PRODUCTS_FROM_OFF)){
            removeProductsFromOff(new ArrayList<>(Arrays.asList(inputSplit)) , sale);
        }
    }

    private void removeProductsFromOff(ArrayList<String> productIds , Sale sale){
        productIds.remove(0);
        productIds.remove(0);
        try {
            this.sellerController.removeGoodsFromSaleApplication(productIds , sale);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProductsToSale(ArrayList<String> productIds , Sale sale){
        productIds.remove(0);
        productIds.remove(0);
        try {
            this.sellerController.addGoodsToSaleApplication(productIds , sale);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void editSalesOffPercent(String newOffPercent ){
        try {
            this.sellerController.setNewOffPercent(newOffPercent );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void editEndingDate(String newDate , Sale sale){
        try{
            this.sellerController.setNewEndingDate(newDate , sale);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void editStartingDate(String newDate , Sale sale){
        try{
            this.sellerController.setNewStartingDate(newDate , sale);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void editSaleHelp(){
        System.out.println("first type the field you want to edit, then enter whats needed :\n" + "starting date [new starting date]\n" + "ending date [new ending date\n" + "off percent [new off percent\n" + "add products [productId1] [productId2] ...\n" + "remove products [productId1] ...");
    }

    private void viewSaleHelp(){
        System.out.println("view [offId]\n" + "edit [offId]\n" + "add off");
    }

    public void addSaleWithExceptionHandler(){
        try{
            addSale();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addSale()throws Exception{
        Date startingDate = getDateFromUser();
        Date endingDate = getDateFromUser();

        //TODO check local date and compare it to system date

        if( (startingDate.compareTo(endingDate) < 0 || startingDate.compareTo(endingDate)== 0) && (startingDate.compareTo(new Date())) < 0 && (endingDate.compareTo(new Date()) > 0) ){
            throw new Exception("this sale would never happen, please try again from beginning");
        }
        ArrayList<Good> inSaleGoods = getInSaleGoods();
        double offPercent = getOffPercent();
        SellerController.sendCreatSaleApplication(inSaleGoods , SaleState.PENDING , startingDate , endingDate , offPercent );
    }

    private ArrayList<Good> getInSaleGoods(){
        String input;
        ArrayList<Good> inSaleGoods = new ArrayList<>();
        do{
            System.out.println("enter Id of the product you want to add");
            input = Menu.getInputFromUser().trim();
            try {
                if(!inSaleGoods.contains(Manager.getGoodById(input))){
                    inSaleGoods.add(Manager.getGoodById(input));
                }
                else{
                    System.out.println("you have added this product before");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("if you dont want to add any other product, enter \"end\", any other input means that you want to add another product");
            input = Menu.getInputFromUser();
        }while(!input.trim().toLowerCase().equals("end"));
        return inSaleGoods;
    }

    private Double getOffPercent(){
        double offPercent;
        while (true) {
            System.out.println("Type a number as off percent(should be a number between 0 and 100) : ");
            try {
                offPercent = Double.parseDouble(Menu.getInputFromUser());
                if(offPercent > 0 && offPercent < 100){
                    return offPercent;
                }
                System.out.println("a percent is a number between 0 and 100, ding dong :/ ");
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input");
            }
        }
    }
    private Date getDateFromUser(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date2=null;
        while(date2 == null) {
            System.out.println("enter a date, format should be like : \"dd-MMM-yyyy HH:mm:ss\"");
            String date = Menu.getInputFromUser();
            try {
                date2 = dateFormat.parse(date);
            } catch (ParseException e) {
                System.out.println("date format is invalid");
            }
        }
        return date2;
    }

    private void viewSale(String saleId){
        try {
            showSellersSale(SellerController.getSellersSale(seller , saleId));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showSellersSale(Sale sale){
        System.out.println(sale.getSaleId());
        System.out.println("starting date : " + sale.getStartingDate() + " , ending date : " + sale.getEndingDate() + " , sale percent : " + sale.getOffPercent() + "%" );
        System.out.println("in sale products : ");
        for (Good inSaleGood : sale.getInSaleGoods()) {
            System.out.println("product name : " + inSaleGood.getProductName() + " , product Id : " + inSaleGood.getProductId());
        }
    }

    private void getRequestType(String command){
        if(command.startsWith("view offs")){
            sellerRequest = SellerRequest.VIEW_SELLERS_SALES;
        }
        else if (command.startsWith("view balance")) {
            sellerRequest = SellerRequest.VIEW_BALANCE;
        }
        else if (command.startsWith("show categories")) {
            sellerRequest = SellerRequest.SHOW_CATEGORIES;
        }

        else if (command.startsWith("view company information")) {
            sellerRequest = SellerRequest.VIEW_COMPANY_INFORMATION;
        }
        else if (command.startsWith("view sales history")) {
            sellerRequest = SellerRequest.VIEW_SALES_HISTORY;
        }
    }

    private void help(){
        //TODO
    }
}

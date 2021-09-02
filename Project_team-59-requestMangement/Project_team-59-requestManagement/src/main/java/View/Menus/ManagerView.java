package View.Menus;

import Controller.ManagerController;
import Model.Account.Manager;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Good;
import View.Requests.ManagerRequest;
import View.Requests.UserRequest;

import java.util.ArrayList;

public class ManagerView extends Menu{
    private ManagerRequest managerRequest;
    private UserRequest userRequest;


    public ManagerView( Menu menu ) {
        this.headMenu = menu;
    }

    public void run(){
        printCategories();
        String input;
        while(!((input = Menu.getInputFromUser()).trim().equalsIgnoreCase("back"))){
            getRequestType(input);
            callAppropriateMethod();
        }
    }

    public void manageCategories(){
        String input;
        while(!((input = Menu.getInputFromUser()).trim().equalsIgnoreCase("back"))){
            getRequestManageRequestType(input);
            callAppropriateManageCategoryMethod(input);
        }
    }
    public void manageRequests(){
    	ManegerController.viewRequests() ; 
        String input;
        while(!((input = Menu.getInputFromUser()).trim().equalsIgnoreCase("back"))){
            getRequestManageRequestType(input);
            callAppropriateManageRequestMethod(input);
        }
    }
    private void  callAppropriateManageRequestMethod(String  input) 
    {
    	 String[] inputSplit = input.split(" ");
    	 if (managerRequest.equals(ManagerRequest.VIEW_REQUEST_DETAILS)){
             try {
                 ManagerController.viewRequestDetails(inputSplit[1]);
             }
             catch(Exception e){
                 System.out.println(e.getMessage());
             }
    	 }
    	 else if (managerRequest.equals(ManagerRequest.ACCEPT_REQUEST)){
             try {
                 ManagerController.acceptRequest(inputSplit[1]);
             }
             catch(Exception e){
                 System.out.println(e.getMessage());
             }
    	 }   
    	 else if (managerRequest.equals(ManagerRequest.DECLINE_REQUEST)){
             try {
                 ManagerController.rejectRequest(inputSplit[1]);
             }
             catch(Exception e){
                 System.out.println(e.getMessage());
             }
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

        else if ( input.trim().toLowerCase().startsWith("details")){
            managerRequest = ManagerRequest.VIEW_REQUEST_DETAILS;
        }
        else if ( input.trim().toLowerCase().startsWith("decline")){
            managerRequest = ManagerRequest.DECLINE_REQUEST;
        }

        else if ( input.trim().toLowerCase().startsWith("accept")){
            managerRequest = ManagerRequest.ACCEPT_REQUEST;
        }
        
        }

    private void getRequestType( String input ){
        if ( input.equalsIgnoreCase("manage categories")){
            managerRequest = ManagerRequest.MANAGE_CATEGORIES;
        }
        else if ( input.equalsIgnoreCase("manage requests")){
            managerRequest = ManagerRequest.VIEW_REQUESTS;
            
        }
    }

    public void callAppropriateMethod(){
        if ( managerRequest.equals(ManagerRequest.MANAGE_CATEGORIES)){
            manageCategories();
        }
        if ( managerRequest.equals(ManagerRequest.VIEW_REQUESTS)){
            manageRequests();
        }
        
    }

    private void help(){
        //TODO
    }
}

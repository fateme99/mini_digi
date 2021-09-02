package Controller;

import Model.Account.AccountInformation;
import Model.Account.Manager;
import Model.Application.Application;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Good;

import java.util.ArrayList;

public class ManagerController extends AccountController{
    private Manager loggedInManager;

    public ManagerController(Manager loggedInManager) {
        super(loggedInManager);
        this.loggedInManager = loggedInManager;
    }

    public Manager getLoggedInManager() {
        return loggedInManager;
    }

    public void setLoggedInManager(Manager loggedInManager) {
        this.loggedInManager = loggedInManager;
    }


    public void manageUsers(){
        //TODO check if account is manager then allow it to use methods
    }

    public void viewUser( String userId ){
        //TODO check if current account is manager and continue
    }

    public void deleteUser( String userId ){
        //TODO check if current account is manager and continue
    }

    public void creatNewManager(AccountInformation accountInformation){
        //TODO instantly creates new manager if current is manager
    }

    public void manageAllProducts(){
        //TODO check if account is manager then allow it to use methods
    }

    public void removeProduct( String productId){
        //TODO check if account is manager then remove good, remember to remove good from all sellers as well
    }

    public void viewDiscountCodes(){
        //TODO check if account is manager then allow it to use methods
    }

    public void viewDiscountCode( String offTicketId ){
        //TODO
    }

    public void editDiscountCode( String offTicketId ){
        //TODO
    }

    public void removeDiscountCode( String offTicketId ){
        //TODO
    }

    public void viewRequests(){
        //TODO check if account is manager then allow it to use methods
    }

    public void viewRequestDetails( String requestId ){
        //TODO check if account is manager then allow it to use methods
    }

    public void acceptRequest( String requestId ){
        //TODO
    }

    public void rejectRequest( String requestId ){
        //TODO
    }

    public void manageProducts(){
        //TODO
    }

    /*public static void addSubCategory(String categoryName, ArrayList<Characteristic> characteristics, Category parentCategory, ArrayList<Good> goodsInCategory ){
        parentCategory.getSubCategories().add(new Category(categoryName , characteristics , goodsInCategory));
    }*/

    public static void removeCategory(String categoryName) throws Exception {
        Category category = Manager.getCategoryByName(categoryName);
        Manager.getCategoryList().remove(category);
    }

    public static void removeSubCategory(String subCategoryName) throws Exception {
        Category category = Manager.getCategoryOfSubCategoryByName(subCategoryName);
        if (category != null) {
            category.getSubCategories().removeIf(subCategory -> subCategory.getCategoryName().equals(subCategoryName));
        }
        throw new Exception("no category with such name");
    }

    /*public static void addCharacteristicToCategory(Category category , Characteristic characteristic){
        category.addCharacteristics(characteristic);
    }*/

    /*public static void removeCharacteristicFromCategory(Category category , String characteristicTitle){
        for (Characteristic characteristic : new ArrayList<>(category.getCharacteristics())) {
            if(characteristic.getCharacteristicName().equals(characteristicTitle)){
                category.removeCharacteristics(characteristic);
            }
        }
    }*/

    public static void changeCategoryName(Category category , String newName){
        category.setCategoryName(newName);
    }

    public static void addProductToCategory(Category category , Good good){
        category.addGood(good);
    }

    public static void removeProductFromCategory(Category category , Good good){
        category.removeGood(good);
    }
}

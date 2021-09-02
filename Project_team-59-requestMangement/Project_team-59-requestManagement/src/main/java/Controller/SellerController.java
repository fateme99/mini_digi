package Controller;

import Model.Application.*;
import Model.Account.Manager;
import Model.Account.Seller;
import Model.Discount.Sale;
import Model.Discount.SaleState;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Good;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SellerController extends AccountController {
    private Seller loggedInSeller;
    private Application application;

    public SellerController(Seller loggedInSeller) {
        super(loggedInSeller);
        this.loggedInSeller = loggedInSeller;
    }

    public void setLoggedInSeller(Seller loggedInSeller) {
        this.loggedInSeller = loggedInSeller;
    }

    public Seller getLoggedInSeller() {
        return loggedInSeller;
    }

    public static void sendCreatSaleApplication(ArrayList<Good> inSaleGoods, SaleState saleState, Date startingDate, Date endingDate, double offPercent ){
        Manager.addApplication(new CreatSaleApplication( ApplicationType.CREAT_SALE , inSaleGoods , SaleState.PENDING , startingDate , endingDate , offPercent ));
    }

    public void editSale( String saleId )throws Exception{
        this.application = new EditSaleApplication(Manager.getSaleByID(saleId));
    }

    public void setNewStartingDate(String newStartingDateAsString , Sale sale) throws Exception {
        Date newStartingDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        try {
            newStartingDate = dateFormat.parse(newStartingDateAsString);
            if(newStartingDate.compareTo(sale.getEndingDate()) > 0){
                throw new Exception("starting date must be before ending date");
            }
            else if(newStartingDate.compareTo(new Date()) <= 0 ){
                throw new Exception("starting date must be after current date");
            }
            else {
                EditSaleApplication editSaleApplication = (EditSaleApplication) this.application;
                editSaleApplication.setNewStartingDate(newStartingDate);
            }
        } catch (ParseException e) {
            System.out.println("date format is invalid");
        }
    }

    public void setNewEndingDate(String newEndingDateAsString , Sale sale ) throws Exception {
        Date newEndingDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        try {
            newEndingDate = dateFormat.parse(newEndingDateAsString);
            if(newEndingDate.compareTo(sale.getStartingDate()) < 0){
                throw new Exception("ending date must be after starting date");
            }
            else if(newEndingDate.compareTo(new Date()) < 0){
                throw new Exception("ending date must happen after current starting date");
            }
            else{
                EditSaleApplication editSaleApplication = (EditSaleApplication) this.application;
                editSaleApplication.setNewEndingDate(newEndingDate);
            }
        } catch (ParseException e) {
            System.out.println("date format is invalid");
        }
    }

    public void setNewOffPercent(String newOffPercentAsString ) throws Exception{
        double newOffPercent;
        try {
            newOffPercent = Double.parseDouble(newOffPercentAsString);
            if(newOffPercent > 0 && newOffPercent < 100){
                EditSaleApplication editSaleApplication = (EditSaleApplication) this.application;
                editSaleApplication.setNewOffPercent(newOffPercent);
            }
        } catch (NumberFormatException ignore) {
            throw new Exception("invalid input :|||||||");
        }
    }

    public void addGoodsToSaleApplication(ArrayList<String> goodIds , Sale sale) throws Exception{
        if(goodIds.size() == 0){
            throw new Exception("you must enter at least one product");
        }
        Good goodToBeAdded;
        for (String goodId : goodIds) {
            Good good = Manager.getGoodById(goodId);
            if ( sale.getInSaleGoods().contains(good) ){
                throw new Exception("sale already contains this good aka : " + good.getProductName() );
            }
            else{
                EditSaleApplication editSaleApplication = (EditSaleApplication) this.application;
                editSaleApplication.getGoodsToBeAdded().add(good);
            }
        }
    }

    public void removeGoodsFromSaleApplication(ArrayList<String> goodIds , Sale sale) throws Exception{
        Good goodToBeRemoved;
        for (String goodId : goodIds) {
            Good good = Manager.getGoodById(goodId);
            if ( !sale.getInSaleGoods().contains(good) ){
                throw new Exception("sale already does not contain this good aka : " + good.getProductName() );
            }
            else{
                EditSaleApplication editSaleApplication = (EditSaleApplication) this.application;
                editSaleApplication.getGoodsToBeRemoved().add(good);
            }
        }
    }

    public void removeProduct(){
        //TODO removes product from offerings
    }

    public void addProduct(String productId, String productName, Category category, Category subCategory, ArrayList<Characteristic> characteristics ){
        //TODO sends application to manager to add new product and adds it to sellers list if accepted
    }

    public void editProduct( String productId, String productName, Category category, Category subCategory, ArrayList<Characteristic> characteristics ){
        //TODO sends application to manager to edit product and adds it to sellers list if accepted
    }

    public void viewProductBuyers( String productId ){
        //TODO prints all buyers of a product
    }

    public static ArrayList<Sale> getSellersSales(Seller seller){
        return seller.getSaleList();
    }

    public static Sale getSellersSale(Seller seller , String saleId) throws Exception{
        for (Sale sale : seller.getSaleList()) {
            if(Integer.toString(sale.getSaleId()).equals(saleId)){
                return sale;
            }
        }
        throw new Exception("this sale either does not exist, or is not this sellers sale");
    }

    public void viewSalesHistory(){
        //TODO prints sellers sales history
    	ArrayList<SellLog> sellLogs = seller.getSellLog () ;
    	for (SellLog sellLog : sellLogs) {
    		System.out.println("Date :" + sellLog.getDate());
    		System.out.println("Money exchanged :" + sellLog.getMoneyExcganged());
    		System.out.println("Discount amount :" + sellLog.getDiscountAmount());
    		 ArrayList<Good> goodsExchanged = sellLog.getGoodsExchanged() :
    		System.out.println("Goods exchanged :");
    		 for (Good good :  goodsExchanged ) {
    			 System.out.println(good.getProductName()) ;
    		 }
    		 
    		System.out.println(" ");
    	}
    	
    }

    public void viewCompanyInformation(){
        //TODO prints sellers company information
    	System.out.println(loggedInSeller.getCompanyInformation()) ;
    }


}

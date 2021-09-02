package Model.Good;

import Model.Account.Seller;
import Model.Application.Application;

import java.util.ArrayList;

public class Good {
    private static int idCounter=0;

    private String productId;
    private String imageAddress;
    private ProductState productState;
    private boolean isAvailable;
    private ArrayList<Seller> sellers;
    private double price;
    private String productName;
    private Category category;
    private double averageRate;
    private int numOfRate;
    private String characteristics;
    private ArrayList<Rating> ratings;
    private ArrayList<Comment> comments;
    private int timesVisited;
    private int timestoUse;

    public int getTimestoUse() {
        return timestoUse;
    }

    public void setTimestoUse(int timestoUse) {
        this.timestoUse = timestoUse;
    }

    private double offpercent;

    public String getImageAddress() {
        return imageAddress;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public int getNumOfRate() {
        return numOfRate;
    }

    public void setNumOfRate(int numOfRate) {
        this.numOfRate = numOfRate;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Good(String productName,Category category, double price, String characteristics , ArrayList<Seller>sellers , ArrayList<Comment>comments , String imageAddress) {
        this.productId = ""+idCounter++;
        this.productName = productName;
        this.price=price;
        this.characteristics = characteristics;
        this.timesVisited = 0;
        this.isAvailable=true;
        this.category=category;
        this.productState=ProductState.TO_BE_CREATED;
        this.averageRate=0;
        this.sellers=sellers;
        this.comments=new ArrayList<>();
        this.comments=comments;
        this.imageAddress=imageAddress;
    }

    public void setOffpercent(double offpercent) {
        this.offpercent = offpercent;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addSeller(Seller seller){
        this.sellers.add(seller);
    }

    public void addRating(Rating rating){
        this.ratings.add(rating);
    }


    public int getTimesVisited() {
        return timesVisited;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public double getOffpercent() {
        return offpercent;
    }

    public String getProductId() {
        return productId;
    }

    public ProductState getProductState() {
        return productState;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public Category getCategory() {
        return category;
    }



    public String getCharacteristics() {
        return characteristics;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }


}

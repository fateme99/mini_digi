package Model.Good;

import Model.Application.Application;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;

public class Category {
    private String categoryName;
    private String characteristics;
    private ArrayList<Category> subCategories;
    private ArrayList<Good> goodsInCategory;
    private Category parentCategory;

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public void setGoodsInCategory(ArrayList<Good> goodsInCategory) {
        this.goodsInCategory = goodsInCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Category(String categoryName, String characteristics, ArrayList<Category>subCategories , ArrayList<Good> goodsInCategory, Category parentCategory) {
        this.categoryName = categoryName;
        this.characteristics = characteristics;
        this.goodsInCategory = goodsInCategory;
        this.subCategories=subCategories;
        this.parentCategory=parentCategory;
        Application.getInstance().getCategoories().put(this.categoryName,this);

    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addSubCategory(Category category){
        this.subCategories.add(category);
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }


    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public void addGood(Good good) {
        this.goodsInCategory.add(good);
    }

    public void removeGood(Good good) {
        this.goodsInCategory.remove(good);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public ArrayList<Good> getGoodsInCategory() {
        return goodsInCategory;
    }

    /*public Characteristic getCategoryCharacteristicByTitle(String characteristicTitle) throws Exception{
        for (Characteristic characteristic : this.characteristics) {
            if(characteristic.getCharacteristicName().equals(characteristicTitle)){
                return characteristic;
            }
        }
        throw new Exception("characteristic with this title does not exist");
    }*/
}

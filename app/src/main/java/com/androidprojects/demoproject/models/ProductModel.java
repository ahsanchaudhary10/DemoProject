package com.androidprojects.demoproject.models;

import java.util.List;

public class ProductModel {
    String key, category, size, productName, productColor;
    double priceForUsers, purchasedPrice;
    List<String> fileUrls;
    boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ProductModel(){}

    public ProductModel(String category, String size, String productName, String productColor, double priceForUsers, double purchasedPrice, List<String> fileUrls) {
        this.category = category;
        this.size = size;
        this.productName = productName;
        this.productColor = productColor;
        this.priceForUsers = priceForUsers;
        this.purchasedPrice = purchasedPrice;
        this.fileUrls = fileUrls;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public double getPriceForUsers() {
        return priceForUsers;
    }

    public void setPriceForUsers(double priceForUsers) {
        this.priceForUsers = priceForUsers;
    }

    public double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }
}

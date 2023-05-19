package com.example.s_waminnpanel;

public class ProductModel {
    private String id;
    private String product;
    private String description;

    private String price;
    private String img;
    private Boolean show;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public ProductModel(String id, String product, String description, String price, String img, Boolean show) {
        this.id = id;
        this.product = product;
        this.description = description;
        this.price = price;
        this.img = img;
        this.show = show;
    }

    public ProductModel() {
    }
}

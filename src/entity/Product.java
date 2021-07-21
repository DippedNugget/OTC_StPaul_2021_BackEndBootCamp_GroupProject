package entity;

//Product Entity
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Andrew Cham

public class Product {
    private int product_id;
    private String theme;
    private String set_name;
    private int piece_count;
    private int quantity;

    public Product (int product_id, String theme, String set_name, int piece_count, int quantity) {
        this.setProduct_id(product_id);
        this.setTheme(theme);
        this.setSet_name(set_name);
        this.setPiece_count(piece_count);
        this.setQuantity(quantity);
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public String getSet_name() {
        return set_name;
    }
    public void setSet_name(String set_name) {
        this.set_name = set_name;
    }
    public int getPiece_count() {
        return piece_count;
    }
    public void setPiece_count(int piece_count) {
        this.piece_count = piece_count;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
  }



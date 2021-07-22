package dao;

//Product Dao
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Andrew Cham

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {
  
	private Connection connection;
    private final String GET_PRODUCTS_QUERY = "SELECT * FROM  products";
    private final String GET_A_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE product_id = ?";
    private final String CREATE_NEW_PRODUCT_QUERY = "INSERT INTO products(theme, set_name, piece_count, quantity) VALUES (?, ?, ?, ?)";
    private final String UPDATE_PRODUCT_QUERY = "UPDATE products SET theme = ?, set_name = ?, piece_count = ?, quantity = ? WHERE product_id = ?";
    private final String DELETE_PRODUCT_BY_ID_QUERY = "DELETE FROM products WHERE product_id = ?";


    public ProductDao() {
        connection = DBConnection.getConnection();
    }
    public List<Product> getProducts() throws SQLException {
        ResultSet rs = connection.prepareStatement(GET_PRODUCTS_QUERY).executeQuery();
        List<Product> products = new ArrayList<Product>();

        while(rs.next()) {
            products.add(populateProducts(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
        }
        return products;
    }

    public Product getAProduct(int product_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_A_PRODUCT_BY_ID_QUERY);
        ps.setInt(1, product_id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return populateProducts(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
    }

    public void addProduct(String theme, String set_name, int piece_count, int quantity) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_PRODUCT_QUERY);
        ps.setString(1, theme);
        ps.setString(2, set_name);
        ps.setInt(3, piece_count);
        ps.setInt(4, quantity);
        ps.executeUpdate();
    }
    
    public void updateProduct(String theme, String set_name, int piece_count, int quantity, int product_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT_QUERY);
        ps.setString(1, theme);
        ps.setString(2, set_name);
        ps.setInt(3, piece_count);
        ps.setInt(4, quantity);
        ps.setInt(5, product_id);
        ps.executeUpdate();
    }
    public void deleteProduct(int product_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT_BY_ID_QUERY);
        ps.setInt(1, product_id);
        ps.executeUpdate();
    }

    private Product populateProducts(int product_id, String theme, String set_name, int piece_count, int quantity) throws SQLException {
        return new Product(product_id, theme, set_name, piece_count, quantity);
    }


}

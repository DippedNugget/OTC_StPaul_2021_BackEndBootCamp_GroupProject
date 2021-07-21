package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Products;

public class ProductsDao {
  private Connection connection;
    private final String GET_PRODUCTS_QUERY = "SELECT * FROM  products";
    private final String GET_A_PRODUCT_BY_ID_QUERY = "SELECT * FROM proucts WHERE product_id = ?";
    private final String CREATE_NEW_PRODUCT_QUERY = "INSERT INTO products(theme, name, piece_count, quantity) VALUES (?, ?, ?, ?)";
    private final String UPDATE_PRODUCT_QUERY = "UPDATE products SET theme = ?, name = ?, piece_count = ?, quantity = ?";
    private final String DELETE_PRODUCT_BY_ID_QUERY = "DELETE FROM products WHERE product_id = ?";


    public ProductsDao {
        connection = DBConnection.getConnetion();
    }
    public List<Products> getProducts() throws SQLException {
        ResultSet rs = connection.prepareStatement(GET_PRODUCTS_QUERY).executeQuery();
        List<Products> products = new ArrayList<Products>();

        while(rs.next()) {
            products.add(populateProducts(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
        }
        return products;
    }

    public Products getAProduct(int product_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_A_PRODUCT_BY_ID_QUERY);
        ps.setInt(1, product_id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return populateProducts(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
    }

    public void addProduct(String theme, String name, int piece_count, int quantity) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_PRODUCT_QUERY);
        ps.setString(1, theme);
        ps.setString(2, name);
        ps.setInt(3, piece_count);
        ps.setInt(4, quantity);
        ps.executeUpdate();
    }
    
    public void updateProduct(int product_id, String theme, String name, int piece_count, int quantity) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT_QUERY);
        ps.setInt(1, product_id);
        ps.setString(2, theme);
        ps.setString(3, name);
        ps.setInt(4, piece_count);
        ps.setInt(5, quantity);
        ps.executeUpdate();
    }
    public void deleteProduct(int product_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT_BY_ID_QUERY);
        ps.setInt(1, product_id);
        ps.executeUpdate();
    }

    private Products populateProducts(int product_id, String theme, String name, int piece_count, int quantity) throws SQLException {
        return new Products(product_id, theme, name, piece_count, quantity);
    }


}

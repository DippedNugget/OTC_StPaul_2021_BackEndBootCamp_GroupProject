package dao;

//Sales Stored SQL Queries
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Adam Fite

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Sales;

public class SalesDao {

	private Connection connection;
	private final String GET_PREVIOUS_SALES_QUERY = "SELECT * FROM sales";
	private final String GET_A_SINGLE_SALE_QUERY = "SELECT * FROM sales where sale_id =?";
	private final String ADD_SALE_BY_QUERY = "INSERT INTO sales(quantity, price, product_id, customer_id) VALUES(?,?,?,?)";
	private final String REMOVE_SALE_BY_ID_QUERY = "DELETE FROM sales WHERE sale_id = ?";
	private final String UPDATE_QUANTITY_BY_ID_QUERY = "UPDATE sales SET quantity = ? Where sale_id = ?";
	private final String UPDATE_PRICE_BY_ID_QUERY = "UPDATE sales SET price = ? Where sale_id = ?";
	private final String UPDATE_PRODUCT_BY_ID_QUERY = "UPDATE sales SET product_id = ? Where sale_id = ?";
	
	public SalesDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Sales> getSales() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_PREVIOUS_SALES_QUERY).executeQuery();
		List<Sales> sales = new ArrayList<Sales>();
		
		while (rs.next()) {
			sales.add(populateSale(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5)));
		}
		return sales;
	}
	
	public Sales getSingleSale(int sale_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_A_SINGLE_SALE_QUERY);
		ps.setInt(1, sale_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateSale(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5));
		}
		
	
	public void addSale(int quantity, double price, int product_id, int customer_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_SALE_BY_QUERY);
		ps.setInt(1, quantity);
		ps.setDouble(2, price);
		ps.setInt(3, product_id);
		ps.setInt(4, customer_id);
		ps.executeUpdate();
	}
	
	public void removeSale(int sale_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(REMOVE_SALE_BY_ID_QUERY);
		ps.setInt(1, sale_id);
		ps.executeUpdate();
	}
	
	public void updateQuantity(int quantity, int sale_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_QUANTITY_BY_ID_QUERY);
		ps.setInt(1, quantity);
		ps.setInt(2, sale_id);
		ps.executeUpdate();
	}
	
	public void updatePrice(double price, int sale_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_PRICE_BY_ID_QUERY);
		ps.setDouble(1, price);
		ps.setInt(2, sale_id);
		ps.executeUpdate();
	}
	
	public void updateProduct(int product_id, int sale_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT_BY_ID_QUERY);
		ps.setInt(1, product_id);
		ps.setInt(2, sale_id);
		ps.executeUpdate();
	}
	
	private Sales populateSale(int sale_id, int quantity, double price, int product_id, int customer_id) throws SQLException {
		return new Sales(sale_id, quantity, price, product_id, customer_id);
	}
}

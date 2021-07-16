package entity;

// Sales Entity File
//OTC_StPaul_2021_BackEndBootCamp_Group_Project
//Created by Adam Fite

public class Sales {

	private int sale_id;
	private int quantity;
	private double price;
	private int product_id;
	private int customer_id;
	
	public Sales( int sale_id,
			int quantity,
			double price,
			int product_id,
			int customer_id) {
		this.setSale_id(sale_id);
		this.setQuantity(quantity);
		this.setPrice(price);
		this.setProduct_id(product_id);
		this.setCustomer_id(customer_id);
	}

	public int getSale_id() {
		return sale_id;
	}

	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
}

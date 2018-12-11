package com.soinsoftware.vissa.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DocumentDetail extends CommonData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -398154587300495199L;
	
	@ManyToOne                           
	@JoinColumn(name = "document")        
	private Document document;   
	@ManyToOne                           
	@JoinColumn(name = "product_id")        
	private Product product;   
	private String description;
	private Integer quantity;
	private String subtotal;


	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Document getDocument() {
		return document;
	}


	public Product getProduct() {
		return product;
	}


	public String getDescription() {
		return description;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public String getSubtotal() {
		return subtotal;
	}


	public void setDocument(Document document) {
		this.document = document;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	
	

}

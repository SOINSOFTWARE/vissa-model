package com.soinsoftware.vissa.model;

import java.util.Date;

public class InventoryTransaction  extends CommonData {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2813843730168190728L;
	/**
	 * 
	 */
	
	private Product product;
	private Date transactionDate;
	private String transactionType;
	private Document document;
	private Integer initialStock;
	private Integer finalStock;
	public Product getProduct() {
		return product;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public Document getDocument() {
		return document;
	}
	public Integer getInitialStock() {
		return initialStock;
	}
	public Integer getFinalStock() {
		return finalStock;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public void setInitialStock(Integer initialStock) {
		this.initialStock = initialStock;
	}
	public void setFinalStock(Integer finalStock) {
		this.finalStock = finalStock;
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}
	

}

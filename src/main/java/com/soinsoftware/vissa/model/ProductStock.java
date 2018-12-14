package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;


public class ProductStock extends CommonData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9129864576513912670L;

	private Product product;
	private Integer stock;
	private Date stockDate;
	
	public ProductStock() {
		super();
	}
	
	public ProductStock(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		product = builder.product;
		stock = builder.stock;
		stockDate = builder.stockDate;
	}

	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}
	
	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Product product;
		private Integer stock;
		private Date stockDate;

		private Builder() {
		}

		private Builder(ProductStock stock) {
			id(stock.getId()).creationDate(stock.getCreationDate()).modifyDate(stock.getModifyDate())
					.archived(stock.isArchived()).product(stock.product)
					.stock(stock.stock).stockDate(stock.stockDate)
					;
		}

		public Builder id(BigInteger id) {
			this.id = id;
			return this;
		}

		public Builder creationDate(Date creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		public Builder modifyDate(Date modifyDate) {
			this.modifyDate = modifyDate;
			return this;
		}

		public Builder archived(boolean archived) {
			this.archived = archived;
			return this;
		}

		public Builder product(Product product) {
			this.product = product;
			return this;
		}

		public Builder stock(Integer stock) {
			this.stock = stock;
			return this;
		}

		public Builder stockDate(Date stockDate) {
			this.stockDate = stockDate;
			return this;
		}

		
		public ProductStock build() {
			return new ProductStock(this);
		}
	}

}

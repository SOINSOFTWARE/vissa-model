package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity(name = "inventory_transaction")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class InventoryTransaction extends CommonData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2813843730168190728L;
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;
	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;
	@Column(name = "initial_stock")
	private Integer initialStock;
	private Integer quantity;
	@Column(name = "final_stock")
	private Integer finalStock;

	public InventoryTransaction() {
		super();
	}

	public InventoryTransaction(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		product = builder.product;
		transactionType = builder.transactionType;
		document = builder.document;
		initialStock = builder.initialStock;
		quantity = builder.quantity;
		finalStock = builder.finalStock;
	}

	public InventoryTransaction(Product product, TransactionType transactionType, Document document,
			Integer initialStock, Integer quantity, Integer finalStock) {
		super();
		this.product = product;
		this.transactionType = transactionType;
		this.document = document;
		this.initialStock = initialStock;
		this.quantity = quantity;
		this.finalStock = finalStock;
	}

	public Product getProduct() {
		return product;
	}

	public TransactionType getTransactionType() {
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

	public void setTransactionType(TransactionType transactionType) {
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(InventoryTransaction inventoryTransaction) {
		return new Builder(inventoryTransaction);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Product product;
		private TransactionType transactionType;
		private Document document;
		private Integer initialStock;
		private Integer quantity;
		private Integer finalStock;

		private Builder() {
		}

		private Builder(InventoryTransaction inventory) {
			id(inventory.getId()).creationDate(inventory.getCreationDate()).modifyDate(inventory.getModifyDate())
					.archived(inventory.isArchived()).product(inventory.product)
					.transactionType(inventory.transactionType).document(inventory.document)
					.initialStock(inventory.initialStock).quantity(inventory.quantity).finalStock(inventory.finalStock);
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

		public Builder transactionType(TransactionType transactionType) {
			this.transactionType = transactionType;
			return this;
		}

		public Builder document(Document document) {
			this.document = document;
			return this;
		}

		public Builder initialStock(Integer initialStock) {
			this.initialStock = initialStock;
			return this;
		}

		public Builder quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder finalStock(Integer finalStock) {
			this.finalStock = finalStock;
			return this;
		}

		public InventoryTransaction build() {
			return new InventoryTransaction(this);
		}
	}
}

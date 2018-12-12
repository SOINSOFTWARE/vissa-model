// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
@Entity(name = "document_detail")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class DocumentDetail extends CommonData {

	private static final long serialVersionUID = -5272764778535491233L;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	private String description;
	private int quantity;
	@Column(name = "sub_total")
	private double subtotal;

	public DocumentDetail() {
		super();
	}

	public DocumentDetail(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		product = builder.product;
		description = builder.description;
		quantity = builder.quantity;
		subtotal = builder.subtotal;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(DocumentDetail documentDetail) {
		return new Builder(documentDetail);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Product product;
		private String description;
		private int quantity;
		private double subtotal;

		private Builder() {
		}

		private Builder(DocumentDetail documentDetail) {
			id(documentDetail.getId()).creationDate(documentDetail.getCreationDate())
					.modifyDate(documentDetail.getModifyDate()).archived(documentDetail.isArchived())
					.product(documentDetail.product).description(documentDetail.description)
					.quantity(documentDetail.quantity).subtotal(documentDetail.subtotal);
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

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder subtotal(double subtotal) {
			this.subtotal = subtotal;
			return this;
		}

		public DocumentDetail build() {
			return new DocumentDetail(this);
		}
	}
}
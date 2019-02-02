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

import com.soinsoftware.vissa.common.CommonsUtil;
import com.soinsoftware.vissa.exception.ModelValidationException;

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
	@JoinColumn(name = "document_id", nullable = false)
	private Document document;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	private String description;
	private String quantity;
	@Column(name = "sub_total")
	private Double subtotal;

	public DocumentDetail() {
		super();
	}

	public DocumentDetail(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		document = builder.document;
		product = builder.product;
		description = builder.description;
		quantity = builder.quantity;
		subtotal = builder.subtotal;
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

	public String getQuantity() {
		return quantity;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public String getSubtotalStr() {
		return String.valueOf(subtotal);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
		calculateSubtotal();
	}

	public void calculateSubtotal() {
		setSubtotalStr(String.valueOf(product.getSalePrice() * Integer.parseInt(quantity)));
		CommonsUtil.currentDocumentDetail = this;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public void setSubtotalStr(String subtotal) {
		this.subtotal = Double.parseDouble(subtotal);
	}

	@Override
	public void validate() {
		if (product == null) {
			throw new ModelValidationException("El producto es obligatorio.");
		} else {
			product.validate();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentDetail other = (DocumentDetail) obj;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
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
		private Document document;
		private Product product;
		private String description;
		private String quantity;
		private Double subtotal;

		private Builder() {
		}

		private Builder(DocumentDetail documentDetail) {
			id(documentDetail.getId()).creationDate(documentDetail.getCreationDate())
					.modifyDate(documentDetail.getModifyDate()).archived(documentDetail.isArchived())
					.document(documentDetail.document).product(documentDetail.product)
					.description(documentDetail.description).quantity(documentDetail.quantity)
					.subtotal(documentDetail.subtotal);
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

		public Builder document(Document document) {
			this.document = document;
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

		public Builder quantity(String quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder subtotal(Double subtotal) {
			this.subtotal = subtotal;
			return this;
		}

		public DocumentDetail build() {
			return new DocumentDetail(this);
		}
	}

	@Override
	public String toString() {
		return "DocumentDetail [product=" + product + ", description=" + description
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}

}
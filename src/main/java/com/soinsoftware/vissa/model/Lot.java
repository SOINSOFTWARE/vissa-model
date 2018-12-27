// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 21/12/2018
 */
@Entity(name = "lot")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Lot extends CommonData {

	private static final long serialVersionUID = 7247768338497639839L;

	@NaturalId
	private String code;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lot_date")
	private Date lotDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiration_date")
	private Date expirationDate;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	private Integer quantity;

	public Lot() {
		super();
	}

	public Lot(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		code = builder.code;
		name = builder.name;
		lotDate = builder.lotDate;
		expirationDate = builder.expirationDate;
		product = builder.product;
		quantity = builder.quantity;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Date getLotDate() {
		return lotDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public void validate() {
		if (code == null || code.trim().equals("")) {
			throw new ModelValidationException("El código es obligatorio.");
		}
		if (product == null) {
			throw new ModelValidationException("El producto es obligatorio.");
		} else {
			product.validate();
		}
		if (quantity <= 0) {
			throw new ModelValidationException("La cantidad no es valida.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Lot lot) {
		return new Builder(lot);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String code;
		private String name;
		private Date lotDate;
		private Date expirationDate;
		private Product product;
		private Integer quantity;

		private Builder() {
		}

		private Builder(Lot lot) {
			id(lot.getId()).creationDate(lot.getCreationDate()).modifyDate(lot.getModifyDate())
					.archived(lot.isArchived()).code(lot.code).name(lot.name).lotDate(lot.lotDate)
					.expirationDate(lot.expirationDate).product(lot.product).quantity(lot.quantity);
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

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder lotDate(Date lotDate) {
			this.lotDate = lotDate;
			return this;
		}

		public Builder expirationDate(Date expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}

		public Builder product(Product product) {
			this.product = product;
			return this;
		}

		public Builder quantity(Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public Lot build() {
			return new Lot(this);
		}
	}
}
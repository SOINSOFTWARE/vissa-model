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
	private Double quantity;
	@ManyToOne
	@JoinColumn(name = "measurement_unit_id")
	private MeasurementUnit measurementUnit;
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse;
	@Column(name = "new")
	private boolean isNew;

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
		warehouse = builder.warehouse;
		measurementUnit = builder.measurementUnit;
		isNew = builder.isNew;
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

	public Double getQuantity() {
		return quantity;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	@Override
	public void validate() {
		if (code == null || code.trim().equals("")) {
			throw new ModelValidationException("El código es obligatorio.");
		}
		if (warehouse == null) {
			throw new ModelValidationException("La bodega es obligatoria.");
		}
		if (quantity == null) {
			throw new ModelValidationException("La cantidad es obligatoria");
		}
		if (measurementUnit == null) {
			throw new ModelValidationException("La unidad de medida es obligatoria");
		}
		if (product == null) {
			throw new ModelValidationException("El producto es obligatorio.");
		} else {
			product.validate();
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
		private Double quantity;
		private MeasurementUnit measurementUnit;
		private Warehouse warehouse;
		private boolean isNew;

		private Builder() {
		}

		private Builder(Lot lot) {
			id(lot.getId()).creationDate(lot.getCreationDate()).modifyDate(lot.getModifyDate())
					.archived(lot.isArchived()).code(lot.code).name(lot.name).lotDate(lot.lotDate)
					.expirationDate(lot.expirationDate).product(lot.product).quantity(lot.quantity)
					.measurementUnit(lot.measurementUnit).warehouse(lot.warehouse).isNew(isNew);
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
			;
			return this;
		}

		public Builder expirationDate(Date expirationDate) {
			this.expirationDate = expirationDate;
			;
			return this;
		}

		public Builder product(Product product) {
			this.product = product;
			return this;
		}

		public Builder quantity(Double quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder measurementUnit(MeasurementUnit measurementUnit) {
			this.measurementUnit = measurementUnit;
			return this;
		}

		public Builder warehouse(Warehouse warehouse) {
			this.warehouse = warehouse;
			return this;
		}

		public Builder isNew(boolean isNew) {
			this.isNew = isNew;
			return this;
		}

		public Lot build() {
			return new Lot(this);
		}
	}

	@Override
	public String toString() {
		return "Lot [code=" + code + ", name=" + name + ", lotDate=" + lotDate + ", expirationDate=" + expirationDate
				+ ", product=" + product + ", quantity=" + quantity + ", warehouse=" + warehouse + "]";
	}

}
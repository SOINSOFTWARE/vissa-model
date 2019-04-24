
// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.common.CommonsConstants;
import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Lina Florez
 * @since 04/12/2018
 */

@Entity(name = "measurement_unit_lot")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class MeasurementUnitLot extends CommonData {

	private static final long serialVersionUID = 2130298664438036929L;

	@ManyToOne
	@JoinColumn(name = "lot_id")
	private Lot lot;
	@ManyToOne
	@JoinColumn(name = "measure_unit_product_id")
	private MeasurementUnitProduct muProduct;
	private Double stock;
	@Transient
	private MeasurementUnit measureUnit;

	public MeasurementUnitLot() {
		super();
	}

	public MeasurementUnitLot(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);

		lot = builder.lot;
		muProduct = builder.muProduct;
		stock = builder.stock;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public MeasurementUnitProduct getMuProduct() {
		return muProduct;
	}

	public void setMuProduct(MeasurementUnitProduct muProduct) {
		this.muProduct = muProduct;
	}

	public Double getStock() {
		return stock == null ? 0.0 : stock;
	}

	public String getStockStr() {
		return String.valueOf(getStock());
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public void setStockStr(String stockStr) {
		setStock(Double.valueOf(stockStr));
	}

	public MeasurementUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasurementUnit measureUnit) {
		CommonsConstants.MEASUREMENT_UNIT_LOT = this;
		this.measureUnit = measureUnit;
	}

	@Override
	public void validate() {
		if (lot == null) {
			throw new ModelValidationException("El lote es obligatorio para la UMxLote");
		}
		if (muProduct == null) {
			throw new ModelValidationException("La unidad de medida es obligatoria UMxLote");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(MeasurementUnitLot product) {
		return new Builder(product);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Lot lot;
		private MeasurementUnitProduct muProduct;
		private Double stock;

		private Builder() {
		}

		private Builder(MeasurementUnitLot muProduct) {
			id(muProduct.getId()).creationDate(muProduct.getCreationDate()).modifyDate(muProduct.getModifyDate())
					.archived(muProduct.isArchived()).lot(muProduct.lot).muProduct(muProduct.muProduct)
					.stock(muProduct.stock);
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

		public Builder lot(Lot lot) {
			this.lot = lot;
			return this;
		}

		public Builder muProduct(MeasurementUnitProduct muProduct) {
			this.muProduct = muProduct;
			return this;
		}

		public Builder stock(Double stock) {
			this.stock = stock;
			return this;
		}

		public MeasurementUnitLot build() {
			return new MeasurementUnitLot(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lot == null) ? 0 : lot.hashCode());
		result = prime * result + ((muProduct == null) ? 0 : muProduct.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
		MeasurementUnitLot other = (MeasurementUnitLot) obj;
		if (lot == null) {
			if (other.lot != null)
				return false;
		} else if (!lot.equals(other.lot))
			return false;
		if (muProduct == null) {
			if (other.muProduct != null)
				return false;
		} else if (!muProduct.equals(other.muProduct))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeasurementUnitLot [lot=" + lot + ", muProduct=" + muProduct + ", stock=" + stock + "]";
	}

}

// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
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

@Entity(name = "measurement_unit_product")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class MeasurementUnitProduct extends CommonData {

	private static final long serialVersionUID = 2130298664438036929L;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "measurement_unit_id")
	private MeasurementUnit measurementUnit;
	@Column(name = "purchase_price")
	private Double purchasePrice;
	@Column(name = "utility_prc")
	private Double utilityPrc;
	private Double utility;
	@Column(name = "sale_price")
	private Double salePrice;
	@Column(name = "sale_tax")
	private Double saleTax;
	@Column(name = "purchase_tax")
	private Double purchaseTax;
	@Column(name = "final_price")
	private Double finalPrice;
	private Double stock;
	@Column(name = "qty_equivalence")
	private Double qtyEquivalence;
	@ManyToOne
	@JoinColumn(name = "mu_equivalence_id")
	private MeasurementUnit muEquivalence;
	@Transient
	private Double saleTaxValue;
	@Transient
	private Double purchaseTaxValue;

	public MeasurementUnitProduct() {
		super();
	}

	public MeasurementUnitProduct(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);

		product = builder.product;
		measurementUnit = builder.measurementUnit;
		salePrice = builder.salePrice;
		purchasePrice = builder.purchasePrice;
		saleTax = builder.saleTax;
		purchaseTax = builder.purchaseTax;
		utilityPrc = builder.utilityPrc;
		utility = builder.utility;
		finalPrice = builder.finalPrice;
		stock = builder.stock;
		qtyEquivalence = builder.qtyEquivalence;
		muEquivalence = builder.muEquivalence;
	}

	public Product getProduct() {
		return product;
	}

	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}

	public Double getSalePrice() {
		return this.salePrice != null ? this.salePrice : 0.0;
	}

	public String getSalePriceStr() {
		return String.valueOf(getSalePrice());
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
		calculateFinalPrice();
	}

	public void setSalePriceStr(String salePriceStr) {
		setSalePrice(Double.valueOf(salePriceStr));
	}

	public Double getPurchasePrice() {
		return purchasePrice != null ? purchasePrice : 0.0;
	}

	public String getPurchasePriceStr() {
		return String.valueOf(getPurchasePrice());
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
		calculateSalePrice();
	}

	public void setPurchasePriceStr(String purchasePriceStr) {
		setPurchasePrice(Double.parseDouble(purchasePriceStr));
	}

	public Double getSaleTax() {
		return saleTax != null ? saleTax : 0.0;
	}

	public String getSaleTaxStr() {
		return String.valueOf(getSaleTax());
	}

	public void setSaleTax(Double saleTax) {
		this.saleTax = saleTax;
		calculateFinalPrice();
	}

	public void setSaleTaxStr(String saleTaxStr) {
		setSaleTax(Double.valueOf(saleTaxStr));
	}

	public Double getPurchaseTax() {
		return purchaseTax != null ? purchaseTax : 0.0;
	}

	public void setPurchaseTax(Double purchaseTax) {
		this.purchaseTax = purchaseTax;
		calculateSalePrice();
	}

	public String getPurchaseTaxStr() {
		return String.valueOf(getPurchaseTax());
	}

	public void setPurchaseTaxStr(String purchaseTaxStr) {
		setPurchaseTax(Double.valueOf(purchaseTaxStr));
	}

	public Double getSaleTaxValue() {
		setSaleTaxValue(getSalePrice() * (getSaleTax() / 100));
		return getSaleTaxValue();
	}

	public String getSaleTaxValueStr() {
		return String.valueOf(getSaleTaxValue());
	}

	public Double getPurchaseTaxValue() {
		setPurchaseTaxValue(getPurchasePrice() * (getPurchaseTax() / 100));
		return getPurchaseTaxValue();
	}

	public void setSaleTaxValue(Double saleTaxValue) {
		this.saleTaxValue = saleTaxValue;
	}

	public void setSaleTaxValueStr(String saleTaxValueStr) {
		this.saleTaxValue = Double.valueOf(saleTaxValueStr);
	}

	public void setPurchaseTaxValue(Double purchaseTaxValue) {
		this.purchaseTaxValue = purchaseTaxValue;
	}

	public Double getFinalPrice() {
		return finalPrice != null ? finalPrice : 0.0;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Double getUtilityPrc() {
		return utilityPrc != null ? utilityPrc : 0.0;
	}

	public String getUtilityPrcStr() {
		return String.valueOf(getUtilityPrc());
	}

	public void setUtilityPrc(Double utilityPrc) {
		this.utilityPrc = utilityPrc;
		calculateSalePrice();
	}

	public void setUtilityPrcStr(String utilityPrcStr) {
		setUtilityPrc(Double.parseDouble(utilityPrcStr));
	}

	public Double getUtility() {
		return utility != null ? utility : 0.0;
	}

	public String getUtilityStr() {
		return String.valueOf(getUtility());
	}

	public void setUtility(Double utility) {
		this.utility = utility;
		calculateSalePrice();
	}

	public void setUtilityStr(String utilityStr) {
		setUtility(Double.valueOf(utilityStr));
	}

	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;
		CommonsConstants.MEASUREMENT_UNIT_PRODUCT = this;
	}

	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnit.setName(measurementUnitName);
	}

	public String getStockStr() {
		return String.valueOf(getStock());
	}

	public Double getStock() {
		return stock != null ? stock : 0.0;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public void setStockStr(String stock) {
		setStock(Double.parseDouble(stock));
	}

	public Double getQtyEquivalence() {
		return qtyEquivalence;
	}

	public String getQtyEquivalenceStr() {
		return String.valueOf(getQtyEquivalence());
	}

	public MeasurementUnit getMuEquivalence() {
		return muEquivalence;
	}

	public void setQtyEquivalence(Double qtyEquivalence) {
		this.qtyEquivalence = qtyEquivalence;
	}

	public void setQtyEquivalenceStr(String qtyEquivalenceStr) {
		setQtyEquivalence(Double.parseDouble(qtyEquivalenceStr));
	}

	public void setMuEquivalence(MeasurementUnit muEquivalence) {
		this.muEquivalence = muEquivalence;
	}

	public void calculateSalePrice() {
		/*
		 * Double purchaseTaxTmp = getPurchasePrice() * getPurchaseTax() / 100; Double
		 * salePriceTmp = getPurchasePrice() + purchaseTaxTmp; salePriceTmp =
		 * salePriceTmp + (salePriceTmp * getUtilityPrc() / 100);
		 */
		// Precio de compra + utilidad
		// Double tax = (getPurchaseTax() / 100 + 1);
		Double priceWhithoutIVA = getPurchasePrice();
		priceWhithoutIVA = (double) Math.round(priceWhithoutIVA);
		// Precio de compra con utilidad + % Iva
		Double utility = (double) Math.round(priceWhithoutIVA * getUtilityPrc() / 100);
		Double salePriceTmp = priceWhithoutIVA + utility;
		salePriceTmp = (double) Math.round(salePriceTmp);
		setSalePrice(salePriceTmp);
	}

	public void calculateFinalPrice() {
		Double saleTaxTmp = (double) Math.round(getSalePrice() * getSaleTax() / 100);
		saleTaxTmp = (double) Math.round(saleTaxTmp);
		Double finalPriceTmp = getSalePrice() + saleTaxTmp;
		finalPriceTmp = (double) Math.round(finalPriceTmp);
		setFinalPrice(finalPriceTmp);
	}

	@Override
	public void validate() {
		if (product == null) {
			throw new ModelValidationException("El producto es obligatorio para la UM");
		}
		if (measurementUnit == null) {
			throw new ModelValidationException("La unidad de medida es obligatoria");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(MeasurementUnitProduct product) {
		return new Builder(product);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((product == null) ? 0 : product.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass())
			return false;
		MeasurementUnitProduct other = (MeasurementUnitProduct) obj;

		if (measurementUnit == null) {
			if (other.measurementUnit != null)
				return false;
		} else if (!measurementUnit.equals(other.measurementUnit))
			return false;

		return true;
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Product product;
		private MeasurementUnit measurementUnit;
		private Double salePrice;
		private Double purchasePrice;
		private Double saleTax;
		private Double purchaseTax;
		private Double utilityPrc;
		private Double utility;
		private Double finalPrice;
		private Double stock;
		private Double qtyEquivalence;
		private MeasurementUnit muEquivalence;

		private Builder() {
		}

		private Builder(MeasurementUnitProduct muProduct) {
			id(muProduct.getId()).creationDate(muProduct.getCreationDate()).modifyDate(muProduct.getModifyDate())
					.archived(muProduct.isArchived()).product(muProduct.product)
					.measurementUnit(muProduct.measurementUnit).salePrice(muProduct.salePrice)
					.purchasePrice(muProduct.purchasePrice).saleTax(muProduct.saleTax)
					.purchaseTax(muProduct.purchaseTax).utilityPrc(muProduct.utilityPrc).utility(muProduct.utility)
					.finalPrice(muProduct.finalPrice).stock(muProduct.stock).qtyEquivalence(muProduct.qtyEquivalence)
					.muEquivalence(muProduct.muEquivalence);
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

		public Builder measurementUnit(MeasurementUnit measurementUnit) {
			this.measurementUnit = measurementUnit;
			return this;
		}

		public Builder salePrice(Double salePrice) {
			this.salePrice = salePrice;
			return this;
		}

		public Builder purchasePrice(Double purchasePrice) {
			this.purchasePrice = purchasePrice;
			return this;
		}

		public Builder saleTax(Double saleTax) {
			this.saleTax = saleTax;
			return this;
		}

		public Builder purchaseTax(Double purchaseTax) {
			this.purchaseTax = purchaseTax;
			return this;
		}

		public Builder utilityPrc(Double utilityPrc) {
			this.utilityPrc = utilityPrc;
			return this;
		}

		public Builder utility(Double utility) {
			this.utility = utility;
			return this;
		}

		public Builder finalPrice(Double finalPrice) {
			this.finalPrice = finalPrice;
			return this;
		}

		public Builder stock(Double stock) {
			this.stock = stock;
			return this;
		}

		public Builder qtyEquivalence(Double qtyEquivalence) {
			this.qtyEquivalence = qtyEquivalence;
			return this;
		}

		public Builder muEquivalence(MeasurementUnit muEquivalence) {
			this.muEquivalence = muEquivalence;
			return this;
		}

		public MeasurementUnitProduct build() {
			return new MeasurementUnitProduct(this);
		}
	}

	@Override
	public String toString() {
		return "MeasurementUnitProduct [product=" + product + ", measurementUnit=" + measurementUnit
				+ ", purchasePrice=" + purchasePrice + ", utility=" + utility + ", salePrice=" + salePrice
				+ ", saleTax=" + saleTax + ", purchaseTax=" + purchaseTax + ", finalPrice=" + finalPrice
				+ ", saleTaxValue=" + saleTaxValue + ", purchaseTaxValue=" + purchaseTaxValue + ", stock=" + stock
				+ "]";
	}

}
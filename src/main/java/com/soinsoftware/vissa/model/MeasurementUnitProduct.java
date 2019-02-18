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
	private Double utility;
	@Column(name = "sale_price")
	private Double salePrice;
	@Column(name = "sale_tax")
	private Double saleTax;
	@Column(name = "purchase_tax")
	private Double purchaseTax;
	@Column(name = "final_price")
	private Double finalPrice;
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
		utility = builder.utility;
		finalPrice = builder.finalPrice;
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
		saleTaxValue = 0.0;
		if ((saleTax != null && !saleTax.equals(0.0) && (salePrice != null && !salePrice.equals(0.0)))) {
			saleTaxValue = salePrice * (saleTax / 100);
		}
		return saleTaxValue;
	}

	public String getSaleTaxValueStr() {
		return String.valueOf(getSaleTaxValue());
	}

	public Double getPurchaseTaxValue() {
		purchaseTaxValue = 0.0;
		if ((purchaseTax != null && !purchaseTax.equals(0.0)
				&& (purchasePrice != null && !purchasePrice.equals(0.0)))) {
			purchaseTaxValue = purchasePrice * (purchaseTax / 100);
		}
		return purchaseTaxValue;
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
	}

	public void setMeasurementUnitName(String measurementUnitName) {
		this.measurementUnit.setName(measurementUnitName);
	}

	public void calculateSalePrice() {
		Double purchaseTaxTmp = getPurchasePrice() * getPurchaseTax() / 100;
		Double salePriceTmp = getPurchasePrice() + purchaseTaxTmp + getUtility();
		setSalePrice(salePriceTmp);
	}

	public void calculateFinalPrice() {
		Double saleTaxTmp = getSalePrice() * getSaleTax() / 100;
		Double finalPriceTmp = getSalePrice() + saleTaxTmp;
		setFinalPrice(finalPriceTmp);
	}

	@Override
	public void validate() {

	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(MeasurementUnitProduct product) {
		return new Builder(product);
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
		private Double utility;
		private Double finalPrice;

		private Builder() {
		}

		private Builder(MeasurementUnitProduct product) {
			id(product.getId()).creationDate(product.getCreationDate()).modifyDate(product.getModifyDate())
					.archived(product.isArchived()).product(product.product).measurementUnit(product.measurementUnit)
					.salePrice(product.salePrice).purchasePrice(product.purchasePrice).saleTax(product.saleTax)
					.purchaseTax(product.purchaseTax).utility(product.utility).finalPrice(product.finalPrice);
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

		public Builder utility(Double utility) {
			this.utility = utility;
			return this;
		}

		public Builder finalPrice(Double finalPrice) {
			this.finalPrice = finalPrice;
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
				+ ", saleTaxValue=" + saleTaxValue + ", purchaseTaxValue=" + purchaseTaxValue + "]";
	}
	
	

}
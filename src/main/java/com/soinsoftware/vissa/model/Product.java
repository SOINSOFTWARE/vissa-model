// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Lina Florez
 * @since 04/12/2018
 */
@Entity(name = "product")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Product extends CommonData {

	private static final long serialVersionUID = 2130298664438036929L;

	@NaturalId
	private String code;
	private String name;
	private String description;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory category;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private ProductType type;
	@ManyToOne
	@JoinColumn(name = "measurement_unit_id")
	private MeasurementUnit measurementUnit;
	@Column(name = "ean_code")
	private String eanCode;
	@Column(name = "sale_price")
	private Double salePrice;
	@Column(name = "purchase_price")
	private Double purchasePrice;
	@Column(name = "sale_tax")
	private Double saleTax;
	@Column(name = "purchase_tax")
	private Double purchaseTax;
	private Integer stock;
	@Column(name = "stock_date")
	private Date stockDate;

	public Product() {
		super();
	}

	public Product(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		code = builder.code;
		name = builder.name;
		description = builder.description;
		category = builder.category;
		type = builder.type;
		measurementUnit = builder.measurementUnit;
		eanCode = builder.eanCode;
		salePrice = builder.salePrice;
		purchasePrice = builder.purchasePrice;
		saleTax = builder.saleTax;
		purchaseTax = builder.purchaseTax;
		stock = builder.stock;
		stockDate = builder.stockDate;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public ProductType getType() {
		return type;
	}

	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}

	public String getEanCode() {
		return eanCode;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public Double getSaleTax() {
		return saleTax;
	}

	public Double getPurchaseTax() {
		return purchaseTax;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	@Override
	public void validate() {
		if (code == null || code.trim().equals("")) {
			throw new ModelValidationException("El código es obligatorio");
		}
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Product product) {
		return new Builder(product);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String code;
		private String name;
		private String description;
		private ProductCategory category;
		private ProductType type;
		private MeasurementUnit measurementUnit;
		private String eanCode;
		private Double salePrice;
		private Double purchasePrice;
		private Double saleTax;
		private Double purchaseTax;
		private Integer stock;
		private Date stockDate;

		private Builder() {
		}

		private Builder(Product product) {
			id(product.getId()).creationDate(product.getCreationDate()).modifyDate(product.getModifyDate())
					.archived(product.isArchived()).code(product.code).name(product.name)
					.description(product.description).category(product.category).type(product.type)
					.measurementUnit(product.measurementUnit).eanCode(product.eanCode).salePrice(product.salePrice)
					.purchasePrice(product.purchasePrice).saleTax(product.saleTax).purchaseTax(product.purchaseTax)
					.stock(product.stock).stockDate(product.stockDate);
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

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder category(ProductCategory category) {
			this.category = category;
			return this;
		}

		public Builder type(ProductType type) {
			this.type = type;
			return this;
		}

		public Builder measurementUnit(MeasurementUnit measurementUnit) {
			this.measurementUnit = measurementUnit;
			return this;
		}

		public Builder eanCode(String eanCode) {
			this.eanCode = eanCode;
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

		public Builder stock(Integer stock) {
			this.stock = stock;
			return this;
		}

		public Builder stockDate(Date stockDate) {
			this.stockDate = stockDate;
			return this;
		}

		public Product build() {
			return new Product(this);
		}
	}
}
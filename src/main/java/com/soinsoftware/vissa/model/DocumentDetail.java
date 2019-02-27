// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
	@Transient
	private String oldQuantity;
	@Transient
	private String diffQuantity;
	@ManyToOne
	@JoinColumn(name = "mu_product_id")
	MeasurementUnitProduct measurementUnitProduct;
	@Transient
	private List<MeasurementUnit> measurementUnitList;
	@ManyToOne
	@JoinColumn(name = "measurement_unit_id")
	private MeasurementUnit measurementUnit;
	private Double price;
	private Double tax;
	@Transient
	private Double taxValue;
	private Double discount;
	@Column(name = "sub_total")
	private Double subtotal;
	@Transient
	private ETransactionType transactionType;
	@Transient
	private String code;
	@Transient
	private int index;

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
		measurementUnit = builder.measurementUnit;
		measurementUnitProduct = builder.measurementUnitProduct;
		price = builder.price;
		tax = builder.tax;
		discount = builder.discount;
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
		return String.valueOf(getSubtotal());
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
		CommonsUtil.CURRENT_DOCUMENT_DETAIL = this;
		calculateSubtotal();
	}

	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}

	public Double getPrice() {
		return price != null ? price : 0.0;
	}

	public String getPriceStr() {
		return String.valueOf(getPrice());
	}

	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;
		CommonsUtil.CURRENT_DOCUMENT_DETAIL = this;
	}

	public void setPrice(Double price) {
		this.price = price;
		calculateSubtotal();
		setMeasurementUnitProduct(CommonsUtil.MEASUREMENT_UNIT_PRODUCT);
	}

	public void setPriceStr(String priceStr) {
		setPrice(Double.valueOf(priceStr));
	}

	public List<MeasurementUnit> getMeasurementUnitList() {
		return measurementUnitList;
	}

	public void setMeasurementUnitList(List<MeasurementUnit> measurementUnitList) {
		this.measurementUnitList = measurementUnitList;
	}

	public Double getTax() {
		return tax != null ? tax : 0.0;
	}

	public void setTax(Double tax) {
		this.tax = tax;
		calculateSubtotal();
	}

	public String getTaxStr() {
		return String.valueOf(getTax());
	}

	public void setTaxStr(String taxStr) {
		setTax(Double.valueOf(taxStr));
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public void setSubtotalStr(String subtotal) {
		this.subtotal = Double.parseDouble(subtotal);
	}

	public Double getDiscount() {
		return discount != null ? discount : 0.0;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
		calculateSubtotal();
	}

	public String getDiscountStr() {
		return String.valueOf(getDiscount());
	}

	public void setDiscountStr(String discount) {
		setDiscount(Double.valueOf(discount));
	}

	public MeasurementUnitProduct getMeasurementUnitProduct() {
		return measurementUnitProduct;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setMeasurementUnitProduct(MeasurementUnitProduct measurementUnitProduct) {
		this.measurementUnitProduct = measurementUnitProduct;
	}

	public String getOldQuantity() {
		return oldQuantity;
	}

	public void setOldQuantity(String oldQuantity) {
		this.oldQuantity = oldQuantity;
	}

	public ETransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(ETransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getDiffQuantity() {
		return diffQuantity;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public void setDiffQuantity(String diffQuantity) {
		this.diffQuantity = diffQuantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		CommonsUtil.CURRENT_DOCUMENT_DETAIL = this;

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Double getTaxValue() {
		setTaxValue(getPrice() * (getTax() / 100));
		return taxValue;
	}

	public void setTaxValue(Double taxValue) {
		this.taxValue = taxValue;
	}

	public void calculateSubtotal() {
		if (quantity != null && !quantity.isEmpty()) {
			Double priceWithTax = 0.0;

			priceWithTax = getPrice() + (getPrice() * getTax() / 100);

			setSubtotalStr(String.valueOf((priceWithTax - getDiscount()) * Double.parseDouble(quantity)));
		}
		CommonsUtil.CURRENT_DOCUMENT_DETAIL = this;
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
		private MeasurementUnit measurementUnit;
		private MeasurementUnitProduct measurementUnitProduct;
		private Double price;
		private Double tax;
		private Double discount;

		private Builder() {
		}

		private Builder(DocumentDetail documentDetail) {
			id(documentDetail.getId()).creationDate(documentDetail.getCreationDate())
					.modifyDate(documentDetail.getModifyDate()).archived(documentDetail.isArchived())
					.document(documentDetail.document).product(documentDetail.product)
					.description(documentDetail.description).quantity(documentDetail.quantity)
					.subtotal(documentDetail.subtotal).measurementUnit(documentDetail.measurementUnit)
					.measurementUnitProduct(documentDetail.measurementUnitProduct).price(documentDetail.price)
					.tax(documentDetail.tax).discount(documentDetail.discount);
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

		public Builder measurementUnit(MeasurementUnit measurementUnit) {
			this.measurementUnit = measurementUnit;
			return this;
		}

		public Builder measurementUnitProduct(MeasurementUnitProduct measurementUnitProduct) {
			this.measurementUnitProduct = measurementUnitProduct;
			return this;
		}

		public Builder price(Double price) {
			this.price = price;
			return this;
		}

		public Builder tax(Double tax) {
			this.tax = price;
			return this;
		}

		public Builder discount(Double discount) {
			this.discount = discount;
			return this;
		}

		public DocumentDetail build() {
			return new DocumentDetail(this);
		}
	}

	@Override
	public String toString() {
		return "DocumentDetail [document=" + document + ", product=" + product + ", description=" + description
				+ ", quantity=" + quantity + ", measurementUnitList=" + measurementUnitList + ", measurementUnit="
				+ measurementUnit + ", price=" + price + ", tax=" + tax + ", discount=" + discount + ", subtotal="
				+ subtotal + "]";
	}
}
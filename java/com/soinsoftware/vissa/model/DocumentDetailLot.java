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

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
@Entity(name = "document_detail_lot")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class DocumentDetailLot extends CommonData {

	private static final long serialVersionUID = -5272764778535491233L;

	@ManyToOne
	@JoinColumn(name = "document_detail_id", nullable = false)
	private DocumentDetail documentDetail;
	@ManyToOne
	@JoinColumn(name = "lot_id", nullable = false)
	private Lot lot;
	@Column(name = "initial_stock_lot")
	private Double initialStockLot;
	private Double quantity;
	@Column(name = "final_stock_lot")
	private Double finalStockLot;

	public DocumentDetailLot() {
		super();
	}

	public DocumentDetailLot(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		documentDetail = builder.documentDetail;
		lot = builder.lot;
		initialStockLot = builder.initialStockLot;
		quantity = builder.quantity;
		finalStockLot = builder.finalStockLot;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DocumentDetail getDocumentDetail() {
		return documentDetail;
	}

	public void setDocumentDetail(DocumentDetail documentDetail) {
		this.documentDetail = documentDetail;
	}

	public Lot getLot() {
		return lot;
	}

	public Double getInitialStockLot() {
		return initialStockLot == null ? 0 : initialStockLot;
	}

	public Double getQuantity() {
		return quantity == null ? 0 : quantity;
	}

	public Double getFinalStockLot() {
		return finalStockLot == null ? 0 : finalStockLot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public void setInitialStockLot(Double initialStockLot) {
		this.initialStockLot = initialStockLot;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setFinalStockLot(Double finalStockLot) {
		this.finalStockLot = finalStockLot;
	}

	@Override
	public void validate() {
		if (documentDetail == null) {
			throw new ModelValidationException("El producto es obligatorio.");
		}
		if (lot == null) {
			throw new ModelValidationException("El lote es obligatorio.");
		}
		if (initialStockLot == null) {
			throw new ModelValidationException("La cantidad inicial del lote es obligatorio.");
		}
		if (finalStockLot == null) {
			throw new ModelValidationException("La cantidad final del lote es obligatorio.");
		}
		if (quantity == null) {
			throw new ModelValidationException("La cantidad de moviemiento es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(DocumentDetailLot documentDetail) {
		return new Builder(documentDetail);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((documentDetail == null) ? 0 : documentDetail.hashCode());
		result = prime * result + ((lot == null) ? 0 : lot.hashCode());
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
		DocumentDetailLot other = (DocumentDetailLot) obj;
		if (documentDetail == null) {
			if (other.documentDetail != null)
				return false;
		} else if (!documentDetail.equals(other.documentDetail))
			return false;
		if (lot == null) {
			if (other.lot != null)
				return false;
		} else if (!lot.equals(other.lot))
			return false;
		return true;
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private DocumentDetail documentDetail;
		private Lot lot;
		private Double initialStockLot;
		private Double quantity;
		private Double finalStockLot;

		private Builder() {
		}

		private Builder(DocumentDetailLot documentDetailLot) {
			id(documentDetailLot.getId()).creationDate(documentDetailLot.getCreationDate())
					.modifyDate(documentDetailLot.getModifyDate()).archived(documentDetailLot.isArchived())
					.documentDetail(documentDetailLot.documentDetail).lot(documentDetailLot.lot)
					.initialStockLot(documentDetailLot.initialStockLot).quantity(documentDetailLot.quantity)
					.finalStockLot(documentDetailLot.finalStockLot);
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

		public Builder documentDetail(DocumentDetail documentDetail) {
			this.documentDetail = documentDetail;
			return this;
		}

		public Builder lot(Lot lot) {
			this.lot = lot;
			return this;
		}

		public Builder initialStockLot(Double initialStockLot) {
			this.initialStockLot = initialStockLot;
			return this;
		}

		public Builder quantity(Double quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder finalStockLot(Double finalStockLot) {
			this.finalStockLot = finalStockLot;
			return this;
		}

		public DocumentDetailLot build() {
			return new DocumentDetailLot(this);
		}
	}

	@Override
	public String toString() {
		return "DocumentDetailLot [documentDetail=" + documentDetail + ", lot=" + lot + ", initialStockLot="
				+ initialStockLot + ", quantity=" + quantity + ", finalStockLot=" + finalStockLot + "]";
	}

}
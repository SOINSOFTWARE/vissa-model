// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 21/12/2018
 */
@Entity(name = "collection")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Collection extends CommonData {

	private static final long serialVersionUID = 7247768338497639839L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "collection_date")
	private Date collectionDate;
	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;
	@Column(name = "initial_balance")
	private BigDecimal initialBalance;
	private BigDecimal fee;
	@Column(name = "final_balance")
	private BigDecimal finalBalance;
	@Column(name = "payment_method_id")
	private PaymentMethod paymentMethod;

	public Collection() {
		super();
	}

	public Collection(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		collectionDate = builder.collectionDate;
		document = builder.document;
		initialBalance = builder.initialBalance;
		fee = builder.fee;
		finalBalance = builder.finalBalance;
		paymentMethod = builder.paymentMethod;
	}

	@Override
	public void validate() {
		if (collectionDate == null) {
			throw new ModelValidationException("La fecha del recaudo es obligatoria.");
		}
		if (document == null) {
			throw new ModelValidationException("La factura es obligatoria.");
		}
		if (fee == null) {
			throw new ModelValidationException("El valor del recaudo es obligatorio.");
		}
	}

	
	public Date getCollectionDate() {
		return collectionDate;
	}

	public Document getDocument() {
		return document;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public BigDecimal getFinalBalance() {
		return finalBalance;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Collection lot) {
		return new Builder(lot);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Date collectionDate;
		private Document document;
		private BigDecimal initialBalance;
		private BigDecimal fee;
		private BigDecimal finalBalance;
		private PaymentMethod paymentMethod;

		private Builder() {
		}

		private Builder(Collection expense) {
			id(expense.getId()).creationDate(expense.getCreationDate()).modifyDate(expense.getModifyDate())
					.archived(expense.isArchived()).collectionDate(expense.collectionDate).document(expense.document)
					.initialBalance(expense.initialBalance).fee(expense.fee).finalBalance(expense.finalBalance)
					.paymentMethod(expense.paymentMethod);
		}

		public Builder id(BigInteger id) {
			this.id = id;
			return this;
		}

		public Builder creationDate(Date expenseDate) {
			this.creationDate = expenseDate;
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

		public Builder collectionDate(Date collectionDate) {
			this.collectionDate = collectionDate;
			return this;
		}

		public Builder document(Document document) {
			this.document = document;
			return this;
		}

		public Builder description(BigDecimal initialBalance) {
			this.initialBalance = initialBalance;
			return this;
		}

		public Builder initialBalance(BigDecimal initialBalance) {
			this.initialBalance = initialBalance;
			return this;
		}
		
		public Builder fee(BigDecimal fee) {
			this.fee = fee;
			return this;
		}

		public Builder finalBalance(BigDecimal finalBalance) {
			this.finalBalance = finalBalance;
			return this;
		}

		public Builder paymentMethod(PaymentMethod paymentMethod) {
			this.paymentMethod = paymentMethod;
			return this;
		}

		public Collection build() {
			return new Collection(this);
		}
	}

}
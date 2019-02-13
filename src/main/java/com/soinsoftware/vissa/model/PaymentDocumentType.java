// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
@Entity(name = "payment_document_type")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class PaymentDocumentType extends CommonData {

	private static final long serialVersionUID = 7247768338497639839L;

	@ManyToOne
	@JoinColumn(name = "document_type")
	private DocumentType documentType;
	@ManyToOne
	@JoinColumn(name = "payment_type")
	private PaymentType paymentType;

	public PaymentDocumentType() {
		super();
	}

	public PaymentDocumentType(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		documentType = builder.documentType;
		paymentType = builder.paymentType;

	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	@Override
	public void validate() {

	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(PaymentDocumentType documentType) {
		return new Builder(documentType);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private DocumentType documentType;
		private PaymentType paymentType;

		private Builder() {
		}

		private Builder(PaymentDocumentType paymentDocumentType) {
			id(paymentDocumentType.getId()).creationDate(paymentDocumentType.getCreationDate())
					.modifyDate(paymentDocumentType.getModifyDate()).archived(paymentDocumentType.isArchived())
					.documentType(paymentDocumentType.documentType).paymentType(paymentDocumentType.paymentType);
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

		public Builder documentType(DocumentType documentType) {
			this.documentType = documentType;
			return this;
		}

		public Builder paymentType(PaymentType paymentType) {
			this.paymentType = paymentType;
			return this;
		}

		public PaymentDocumentType build() {
			return new PaymentDocumentType(this);
		}
	}

}
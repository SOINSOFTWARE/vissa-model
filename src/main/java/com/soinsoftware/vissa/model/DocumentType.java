// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
@Entity(name = "document_type")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class DocumentType extends CommonData {

	private static final long serialVersionUID = 7247768338497639839L;

	@NaturalId
	private String code;
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;

	public DocumentType() {
		super();
	}

	public DocumentType(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		code = builder.code;
		name = builder.name;
		transactionType = builder.transactionType;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	@Override
	public void validate() {
		if (code == null || code.trim().equals("")) {
			throw new ModelValidationException("El código es obligatorio.");
		}
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(DocumentType documentType) {
		return new Builder(documentType);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String code;
		private String name;
		private TransactionType transactionType;

		private Builder() {
		}

		private Builder(DocumentType documentType) {
			id(documentType.getId()).creationDate(documentType.getCreationDate())
					.modifyDate(documentType.getModifyDate()).archived(documentType.isArchived())
					.code(documentType.code).name(documentType.name).transactionType(documentType.transactionType);
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

		public Builder transactionType(TransactionType transactionType) {
			this.transactionType = transactionType;
			return this;
		}

		public DocumentType build() {
			return new DocumentType(this);
		}
	}
}
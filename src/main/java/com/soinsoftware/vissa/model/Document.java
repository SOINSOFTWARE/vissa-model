// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * @since 11/12/2018
 */
@Entity(name = "document")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Document extends CommonData {

	private static final long serialVersionUID = -3704226411029215206L;

	@NaturalId
	private String code;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private DocumentType documentType;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "document_date")
	private Date documentDate;
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentType paymentType;
	@ManyToOne
	@JoinColumn(name = "payment_method_id")
	private PaymentMethod paymentMethod;
	@Column(name = "payment_term")
	private String paymentTerm;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiration_date")
	private Date expirationDate;
	@ManyToOne
	@JoinColumn(name = "currency_id")
	private Currency currency;
	@Column(name = "total_value_no_tax")
	private double totalValueNoTax;
	@Column(name = "total_value")
	private double totalValue;
	private String reference;
	@OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DocumentDetail> details = new HashSet<>();

	public Document() {
		super();
	}

	public Document(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		code = builder.code;
		documentType = builder.documentType;
		person = builder.person;
		documentDate = builder.documentDate;
		paymentType = builder.paymentType;
		paymentMethod = builder.paymentMethod;
		paymentTerm = builder.paymentTerm;
		expirationDate = builder.expirationDate;
		currency = builder.currency;
		totalValueNoTax = builder.totalValueNoTax;
		totalValue = builder.totalValue;
		reference = builder.reference;
		details = builder.details;
	}

	public String getCode() {
		return code;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public Person getPerson() {
		return person;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public double getTotalValueNoTax() {
		return totalValueNoTax;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public String getReference() {
		return reference;
	}

	public Set<DocumentDetail> getDetails() {
		return details;
	}

	@Override
	public void validate() {
		if (code == null || code.trim().equals("")) {
			throw new ModelValidationException("El código es obligatorio.");
		}else if (documentType == null) {
			throw new ModelValidationException("El tipo es obligatorio.");
		}else if (person == null) {
				throw new ModelValidationException("El tercero (proveedor/cliente) es obligatorio.");
		} else if (documentDate == null) {
			throw new ModelValidationException("La fecha de la factura es obligatoria.");
		}else {
			documentType.validate();
		}		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Document other = (Document) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Document document) {
		return new Builder(document);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String code;
		private DocumentType documentType;
		private Person person;
		private Date documentDate;
		private PaymentType paymentType;
		private PaymentMethod paymentMethod;
		private String paymentTerm;
		private Date expirationDate;
		private Currency currency;
		private double totalValueNoTax;
		private double totalValue;
		private String reference;
		private Set<DocumentDetail> details = new HashSet<>();

		private Builder() {
		}

		private Builder(Document document) {
			id(document.getId()).creationDate(document.getCreationDate()).modifyDate(document.getModifyDate())
					.archived(document.isArchived()).code(document.code).documentType(document.documentType)
					.person(document.person).documentDate(document.documentDate).paymentType(document.paymentType)
					.paymentMethod(document.paymentMethod).paymentTerm(document.paymentTerm)
					.expirationDate(document.expirationDate).currency(document.currency)
					.totalValueNoTax(document.totalValueNoTax).totalValue(document.totalValue)
					.reference(document.reference).details(document.details);
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

		public Builder documentType(DocumentType documentType) {
			this.documentType = documentType;
			return this;
		}

		public Builder person(Person person) {
			this.person = person;
			return this;
		}

		public Builder documentDate(Date documentDate) {
			this.documentDate = documentDate;
			return this;
		}

		public Builder paymentType(PaymentType paymentType) {
			this.paymentType = paymentType;
			return this;
		}

		public Builder paymentMethod(PaymentMethod paymentMethod) {
			this.paymentMethod = paymentMethod;
			return this;
		}

		public Builder paymentTerm(String paymentTerm) {
			this.paymentTerm = paymentTerm;
			return this;
		}

		public Builder expirationDate(Date expirationDate) {
			this.expirationDate = expirationDate;
			return this;
		}

		public Builder currency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public Builder totalValueNoTax(double totalValueNoTax) {
			this.totalValueNoTax = totalValueNoTax;
			return this;
		}

		public Builder totalValue(double totalValue) {
			this.totalValue = totalValue;
			return this;
		}

		public Builder reference(String reference) {
			this.reference = reference;
			return this;
		}

		public Builder details(Set<DocumentDetail> details) {
			this.details = details;
			return this;
		}

		public Document build() {
			return new Document(this);
		}
	}
}
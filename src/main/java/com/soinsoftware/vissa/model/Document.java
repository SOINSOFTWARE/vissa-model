// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
	private String resolution;
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
	private Double totalValueNoTax;
	@Column(name = "total_value")
	private Double totalValue;
	private String reference;
	@ManyToOne
	@JoinColumn(name = "status_id")
	private DocumentStatus status;
	@ManyToOne
	@JoinColumn(name = "salesman_id")
	private Person salesman;
	private String cash;
	@Column(name = "pay_value")
	private Double payValue;
	@Column(name = "payment_status")
	private String paymentStatus;
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
		resolution = builder.resolution;
		status = builder.status;
		salesman = builder.salesman;
		cash = builder.cash;
		payValue = builder.payValue;
		paymentStatus = builder.paymentStatus;
		details = forHibernateDetails(builder.details);
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

	public Double getTotalValueNoTax() {
		return totalValueNoTax;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public String getReference() {
		return reference;
	}

	public DocumentStatus getStatus() {
		return status;
	}

	public Person getSalesman() {
		return salesman;
	}

	public String getCash() {
		return cash;
	}

	public String getResolution() {
		return resolution;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}

	public Set<DocumentDetail> getDetails() {
		return details;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public Double getPayValue() {
		return payValue;
	}

	public void setPayValue(Double payValue) {
		this.payValue = payValue;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public void validate() {
		if (code == null || code.trim().equals("")) {
			throw new ModelValidationException("El código es obligatorio.");
		}
		if (documentType == null) {
			throw new ModelValidationException("El tipo es obligatorio.");
		} else {
			documentType.validate();
		}
		if (person == null) {
			throw new ModelValidationException("El tercero (proveedor/cliente) es obligatorio.");
		} else {
			person.validate();
		}
		if (status == null) {
			throw new ModelValidationException("El estado es obligatorio.");
		} else {
			status.validate();
		}
		if (documentDate == null) {
			throw new ModelValidationException("La fecha de la factura es obligatoria.");
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

	private Set<DocumentDetail> forHibernateDetails(Set<DocumentDetail> details) {
		return details.stream().map(detail -> DocumentDetail.builder(detail).document(this).build())
				.collect(Collectors.toSet());
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
		private String resolution;
		private PaymentMethod paymentMethod;
		private String paymentTerm;
		private Date expirationDate;
		private Currency currency;
		private Double totalValueNoTax;
		private Double totalValue;
		private String reference;
		private DocumentStatus status;
		private Person salesman;
		private String cash;
		private Double payValue;
		private String paymentStatus;
		private Set<DocumentDetail> details = new HashSet<>();

		private Builder() {
		}

		private Builder(Document document) {
			id(document.getId()).creationDate(document.getCreationDate()).modifyDate(document.getModifyDate())
					.archived(document.isArchived()).code(document.code).documentType(document.documentType)
					.person(document.person).documentDate(document.documentDate).paymentType(document.paymentType)
					.resolution(document.resolution).paymentMethod(document.paymentMethod)
					.paymentTerm(document.paymentTerm).expirationDate(document.expirationDate)
					.currency(document.currency).totalValueNoTax(document.totalValueNoTax)
					.totalValue(document.totalValue).reference(document.reference).status(document.status)
					.salesman(document.salesman).cash(document.cash).payValue(document.payValue)
					.paymentStatus(document.paymentStatus).details(document.details);
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
			this.documentDate = documentDate;// DateUtil.addOrSubstractHoursToDate(documentDate,
												// CommonsUtil.DIFF_HOURS);
			return this;
		}

		public Builder paymentType(PaymentType paymentType) {
			this.paymentType = paymentType;
			return this;
		}

		public Builder resolution(String resolution) {
			this.resolution = resolution;
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

		public Builder totalValueNoTax(Double totalValueNoTax) {
			this.totalValueNoTax = totalValueNoTax;
			return this;
		}

		public Builder totalValue(Double totalValue) {
			this.totalValue = totalValue;
			return this;
		}

		public Builder reference(String reference) {
			this.reference = reference;
			return this;
		}

		public Builder status(DocumentStatus status) {
			this.status = status;
			return this;
		}

		public Builder salesman(Person salesman) {
			this.salesman = salesman;
			return this;
		}

		public Builder cash(String cash) {
			this.cash = cash;
			return this;
		}

		public Builder payValue(Double payValue) {
			this.payValue = payValue;
			return this;
		}

		public Builder paymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
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

	@Override
	public String toString() {
		return "Document [code=" + code + ", documentType=" + documentType + ", person=" + person + ", documentDate="
				+ documentDate + ", paymentType=" + paymentType + ", resolution=" + resolution + ", paymentMethod="
				+ paymentMethod + ", paymentTerm=" + paymentTerm + ", expirationDate=" + expirationDate + ", currency="
				+ currency + ", totalValueNoTax=" + totalValueNoTax + ", totalValue=" + totalValue + ", reference="
				+ reference + ", status=" + status + ", salesman=" + salesman + ", cash=" + cash + ", payValue="
				+ payValue + ", paymentStatus=" + paymentStatus + "]";
	}

}
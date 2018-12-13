// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
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
 * @since 27/11/2018
 */
@Entity(name = "supplier")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Supplier extends CommonData {

	private static final long serialVersionUID = 443849888639211631L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private Person person;
	@ManyToOne
	@JoinColumn(name = "payment_type_id")
	private PaymentType paymentType;
	@Column(name = "payment_term")
	private String paymentTerm;
	@Column(name = "payment_method")
	private String paymentMethod;

	public Supplier() {
		super();
	}

	public Supplier(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		person = builder.person;
		paymentType = builder.paymentType;
		paymentTerm = builder.paymentTerm;
		paymentMethod = builder.paymentMethod;
	}

	public Person getPerson() {
		return person;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public void validate() {
		if (person == null) {
			throw new ModelValidationException("Los datos basicos de la persona como proveedor son obligatorios.");
		} else {
			person.validate();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Supplier supplier) {
		return new Builder(supplier);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Person person;
		private PaymentType paymentType;
		private String paymentTerm;
		private String paymentMethod;

		private Builder() {
		}

		private Builder(Supplier supplier) {
			id(supplier.getId()).creationDate(supplier.getCreationDate()).modifyDate(supplier.getModifyDate())
					.archived(supplier.isArchived()).person(supplier.person).paymentType(supplier.paymentType)
					.paymentTerm(supplier.paymentTerm).paymentMethod(supplier.paymentMethod);
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

		public Builder person(Person person) {
			this.person = person;
			return this;
		}

		public Builder paymentType(PaymentType paymentType) {
			this.paymentType = paymentType;
			return this;
		}

		public Builder paymentTerm(String paymentTerm) {
			this.paymentTerm = paymentTerm;
			return this;
		}

		public Builder paymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
			return this;
		}

		public Supplier build() {
			return new Supplier(this);
		}
	}
}
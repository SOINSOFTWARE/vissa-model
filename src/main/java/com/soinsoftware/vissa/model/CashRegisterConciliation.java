// Soin Software, 2018
package com.soinsoftware.vissa.model;

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
@Entity(name = "cash_register_conciliation")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class CashRegisterConciliation extends CommonData {

	private static final long serialVersionUID = 7247768338497639839L;

	private Double cashBase;
	private int cashRegisterNumber;
	private Double saleTotal;
	private Double expenseTotal;
	private Double supplierPayments;
	private Double egressTotal;	
	private Double finalCash;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expense_date")
	private Date expenseDate;
	private String type;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public CashRegisterConciliation() {
		super();
	}

	public CashRegisterConciliation(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		
		expenseDate = builder.expenseDate;
		description = builder.description;
		type = builder.type;
		person = builder.person;
	}

	@Override
	public void validate() {
		if (expenseDate == null) {
			throw new ModelValidationException("La fecha es oblgiatoria es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(CashRegisterConciliation lot) {
		return new Builder(lot);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;
		private String description;
		private Date expenseDate;
		private String type;
		private Person person;

		private Builder() {
		}

		private Builder(CashRegisterConciliation expense) {
			id(expense.getId()).creationDate(expense.getCreationDate()).modifyDate(expense.getModifyDate())
					.archived(expense.isArchived()).expenseDate(expense.expenseDate)
					.description(expense.description).type(expense.type).person(expense.person);
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

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder expenseDate(Date expenseDate) {
			this.expenseDate = expenseDate;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder person(Person person) {
			this.person = person;
			return this;
		}

		public CashRegisterConciliation build() {
			return new CashRegisterConciliation(this);
		}
	}

}
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

	@Column(name = "cash_base")
	private Double cashBase;
	@Column(name = "cash_register_number")
	private String cashRegisterNumber;
	@Column(name = "total_Sale")
	private Double totalSale;
	@Column(name = "total_expense")
	private Double totalExpense;
	@Column(name = "supplier_payments")
	private Double supplierPayments;
	private Double collection;
	@Column(name = "final_cash")
	private Double finalCash;
	@Column(name = "total_egress")
	private Double totalEgress;
	@Column(name = "total_credit")
	private Double totalCredit;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "conciliation_date")
	private Date conciliationDate;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public CashRegisterConciliation() {
		super();
	}

	public CashRegisterConciliation(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);

		cashBase = builder.cashBase;
		cashRegisterNumber = builder.cashRegisterNumber;
		totalSale = builder.totalSale;
		totalExpense = builder.totalExpense;
		totalExpense = builder.totalExpense;
		totalExpense = builder.totalExpense;
		supplierPayments = builder.supplierPayments;
		collection = builder.collection;
		finalCash = builder.finalCash;
		totalEgress = builder.totalEgress;
		totalCredit = builder.totalCredit;
		conciliationDate = builder.conciliationDate;
		person = builder.person;
	}

	
	@Override
	public void validate() {
		if (supplierPayments == null) {
			throw new ModelValidationException("El pago a proveedores es obligatorio.");
		}
	}

	
	public Double getCashBase() {
		return cashBase;
	}

	public String getCashRegisterNumber() {
		return cashRegisterNumber;
	}

	public Double getTotalSale() {
		return totalSale;
	}

	public Double getTotalExpense() {
		return totalExpense;
	}

	public Double getSupplierPayments() {
		return supplierPayments;
	}

	public Double getCollection() {
		return collection;
	}

	public Double getFinalCash() {
		return finalCash;
	}

	public Double getTotalEgress() {
		return totalEgress;
	}

	public Double getTotalCredit() {
		return totalCredit;
	}

	public Date getConciliationDate() {
		return conciliationDate;
	}

	public Person getPerson() {
		return person;
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
		private Double cashBase;
		private String cashRegisterNumber;
		private Double totalSale;
		private Double totalExpense;
		private Double supplierPayments;
		private Double collection;
		private Double finalCash;
		private Double totalEgress;
		private Double totalCredit;
		private Date conciliationDate;
		private Person person;

		private Builder() {
		}

		private Builder(CashRegisterConciliation expense) {
			id(expense.getId()).creationDate(expense.getCreationDate()).modifyDate(expense.getModifyDate())
					.archived(expense.isArchived()).cashBase(expense.cashBase)
					.cashRegisterNumber(expense.cashRegisterNumber).totalSale(expense.totalSale)
					.totalExpense(expense.totalExpense).supplierPayments(expense.supplierPayments)
					.collection(expense.collection).finalCash(expense.finalCash).totalEgress(expense.totalEgress)
					.totalCredit(expense.totalCredit).conciliationDate(expense.conciliationDate).person(expense.person);
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

		public Builder cashBase(Double cashBase) {
			this.cashBase = cashBase;
			return this;
		}

		public Builder cashRegisterNumber(String cashRegisterNumber) {
			this.cashRegisterNumber = cashRegisterNumber;
			return this;
		}

		public Builder totalSale(Double totalSale) {
			this.totalSale = totalSale;
			return this;
		}

		public Builder totalExpense(Double totalExpense) {
			this.totalExpense = totalExpense;
			return this;
		}

		public Builder supplierPayments(Double supplierPayments) {
			this.supplierPayments = supplierPayments;
			return this;
		}

		public Builder collection(Double collection) {
			this.collection = collection;
			return this;
		}

		public Builder finalCash(Double finalCash) {
			this.finalCash = finalCash;
			return this;
		}

		public Builder totalEgress(Double totalEgress) {
			this.totalEgress = totalEgress;
			return this;
		}

		public Builder totalCredit(Double totalCredit) {
			this.totalCredit = totalCredit;
			return this;
		}
		
		public Builder conciliationDate(Date conciliationDate) {
			this.conciliationDate = conciliationDate;
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
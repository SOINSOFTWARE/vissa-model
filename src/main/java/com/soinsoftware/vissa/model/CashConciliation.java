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
@Entity(name = "cash_register_conciliation")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class CashConciliation extends CommonData {

	private static final long serialVersionUID = 7247768338497639839L;

	@Column(name = "cash_base")
	private BigDecimal cashBase;
	@Column(name = "cash_register_number")
	private String cashRegisterNumber;
	@Column(name = "sales")
	private BigDecimal sales;
	@Column(name = "credit_collection")
	private BigDecimal creditCollection;
	@Column(name = "remnant_sale")
	private BigDecimal remnantSale;
	@Column(name = "general_expense")
	private BigDecimal generalExpense;
	@Column(name = "supplier_payments_loan")
	private BigDecimal supplierPaymentsLoan;
	@Column(name = "remnant_egress")
	private BigDecimal remnantEgress;
	@Column(name = "total_sale")
	private BigDecimal totalIngress;
	@Column(name = "total_cash")
	private BigDecimal totalCash;
	@Column(name = "total_egress")
	private BigDecimal totalEgress;
	@Column(name = "total_credit")
	private BigDecimal totalCredit;
	@Column(name = "supplier_payments")
	private BigDecimal supplierPayments;
	@Column(name = "cash_register_borrow")
	private BigDecimal cashRegisterBorrow;
	@Column(name = "balance")
	private BigDecimal balance;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "conciliation_date")
	private Date conciliationDate;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	private String comments;

	public CashConciliation() {
		super();
	}

	public CashConciliation(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);

		cashBase = builder.cashBase;
		cashRegisterNumber = builder.cashRegisterNumber;
		sales = builder.sales;
		creditCollection = builder.creditCollection;
		remnantSale = builder.remnantSale;
		generalExpense = builder.generalExpense;
		supplierPaymentsLoan = builder.supplierPaymentsLoan;
		remnantEgress = builder.remnantEgress;
		totalIngress = builder.totalIngress;
		totalCash = builder.totalCash;
		totalEgress = builder.totalEgress;
		totalCredit = builder.totalCredit;
		supplierPayments = builder.supplierPayments;
		cashRegisterBorrow = builder.cashRegisterBorrow;
		balance = builder.balance;
		conciliationDate = builder.conciliationDate;
		person = builder.person;
		comments = builder.comments;
	}

	@Override
	public void validate() {
		if (supplierPaymentsLoan == null) {
			throw new ModelValidationException("El pago a proveedores es obligatorio.");
		}
	}

	public BigDecimal getCashBase() {
		return cashBase;
	}

	public String getCashRegisterNumber() {
		return cashRegisterNumber;
	}

	public BigDecimal getSales() {
		return sales;
	}

	public BigDecimal getCreditCollection() {
		return creditCollection;
	}

	public BigDecimal getRemnantSale() {
		return remnantSale;
	}

	public BigDecimal getGeneralExpense() {
		return generalExpense;
	}

	public BigDecimal getSupplierPaymentsLoan() {
		return supplierPaymentsLoan;
	}

	public BigDecimal getRemnantEgress() {
		return remnantEgress;
	}

	public BigDecimal getTotalIngress() {
		return totalIngress;
	}

	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public BigDecimal getTotalEgress() {
		return totalEgress;
	}

	public BigDecimal getTotalCredit() {
		return totalCredit;
	}

	public BigDecimal getSupplierPayments() {
		return supplierPayments;
	}

	public BigDecimal getCashRegisterBorrow() {
		return cashRegisterBorrow;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public Date getConciliationDate() {
		return conciliationDate;
	}

	public Person getPerson() {
		return person;
	}

	public String getComments() {
		return comments;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(CashConciliation cashRegisterConciliation) {
		return new Builder(cashRegisterConciliation);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private BigDecimal cashBase;
		private String cashRegisterNumber;
		private BigDecimal sales;
		private BigDecimal creditCollection;
		private BigDecimal remnantSale;
		private BigDecimal generalExpense;
		private BigDecimal supplierPaymentsLoan;
		private BigDecimal remnantEgress;
		private BigDecimal totalIngress;
		private BigDecimal totalCash;
		private BigDecimal totalEgress;
		private BigDecimal totalCredit;
		private BigDecimal supplierPayments;
		private BigDecimal cashRegisterBorrow;//Prestamo caja
		private BigDecimal balance;
		private Date conciliationDate;
		private Person person;
		private String comments;

		private Builder() {
		}

		private Builder(CashConciliation cashRegisterConciliation) {
			id(cashRegisterConciliation.getId()).creationDate(cashRegisterConciliation.getCreationDate())
					.modifyDate(cashRegisterConciliation.getModifyDate())
					.archived(cashRegisterConciliation.isArchived()).cashBase(cashRegisterConciliation.cashBase)
					.cashRegisterNumber(cashRegisterConciliation.cashRegisterNumber)
					.totalIngress(cashRegisterConciliation.totalIngress)
					.generalExpense(cashRegisterConciliation.generalExpense)
					.supplierPaymentsLoan(cashRegisterConciliation.supplierPaymentsLoan)
					.creditCollection(cashRegisterConciliation.creditCollection)
					.totalCash(cashRegisterConciliation.totalCash).totalEgress(cashRegisterConciliation.totalEgress)
					.totalCredit(cashRegisterConciliation.totalCredit)
					.conciliationDate(cashRegisterConciliation.conciliationDate).person(cashRegisterConciliation.person)
					.remnantEgress(cashRegisterConciliation.remnantEgress)
					.remnantSale(cashRegisterConciliation.remnantSale).sales(cashRegisterConciliation.sales)
					.supplierPayments(cashRegisterConciliation.supplierPayments)
					.cashRegisterBorrow(cashRegisterConciliation.cashRegisterBorrow)
					.balance(cashRegisterConciliation.balance)
					.comments(cashRegisterConciliation.comments);
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

		public Builder cashBase(BigDecimal cashBase) {
			this.cashBase = cashBase;
			return this;
		}

		public Builder cashRegisterNumber(String cashRegisterNumber) {
			this.cashRegisterNumber = cashRegisterNumber;
			return this;
		}

		public Builder totalIngress(BigDecimal totalSale) {
			this.totalIngress = totalSale;
			return this;
		}

		public Builder generalExpense(BigDecimal generalExpense) {
			this.generalExpense = generalExpense;
			return this;
		}

		public Builder supplierPaymentsLoan(BigDecimal supplierPaymentsLoan) {
			this.supplierPaymentsLoan = supplierPaymentsLoan;
			return this;
		}

		public Builder creditCollection(BigDecimal creditCollection) {
			this.creditCollection = creditCollection;
			return this;
		}

		public Builder totalCash(BigDecimal totalCash) {
			this.totalCash = totalCash;
			return this;
		}

		public Builder totalEgress(BigDecimal totalEgress) {
			this.totalEgress = totalEgress;
			return this;
		}

		public Builder totalCredit(BigDecimal totalCredit) {
			this.totalCredit = totalCredit;
			return this;
		}

		public Builder sales(BigDecimal sales) {
			this.sales = sales;
			return this;
		}

		public Builder comments(String comments) {
			this.comments = comments;
			return this;
		}

		public Builder remnantSale(BigDecimal remnantSale) {
			this.remnantSale = remnantSale;
			return this;
		}

		public Builder remnantEgress(BigDecimal remnantEgress) {
			this.remnantEgress = remnantEgress;
			return this;
		}
		
		public Builder supplierPayments(BigDecimal supplierPayments) {
			this.supplierPayments = supplierPayments;
			return this;
		}
		
		public Builder cashRegisterBorrow(BigDecimal cashRegisterBorrow) {
			this.cashRegisterBorrow = cashRegisterBorrow;
			return this;
		}
		public Builder balance(BigDecimal balance) {
			this.balance = balance;
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

		public CashConciliation build() {
			return new CashConciliation(this);
		}
	}

}
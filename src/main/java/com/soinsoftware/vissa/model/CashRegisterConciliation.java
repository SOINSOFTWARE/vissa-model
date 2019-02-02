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
public class CashRegisterConciliation extends CommonData {

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
	@Column(name = "supplier_payments")
	private BigDecimal supplierPayments;
	@Column(name = "remnant_egress")
	private BigDecimal remnantEgress;
	@Column(name = "total_sale")
	private BigDecimal totalSale;
	@Column(name = "total_cash")
	private BigDecimal totalCash;
	@Column(name = "total_egress")
	private BigDecimal totalEgress;
	@Column(name = "total_credit")
	private BigDecimal totalCredit;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "conciliation_date")
	private Date conciliationDate;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	private String comments;

	public CashRegisterConciliation() {
		super();
	}

	public CashRegisterConciliation(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);

		cashBase = builder.cashBase;
		cashRegisterNumber = builder.cashRegisterNumber;
		sales = builder.sales;
		creditCollection = builder.creditCollection;
		remnantSale = builder.remnantSale;
		generalExpense = builder.generalExpense;
		supplierPayments = builder.supplierPayments;
		remnantEgress = builder.remnantEgress;
		totalSale = builder.totalSale;
		totalCash = builder.totalCash;
		totalEgress = builder.totalEgress;
		totalCredit = builder.totalCredit;
		conciliationDate = builder.conciliationDate;
		person = builder.person;
		comments = builder.comments;
	}

	@Override
	public void validate() {
		if (supplierPayments == null) {
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

	public BigDecimal getSupplierPayments() {
		return supplierPayments;
	}

	public BigDecimal getRemnantEgress() {
		return remnantEgress;
	}

	public BigDecimal getTotalSale() {
		return totalSale;
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

	public static Builder builder(CashRegisterConciliation cashRegisterConciliation) {
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
		private BigDecimal supplierPayments;
		private BigDecimal remnantEgress;
		private BigDecimal totalSale;
		private BigDecimal totalCash;
		private BigDecimal totalEgress;
		private BigDecimal totalCredit;
		private Date conciliationDate;
		private Person person;
		private String comments;

		private Builder() {
		}

		private Builder(CashRegisterConciliation cashRegisterConciliation) {
			id(cashRegisterConciliation.getId()).creationDate(cashRegisterConciliation.getCreationDate())
					.modifyDate(cashRegisterConciliation.getModifyDate())
					.archived(cashRegisterConciliation.isArchived()).cashBase(cashRegisterConciliation.cashBase)
					.cashRegisterNumber(cashRegisterConciliation.cashRegisterNumber)
					.totalSale(cashRegisterConciliation.totalSale)
					.generalExpense(cashRegisterConciliation.generalExpense)
					.supplierPayments(cashRegisterConciliation.supplierPayments)
					.creditCollection(cashRegisterConciliation.creditCollection)
					.totalCash(cashRegisterConciliation.totalCash).totalEgress(cashRegisterConciliation.totalEgress)
					.totalCredit(cashRegisterConciliation.totalCredit)
					.conciliationDate(cashRegisterConciliation.conciliationDate).person(cashRegisterConciliation.person)
					.remnantEgress(cashRegisterConciliation.remnantEgress)
					.remnantSale(cashRegisterConciliation.remnantSale).sales(cashRegisterConciliation.sales)
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

		public Builder totalSale(BigDecimal totalSale) {
			this.totalSale = totalSale;
			return this;
		}

		public Builder generalExpense(BigDecimal generalExpense) {
			this.generalExpense = generalExpense;
			return this;
		}

		public Builder supplierPayments(BigDecimal supplierPayments) {
			this.supplierPayments = supplierPayments;
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
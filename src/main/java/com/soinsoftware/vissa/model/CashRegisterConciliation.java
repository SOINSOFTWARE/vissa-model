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
	@Column(name = "sales")
	private Double sales;
	@Column(name = "credit_collection")
	private Double creditCollection;
	@Column(name = "remnant_sale")
	private Double remnantSale;
	@Column(name = "general_expense")
	private Double generalExpense;
	@Column(name = "supplier_payments")
	private Double supplierPayments;
	@Column(name = "remnant_egress")
	private Double remnantEgress;
	@Column(name = "total_sale")
	private Double totalSale;
	@Column(name = "total_cash")
	private Double totalCash;
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

	public Double getSales() {
		return sales;
	}

	public Double getCreditCollection() {
		return creditCollection;
	}

	public Double getRemnantSale() {
		return remnantSale;
	}

	public Double getGeneralExpense() {
		return generalExpense;
	}

	public Double getSupplierPayments() {
		return supplierPayments;
	}

	public Double getRemnantEgress() {
		return remnantEgress;
	}

	public Double getTotalSale() {
		return totalSale;
	}

	public Double getTotalCash() {
		return totalCash;
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

	public static Builder builder(CashRegisterConciliation cashRegisterConciliation) {
		return new Builder(cashRegisterConciliation);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Double cashBase;
		private String cashRegisterNumber;
		private Double sales;
		private Double creditCollection;
		private Double remnantSale;
		private Double generalExpense;
		private Double supplierPayments;
		private Double remnantEgress;
		private Double totalSale;
		private Double totalCash;
		private Double totalEgress;
		private Double totalCredit;
		private Date conciliationDate;
		private Person person;

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
					.remnantSale(cashRegisterConciliation.remnantSale).sales(cashRegisterConciliation.sales);
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

		public Builder generalExpense(Double generalExpense) {
			this.generalExpense = generalExpense;
			return this;
		}

		public Builder supplierPayments(Double supplierPayments) {
			this.supplierPayments = supplierPayments;
			return this;
		}

		public Builder creditCollection(Double creditCollection) {
			this.creditCollection = creditCollection;
			return this;
		}

		public Builder totalCash(Double totalCash) {
			this.totalCash = totalCash;
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

		public Builder sales(Double sales) {
			this.sales = sales;
			return this;
		}

		public Builder remnantSale(Double remnantSale) {
			this.remnantSale = remnantSale;
			return this;
		}

		public Builder remnantEgress(Double remnantEgress) {
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
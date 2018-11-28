// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
@Entity(name = "bank_account")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class BankAccount extends CommonData {

	private static final long serialVersionUID = 5807717858270495166L;

	@NaturalId
	private String account;
	@Enumerated(EnumType.STRING)
	private BankAccountType type;
	@ManyToOne
	@JoinColumn(name = "bank_id")
	private Bank bank;
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	public BankAccount() {
		super();
	}

	public BankAccount(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		account = builder.account;
		type = builder.type;
		bank = builder.bank;
		supplier = builder.supplier;
	}

	@Override
	public void validate() {
		if (account == null || account.trim().equals("")) {
			throw new ModelValidationException("El número de cuenta es obligatorio.");
		}
		if (type == null) {
			throw new ModelValidationException("El tipo de cuenta es obligatorio.");
		}
		if (bank == null) {
			throw new ModelValidationException("El banco es obligatorio.");
		} else {
			bank.validate();
		}
		if (supplier == null) {
			throw new ModelValidationException("El proveedor es obligatorio.");
		} else {
			supplier.validate();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(BankAccount bankAccount) {
		return new Builder(bankAccount);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String account;
		private BankAccountType type;
		private Bank bank;
		private Supplier supplier;

		private Builder() {
		}

		private Builder(BankAccount bankAccount) {
			id(bankAccount.getId()).creationDate(bankAccount.getCreationDate()).modifyDate(bankAccount.getModifyDate())
					.archived(bankAccount.isArchived()).account(bankAccount.account).type(bankAccount.type)
					.bank(bankAccount.bank).supplier(bankAccount.supplier);
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

		public Builder account(String account) {
			this.account = account;
			return this;
		}

		public Builder type(BankAccountType type) {
			this.type = type;
			return this;
		}

		public Builder bank(Bank bank) {
			this.bank = bank;
			return this;
		}

		public Builder supplier(Supplier supplier) {
			this.supplier = supplier;
			return this;
		}

		public BankAccount build() {
			return new BankAccount(this);
		}
	}
}
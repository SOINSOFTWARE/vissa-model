// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
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


	@Column(name = "account_number")
	private String accountNumber;
	@Enumerated(EnumType.STRING)
	private BankAccountType type;
	@ManyToOne
	@JoinColumn(name = "bank_id")
	private Bank bank;
	@Enumerated(EnumType.STRING)
	private BankAccountStatus status;

	public BankAccount() {
		super();
	}

	public BankAccount(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		accountNumber = builder.account;
		type = builder.type;
		bank = builder.bank;
		status = builder.status;
	}

	@Override
	public void validate() {

		if (type == null) {
			throw new ModelValidationException("El tipo de cuenta es obligatorio.");
		}

		if (accountNumber == null || accountNumber.trim().equals("")) {
			throw new ModelValidationException("El número de cuenta es obligatorio.");
		}
		if ( bank == null) {
			throw new ModelValidationException("El banco es obligatorio.");
		} else {
			bank.validate();
		}
		if (status == null) {
			throw new ModelValidationException("El estado de la cuenta es obligatorio.");
		}
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public BankAccountType getType() {
		return type;
	}

	public Bank getBank() {
		return bank;
	}

	public BankAccountStatus getStatus() {
		return status;
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
		private BankAccountStatus status;

		private Builder() {
		}

		private Builder(BankAccount bankAccount) {
			id(bankAccount.getId()).creationDate(bankAccount.getCreationDate()).modifyDate(bankAccount.getModifyDate())
					.archived(bankAccount.isArchived()).account(bankAccount.accountNumber).type(bankAccount.type)
					.bank(bankAccount.bank).status(bankAccount.status);
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

		public Builder status(BankAccountStatus status) {
			this.status = status;
			return this;
		}

		public BankAccount build() {
			return new BankAccount(this);
		}
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", type=" + type + ", bank=" + bank + ", status="
				+ status + "]";
	}

}
// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;

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
@Entity(name = "currency")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Currency extends CommonData {

	private static final long serialVersionUID = -6855449101935095974L;

	@NaturalId
	private String code;
	private String name;

	public Currency() {
		super();
	}

	public Currency(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		code = builder.code;
		name = builder.name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	@Override
	public void validate() {
		if (code == null || code.trim().equals("")) {
			throw new ModelValidationException("El código es obligatorio.");
		}
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Currency currency) {
		return new Builder(currency);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String code;
		private String name;

		private Builder() {
		}

		private Builder(Currency currency) {
			id(currency.getId()).creationDate(currency.getCreationDate()).modifyDate(currency.getModifyDate())
					.archived(currency.isArchived()).code(currency.code).name(currency.name);
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

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Currency build() {
			return new Currency(this);
		}
	}
}
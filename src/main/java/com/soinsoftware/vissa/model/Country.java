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
 * @since 29/11/2018
 */
@Entity(name = "country")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Country extends CommonData {

	private static final long serialVersionUID = -3934490987205731259L;

	@NaturalId
	private String name;

	public Country() {
		super();
	}

	public Country(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		name = builder.name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void validate() {
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Country country) {
		return new Builder(country);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;

		private Builder() {
		}

		private Builder(Country country) {
			id(country.getId()).creationDate(country.getCreationDate()).modifyDate(country.getModifyDate())
					.archived(country.isArchived()).name(country.getName());
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

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Country build() {
			return new Country(this);
		}
	}
}
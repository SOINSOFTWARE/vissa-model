// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

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
 * @since 29/11/2018
 */
@Entity(name = "state")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class State extends CommonData {

	private static final long serialVersionUID = -9132867093609688537L;

	private String name;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public State() {
		super();
	}

	public State(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		name = builder.name;
		country = builder.country;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}

	@Override
	public void validate() {
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
		if (country == null) {
			throw new ModelValidationException("El país es obligatorio.");
		} else {
			country.validate();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(State country) {
		return new Builder(country);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;
		private Country country;

		private Builder() {
		}

		private Builder(State state) {
			id(state.getId()).creationDate(state.getCreationDate()).modifyDate(state.getModifyDate())
					.archived(state.isArchived()).name(state.getName()).country(state.country);
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

		public Builder country(Country country) {
			this.country = country;
			return this;
		}

		public State build() {
			return new State(this);
		}
	}
}
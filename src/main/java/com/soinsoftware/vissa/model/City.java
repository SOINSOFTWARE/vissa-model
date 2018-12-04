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
 * @since 04/12/2018
 */
@Entity(name = "city")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class City extends AbstractNameModel {

	private static final long serialVersionUID = 7247768338497639839L;
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	public City() {
		super();
	}

	public City(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived, builder.name);
		state = builder.state;
	}

	public State getState() {
		return state;
	}
	
	@Override
	public void validate() {
		super.validate();
		if (state == null) {
			throw new ModelValidationException("El departamento es obligatorio.");
		} else {
			state.validate();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(City city) {
		return new Builder(city);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;
		private State state;

		private Builder() {
		}

		private Builder(City city) {
			id(city.getId()).creationDate(city.getCreationDate()).modifyDate(city.getModifyDate())
					.archived(city.isArchived()).name(city.getName()).state(city.state);
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

		public Builder state(State state) {
			this.state = state;
			return this;
		}

		public City build() {
			return new City(this);
		}
	}
}
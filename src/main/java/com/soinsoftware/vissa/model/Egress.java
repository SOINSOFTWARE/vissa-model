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
@Entity(name = "egress")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Egress extends CommonData {

	private static final long serialVersionUID = 7247768338497639839L;

	private String name;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "egress_date")
	private Date egressDate;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private EgressType type;
	@Column(name = "egress_value")
	private BigDecimal value;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public Egress() {
		super();
	}

	public Egress(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		name = builder.name;
		egressDate = builder.egressDate;
		description = builder.description;
		type = builder.type;
		value = builder.value;
		person = builder.person;
	}

	@Override
	public void validate() {
		if (egressDate == null) {
			throw new ModelValidationException("La fecha es oblgiatoria es obligatorio.");
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getEgressDate() {
		return egressDate;
	}

	public EgressType getType() {
		return type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Person getPerson() {
		return person;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Egress lot) {
		return new Builder(lot);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;
		private String description;
		private Date egressDate;
		private EgressType type;
		private BigDecimal value;
		private Person person;

		private Builder() {
		}

		private Builder(Egress expense) {
			id(expense.getId()).creationDate(expense.getCreationDate()).modifyDate(expense.getModifyDate())
					.archived(expense.isArchived()).name(expense.name).egressDate(expense.egressDate)
					.description(expense.description).type(expense.type).value(expense.value).person(expense.person);
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

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder egressDate(Date egressDate) {
			this.egressDate = egressDate;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder type(EgressType type) {
			this.type = type;
			return this;
		}

		public Builder value(BigDecimal value) {
			this.value = value;
			return this;
		}

		public Builder person(Person person) {
			this.person = person;
			return this;
		}

		public Egress build() {
			return new Egress(this);
		}
	}

}
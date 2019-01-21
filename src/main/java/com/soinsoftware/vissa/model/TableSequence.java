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
@Entity(name = "table_sequence")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class TableSequence extends CommonData {

	private static final long serialVersionUID = 7247768338497639839L;

	@NaturalId
	private String code;
	private String name;
	private BigInteger sequence;

	public TableSequence() {
		super();
	}

	public TableSequence(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		code = builder.code;
		name = builder.name;
		sequence = builder.sequence;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public BigInteger getSequence() {
		return sequence;
	}

	@Override
	public void validate() {
		if (code == null || code.trim().equals("")) {
			throw new ModelValidationException("El código es obligatorio.");
		}

	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(TableSequence documentType) {
		return new Builder(documentType);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String code;
		private String name;

		private BigInteger sequence;

		private Builder() {
		}

		private Builder(TableSequence documentType) {
			id(documentType.getId()).creationDate(documentType.getCreationDate())
					.modifyDate(documentType.getModifyDate()).archived(documentType.isArchived())
					.code(documentType.code).name(documentType.name).sequence(sequence);
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

		public Builder sequence(BigInteger sequence) {
			this.sequence = sequence;
			return this;
		}

		public TableSequence build() {
			return new TableSequence(this);
		}
	}

	@Override
	public String toString() {
		return "TableSequence [code=" + code + ", name=" + name + ", sequence=" + sequence + "]";
	}
	
	
}
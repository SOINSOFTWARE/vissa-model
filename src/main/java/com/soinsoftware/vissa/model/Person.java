// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
@Entity(name = "person")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Person extends CommonData {

	private static final long serialVersionUID = 5807717858270495166L;

	@Enumerated(EnumType.STRING)
	@Column(name = "document_type")
	private DocumentType documentType;
	@NaturalId
	@Column(name = "document_number")
	private String documentNumber;
	private String name;
	@Column(name = "last_name")
	private String lastName;
	@Enumerated(EnumType.STRING)
	private PersonType type;

	public Person() {
		super();
	}

	public Person(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		documentType = builder.documentType;
		documentNumber = builder.documentNumber;
		name = builder.name;
		lastName = builder.lastName;
		type = builder.type;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public PersonType getType() {
		return type;
	}

	@Override
	public void validate() {
		if (documentType == null) {
			throw new ModelValidationException("El tipo de documento es obligatorio.");
		}
		if (documentNumber == null) {
			throw new ModelValidationException("El número de documento es obligatorio.");
		}
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre de la persona (natural o jurídica) es obligatorio.");
		}
		if (type == null) {
			throw new ModelValidationException("El tipo de la persona es obligatorio.");
		} else if (type.equals(PersonType.CUSTOMER) && (lastName == null || lastName.trim().equals(""))) {
			throw new ModelValidationException("El apellido de la persona natural es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Person person) {
		return new Builder(person);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private DocumentType documentType;
		private String documentNumber;
		private String name;
		private String lastName;
		private PersonType type;

		private Builder() {
		}

		private Builder(Person person) {
			id(person.getId()).creationDate(person.getCreationDate()).modifyDate(person.getModifyDate())
					.archived(person.isArchived()).documentType(person.documentType)
					.documentNumber(person.documentNumber).name(person.name).lastName(person.lastName)
					.type(person.type);
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

		public Builder documentType(DocumentType documentType) {
			this.documentType = documentType;
			return this;
		}

		public Builder documentNumber(String documentNumber) {
			this.documentNumber = documentNumber;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder type(PersonType type) {
			this.type = type;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}
}
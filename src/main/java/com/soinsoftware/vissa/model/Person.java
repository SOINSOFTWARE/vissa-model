// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	private DocumentIdType documentType;
	@NaturalId
	@Column(name = "document_number")
	private String documentNumber;
	private String name;
	@Column(name = "last_name")
	private String lastName;
	@Enumerated(EnumType.STRING)
	private PersonType type;
	@Column(name = "contact_name")
	private String contactName;
	private String address;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	private String neighborhood;
	private String mobile;
	private String phone;
	private String email;
	@Column(name = "web_site")
	private String webSite;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;

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
		address = builder.address;
		city = builder.city;
		mobile = builder.mobile;
		phone = builder.phone;
		email = builder.email;
		webSite = builder.webSite;
		contactName = builder.contactName;
		bankAccount = builder.bankAccount;
		neighborhood = builder.neighborhood;
	}

	public DocumentIdType getDocumentType() {
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

	public String getContactName() {
		return contactName;
	}

	public String getAddress() {
		return address;
	}

	public City getCity() {
		return city;
	}

	
	public String getNeighborhood() {
		return neighborhood;
	}



	public String getMobile() {
		return mobile;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getWebSite() {
		return webSite;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
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
		private DocumentIdType documentType;
		private String documentNumber;
		private String name;
		private String lastName;
		private PersonType type;
		private String contactName;
		private String address;
		private City city;
		private String neighborhood;
		private String mobile;
		private String phone;
		private String email;
		private String webSite;
		private BankAccount bankAccount;

		private Builder() {
		}

		private Builder(Person person) {
			id(person.getId()).creationDate(person.getCreationDate()).modifyDate(person.getModifyDate())
					.archived(person.isArchived()).documentType(person.documentType)
					.documentNumber(person.documentNumber).name(person.name).lastName(person.lastName).type(person.type)
					.contactName(person.contactName).address(person.address).city(person.city)
					.neighborhood(person.neighborhood).mobile(person.mobile).phone(person.phone).email(person.email)
					.webSite(person.webSite).bankAccount(person.bankAccount);
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

		public Builder documentType(DocumentIdType documentType) {
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

		public Builder contactName(String contactName) {
			this.contactName = contactName;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder city(City city) {
			this.city = city;
			return this;
		}

		public Builder neighborhood(String neighborhood) {
			this.neighborhood = neighborhood;
			return this;
		}

		public Builder mobile(String mobile) {
			this.mobile = mobile;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder webSite(String webSite) {
			this.webSite = webSite;
			return this;
		}

		public Builder bankAccount(BankAccount bankAccount) {
			this.bankAccount = bankAccount;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}

	@Override
	public String toString() {
		return "Person [documentType=" + documentType + ", documentNumber=" + documentNumber + ", name=" + name
				+ ", lastName=" + lastName + ", type=" + type + ", contactName=" + contactName + ", address=" + address
				+ ", city=" + city + ", neighborhood=" + neighborhood + ", mobile=" + mobile + ", phone=" + phone
				+ ", email=" + email + ", webSite=" + webSite + ", bankAccount=" + bankAccount + "]";
	}

	

}
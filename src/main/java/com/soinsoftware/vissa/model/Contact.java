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
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
@Entity(name = "contact")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Contact extends AbstractNameModel {

	private static final long serialVersionUID = 7247768338497639839L;

	private String address;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	private String mobile;
	private String phone;
	private String email;
	@Column(name = "web_site")
	private String webSite;
	@Enumerated(EnumType.STRING)
	private ContactType type;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	public Contact() {
		super();
	}

	public Contact(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived, builder.name);
		address = builder.address;
		city = builder.city;
		mobile = builder.mobile;
		phone = builder.phone;
		email = builder.email;
		webSite = builder.webSite;
		type = builder.type;
		person = builder.person;
	}

	public String getAddress() {
		return address;
	}

	public City getCity() {
		return city;
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

	public ContactType getType() {
		return type;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public void validate() {
		super.validate();
		if (address == null || address.trim().equals("")) {
			throw new ModelValidationException("La dirección es obligatoria.");
		}
		if (city == null) {
			throw new ModelValidationException("La ciudad es obligatoria.");
		} else {
			city.validate();
		}
		if (mobile == null || mobile.trim().equals("")) {
			throw new ModelValidationException("El número de celular es obligatorio.");
		}
		if (type == null) {
			throw new ModelValidationException("El tipo es obligatorio.");
		}
		if (person == null) {
			throw new ModelValidationException("Los datos de la persona son obligatorios.");
		} else {
			person.validate();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Contact contact) {
		return new Builder(contact);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;
		private String address;
		private City city;
		private String mobile;
		private String phone;
		private String email;
		private String webSite;
		private ContactType type;
		private Person person;

		private Builder() {
		}

		private Builder(Contact contact) {
			id(contact.getId()).creationDate(contact.getCreationDate()).modifyDate(contact.getModifyDate())
					.archived(contact.isArchived()).name(contact.getName()).address(contact.address).city(contact.city)
					.mobile(contact.mobile).phone(contact.phone).email(contact.email).webSite(contact.webSite)
					.type(contact.type).person(contact.person);
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

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder city(City city) {
			this.city = city;
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

		public Builder type(ContactType type) {
			this.type = type;
			return this;
		}

		public Builder person(Person person) {
			this.person = person;
			return this;
		}

		public Contact build() {
			return new Contact(this);
		}
	}
}
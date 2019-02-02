// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

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
@Entity(name = "company")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Company extends CommonData {

	private static final long serialVersionUID = 5807717858270495166L;

	@NaturalId
	private String nit;
	private String name;
	@Column(name = "invoice_resolution")
	private String invoiceResolution;
	@Column(name = "regime_type")
	private String regimeType;
	private String address;
	private String phone;
	private String mobile;
	private String email;
	private String website;

	public Company() {
		super();
	}

	public Company(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		nit = builder.nit;
		name = builder.name;
		invoiceResolution = builder.invoiceResolution;
		regimeType = builder.regimeType;
		address = builder.address;
		phone = builder.phone;
		mobile = builder.mobile;
		email = builder.email;
		website = builder.website;
	}

	public String getName() {
		return name;
	}
	
	

	public String getNit() {
		return nit;
	}

	public String getInvoiceResolution() {
		return invoiceResolution;
	}

	public String getRegimeType() {
		return regimeType;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public String getWebsite() {
		return website;
	}

	@Override
	public void validate() {
		if (nit == null || nit.trim().equals("")) {
			throw new ModelValidationException("El nit es obligatorio.");
		}
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Company person) {
		return new Builder(person);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String nit;
		private String name;
		private String invoiceResolution;
		private String regimeType;
		private String address;
		private String phone;
		private String mobile;
		private String email;
		private String website;

		private Builder() {
		}

		private Builder(Company person) {
			id(person.getId()).creationDate(person.getCreationDate()).modifyDate(person.getModifyDate())
					.archived(person.isArchived()).nit(person.nit).name(person.name).regimeType(person.regimeType)
					.address(person.regimeType).address(person.address).phone(person.phone).mobile(person.mobile)
					.email(person.email).website(person.website);
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

		public Builder nit(String nit) {
			this.nit = nit;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder invoiceResolution(String invoiceResolution) {
			this.invoiceResolution = invoiceResolution;
			return this;
		}

		public Builder regimeType(String regimeType) {
			this.regimeType = regimeType;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
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

		public Builder website(String website) {
			this.website = website;
			return this;
		}

		public Builder mobile(String mobile) {
			this.mobile = mobile;
			return this;
		}

		public Company build() {
			return new Company(this);
		}
	}

	@Override
	public String toString() {
		return "Company [nit=" + nit + ", name=" + name + ", invoiceResolution=" + invoiceResolution + ", regimeType="
				+ regimeType + ", address=" + address + ", phone=" + phone + ", mobile=" + mobile + ", email=" + email
				+ ", website=" + website + "]";
	}
	
	
}
// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 26/12/2018
 */
@Entity(name = "user")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class User extends CommonData {

	private static final long serialVersionUID = -3586115569043524736L;

	@NaturalId
	private String login;
	@Column(name = "pass")
	private String password;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private Person person;

	public User() {
		super();
	}

	public User(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		login = builder.login;
		password = builder.password;
		person = builder.person;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public void validate() {
		if (login == null || login.trim().equals("")) {
			throw new ModelValidationException("El login es obligatorio.");
		}
		if (password == null) {
			throw new ModelValidationException("La contraseña es obligatoria.");
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

	public static Builder builder(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return new Builder(user);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String login;
		private String password;
		private Person person;

		private Builder() {
		}

		private Builder(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
			id(user.getId()).creationDate(user.getCreationDate()).modifyDate(user.getModifyDate())
					.archived(user.isArchived()).login(user.login).password(user.password).person(user.person);
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

		public Builder login(String login) {
			this.login = login;
			return this;
		}

		public Builder password(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
			this.password = generateSecurePassword(password);
			return this;
		}

		public Builder person(Person person) {
			this.person = person;
			return this;
		}

		public User build() {
			return new User(this);
		}

		private byte[] hash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
			KeySpec spec = new PBEKeySpec(password.toCharArray(), "VissaModelGenerator".getBytes(), 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return factory.generateSecret(spec).getEncoded();
		}

		private String generateSecurePassword(String password)
				throws NoSuchAlgorithmException, InvalidKeySpecException {
			byte[] securePassword = hash(password);
			return Base64.getEncoder().encodeToString(securePassword);
		}
	}
}
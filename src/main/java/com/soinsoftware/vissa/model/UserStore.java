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
 * @since 28/11/2018
 */
@Entity(name = "user_store")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class UserStore extends CommonData {

	private static final long serialVersionUID = 5807717858271495166L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	public User getUser() {
		return user;
	}

	public Store getStore() {
		return store;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public UserStore() {
		super();
	}

	public UserStore(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		user = builder.user;
		store = builder.store;

	}

	@Override
	public void validate() {
		if (user == null) {
			throw new ModelValidationException("El usuario es obligatorio.");
		}
		if (store == null) {
			throw new ModelValidationException("El punto de venta es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(UserStore person) {
		return new Builder(person);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private User user;
		private Store store;;

		private Builder() {
		}

		private Builder(UserStore store) {
			id(store.getId()).creationDate(store.getCreationDate()).modifyDate(store.getModifyDate())
					.archived(store.isArchived()).user(store.user).store(store.store);
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

		public Builder user(User user) {
			this.user = user;
			return this;
		}

		public Builder store(Store store) {
			this.store = store;
			return this;
		}

		public UserStore build() {
			return new UserStore(this);
		}
	}

	@Override
	public String toString() {
		return "UserStore [user=" + user + ", store=" + store + "]";
	}

}
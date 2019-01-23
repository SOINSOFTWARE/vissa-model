// Soin Software, 2019
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
 * @since 23/01/2019
 */
@Entity(name = "menu")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Menu extends CommonData {

	private static final long serialVersionUID = -1498187201548304275L;

	@NaturalId
	private String name;

	public Menu() {
		super();
	}

	public Menu(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		name = builder.name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void validate() {
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Menu menu) {
		return new Builder(menu);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;

		private Builder() {
		}

		private Builder(Menu menu) {
			id(menu.getId()).creationDate(menu.getCreationDate()).modifyDate(menu.getModifyDate())
					.archived(menu.isArchived()).name(menu.getName());
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

		public Menu build() {
			return new Menu(this);
		}
	}
}
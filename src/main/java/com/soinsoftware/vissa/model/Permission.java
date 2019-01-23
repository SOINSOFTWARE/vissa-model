// Soin Software, 2019
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
 * @since 23/01/2019
 */
@Entity(name = "permission")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Permission extends CommonData {

	private static final long serialVersionUID = -1498187201548304275L;

	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	private boolean view;
	private boolean edit;
	private boolean delete;

	public Permission() {
		super();
	}

	public Permission(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		menu = builder.menu;
		role = builder.role;
		view = builder.view;
		edit = builder.edit;
		delete = builder.delete;
	}

	public Menu getMenu() {
		return menu;
	}

	public Role getRole() {
		return role;
	}

	public boolean isView() {
		return view;
	}

	public boolean isEdit() {
		return edit;
	}

	public boolean isDelete() {
		return delete;
	}

	@Override
	public void validate() {
		if (menu == null) {
			throw new ModelValidationException("El menú es obligatorio.");
		} else {
			menu.validate();
		}
		if (role == null) {
			throw new ModelValidationException("El rol es obligatorio.");
		} else {
			role.validate();
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Permission permission) {
		return new Builder(permission);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Menu menu;
		private Role role;
		private boolean view;
		private boolean edit;
		private boolean delete;

		private Builder() {
		}

		private Builder(Permission permission) {
			id(permission.getId()).creationDate(permission.getCreationDate()).modifyDate(permission.getModifyDate())
					.archived(permission.isArchived()).menu(permission.menu).role(permission.role).view(permission.view)
					.edit(permission.edit).delete(permission.delete);
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

		public Builder menu(Menu menu) {
			this.menu = menu;
			return this;
		}

		public Builder role(Role role) {
			this.role = role;
			return this;
		}

		public Builder view(boolean view) {
			this.view = view;
			return this;
		}

		public Builder edit(boolean edit) {
			this.edit = edit;
			return this;
		}

		public Builder delete(boolean delete) {
			this.delete = delete;
			return this;
		}

		public Permission build() {
			return new Permission(this);
		}
	}
}
// Soin Software, 2019
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
@Entity(name = "role")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class Role extends CommonData {

	private static final long serialVersionUID = -1498187201548304275L;

	@NaturalId
	private String name;
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Permission> permissions = new HashSet<>();

	public Role() {
		super();
	}

	public Role(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		name = builder.name;
		permissions = builder.permissions;
	}

	public String getName() {
		return name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	@Override
	public void validate() {
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
		if (permissions.isEmpty()) {
			throw new ModelValidationException("Los permisos son obligatorios.");
		} else {
			permissions.forEach(permission -> permission.validate());
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(Role role) {
		return new Builder(role);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;
		private Set<Permission> permissions = new HashSet<>();

		private Builder() {
		}

		private Builder(Role role) {
			id(role.getId()).creationDate(role.getCreationDate()).modifyDate(role.getModifyDate())
					.archived(role.isArchived()).name(role.getName()).permissions(role.permissions);
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

		public Builder permissions(Set<Permission> permissions) {
			this.permissions = permissions;
			return this;
		}

		public Role build() {
			return new Role(this);
		}
	}
}
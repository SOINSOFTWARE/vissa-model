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
 * @since 04/12/2018
 */
@Entity(name = "product_category")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class ProductCategory extends CommonData {

	private static final long serialVersionUID = -3630824288387965257L;

	@NaturalId
	private String name;
	private String description;

	public ProductCategory() {
		super();
	}

	public ProductCategory(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		name = builder.name;
		description = builder.description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
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

	public static Builder builder(ProductCategory productCategory) {
		return new Builder(productCategory);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;
		private String description;

		private Builder() {
		}

		private Builder(ProductCategory productCategory) {
			id(productCategory.getId()).creationDate(productCategory.getCreationDate())
					.modifyDate(productCategory.getModifyDate()).archived(productCategory.isArchived())
					.name(productCategory.getName()).description(productCategory.description);
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

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public ProductCategory build() {
			return new ProductCategory(this);
		}
	}
}
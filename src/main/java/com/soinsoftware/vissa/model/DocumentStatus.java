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
 * @since 11/12/2018
 */
@Entity(name = "document_status")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class DocumentStatus extends CommonData {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3554942513899056053L;

	private String name;
	@ManyToOne
	@JoinColumn(name = "document_type_id")
	private DocumentType documentType;

	public DocumentStatus() {
		super();
	}

	public DocumentStatus(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);

		name = builder.name;
		documentType = builder.documentType;
	}

	public String getName() {
		return name;
	}

	public DocumentType getDocumentType() {
		return documentType;
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

	public static Builder builder(DocumentStatus documentType) {
		return new Builder(documentType);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private String name;
		private DocumentType documentType;

		private Builder() {
		}

		private Builder(DocumentStatus documentStatus) {
			id(documentStatus.getId()).creationDate(documentStatus.getCreationDate())
					.modifyDate(documentStatus.getModifyDate()).archived(documentStatus.isArchived())
					.name(documentStatus.name).documentType(documentStatus.documentType);
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

		public Builder documentType(DocumentType documentType) {
			this.documentType = documentType;
			return this;
		}

		public DocumentStatus build() {
			return new DocumentStatus(this);
		}
	}
}
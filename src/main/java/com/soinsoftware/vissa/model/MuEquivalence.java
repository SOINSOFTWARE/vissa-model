// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
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
 * @since 04/12/2018
 */
@Entity(name = "mu_equivalences")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class MuEquivalence extends CommonData {

	private static final long serialVersionUID = -3934490987205731259L;

	@ManyToOne
	@JoinColumn(name = "mu_source")
	private MeasurementUnit muSource;
	@ManyToOne
	@JoinColumn(name = "mu_target")
	private MeasurementUnit muTarget;
	@Column(name = "mu_source_factor")
	private String muSourceFactor;
	@Column(name = "mu_target_factor")
	private String muTargetFactor;

	public MuEquivalence() {
		super();
	}

	public MuEquivalence(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		muSource = builder.muSource;
		muTarget = builder.muTarget;
		muSourceFactor = builder.muSourceFactor;
		muTargetFactor = builder.muTargetFactor;
	}

	public MeasurementUnit getMuSource() {
		return muSource;
	}

	public MeasurementUnit getMuTarget() {
		return muTarget;
	}

	public String getMuSourceFactor() {
		return muSourceFactor;
	}

	public String getMuTargetFactor() {
		return muTargetFactor;
	}

	public void setMuSource(MeasurementUnit muSource) {
		this.muSource = muSource;
	}

	public void setMuTarget(MeasurementUnit muTarget) {
		this.muTarget = muTarget;
	}

	public void setMuSourceFactor(String muSourceFactor) {
		this.muSourceFactor = muSourceFactor;
	}

	public void setMuTargetFactor(String muTargetFactor) {
		this.muTargetFactor = muTargetFactor;
	}

	@Override
	public void validate() {
		if (muSource == null) {
			throw new ModelValidationException("El unidad de medida origen es obligatoria.");
		}
		if (muTarget == null) {
			throw new ModelValidationException("El unidad de medida destino es obligatoria.");
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(MuEquivalence measurementUnit) {
		return new Builder(measurementUnit);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private MeasurementUnit muSource;
		private MeasurementUnit muTarget;
		private String muSourceFactor;
		private String muTargetFactor;

		private Builder() {
		}

		private Builder(MuEquivalence muEquivalences) {
			id(muEquivalences.getId()).creationDate(muEquivalences.getCreationDate())
					.modifyDate(muEquivalences.getModifyDate()).archived(muEquivalences.isArchived())
					.muSource(muEquivalences.getMuSource()).muTarget(muEquivalences.getMuTarget())
					.muSourceFactor(muEquivalences.getMuSourceFactor())
					.muTargetFactor(muEquivalences.getMuTargetFactor());
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

		public Builder muSource(MeasurementUnit muSource) {
			this.muSource = muSource;
			return this;
		}

		public Builder muTarget(MeasurementUnit muTarget) {
			this.muTarget = muTarget;
			return this;
		}

		public Builder muSourceFactor(String muSourceFactor) {
			this.muSourceFactor = muSourceFactor;
			return this;
		}

		public Builder muTargetFactor(String muTargetFactor) {
			this.muTargetFactor = muTargetFactor;
			return this;
		}

		public MuEquivalence build() {
			return new MuEquivalence(this);
		}
	}

	@Override
	public String toString() {
		return "MuEquivalences [muSource=" + muSource + ", muTarget=" + muTarget + ", muSourceFactor=" + muSourceFactor
				+ ", muTargetFactor=" + muTargetFactor + "]";
	}

}
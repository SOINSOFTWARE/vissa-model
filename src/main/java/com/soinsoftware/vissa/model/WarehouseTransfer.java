package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import org.hibernate.annotations.NaturalId;

public class WarehouseTransfer extends CommonData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1671489696021638659L;

	@NaturalId
	private Integer code;
	private Warehouse warehouseSource;
	private Warehouse warehouseTarget;
	private Lot lot;
	private Double quantity;
	private Date transferDate;

	public WarehouseTransfer(Builder builder) {
		super(builder.id, builder.creationDate, builder.modifyDate, builder.archived);
		code = builder.code;
		warehouseSource = builder.warehouseSource;
		warehouseTarget = builder.warehouseTarget;
		lot = builder.lot;
		quantity = builder.quantity;
		transferDate = builder.transferDate;

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Warehouse getWarehouseSource() {
		return warehouseSource;
	}

	public void setWarehouseSource(Warehouse warehouseSource) {
		this.warehouseSource = warehouseSource;
	}

	public Warehouse getWarehouseTarget() {
		return warehouseTarget;
	}

	public void setWarehouseTarget(Warehouse warehouseTarget) {
		this.warehouseTarget = warehouseTarget;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder builder(WarehouseTransfer lot) {
		return new Builder(lot);
	}

	public static class Builder {

		private BigInteger id;
		private Date creationDate;
		private Date modifyDate;
		private boolean archived;
		private Integer code;
		private Warehouse warehouseSource;
		private Warehouse warehouseTarget;
		private Lot lot;
		private Double quantity;
		private Date transferDate;

		private Builder() {
		}

		private Builder(WarehouseTransfer warehouseTransfer) {
			id(warehouseTransfer.getId()).creationDate(warehouseTransfer.getCreationDate()).modifyDate(warehouseTransfer.getModifyDate())
					.archived(warehouseTransfer.isArchived()).code(warehouseTransfer.code).warehouseSource(warehouseTransfer.warehouseSource)
					.warehouseTarget(warehouseTransfer.warehouseTarget).lot(warehouseTransfer.lot).quantity(warehouseTransfer.quantity)
					.transferDate(warehouseTransfer.transferDate);
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

		public Builder code(Integer code) {
			this.code = code;
			return this;
		}

		public Builder warehouseSource(Warehouse warehouseSource) {
			this.warehouseSource = warehouseSource;
			return this;
		}

		public Builder warehouseTarget(Warehouse warehouseTarget) {
			this.warehouseTarget = warehouseTarget;
			return this;
		}

		public Builder lot(Lot lot) {
			this.lot = lot;
			return this;
		}

		public Builder quantity(Double quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder transferDate(Date transferDate) {
			this.transferDate = transferDate;
			return this;
		}

		public WarehouseTransfer build() {
			return new WarehouseTransfer(this);
		}
	}

}

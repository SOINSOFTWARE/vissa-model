package com.soinsoftware.vissa.model;

import java.util.Date;

public class WarehouseTransfer extends CommonData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1671489696021638659L;
	
	private Warehouse warehouseSource;
	private Warehouse warehouseTarget;
	private Lot lot;
	private Double quantity;
	private Date transferDate;
	
	

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
	}

}

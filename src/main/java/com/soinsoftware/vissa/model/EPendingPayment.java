package com.soinsoftware.vissa.model;

public enum EPendingPayment {

	COLLECTION("RECAUDO"), PAYMENT_SUPPLIER("PAGO A PROVEEDORES");
	private String name;

	private EPendingPayment(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

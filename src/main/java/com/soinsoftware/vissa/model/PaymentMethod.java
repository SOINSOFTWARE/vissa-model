// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public enum PaymentMethod {

	CASH("Efectivo"), BANK_TRANSFER("Transferencia bancaria"), BANK_DEPOSIT("Consignación bancaria"),
	ONLINE("Pago en línea");

	private String display;

	private PaymentMethod(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public static PaymentMethod fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}
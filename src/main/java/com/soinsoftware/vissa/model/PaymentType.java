// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public enum PaymentType {

	PRE_PAID("Pago anticipado"), PAID("Pago en el momento"), POST_PAID("Pago posterior");

	private String display;

	private PaymentType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public static PaymentType fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}
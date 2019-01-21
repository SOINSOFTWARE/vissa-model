// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public enum BankAccountType {

	SAVING("Cuenta de ahorros"), COMMON("Cuenta corriente");

	private String display;

	private BankAccountType(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public static BankAccountType fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}
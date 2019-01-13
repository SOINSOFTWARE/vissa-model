// Soin Software, 2018
package com.soinsoftware.vissa.model;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public enum BankAccountStatus {

	ACIVE("Activa"), INACTIVE("Inactiva"), CANCELED("Cancelada");

	private String display;

	private BankAccountStatus(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public static BankAccountStatus fromString(String value) throws IllegalArgumentException {
		return valueOf(value);
	}
}
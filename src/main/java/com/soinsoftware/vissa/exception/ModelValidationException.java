/** Soin Software, 2018 */
package com.soinsoftware.vissa.exception;

/**
* @author Carlos Rodriguez
* @since 21/11/2018
*/
public class ModelValidationException extends RuntimeException {
	private static final long serialVersionUID = -8813430249225093655L;
	
	public ModelValidationException(String message) {
		super(message);
	}
}
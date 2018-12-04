// Soin Software, 2018
package com.soinsoftware.vissa.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NaturalId;

import com.soinsoftware.vissa.exception.ModelValidationException;

/**
 * @author Carlos Rodriguez
 * @since 29/11/2018
 */
@MappedSuperclass
public class AbstractNameModel extends CommonData {

	private static final long serialVersionUID = -8510776027196885308L;

	@NaturalId
	private String name;

	public AbstractNameModel() {
		super();
	}

	public AbstractNameModel(final BigInteger id, final Date creationDate, final Date modifyDate,
			final boolean archived, final String name) {
		super(id, creationDate, modifyDate, archived);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void validate() {
		if (name == null || name.trim().equals("")) {
			throw new ModelValidationException("El nombre es obligatorio.");
		}
	}
}
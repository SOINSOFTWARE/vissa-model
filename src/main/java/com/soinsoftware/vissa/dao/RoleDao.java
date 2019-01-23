// Soin Software, 2019
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.Role;

/**
 * @author Carlos Rodriguez
 * @since 23/01/2019
 */
public class RoleDao extends AbstractDataAccessibleObject<Role, BigInteger> {

	public RoleDao() throws IOException {
		super(Role.class);
	}

	public Role select(final String name) {
		return getSession().bySimpleNaturalId(Role.class).load(name);
	}
}
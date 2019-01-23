// Soin Software, 2019
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.RoleDao;
import com.soinsoftware.vissa.model.Role;

/**
 * @author Carlos Rodriguez
 * @since 23/01/2019
 */
public class RoleBll extends AbstractBll<Role, BigInteger> {

	private static RoleBll instance;

	private RoleBll() throws IOException {
		super(new RoleDao());
	}

	public Role select(final String name) {
		return ((RoleDao) dao).select(name);
	}

	public static RoleBll getInstance() throws IOException {
		if (instance == null) {
			instance = new RoleBll();
		}
		return instance;
	}
}
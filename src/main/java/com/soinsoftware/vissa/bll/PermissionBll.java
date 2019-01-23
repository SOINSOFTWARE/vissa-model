// Soin Software, 2019
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.PermissionDao;
import com.soinsoftware.vissa.model.Menu;
import com.soinsoftware.vissa.model.Permission;
import com.soinsoftware.vissa.model.Role;

/**
 * @author Carlos Rodriguez
 * @since 23/01/2019
 */
public class PermissionBll extends AbstractBll<Permission, BigInteger> {

	private static PermissionBll instance;

	private PermissionBll() throws IOException {
		super(new PermissionDao());
	}

	public List<Permission> select(final Role role) {
		return ((PermissionDao) dao).select(role);
	}
	
	public List<Permission> select(final Menu menu) {
		return ((PermissionDao) dao).select(menu);
	}
	
	public Permission select(final Role role, final Menu menu) {
		return ((PermissionDao) dao).select(role, menu);
	}

	public static PermissionBll getInstance() throws IOException {
		if (instance == null) {
			instance = new PermissionBll();
		}
		return instance;
	}
}
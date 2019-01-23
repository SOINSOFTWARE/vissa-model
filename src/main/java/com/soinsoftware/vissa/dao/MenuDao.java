// Soin Software, 2019
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.Menu;

/**
 * @author Carlos Rodriguez
 * @since 23/01/2019
 */
public class MenuDao extends AbstractDataAccessibleObject<Menu, BigInteger> {

	public MenuDao() throws IOException {
		super(Menu.class);
	}

	public Menu select(final String name) {
		return getSession().bySimpleNaturalId(Menu.class).load(name);
	}
}
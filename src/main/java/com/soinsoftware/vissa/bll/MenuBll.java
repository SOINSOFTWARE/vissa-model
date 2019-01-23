// Soin Software, 2019
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.MenuDao;
import com.soinsoftware.vissa.model.Menu;

/**
 * @author Carlos Rodriguez
 * @since 23/01/2019
 */
public class MenuBll extends AbstractBll<Menu, BigInteger> {

	private static MenuBll instance;

	private MenuBll() throws IOException {
		super(new MenuDao());
	}

	public Menu select(final String name) {
		return ((MenuDao) dao).select(name);
	}

	public static MenuBll getInstance() throws IOException {
		if (instance == null) {
			instance = new MenuBll();
		}
		return instance;
	}
}
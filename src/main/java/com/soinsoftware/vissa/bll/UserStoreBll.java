// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.UserStoreDao;
import com.soinsoftware.vissa.model.Store;
import com.soinsoftware.vissa.model.User;
import com.soinsoftware.vissa.model.UserStore;

/**
 * @author Carlos Rodriguez
 * @since 26/12/2018
 */
public class UserStoreBll extends AbstractBll<UserStore, BigInteger> {

	private static UserStoreBll instance;

	private UserStoreBll() throws IOException {
		super(new UserStoreDao());
	}

	public List<UserStore> select(User user) {
		return ((UserStoreDao) dao).select(user);
	}

	public List<UserStore> select(Store store) {
		return ((UserStoreDao) dao).select(store);
	}

	public static UserStoreBll getInstance() throws IOException {
		if (instance == null) {
			instance = new UserStoreBll();
		}
		return instance;
	}
}
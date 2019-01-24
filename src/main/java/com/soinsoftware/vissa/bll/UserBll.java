// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.UserDao;
import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.model.Person;
import com.soinsoftware.vissa.model.User;

/**
 * @author Carlos Rodriguez
 * @since 26/12/2018
 */
public class UserBll extends AbstractBll<User, BigInteger> {

	private static UserBll instance;

	private UserBll() throws IOException {
		super(new UserDao());
	}

	public User select(final String login) {
		return ((UserDao) dao).select(login);
	}

	public User select(User user) {
		return ((UserDao) dao).select(user.getLogin(), user.getPassword());
	}

	public User select(Person person) {
		return ((UserDao) dao).select(person);
	}

	@Override
	public void save(User user) {
		if (select(user.getLogin()) != null) {
			throw new ModelValidationException(String.format("El usuario %s está siendo usando", user.getLogin()));
		}
		super.save(user);
	}

	public static UserBll getInstance() throws IOException {
		if (instance == null) {
			instance = new UserBll();
		}
		return instance;
	}
}
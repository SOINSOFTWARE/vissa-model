// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.PersonDao;
import com.soinsoftware.vissa.model.Person;
import com.soinsoftware.vissa.model.PersonType;

/**
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public class PersonBll extends AbstractBll<Person, BigInteger> {

	private static PersonBll instance;

	private PersonBll() throws IOException {
		super(new PersonDao());
	}

	public Person select(final String document) {
		return ((PersonDao) dao).select(document);
	}

	public List<Person> selectByName(String name) {
		return ((PersonDao) dao).selectByName(name);
	}

	public List<Person> select(final PersonType personType) {
		return ((PersonDao) dao).select(personType);
	}

	public static PersonBll getInstance() throws IOException {
		if (instance == null) {
			instance = new PersonBll();
		}
		return instance;
	}
}
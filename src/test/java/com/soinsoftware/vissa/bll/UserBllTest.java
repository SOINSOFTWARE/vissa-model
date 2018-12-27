// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.DocumentIdType;
import com.soinsoftware.vissa.model.Person;
import com.soinsoftware.vissa.model.PersonType;
import com.soinsoftware.vissa.model.User;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 26/12/2018
 */
public class UserBllTest extends TestCase {

	private UserBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = UserBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<User> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<User> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByLoginAndPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
		final User user = bll.select(User.builder().login("test").password("test").build());
		assertNotNull(user);
	}

	public void testSelectByLoginAndPasswordNotFound() throws NoSuchAlgorithmException, InvalidKeySpecException {
		final User user = bll.select(User.builder().login("test").password("other").build());
		assertNull(user);
	}

	public void testSaveInvalid() {
		try {
			bll.save(User.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = bll.select("test");
		if (user == null) {
			user = buildTestData();
			bll.save(user);
		}
	}

	private User buildTestData() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Person person = Person.builder().documentType(DocumentIdType.CC).documentNumber("14579").name("Other")
				.lastName("Test").type(PersonType.USER).build();
		return User.builder().login("test").password("test").person(person).build();
	}
}
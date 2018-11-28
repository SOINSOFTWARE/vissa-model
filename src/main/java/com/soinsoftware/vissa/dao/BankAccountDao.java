// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import org.hibernate.Session;

import com.soinsoftware.vissa.model.BankAccount;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class BankAccountDao extends AbstractDataAccessibleObject<BankAccount, BigInteger> {

	public BankAccountDao() throws IOException {
		super(BankAccount.class);
	}

	public BankAccount select(final String account) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(BankAccount.class).load(account);
	}
}
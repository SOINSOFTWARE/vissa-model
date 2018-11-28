// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import org.hibernate.Session;

import com.soinsoftware.vissa.model.Bank;

/**
 * @author Carlos Rodriguez
 * @since 28/11/2018
 */
public class BankDao extends AbstractDataAccessibleObject<Bank, BigInteger> {

	public BankDao() throws IOException {
		super(Bank.class);
	}

	public Bank select(final String name) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(Bank.class).load(name);
	}
}
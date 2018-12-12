// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import org.hibernate.Session;

import com.soinsoftware.vissa.model.DocumentType;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
public class DocumentTypeDao extends AbstractDataAccessibleObject<DocumentType, BigInteger> {

	public DocumentTypeDao() throws IOException {
		super(DocumentType.class);
	}

	public DocumentType select(final String code) {
		final Session session = (Session) manager.getDelegate();
		return session.bySimpleNaturalId(DocumentType.class).load(code);
	}
}
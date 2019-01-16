// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import javax.persistence.Query;

import org.hibernate.Session;

import com.soinsoftware.vissa.model.Document;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentDao extends AbstractDataAccessibleObject<Document, BigInteger> {

	public DocumentDao() throws IOException {
		super(Document.class);
	}

	public Document select(final String code) {
		return getSession().bySimpleNaturalId(Document.class).load(code);
	}
	

	public String selectNextDocumentNumber() {
		//Query query = manager.createNativeQuery("SELECT IFNULL(max(cast(id as signed)),0) + 1 from document" );
		//BigInteger num = (java.math.BigInteger)  query.getSingleResult();	
		return String.valueOf(1);
	}
}
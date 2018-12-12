// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.DocumentTypeDao;
import com.soinsoftware.vissa.model.DocumentType;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
public class DocumentTypeBll extends AbstractBll<DocumentType, BigInteger> {

	private static DocumentTypeBll instance;

	private DocumentTypeBll() throws IOException {
		super(new DocumentTypeDao());
	}

	public DocumentType select(final String code) {
		return ((DocumentTypeDao) dao).select(code);
	}

	public static DocumentTypeBll getInstance() throws IOException {
		if (instance == null) {
			instance = new DocumentTypeBll();
		}
		return instance;
	}
}
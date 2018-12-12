// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.DocumentDao;
import com.soinsoftware.vissa.model.Document;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentBll extends AbstractBll<Document, BigInteger> {

	private static DocumentBll instance;

	private DocumentBll() throws IOException {
		super(new DocumentDao());
	}

	public Document select(final String code) {
		return ((DocumentDao) dao).select(code);
	}

	public static DocumentBll getInstance() throws IOException {
		if (instance == null) {
			instance = new DocumentBll();
		}
		return instance;
	}
}
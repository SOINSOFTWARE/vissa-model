// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.DocumentStatusDao;
import com.soinsoftware.vissa.model.DocumentStatus;
import com.soinsoftware.vissa.model.DocumentType;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
public class DocumentStatusBll extends AbstractBll<DocumentStatus, BigInteger> {

	private static DocumentStatusBll instance;

	private DocumentStatusBll() throws IOException {
		super(new DocumentStatusDao());
	}

	public List<DocumentStatus> select(String name) {
		List<DocumentStatus> documentStatus = ((DocumentStatusDao) dao).select(name);
		return documentStatus;
	}
	
	public List<DocumentStatus> select(DocumentType documentType) {
		List<DocumentStatus> documentStatus = ((DocumentStatusDao) dao).select(documentType);
		return documentStatus;
	}

	public static DocumentStatusBll getInstance() throws IOException {
		if (instance == null) {
			instance = new DocumentStatusBll();
		}
		return instance;
	}
}
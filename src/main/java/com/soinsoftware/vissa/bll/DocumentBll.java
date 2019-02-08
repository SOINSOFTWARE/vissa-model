// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.soinsoftware.vissa.dao.DocumentDao;
import com.soinsoftware.vissa.model.Document;
import com.soinsoftware.vissa.model.DocumentType;
import com.soinsoftware.vissa.model.PaymentType;

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

	public Document select(String code, DocumentType documentType) {
		return ((DocumentDao) dao).select(code, documentType);
	}

	public Document select(String code, List<DocumentType> documentType) {
		return ((DocumentDao) dao).select(code, documentType);
	}

	public List<Document> select(DocumentType documentType) {
		return ((DocumentDao) dao).select(documentType);
	}

	public List<Document> select(final PaymentType paymentType) {
		return ((DocumentDao) dao).select(paymentType);
	}

	public List<Document> select(List<DocumentType> documentTypes) {
		return ((DocumentDao) dao).select(documentTypes);
	}
	
	public List<Document> select(List<DocumentType> documentTypes, List<PaymentType> paymentTypes) {
		return ((DocumentDao) dao).select(documentTypes, paymentTypes);
	}

	public List<Document> selectByPaymentTypes(List<PaymentType> paymentTypes) {
		return ((DocumentDao) dao).selectByPaymentTypes(paymentTypes);
	}

	public List<Document> select(Date documentDate, Date iniDate, Date finalDate) {
		return ((DocumentDao) dao).select(documentDate, iniDate, finalDate);
	}

	public static DocumentBll getInstance() throws IOException {
		if (instance == null) {
			instance = new DocumentBll();
		}
		return instance;
	}
}
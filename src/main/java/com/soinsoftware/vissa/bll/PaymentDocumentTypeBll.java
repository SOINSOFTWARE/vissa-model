// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.PaymentDocumentTypeDao;
import com.soinsoftware.vissa.model.DocumentType;
import com.soinsoftware.vissa.model.PaymentType;
import com.soinsoftware.vissa.model.PaymentDocumentType;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
public class PaymentDocumentTypeBll extends AbstractBll<PaymentDocumentType, BigInteger> {

	private static PaymentDocumentTypeBll instance;

	private PaymentDocumentTypeBll() throws IOException {
		super(new PaymentDocumentTypeDao());
	}

	public List<PaymentDocumentType> select(DocumentType documentType) {
		List<PaymentDocumentType> paymentTypes = ((PaymentDocumentTypeDao) dao).select(documentType);
		return paymentTypes;
	}
	
	public List<DocumentType> select(PaymentType paymentType) {
		List<DocumentType> documentTypes = ((PaymentDocumentTypeDao) dao).select(paymentType);
		return documentTypes;
	}

	public PaymentDocumentType select(DocumentType documentType, PaymentType paymentType) {
		return ((PaymentDocumentTypeDao) dao).select(documentType , paymentType);		
	}


	public static PaymentDocumentTypeBll getInstance() throws IOException {
		if (instance == null) {
			instance = new PaymentDocumentTypeBll();
		}
		return instance;
	}
}
// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.DocumentDetailLotDao;
import com.soinsoftware.vissa.model.DocumentDetail;
import com.soinsoftware.vissa.model.DocumentDetailLot;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentDetailLotBll extends AbstractBll<DocumentDetailLot, BigInteger> {

	private static DocumentDetailLotBll instance;

	private DocumentDetailLotBll() throws IOException {
		super(new DocumentDetailLotDao());
	}
	
	public DocumentDetailLot select(DocumentDetail documentDetail) {
		return ((DocumentDetailLotDao) dao).select(documentDetail);
	}

	public static DocumentDetailLotBll getInstance() throws IOException {
		if (instance == null) {
			instance = new DocumentDetailLotBll();
		}
		return instance;
	}
}
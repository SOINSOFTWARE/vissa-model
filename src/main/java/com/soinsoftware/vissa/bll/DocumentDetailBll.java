// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.DocumentDetailDao;
import com.soinsoftware.vissa.model.DocumentDetail;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentDetailBll extends AbstractBll<DocumentDetail, BigInteger> {

	private static DocumentDetailBll instance;

	private DocumentDetailBll() throws IOException {
		super(new DocumentDetailDao());
	}

	public static DocumentDetailBll getInstance() throws IOException {
		if (instance == null) {
			instance = new DocumentDetailBll();
		}
		return instance;
	}
}
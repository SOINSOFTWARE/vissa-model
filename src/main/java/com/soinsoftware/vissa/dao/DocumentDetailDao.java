// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.DocumentDetail;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentDetailDao extends AbstractDataAccessibleObject<DocumentDetail, BigInteger> {

	public DocumentDetailDao() throws IOException {
		super(DocumentDetail.class);
	}
}
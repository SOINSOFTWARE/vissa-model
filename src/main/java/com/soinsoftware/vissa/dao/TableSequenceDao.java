// Soin Software, 2018
package com.soinsoftware.vissa.dao;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.model.TableSequence;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
public class TableSequenceDao extends AbstractDataAccessibleObject<TableSequence, BigInteger> {

	public TableSequenceDao() throws IOException {
		super(TableSequence.class);
	}

	public TableSequence select(final String code) {
		return getSession().bySimpleNaturalId(TableSequence.class).load(code);
	}
}
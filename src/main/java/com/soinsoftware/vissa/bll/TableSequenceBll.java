// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.vissa.dao.TableSequenceDao;
import com.soinsoftware.vissa.model.TableSequence;

/**
 * @author Carlos Rodriguez
 * @since 11/12/2018
 */
public class TableSequenceBll extends AbstractBll<TableSequence, BigInteger> {

	private static TableSequenceBll instance;

	private TableSequenceBll() throws IOException {
		super(new TableSequenceDao());
	}

	public TableSequence select(final String code) {
		return ((TableSequenceDao) dao).select(code);
	}

	public static TableSequenceBll getInstance() throws IOException {
		if (instance == null) {
			instance = new TableSequenceBll();
		}
		return instance;
	}
}
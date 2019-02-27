// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.MuEquivalenceDao;
import com.soinsoftware.vissa.model.MeasurementUnit;
import com.soinsoftware.vissa.model.MuEquivalence;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class MuEquivalenceBll extends AbstractBll<MuEquivalence, BigInteger> {

	private static MuEquivalenceBll instance;

	private MuEquivalenceBll() throws IOException {
		super(new MuEquivalenceDao());
	}

	public List<MuEquivalence> selectByMuSource(MeasurementUnit muSource) {
		List<MuEquivalence> muEquivalences = ((MuEquivalenceDao) dao).selectByMuSource(muSource);
		return muEquivalences;
	}

	public List<MuEquivalence> selectByMuTarget(MeasurementUnit muTarget) {
		List<MuEquivalence> muEquivalences = ((MuEquivalenceDao) dao).selectByMuTarget(muTarget);
		return muEquivalences;
	}

	public MuEquivalence select(MeasurementUnit muSource, MeasurementUnit muTarget) {
		MuEquivalence muEquivalence = ((MuEquivalenceDao) dao).select(muSource, muTarget);
		return muEquivalence;
	}

	public static MuEquivalenceBll getInstance() throws IOException {
		if (instance == null) {
			instance = new MuEquivalenceBll();
		}
		return instance;
	}
}
// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.soinsoftware.vissa.dao.MuEquivalencesDao;
import com.soinsoftware.vissa.model.MeasurementUnit;
import com.soinsoftware.vissa.model.MuEquivalences;

/**
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class MuEquivalancesBll extends AbstractBll<MuEquivalencesDao, BigInteger> {

	private static MuEquivalancesBll instance;

	private MuEquivalancesBll() throws IOException {
		super(new MuEquivalencesDao());
	}

	public List<MuEquivalences> selectByMuSource(MeasurementUnit muSource) {
		List<MuEquivalences> muEquivalences = ((MuEquivalencesDao) dao).selectByMuSource(muSource);
		return muEquivalences;
	}

	public List<MuEquivalences> selectByMuTarget(MeasurementUnit muTarget) {
		List<MuEquivalences> muEquivalences = ((MuEquivalencesDao) dao).selectByMuTarget(muTarget);
		return muEquivalences;
	}

	public static MuEquivalancesBll getInstance() throws IOException {
		if (instance == null) {
			instance = new MuEquivalancesBll();
		}
		return instance;
	}
}
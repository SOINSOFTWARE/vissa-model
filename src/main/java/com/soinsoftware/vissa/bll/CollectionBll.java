// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.soinsoftware.vissa.dao.CollectionDao;
import com.soinsoftware.vissa.model.Collection;
import com.soinsoftware.vissa.model.Document;
import com.soinsoftware.vissa.model.Person;

/**
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class CollectionBll extends AbstractBll<Collection, BigInteger> {

	private static CollectionBll instance;

	private CollectionBll() throws IOException {
		super(new CollectionDao());
	}

	public List<Collection> select(Document document) {
		List<Collection> collections = ((CollectionDao) dao).select(document);
		return collections.stream().sorted(Comparator.comparing(Collection::getCollectionDate).reversed())
				.collect(Collectors.toList());
	}

	public List<Collection> select(Person person) {
		return ((CollectionDao) dao).select(person);
	}

	public static CollectionBll getInstance() throws IOException {
		if (instance == null) {
			instance = new CollectionBll();
		}
		return instance;
	}
}
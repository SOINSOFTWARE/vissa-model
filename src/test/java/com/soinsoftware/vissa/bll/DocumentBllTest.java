// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.Document;
import com.soinsoftware.vissa.model.DocumentType;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentBllTest extends TestCase {

	private DocumentBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = DocumentBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<Document> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<Document> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final Document entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final Document entity = bll.select("CO-39");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(Document.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() throws IOException {
		Document document = bll.select("CO-39");
		if (document == null) {
			document = buildTestData();
			bll.save(document);
		}
	}

	private Document buildTestData() throws IOException {
		DocumentType documentType = DocumentTypeBll.getInstance().select("CO");
		return Document.builder().code("CO-1").documentType(documentType).build();
	}
}
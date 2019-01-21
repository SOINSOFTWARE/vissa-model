// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.DocumentType;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 04/12/2018
 */
public class DocumentTypeBllTest extends TestCase {

	private DocumentTypeBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = DocumentTypeBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<DocumentType> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<DocumentType> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectByCodeNotExists() {
		final DocumentType entity = bll.select("test-non-exists");
		assertNull(entity);
	}

	public void testSelectByCodeExists() {
		final DocumentType entity = bll.select("FV");
		assertNotNull(entity);
	}

	public void testSaveInvalid() {
		try {
			bll.save(DocumentType.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() {
		DocumentType documentType = bll.select("FV");
		if (documentType == null) {
			documentType = buildTestData();
			bll.save(documentType);
		}
	}

	private DocumentType buildTestData() {
		return DocumentType.builder().code("FV").name("Factura de venta").build();
	}
}
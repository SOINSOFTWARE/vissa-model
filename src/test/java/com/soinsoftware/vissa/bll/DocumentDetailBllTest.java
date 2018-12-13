// Soin Software, 2018
package com.soinsoftware.vissa.bll;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.vissa.exception.ModelValidationException;
import com.soinsoftware.vissa.manager.VissaManagerFactory;
import com.soinsoftware.vissa.model.Document;
import com.soinsoftware.vissa.model.DocumentDetail;
import com.soinsoftware.vissa.model.Product;

import junit.framework.TestCase;

/**
 * 
 * @author Carlos Rodriguez
 * @since 12/12/2018
 */
public class DocumentDetailBllTest extends TestCase {

	private DocumentDetailBll bll;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		VissaManagerFactory.getInstance();
		bll = DocumentDetailBll.getInstance();
		saveTestData();
	}

	public void testSelectAll() {
		final List<DocumentDetail> entities = bll.selectAll();
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSelectEnabled() {
		final List<DocumentDetail> entities = bll.selectAll(false);
		assertNotNull(entities);
		assertNotSame(entities.size(), 0);
	}

	public void testSaveInvalid() {
		try {
			bll.save(DocumentDetail.builder().build());
		} catch (Exception ex) {
			assertTrue(ex instanceof ModelValidationException);
		}
	}

	private void saveTestData() throws IOException {
		if (bll.selectAll().isEmpty()) {
			DocumentDetail documentDetail = buildTestData();
			bll.save(documentDetail);
		}
	}

	private DocumentDetail buildTestData() throws IOException {
		Document document = DocumentBll.getInstance().select("FAC-001");
		Product product = ProductBll.getInstance().select("TEST");
		return DocumentDetail.builder().document(document).product(product).build();
	}
}
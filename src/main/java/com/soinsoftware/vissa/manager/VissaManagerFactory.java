// Soin Software, 2018
package com.soinsoftware.vissa.manager;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;

/**
 * This class provides the {@link EntityManagerFactory} to connect to database.
 * 
 * @author Carlos Rodriguez
 * @since 27/11/2018
 */
public class VissaManagerFactory extends AbstractManagerFactory {

	private static VissaManagerFactory instance;

	private static final String PACKAGE_INFO = "com.soinsoftware.vissa.model";

	private static final String PERSISTENCE_UNIT_NAME = "Vissa";

	private static final String PROPERTY_FILE = "/connection.properties";

	private VissaManagerFactory() throws IOException {
		super(PACKAGE_INFO, PERSISTENCE_UNIT_NAME, PROPERTY_FILE, true);
	}

	private VissaManagerFactory(final String propertyFilePath) throws IOException {
		super(PACKAGE_INFO, PERSISTENCE_UNIT_NAME, propertyFilePath, false);
	}

	public static VissaManagerFactory getInstance() throws IOException {
		if (instance == null) {
			instance = new VissaManagerFactory();
		}
		return instance;
	}

	public static VissaManagerFactory getInstance(final String propertyFilePath) throws IOException {
		if (instance == null) {
			instance = new VissaManagerFactory(propertyFilePath);
		}
		return instance;
	}
}
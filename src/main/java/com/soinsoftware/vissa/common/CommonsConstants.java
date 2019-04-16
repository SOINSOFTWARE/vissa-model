package com.soinsoftware.vissa.common;

import org.apache.log4j.Logger;

import com.soinsoftware.vissa.common.PropertiesReader;
import com.soinsoftware.vissa.model.DocumentDetail;
import com.soinsoftware.vissa.model.MeasurementUnitProduct;

public class CommonsConstants {

	protected static final Logger log = Logger.getLogger(CommonsConstants.class);

	public static DocumentDetail CURRENT_DOCUMENT_DETAIL;
	public static MeasurementUnitProduct MEASUREMENT_UNIT_PRODUCT;
	public static String TRANSACTION_TYPE;
	public static int DIFF_HOURS = -5;
	public static Integer PAYMENT_PENDING_DAYS;

	private static final String PROPERTY_FILE = "/vissa-model.properties";

	static {
		if (PROPERTY_FILE != null) {
			if (!reloadProperties()) {
				throw new ExceptionInInitializerError("Error al inicializar archivo de propiedades: " + PROPERTY_FILE);
			}
		}
	}

	/*
	 * Cargar propiedades del archivo de propiedades
	 *
	 */
	public static boolean reloadProperties() {
		try {

			PropertiesReader properties = PropertiesReader.getInstance();
			properties.loadProperties(PROPERTY_FILE);

			String days = StringUtility.deleteWhitespace(properties.getTextProperty("pending.payments.alert.days"));
			PAYMENT_PENDING_DAYS = Integer.parseInt(days);

		} catch (Exception e) {
			log.error("Error al cargar propiedades: " + PROPERTY_FILE, e);
			return false;
		}
		return true;
	}
}

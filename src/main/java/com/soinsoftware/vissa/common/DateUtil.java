package com.soinsoftware.vissa.common;

import java.time.LocalDateTime;

public class DateUtil {



	/**
	 * Retorna la fecha inicial para reportes, hasta las 00:00:00
	 * 
	 * @return
	 */
	public static LocalDateTime getDefaultIniDate() {
		return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
	}

	/**
	 * Retorna la fecha fin para reportes, hasta las 23:59:59
	 * 
	 * @return
	 */
	public static LocalDateTime getDefaultEndDate() {
		return LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
	}
}

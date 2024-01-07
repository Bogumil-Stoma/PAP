package org.openjfx.database;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Convert {
	public static LocalDate ToLocalDate(Date date) {
		return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
}

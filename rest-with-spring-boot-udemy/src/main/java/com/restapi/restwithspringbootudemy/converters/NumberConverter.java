package com.restapi.restwithspringbootudemy.converters;

public class NumberConverter {

	public static Double convertToDouble(String strNumber) {
		if (strNumber == null)
			return 0D;
		if (isNumeric(strNumber)) {
			strNumber = strNumber.replaceAll("\\,", ".");
			return Double.parseDouble(strNumber);
		}
		return 0D;
	}

	public static boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;
		return strNumber.matches("[-+]?[0-9]*[\\,\\.]?[0-9]+");
	}
}

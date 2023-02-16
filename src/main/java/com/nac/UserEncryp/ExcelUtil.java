package com.nac.UserEncryp;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtil {

	private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String cellType(final Cell cell) {
		if (cell == null) {
			return "NONE";
		}

		if (cell.getCellTypeEnum() == CellType.BLANK) {
			return "BLANK";
		}

		if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
			return "BLANK";
		}

		if (cell.getCellTypeEnum() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
			return "STRING";
		}

		if (cell.getCellTypeEnum() == CellType.NUMERIC) {
			return "NUMBER";
		}
		if (cell.getCellTypeEnum() == CellType.ERROR) {
			return "ERROR";
		}
		if (cell.getCellTypeEnum() == CellType.FORMULA) {
			return "FORMULA";
		}
		if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
			return "BOOLEAN";
		}

		return "NONE";
	}

	public static String getExtension(String fileName) {
		char ch;
		int len;
		if (fileName == null || (len = fileName.length()) == 0 || (ch = fileName.charAt(len - 1)) == '/' || ch == '\\'
				|| // in the case of a directory
				ch == '.') // in the case of . or ..
			return "";
		int dotInd = fileName.lastIndexOf('.'),
				sepInd = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
		if (dotInd <= sepInd)
			return "";
		else
			return fileName.substring(dotInd + 1).toLowerCase();
	}

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static Double convertToDouble(Cell cell) {

		if (cellType(cell).equals("STRING")) {

			return Double.valueOf(cell.getStringCellValue());
		} else {

			return cell.getNumericCellValue();
		}

	}

	public static Long convertToLong(Cell cell) {

		if (cellType(cell).equals("STRING")) {

			return Long.valueOf(cell.getStringCellValue());
		} else {

			return new Long((long) cell.getNumericCellValue());
		}
	}

	public static Integer convertToInteger(Cell cell) {

		if (cellType(cell).equals("STRING")) {

			return Integer.valueOf(cell.getStringCellValue());
		} else {

			return new Integer((int) cell.getNumericCellValue());
		}
	}

	public static String convertToString(Cell cell) {
		
		if (cellType(cell).equals("STRING")) {

			return cell.getStringCellValue();
		} else {

			return String.valueOf(cell.getNumericCellValue());
		}
	}
}

package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;





public class Conversor {

	public static InputStream convertxlstoCSV(InputStream inputStream) throws IOException, EncryptedDocumentException, InvalidFormatException {

		Workbook wb = WorkbookFactory.create(inputStream);

		return csvConverter(wb.getSheetAt(0));
	}

	private static InputStream csvConverter(org.apache.poi.ss.usermodel.Sheet sheet) {
		Row row = null;
		String str = new String();
		for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
			row = sheet.getRow(i);
			String rowString = new String();
			for (int j = 0; j < 7; j++) {
				if (row.getCell(j) == null) {
					rowString = rowString + " ,";
				} else {
					rowString = rowString + row.getCell(j) + ",";
				}
			}
			str = str + rowString.substring(0, rowString.length() - 1) + "\n";
		}
		
		return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
	}
}

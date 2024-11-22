package GenericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains reusable methods to read and write data in excel
 * 
 * @author Harry
 *
 */
public class ExcelUtility {
	private Workbook wb;
	private FileInputStream fis;
	private FileOutputStream fos;
	private DataFormatter df;

	/**
	 * This method initializes excel file
	 * 
	 * @param excelPath
	 */
	public void excelinit(String excelPath) {
		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		df = new DataFormatter();
	}

	/**
	 * This method is used to close workbook
	 */
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to update test status in excel
	 * 
	 * @param sheetName
	 * @param expectedTestName
	 * @param status
	 * @param excelPath
	 */
	public void setTestStatus(String sheetName, String status, String excelPath, int row, int cell) {
		// Update Test status in excel
		wb.getSheet(sheetName).getRow(row).createCell(cell).setCellValue(status);

		// 7.Convert Java Readable File Into Physical File
		try {
			fos = new FileOutputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// 8.Write The data
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method Used To Read The Data From Excel
	 * 
	 * 
	 * 
	 * @param SheetName
	 * @param Row number
	 * @param Column/Cell Number
	 * @return Expected Data
	 */
	public String readSingleData(String Sheetname, int row, int cell) {
		String data = wb.getSheet(Sheetname).getRow(row).getCell(cell).getStringCellValue();
		return data;
	}

}

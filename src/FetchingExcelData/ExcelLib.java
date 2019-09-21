package FetchingExcelData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLib {
	

	public int countFieldID(String filePath, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		FileInputStream fis=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(fis);
		int notNullCount = 0;
		Sheet sheet = wb.getSheet(sheetName);
		for (Row row : sheet) {
		    for (Cell cell : row) {
		        if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
		            if (cell.getCellType() != Cell.CELL_TYPE_STRING ||
		                cell.getStringCellValue().length() > 0) {
		                notNullCount++;
		                break;
		            }
		        }
		    }
		    
		}
		return notNullCount;		
		
		
	}
	
	public String getFieldDescription(String filePath, String sheetName,int rowID) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		FileInputStream fis=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row=sh.getRow(rowID);
		String fieldDescription=row.getCell(2).getStringCellValue();
		return fieldDescription;
	}
	
	public String getAPVariable(String filePath, String sheetName,int rowID) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		FileInputStream fis=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		
		Row row=sh.getRow(rowID);
		String variable=row.getCell(3).getStringCellValue();
		return variable;
	}

	public String getDocumentName(String filePath, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		FileInputStream fis=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row=sh.getRow(2);
		String documentName=row.getCell(1).getStringCellValue();
		return documentName;
	}
	
	public String getFormName(String filePath,String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		FileInputStream fis=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row=sh.getRow(4);
		String formName=row.getCell(1).getStringCellValue();
		return formName;
	}
	
	public String getApplicationName(String filePath, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		FileInputStream fis=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row=sh.getRow(6);
		String formName=row.getCell(1).getStringCellValue();
		return formName;
	}
	public String getPhase(String filePath, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		FileInputStream fis=new FileInputStream(filePath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row=sh.getRow(7);
		String phaseName=row.getCell(1).getStringCellValue();
		return phaseName;
	}
	public int getFormID(String filePath2, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
	
		FileInputStream fis=new FileInputStream(filePath2);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row=sh.getRow(1);
		int formID= row.getCell(1).getCellType();
		System.out.println(formID);
		return formID+1;
	}
	
}

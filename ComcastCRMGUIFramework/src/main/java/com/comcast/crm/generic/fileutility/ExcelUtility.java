package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		//close the excel other wise object of excel opened and it may crash the excel
		wb.close();
		return data;
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis=new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheetName).getLastRowNum();
		//close the excel other wise object of excel opened and it may crash the excel
		wb.close();
		return rowCount;
	}

	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/testScriptData.xlsx");
		//FileInputStream fis=new FileInputStream("C:\\Users\\shwet\\OneDrive\\Desktop\\T@Table.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);;

		FileOutputStream fos=new FileOutputStream("./testData/testScriptData.xlsx");
		//FileOutputStream fos=new FileOutputStream("C:\\Users\\shwet\\OneDrive\\Desktop\\T@Table.xlsx");
		wb.write(fos);
		wb.close();
	}

	public String onCondition(String sheetName) throws EncryptedDocumentException, IOException
	{
		String Data = "TC_03";

		FileInputStream fis = new FileInputStream("./src/test/resources/non_Sponsored.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int LastRow = sh.getLastRowNum();
		//		System.out.println(LastRow);

		for(int i=0;i<=LastRow;i++)
		{
			String ActualData = sh.getRow(i).getCell(0).getStringCellValue();
			if(Data.equalsIgnoreCase(ActualData))
			{
				int lastCell = sh.getRow(i).getLastCellNum();
				//				System.out.println(lastCell);
				for(int j=1;j<lastCell;j++)
					Data = Data+"\t"+sh.getRow(i).getCell(j).getStringCellValue();			
			}
		}
		return Data;

	}
	
	public int getColCount(String sheetName, int rowNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int colCount = wb.getSheet(sheetName).getRow(rowNum).getLastCellNum();
		//close the excel other wise object of excel opened and it may crash the excel
		wb.close();
		return colCount;
	}
}

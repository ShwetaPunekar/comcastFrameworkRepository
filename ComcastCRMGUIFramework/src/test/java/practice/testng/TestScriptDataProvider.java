package practice.testng;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class TestScriptDataProvider 
{
	ExcelUtility eLib=new ExcelUtility();


	@Test
	public void simple() throws EncryptedDocumentException, IOException
	{
	    int rowCount = eLib.getRowCount("DataProvider")+1;
		int colCount = eLib.getColCount("DataProvider", 1);

		System.out.println(colCount);

	}
	@Test(dataProvider = "loginData")
	public void getProductInfoTest(String Col1, String Col2) throws InterruptedException
	{

		System.out.println(Col1+"    "+Col2);
	}

		@DataProvider
		public Object[][] loginData() throws EncryptedDocumentException, IOException
		{
			ExcelUtility eLib=new ExcelUtility();
			
			int rowCount = eLib.getRowCount("DataProvider");
			
			Object[][] objArr = new Object[rowCount][2];	
			
			for(int i=0;i<rowCount;i++)
			{
			objArr[i][0] = eLib.getDataFromExcel("DataProvider", i+1, 0);
			objArr[i][1] = eLib.getDataFromExcel("DataProvider", i+1, 1);	
			}
			
			return objArr;
		}

//	@DataProvider
//	public Object[][]  loginData() throws EncryptedDocumentException, IOException
//	{
//
//
//		int rowCount = eLib.getRowCount("DataProvider")+1;
//		int colCount = eLib.getColCount("DataProvider", rowCount);
//
//		System.out.println(colCount);
//		Object[][] objArr = new Object[rowCount][colCount];	
//
//		for(int i=1;i<rowCount;i++)
//		{
//			for(int j=0;j<colCount;j++)
//			{
//				objArr[i][j] = eLib.getDataFromExcel("DataProvider", i, j);
//			}
//
//		}
//		return objArr;
//	}
}

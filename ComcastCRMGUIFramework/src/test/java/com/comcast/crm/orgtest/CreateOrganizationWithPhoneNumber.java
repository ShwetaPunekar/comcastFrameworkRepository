 package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithPhoneNumber 
{
	public static void main(String[] args) throws IOException
	{
		//Create an object for property file
		FileUtility fLib=new FileUtility();

		//Create an object for Excel file
		ExcelUtility eLib=new ExcelUtility();

		//Create an object for java utility
		JavaUtility jLib=new JavaUtility();

		//read common data from Property file
		String BROWSER=fLib.getDataFromPropertiesFile("browser");
		String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME=fLib.getDataFromPropertiesFile("username");
		String PASSWORD=fLib.getDataFromPropertiesFile("password");


		//read the testScript data from Excel file
		String orgName=eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNum();
		String phNum = eLib.getDataFromExcel("org", 7, 3);
		
		

		//polymorphisam program
		WebDriver driver=null;
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}	

		//Step 1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 2: navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 3: Click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		
		//Step 4: enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		//before clicking save button -> to select the value from dropdown
		driver.findElement(By.id("phone")).sendKeys(phNum);
		
		
		driver.findElement(By.className("save")).click();
		
		//===========================Validation==================================
		
		
		
		//TestCase : 2
		
		//Verify the Phone number info
		String actualPhoneNum=driver.findElement(By.id("dtlview_Phone")).getText();
		if(actualPhoneNum.equals(phNum))
		{
			System.out.println(phNum + " information is verified==PASS");
		}
		else
		{
			System.out.println(phNum + " information is not verified==FAIL");
		}
		
		

		
		//Step 5: LogOut
		driver.quit();

	}

}

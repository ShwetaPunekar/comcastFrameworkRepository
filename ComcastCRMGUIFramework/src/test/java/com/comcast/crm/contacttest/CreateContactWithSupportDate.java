package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDate {

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
		String lastName=eLib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNum();

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
		driver.findElement(By.linkText("Contacts")).click();

		//Step 3: Click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();

		//Step 4: enter all the details and create new organization
		String StartDate = jLib.getSystemDateYYYYMMDD();
		String EndDate = jLib.getRequiredDataYYYYMMDD(30);
				
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		//start date program
		//Current date
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(StartDate);
		
		//end date program
		//date for 30 days
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(EndDate);
		
		driver.findElement(By.className("save")).click();


		//TestCase : 4

		//verify Header msg expected result
		String actualStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actualStartDate.equals(StartDate))
		{
			System.out.println(StartDate + " info is verified==PASS");
		}
		else
		{
			System.out.println(StartDate + " info is verified==FAIL");
		}
		
		String actualEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actualEndDate.equals(EndDate))
		{
			System.out.println(EndDate + " info is verified==PASS");
		}
		else
		{
			System.out.println(EndDate + " info is verified==FAIL");
		}

		//Step 5: LogOut			
		driver.quit();

	}

}

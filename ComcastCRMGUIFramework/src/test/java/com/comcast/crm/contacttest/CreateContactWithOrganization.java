package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrganization {

	public static void main(String[] args) throws IOException, InterruptedException 
	{		
		//Create an object for property file
		FileUtility fLib=new FileUtility();

		//Create an object for Excel file
		ExcelUtility eLib=new ExcelUtility();

		//Create an object for java utility
		JavaUtility jLib=new JavaUtility();

		//Create an object for WebDriver utility
		WebDriverUtility wLib=new WebDriverUtility();

		//read common data from Property file
		String BROWSER=fLib.getDataFromPropertiesFile("browser");
		String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME=fLib.getDataFromPropertiesFile("username");
		String PASSWORD=fLib.getDataFromPropertiesFile("password");


		//read the testScript data from Excel file
		String orgName=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNum();
		String contactlastName = eLib.getDataFromExcel("contact", 7, 3);



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
		driver.manage().window().maximize();
		
		wLib.waitForPageLoad(driver);
		
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
		driver.findElement(By.className("save")).click();

		//verify Header msg expected result
		String headerInfo=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		//contains function is available in java to verify parcial data
		if(headerInfo.contains(orgName))
		{
			System.out.println(orgName + " header verified==PASS");
		}
		else
		{
			System.out.println(orgName + " header is not verified==FAIL");
		}

		//TestCase : 6
		//Step 5: Navigate to contact module 
		driver.findElement(By.linkText("Contacts")).click();

		//Step 6: Click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();

		//Step 7: enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(contactlastName);
		//lookUp window
		driver.findElement(By.xpath("//input[@name=\"account_name\"]/following-sibling::img")).click();

		//Switch to child window
		wLib.switchToTabOnTitle(driver, "module=Accounts&action");



		Thread.sleep(1000);
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		Thread.sleep(2000);
		driver.findElement(By.name("search")).click();
		//concantinate static to dynamic
		//very important its
		//driver.findElement(By.xpath("//a[text()='Instagram']")).click();//static xpath
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		//above one after concantinate it will be dynamic xpath its getting created dynamically in run time


		//switch to parent window
		wLib.switchToTabOnTitle(driver, "Contacts&action");

		//click on save button
		driver.findElement(By.className("save")).click();


		//verify Header phone number expected result
		headerInfo=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		//contains function is available in java to verify parcial data
		if(headerInfo.contains(contactlastName))
		{
			System.out.println(contactlastName + " Header is created==PASS");
		}
		else
		{
			System.out.println(contactlastName + " Header is not created==FAIL");
		}

		//TestCase : 1

		//verify Header msg expected result
		String actualOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actualOrgName);
		if(actualOrgName.trim().equals(orgName))
		{
			System.out.println(orgName + " information is created==PASS");
		}
		else
		{
			System.out.println(orgName + " information is not created==FAIL");
		}

		//Step 9: LogOut
		driver.quit();
	}

}

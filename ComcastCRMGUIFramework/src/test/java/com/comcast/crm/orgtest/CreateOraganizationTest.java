package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;


public class CreateOraganizationTest {

	public static void main(String[] args) throws InterruptedException, IOException 
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
		String orgName=eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNum();


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
		driver.findElement(By.className("save")).click();

		//verify Header msg expected result
		String headerInfo=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		//contains function is available in java to verify parcial data
		if(headerInfo.contains(orgName))
		{
			System.out.println(orgName + " Header is created==PASS");
		}
		else
		{
			System.out.println(orgName + " Header is not created==FAIL");
		}

		//TestCase : 1

		//verify Header msg expected result
		String actualOrgName=driver.findElement(By.id("dtlview_Organization")).getText();
		if(actualOrgName.equals(orgName))
		{
			System.out.println(orgName + " information is created==PASS");
		}
		else
		{
			System.out.println(orgName + " information is not created==FAIL");
		}


		//Step 5: LogOut
		//		Actions act=new Actions(driver);
		//		Thread.sleep(2000);
		//		act.moveToElement(driver.findElement(By.xpath("//td[@valign=\"bottom\" and @onmouseover=\"fnDropDownUser(this,'ondemand_sub','~shwe@gmail.com`');\"]"))).perform();
		//		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}

package practice.testng;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class pppppp 
{
	@Test(invocationCount = 4, threadPoolSize = 3)
	public void CreateContact() throws IOException, InterruptedException
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
		String PASSWORD=fLib.getDataFromPropertiesFile("passworddddd");


		//read the testScript data from Excel file
		String lastName=eLib.getDataFromExcel("contact", 1, 2)+jLib.getRandomNum();
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
		Thread.sleep(1000);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//Step 2: navigate to organization module
		driver.findElement(By.linkText("Contacts")).click();

		//Step 3: Click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();

		//Step 4: enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		driver.findElement(By.className("save")).click();


		//TestCase : 4

		//verify Header msg expected result
		String actualLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actualLastName.equals(lastName))
		{
			System.out.println(lastName + " info is verified==PASS");
		}
		else
		{
			System.out.println(lastName + " info is verified==FAIL");
		}

		//Step 5: LogOut			
		driver.quit();
	}
}

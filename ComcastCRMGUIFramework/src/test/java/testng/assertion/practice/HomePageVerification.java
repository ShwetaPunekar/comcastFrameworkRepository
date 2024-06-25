package testng.assertion.practice;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageVerification 
{
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");

		String ExpTitle="Home Page";

		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		String actTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
//as per the rule of the automation we got to know expected result of the test scricpt should not be verified using java if else block, it doesn't have capability to fail the testng test case
//		if(actTitle.trim().equals(ExpTitle))
//		{
//			System.out.println(ExpTitle+" page is verified==PASS");
//		}
//		else
//		{
//			System.out.println(ExpTitle+" page is not verified==FAIL");
//		}
		//hard assert
		Assert.assertEquals(actTitle, ExpTitle);
		
		driver.quit();
		System.out.println(mtd.getName()+" Test End");
	}

	@Test
	public void verifyHomePageLogo(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");


		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status = driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
//		if(status)
//		{
//			System.out.println("logo is verified");
//		}else
//		{
//			System.out.println("logo is not verified");
//		}
		
		//Assert.assertEquals(true, status);
		//hard assert
		Assert.assertTrue(status);
		driver.quit();
		System.out.println(mtd.getName()+" Test End");
	}
}

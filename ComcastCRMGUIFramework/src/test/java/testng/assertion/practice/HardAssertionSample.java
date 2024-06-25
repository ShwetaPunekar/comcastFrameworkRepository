package testng.assertion.practice;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class HardAssertionSample 
{
//	@Test
//	public void homePageTest(Method mtd)
//	{
//		System.out.println(mtd.getName()+" Test Start");
//		
//		System.out.println("Step-1");
//		System.out.println("Step-2");
//		Assert.assertEquals("Home", "Home_Page");
//		System.out.println("Step-3");
//		Assert.assertEquals("Home-CRM", "Home-CRM");
//		System.out.println("Step-4");
//		
//		System.out.println(mtd.getName()+" Test End");
//	}
//
//	@Test
//	public void verifyHomePageLogo(Method mtd)
//	{
//		System.out.println(mtd.getName()+" Test Start");
//
//		System.out.println("Step-1");
//		System.out.println("Step-2");
//		Assert.assertTrue(true);
//		System.out.println("Step-3");
//		System.out.println("Step-4");
//		
//		System.out.println(mtd.getName()+" Test End");
//	}
	
//=============================================================================================================//
	//Reports
	
	@Test
	public void homePageTest(Method mtd)
	{
		//System.out.println(mtd.getName()+" Test Start");
		Reporter.log(mtd.getName()+" Test Start");
		
//		System.out.println("Step-1");
//		System.out.println("Step-2");
//		System.out.println("Step-3");
//		System.out.println("Step-4");
//		Reporter.log("Step-1");
//		Reporter.log("Step-2");
//		Reporter.log("Step-3");
//		Reporter.log("Step-4");
		
		//With console also want to see result
		Reporter.log("Step-1",true);
		Reporter.log("Step-2",true);
		Reporter.log("Step-3",true);
		Reporter.log("Step-4",true);
		
		Reporter.log(mtd.getName()+" Test End");
	}

	@Test
	public void verifyHomePageLogo(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test Start");

		Reporter.log("Step-1",true);
		Reporter.log("Step-2",true);
		Reporter.log("Step-3",true);
		Reporter.log("Step-4",true);
		
		Reporter.log(mtd.getName()+" Test End");
	}
	
}


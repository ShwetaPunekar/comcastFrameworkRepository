package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject 
{
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	//any body have to access driver object use getter and setter method
	//getter
	public static ExtentTest getTest()
	{
		return test.get();
	}
	//setter
	public static void setTest(ExtentTest actTest)
	{
		test.set(actTest);
	}
	
	//for WebDriver
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	public static void setDriver(WebDriver actDriver)
	{
		driver.set(actDriver);
	}
	
	
}

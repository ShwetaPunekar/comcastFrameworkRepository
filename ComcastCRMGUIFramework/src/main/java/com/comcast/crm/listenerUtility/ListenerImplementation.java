package com.comcast.crm.listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

import testng.com.crm.generic.baseclassutility.BaseClass;

public class ListenerImplementation implements ITestListener, ISuiteListener
{
	public ExtentSparkReporter spark;
	//make this variable as static so that taking help of variable insert the log
	public ExtentReports report;
	//make it as public we can access in sequential testing
	public static ExtentTest test;
	
	

	
	//this onStart() will execute exactly similar to @BeforeSuite
	public void onStart(ISuite suite) {
		System.out.println("Report back up");
		//this time stamp concant with report
		String time_date = new Date().toString().replace(" ", "_").replace(":", "_");
		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceExtentReport/report"+time_date+".html");
		spark.config().setDocumentTitle("CRM test suite result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);


		//add environment information
		report = new ExtentReports();   
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows-11");
		report.setSystemInfo("BROWSER", "chrome-200");
		
	}
	
	public void onFinish(ISuite suite) {
		System.out.println("Report configuration");
		
		report.flush();
	}


	//before executing each test so add extentTest
	public void onTestStart(ITestResult result) {
		System.out.println("======>"+result.getMethod().getMethodName()+"<======");
		
		
		// write ExtentTest
		//createcontact test is hardcoded use getMethod.getMethodName
		//ExtentTest test = report.createTest("createcontact");
		 test = report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 //write test is started
		 test.log(Status.INFO, result.getMethod().getMethodName()+"===>STARTED<===");
	}
	
	
	public void onTestFailure(ITestResult result) 
	{
		String testCaseName = result.getMethod().getMethodName();
		
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
		//Extent report
		String filepath= ts.getScreenshotAs(OutputType.BASE64);
		//use this time stamp program to take report name along with time and date
		String time_date = new Date().toString().replace(" ", "_").replace(":", "_");
		//instead of error file have to give testcasename in run time and to get multiple screenshot concat with time_date
		//test.addScreenCaptureFromBase64String(filepath, "Errorfile");
		test.addScreenCaptureFromBase64String(filepath, testCaseName+"_"+time_date);
		
		//WRIRE TEST IS FAIL
		 test.log(Status.FAIL, result.getMethod().getMethodName()+"===>STARTED<===");

		
//		//EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.sdriver);
//		File src = ts.getScreenshotAs(OutputType.FILE); 
//		try {
//			
//			//whenever you take screenshot name with always test 
//			//FileUtils.copyFile(src, new File("./All_ScreenShots/test.png"));
//			//whenever take screenshot name always be failed test case name so concat without hard coding 
//			
//			FileUtils.copyFile(src, new File("./All_ScreenShots/"+testCaseName+"+"+time_date+".png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	

	public void onTestSuccess(ITestResult result) {
		//System.out.println("======>"+result.getMethod().getMethodName()+"<======");
		
		
		//WRIRE TEST IS PASSED
		 test.log(Status.PASS, result.getMethod().getMethodName()+"===>COMPLETED<===");

	}
	
}

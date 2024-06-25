package FrameWork;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class sampleReportTest 
{
	
	@Test
	public void createContactTest()
	{
//		//for reporting used system.out.println statement 
//		System.out.println("Login to app");
//		System.out.println("navigate to contact page");
//		System.out.println("create contact");
//		if("HDFC".equals("HDFC"))
//			System.out.println("contact is created");
//		else
//			System.out.println("contact is not created");
		
		//in realtime we never ever use system.out.println statement
		//create the obj for ExtentSparkReporter
		
		//Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM test suite result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		
		
		//add environment information
		ExtentReports report = new ExtentReports();   
		report.attachReporter(spark);
		report.setSystemInfo("OS", "windows-11");
		report.setSystemInfo("BROWSER", "chrome-200");
		ExtentTest test = report.createTest("createContactTest");
		
		test.log(Status.INFO,"Login to app");
		test.log(Status.INFO,"navigate to contact page");
		test.log(Status.INFO,"create contact");
		if("HDFC".equals("HDFC"))
			test.log(Status.PASS,"contact is created");
		else
			test.log(Status.FAIL,"contact is not created");
		report.flush();
		
	}
}

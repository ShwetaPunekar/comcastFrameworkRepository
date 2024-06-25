package testng.com.crm.generic.baseclassutility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.fileutility.JsonUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass 
{
	//====================================================================================================//
	//sample program
	//	@BeforeSuite
	//	public void configBS()
	//	{
	//		System.out.println("===Connect to DB=== , ===Report configaration===");
	//	}
	//	@AfterSuite
	//	public void configAS()
	//	{
	//		System.out.println("===Close DB=== , ===Report back up===");
	//	}
	//	
	//	@BeforeClass
	//	public void configBC()
	//	{
	//		System.out.println("==Launch the browser==");
	//	}
	//	@AfterClass
	//	public void configAC()
	//	{
	//		System.out.println("==close the browser==");
	//	}
	//	
	//	@BeforeMethod
	//	public void configBM()
	//	{
	//		System.out.println("=login=");
	//	}
	//	@AfterMethod
	//	public void configAM()
	//	{
	//		System.out.println("=logout=");
	//	}

	//======================================================================================================//
	//real time 
	public DatabaseUtility dLib=new DatabaseUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JsonUtility jsonLib=new JsonUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public JavaUtility jLib=new JavaUtility();
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
//	public ExtentSparkReporter spark;
//	public ExtentReports report;



	@BeforeSuite(groups = {"SmokeTest", "RegressionTest"})
	public void configBS() throws SQLException
	{
		System.out.println("===Connect to DB=== , ===Report configaration===");
		dLib.getDBConnection();
//		
//		//spark report config
//		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
//		spark.config().setDocumentTitle("CRM test suite result");
//		spark.config().setReportName("CRM report");
//		spark.config().setTheme(Theme.DARK);
//
//
//		//add environment information
//		report = new ExtentReports();   
//		report.attachReporter(spark);
//		report.setSystemInfo("OS", "windows-11");
//		report.setSystemInfo("BROWSER", "chrome-200");
	}

	@Parameters("BROWSER")
	@BeforeClass(groups = {"SmokeTest", "RegressionTest"})
	public void configBC() throws IOException
	{
		System.out.println("==Launch the browser==");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		//String BROWSER = browser;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups = {"SmokeTest", "RegressionTest"})
	public void configBM() throws IOException
	{
		System.out.println("=login=");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		wLib.waitForPageLoad(driver);
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

	}
	@AfterMethod(groups = {"SmokeTest", "RegressionTest"})
	public void configAM()
	{
		System.out.println("=logout=");
		HomePage hp = new HomePage(driver);
		hp.logOut();

	}

	@AfterClass(groups = {"SmokeTest", "RegressionTest"})
	public void configAC()
	{
		System.out.println("==close the browser==");
		driver.quit();
	}

	@AfterSuite(groups = {"SmokeTest", "RegressionTest"})
	public void configAS() throws SQLException
	{
		System.out.println("===Close DB=== , ===Report back up===");
		dLib.closeDBConnection();
		
		
		//back up of report
		//report.flush();
	}
}

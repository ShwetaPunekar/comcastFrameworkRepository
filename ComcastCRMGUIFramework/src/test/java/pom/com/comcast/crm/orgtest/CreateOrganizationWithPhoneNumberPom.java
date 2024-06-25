package pom.com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.organizationInfoPage;

public class CreateOrganizationWithPhoneNumberPom {

	public static void main(String[] args) throws IOException 
	{
		FileUtility fLib = new FileUtility();

		String BROWSER=fLib.getDataFromPropertiesFile("browser");
		String URL=fLib.getDataFromPropertiesFile("url");

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

		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		String orgName=eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNum();
		String PhoneNum=eLib.getDataFromExcel("org", 7, 3);

		//Step 1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);

		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("admin", "admin");

		//Step 2: navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();

		//Step 3: Click on "create Organization" Button
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		//Step 4: enter all the details and create new organization
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.enterPhoneNum(orgName, PhoneNum);

		//verify Header msg expected result
		organizationInfoPage oip=new organizationInfoPage(driver);
		String actPhoneNum = oip.getPhoneNumber().getText();
		if(actPhoneNum.contains(PhoneNum))
		{
			System.out.println(PhoneNum+" phone number is verified == PASS");
		}
		else
		{
			System.out.println(PhoneNum+" phone number is not verified == FAIL");
		}
		
		hp.logOut();
		
		driver.quit();
	}


}

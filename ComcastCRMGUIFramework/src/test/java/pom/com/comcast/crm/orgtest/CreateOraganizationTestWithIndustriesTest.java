package pom.com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateOraganizationTestWithIndustriesTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
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
		//				String USERNAME=fLib.getDataFromPropertiesFile("username");
		//				String PASSWORD=fLib.getDataFromPropertiesFile("password");


		//read the testScript data from Excel file
		String orgName=eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNum();
		String Industry=eLib.getDataFromExcel("org", 4, 3);


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
		cnop.createOrg(orgName, Industry);

		//verify Header msg expected result
		organizationInfoPage oip=new organizationInfoPage(driver);
		String actHeaderName = oip.getHeaderMsg().getText();
		if(actHeaderName.contains(orgName))
		{
			System.out.println(orgName+" name is verified == PASS");
		}
		else
		{
			System.out.println(orgName+" name is not verified == FAIL");
		}

		//verify industry name expected result
		String actIndustry = oip.getIndustryName().getText();
		if(actIndustry.contains(Industry))
		{
			System.out.println(Industry+" information is verified == PASS");
		}
		else
		{
			System.out.println(Industry+" information is not verified == FAIL");
		}

		//Step 5: LogOut
		hp.logOut();
		
		driver.quit();
	}

}

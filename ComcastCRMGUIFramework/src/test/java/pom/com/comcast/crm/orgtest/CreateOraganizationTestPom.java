package pom.com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.organizationInfoPage;

public class CreateOraganizationTestPom {

	public static void main(String[] args) throws IOException 
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
//		String USERNAME=fLib.getDataFromPropertiesFile("username");
//		String PASSWORD=fLib.getDataFromPropertiesFile("password");


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
		
		//pageFactory class
		//*simplify this object page initialization go to login class
		//LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
		//# adv: no need to worry about initialization because calling the constractor
		// just pass driver object then this constrator will take care of initialization
		//just simpliphy the object creation
		LoginPage lp = new LoginPage(driver);
//		lp.usernameEdt.sendKeys("admin");
//		lp.passwordEdt.sendKeys("admin");
//		lp.submitBtn.click();
		
		//after getter method adding:- instead of accessing the method directly public getter methods 
//		lp.getUsernameEdt().sendKeys("admin");
//		lp.getPasswordEdt().sendKeys("admin");
//		lp.getSubmitBtn().click();
		
		//5th rule 
		lp.loginToApp("admin", "admin");
		//==================================================================================================
		
		//Step 2: navigate to organization module
		 HomePage hp=new HomePage(driver);
		 hp.getOrgLink().click();
		
		
		//Step 3: Click on "create Organization" Button
		 OrganizationPage op=new OrganizationPage(driver);
		 op.getCreateOrgBtn().click();
		 
		//Step 4: enter all the details and create new organization
		 CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		 cnop.createOrg(orgName);

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


		//Step 5: LogOut
		hp.logOut();
		 
		 
		driver.quit();

	}

}

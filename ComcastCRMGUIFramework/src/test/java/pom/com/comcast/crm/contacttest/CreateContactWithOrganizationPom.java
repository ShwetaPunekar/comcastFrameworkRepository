package pom.com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.SwitchingWindow;
import com.comcast.crm.objectrepositoryutility.organizationInfoPage;

public class CreateContactWithOrganizationPom {

	public static void main(String[] args) throws IOException, InterruptedException 
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

		String orgName=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNum();
		String lastName=eLib.getDataFromExcel("contact", 7, 3)+jLib.getRandomNum();

		//Step 1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);

		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("admin", "admin");
//===================================================================================================
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
		 
		 //step 5:
		 hp.getContactLink().click();
		 
		 //step 6:
		 ContactsPage cp=new ContactsPage(driver);
		 cp.getContactBtn().click();
		 
		 //step 7:
		 CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		 cncp.getLastName().sendKeys(lastName);;
		 cncp.getLookUpBtn().click();
		 
		 Thread.sleep(2000);
		 WebDriverUtility wLib=new WebDriverUtility();
		 wLib.switchToBrowser(driver, "module=Accounts");
		 
		 SwitchingWindow sw=new SwitchingWindow(driver);
		 sw.getSearchTextF().sendKeys(orgName);
		 sw.getSearchBtn().click();
		 
		 driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		 
		 wLib.switchToBrowser(driver, "module=Contacts&action");
		cncp.getsaveBtn().click();
		 
		 ContactInfoPage cip=new ContactInfoPage(driver);
		 String ActualComOrgName = cip.getComOrgName().getText();
		 if(ActualComOrgName.contains(orgName))
		 {
			 System.out.println("Both Org names are same");
		 }
		 else
		 {
			 System.out.println("Both Org names are not same");
		 }
		
		 
		 
		 
		hp.logOut();
		
		driver.quit();

	}

}

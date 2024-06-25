package testng.com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

import testng.com.crm.generic.baseclassutility.BaseClass;

public class createContactTest extends BaseClass
{
//	@Test
//	public void createContact()
//	{
//		System.out.println("Execute Create contact with verify");
//	}
//	
//	@Test
//	public void createContactWithDate()
//	{
//		System.out.println("Execute create contact with date and verify");
//	}
	
	//=========================================================================================================================================//
	
	@Test(groups = "SmokeTest")
	public void createContact() throws EncryptedDocumentException, IOException
	{
		System.out.println("Execute Create contact with verify");
		String lastName=eLib.getDataFromExcel("contact", 1, 2)+jLib.getRandomNum();
		//String PhoneNum=eLib.getDataFromExcel("contact", 7, 3);

		//Step 2: navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();

		//Step 3: Click on "create Contact" Button
		ContactsPage cp=new ContactsPage(driver);
		cp.getContactBtn().click();

		//Step 4: enter last name and create new organization
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.createLastName(lastName);

		//verify Header msg expected result
		ContactInfoPage cip=new ContactInfoPage(driver);
		String ActHeaderMsg = cip.getHeaderMsg().getText();
		boolean status = ActHeaderMsg.contains(lastName);
		Assert.assertEquals(status, true);
		
		String actLastName = cip.getLastNameHeaderMsg().getText();
		SoftAssert assertObj = new SoftAssert();
		assertObj.assertEquals(actLastName, lastName);
		assertObj.assertAll();
//		if(actLastName.contains(lastName))
//		{
//			System.out.println(lastName+" this last name is verified == PASS");
//		}
//		else
//		{
//			System.out.println(lastName+" this last name is not verified == FAIL");
//		}
		
	}
	
	@Test(groups = "RegressionTest")
	public void createContactWithDate() throws EncryptedDocumentException, IOException
	{
		
		String lastName=eLib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNum();
		//String PhoneNum=eLib.getDataFromExcel("contact", 7, 3);
		
		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDataYYYYMMDD(20);
		
		//Step 2: navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();

		//Step 3: Click on "create Contact" Button
		ContactsPage cp=new ContactsPage(driver);
		cp.getContactBtn().click();

		//Step 4: enter last name and create new contact and date
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.createLastName(lastName, startDate, endDate);
		
		//verify Header msg expected result
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actLastName = cip.getLastNameHeaderMsg().getText();
		if(actLastName.contains(lastName))
		{
			System.out.println(lastName+" this last name is verified == PASS");
		}
		else
		{
			System.out.println(lastName+" this last name is not verified == FAIL");
		}
		
		//verify start date
		String actStartDate = cip.getStartDateMsg().getText();
		if(actStartDate.contains(startDate))
		{
			System.out.println(startDate+" this is verified == PASS");
		}
		else
		{
			System.out.println(startDate+" this is not verified == FAIL");
		}
		
		//verify end date
		String actEndDate = cip.getEndDateMsg().getText();
		if(actEndDate.contains(endDate))
		{
			System.out.println(endDate+" this is verified == PASS");
		}
		else
		{
			System.out.println(endDate+" this is not verified == FAIL");
		}
		
		System.out.println(endDate+"<==========="+actEndDate);
		
	}
	
	@Test(groups = "RegressionTest")
	public void createContactWithOrganization() throws EncryptedDocumentException, IOException, InterruptedException
	{
		String orgName=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNum();
		String lastName=eLib.getDataFromExcel("contact", 7, 3)+jLib.getRandomNum();

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
	}
}

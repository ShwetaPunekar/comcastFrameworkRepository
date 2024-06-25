package FrameWork;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.listenerUtility.ListenerImplementation;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.SwitchingWindow;
import com.comcast.crm.objectrepositoryutility.organizationInfoPage;

import testng.com.crm.generic.baseclassutility.BaseClass;

//Write actual logs in script to get the low level report
//Execute without xml file
@Listeners(com.comcast.crm.listenerUtility.ListenerImplementation.class)
public class CrmApp_CreateContactTest extends BaseClass
{
	@Test(groups = "SmokeTest")
	public void createContact() throws EncryptedDocumentException, IOException
	{
		//if static variable we can use this
		//ListenerImplementation.test.log(Status.INFO, "read data from Excel");
		
		
		
		//System.out.println("Execute Create contact with verify");
		//ListenerImplementation.test.log(Status.INFO, "Execute Create contact with verify");
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
		
		String lastName=eLib.getDataFromExcel("contact", 4, 3)+jLib.getRandomNum();
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
			UtilityClassObject.getTest().log(Status.INFO,lastName+" this last name is verified == PASS");
		}
		else
		{
			UtilityClassObject.getTest().log(Status.INFO,lastName+" this last name is not verified == FAIL");
			UtilityClassObject.getTest().info(lastName);
		}
		
		//verify start date
		String actStartDate = cip.getStartDateMsg().getText();
		if(actStartDate.contains(startDate))
		{
			UtilityClassObject.getTest().log(Status.INFO,startDate+" this is verified == PASS");
		}
		else
		{
			UtilityClassObject.getTest().log(Status.INFO,startDate+" this is not verified == FAIL");
		}
		
		//verify end date
		String actEndDate = cip.getEndDateMsg().getText();
		if(actEndDate.contains(endDate))
		{
			UtilityClassObject.getTest().log(Status.INFO,endDate+" this is verified == PASS");
		}
		else
		{
			UtilityClassObject.getTest().log(Status.INFO,endDate+" this is not verified == FAIL");
		}
		
		UtilityClassObject.getTest().log(Status.INFO,endDate+"<==========="+actEndDate);
		
	}
	
	@Test(groups = "RegressionTest")
	public void createContactWithOrganization() throws EncryptedDocumentException, IOException, InterruptedException
	{
//		ListenerImplementation.test.log(Status.INFO, "===>Started<===");
		UtilityClassObject.getTest().log(Status.INFO, "===>Started<===");
		
		//ListenerImplementation.test.log(Status.INFO, "read the data from Excel");
		UtilityClassObject.getTest().log(Status.INFO, "read the data from Excel");

		String orgName=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNum();
		String lastName=eLib.getDataFromExcel("contact", 7, 3)+jLib.getRandomNum();
		
//		ListenerImplementation.test.log(Status.INFO, "Navigate to contact page");
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to contact page");

		//Step 2: navigate to organization module
		HomePage hp=new HomePage(driver);
		 hp.getOrgLink().click();
		
		
		//Step 3: Click on "create Organization" Button
		 OrganizationPage op=new OrganizationPage(driver);
		 op.getCreateOrgBtn().click();
		 
//		 ListenerImplementation.test.log(Status.INFO, "Create a new Contact");
		 UtilityClassObject.getTest().log(Status.INFO, "Create a new Contact");
		 
		//Step 4: enter all the details and create new organization
		 CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		 cnop.createOrg(orgName);

		//verify Header msg expected result
//		 ListenerImplementation.test.log(Status.INFO, "verify the data");
		 UtilityClassObject.getTest().log(Status.INFO, "verify the data");
		 
		 organizationInfoPage oip=new organizationInfoPage(driver);
		 String actHeaderName = oip.getHeaderMsg().getText();
		 if(actHeaderName.contains(orgName))
		 {
			 //System.out.println(orgName+" name is verified == PASS");
//			 ListenerImplementation.test.log(Status.PASS, "name is verified == PASS");
			 UtilityClassObject.getTest().log(Status.PASS, "name is verified == PASS");
			 
		 }
		 else
		 {
			 //System.out.println(orgName+" name is not verified == FAIL");
//			 ListenerImplementation.test.log(Status.FAIL, "name is not verified == FAIL");
			 UtilityClassObject.getTest().log(Status.FAIL, "name is not verified == FAIL");
		 }
		 
		 //step 5:
		 hp.getContactLink().click();
		 //ListenerImplementation.test.log(Status.INFO, "In Contact window Contact link clicked");
		 UtilityClassObject.getTest().log(Status.INFO, "In Contact window Contact link clicked");
		 
		 //step 6:
		 ContactsPage cp=new ContactsPage(driver);
		 cp.getContactBtn().click();
//		 ListenerImplementation.test.log(Status.INFO, "Cretaed contact button");
		 UtilityClassObject.getTest().log(Status.INFO, "Cretaed contact button");
		 
		 //step 7:
		 CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		 cncp.getLastName().sendKeys(lastName);;
		 cncp.getLookUpBtn().click();
//		 ListenerImplementation.test.log(Status.INFO, "Cretaed contact button");
		 UtilityClassObject.getTest().log(Status.INFO, "Cretaed contact button");
		 
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
			 //System.out.println("Both Org names are same");
//			 ListenerImplementation.test.log(Status.INFO, "Org name is verified");
			 UtilityClassObject.getTest().log(Status.INFO, "Org name is verified");
		 }
		 else
		 {
			 //System.out.println("Both Org names are not same");
//			 ListenerImplementation.test.log(Status.INFO, "Org name is not verified");
			 UtilityClassObject.getTest().log(Status.INFO, "Org name is not verified");
		 }
	}
}

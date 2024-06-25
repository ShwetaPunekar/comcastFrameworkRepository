package testng.com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hpsf.HPSFException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.organizationInfoPage;

import testng.com.crm.generic.baseclassutility.BaseClass;

public class createOrgTest extends BaseClass
{
	//	@Test
	//	public void createOrg()
	//	{
	//		System.out.println("Execute create org name and verify");
	//	}
	//	
	//	@Test
	//	public void createOrgNameWithIndustry()
	//	{
	//		System.out.println("Execute craete org name with industry and verify");
	//	}
	//=========================================================================================================//

	@Test(groups = "SmokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException
	{
		System.out.println("Execute create org name and verify");

		//read the testScript data from Excel file
		String orgName=eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNum();


		//Step 1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

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
	}

	@Test(groups = "RegressionTest")
	public void createOrgNameWithIndustry() throws EncryptedDocumentException, IOException
	{
		System.out.println("Execute craete org name with industry and verify");


		//read the testScript data from Excel file
		String orgName=eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNum();
		String Industry=eLib.getDataFromExcel("org", 4, 3);


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
	}

	@Test(groups = "RegressionTest")
	public void createOrganizationWithPhoneNumber() throws EncryptedDocumentException, IOException
	{

		String orgName=eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNum();
		String PhoneNum=eLib.getDataFromExcel("org", 7, 3);

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
	}
}

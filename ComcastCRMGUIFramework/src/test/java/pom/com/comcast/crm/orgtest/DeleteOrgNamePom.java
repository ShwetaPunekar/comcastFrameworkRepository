package pom.com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.comcast.crm.objectrepositoryutility.organizationInfoPage;

public class DeleteOrgNamePom {

	public static void main(String[] args) throws IOException 
	{
		//Create an object for property file
				FileUtility fLib=new FileUtility();

				//Create an object for Excel file
				ExcelUtility eLib=new ExcelUtility();

				//Create an object for java utility
				JavaUtility jLib=new JavaUtility();
				
				//create an object for webDriver utility
				WebDriverUtility wLib=new WebDriverUtility();

				//read common data from Property file
				String BROWSER=fLib.getDataFromPropertiesFile("browser");
				String URL=fLib.getDataFromPropertiesFile("url");
				
				//read the testScript data from Excel file
				String orgName=eLib.getDataFromExcel("org", 10, 2)+jLib.getRandomNum();
				String orgNameDD=eLib.getDataFromExcel("org", 10, 3);


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
				LoginPage lp = new LoginPage(driver);
				
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

				 
				 //go back to organization page
				 hp.getOrgLink().click();
				 
				 //Search for organization
				 op.getSearchTxtF().sendKeys(orgName);
//				 Select sel = new Select(op.getSearchDD());
//				 sel.selectByVisibleText(orgNameDD);
//				 op.getSearchBtn().click();
				 
				 wLib.select(op.getSearchDD(), "Organization Name");
				 op.getSearchBtn().click();
				 
				 
				 //in Dynamic webtable select & delete org
				 //driver.findElement(By.xpath("//a[text()='gayatri_9876']/ancestor::tr/td[8]/a[text()='del']")).click();
				 driver.findElement(By.xpath("//a[text()='"+orgName+"']/ancestor::tr/td[8]/a[text()='del']")).click();
				 wLib.switchToAlertAndAccept(driver);
				 
				//Step 5: LogOut
				hp.logOut();
				 
				 
				driver.quit();

	}

}

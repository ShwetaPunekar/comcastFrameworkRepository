package pom.com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;


public class CreateContactPom {

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

		String lastName=eLib.getDataFromExcel("contact", 1, 2)+jLib.getRandomNum();
		//String PhoneNum=eLib.getDataFromExcel("contact", 7, 3);

		//Step 1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);

		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("admin", "admin");

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
		String actLastName = cip.getLastNameHeaderMsg().getText();
		if(actLastName.contains(lastName))
		{
			System.out.println(lastName+" this last name is verified == PASS");
		}
		else
		{
			System.out.println(lastName+" this last name is not verified == FAIL");
		}
		
		hp.logOut();
		
		driver.quit();
	}

}

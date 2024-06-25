 package practice.testng;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.FileUtility;

public class SampleTestForScreenShot 
{
	@Test
	public void AmazonTest() throws IOException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://amazon.in/");
		
		//step-1 : Create an object to EventFiringWebDriver
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		
		//Step-2 : use getScreenshotAs() to get the file type of screenshot
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		
		//add the 3rd party tool 
		//store screen shot in local drive
		FileUtils.copyFile(srcFile, new File("./All_ScreenShots/test.png"));
		driver.quit();
		
	
	}
}

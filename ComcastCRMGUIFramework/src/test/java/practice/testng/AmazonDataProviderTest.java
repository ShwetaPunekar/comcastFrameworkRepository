package practice.testng;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class AmazonDataProviderTest
{
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
		//search the product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
		
		//capture product info
		//String x = "//span[text()='"+productName+"']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/div/following-sibling::div[@class='puisg-row']/descendant::span[@class='a-price-whole']";
		String x = "//span[contains(text(),'"+productName+"')]/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/div/following-sibling::div[@class='puisg-row']/descendant::span[@class='a-price-whole']";
		
		Actions act = new Actions(driver);
		
		WebElement price = driver.findElement(By.xpath(x));
		Thread.sleep(2000);
		act.moveToElement(price).perform();
		System.out.println(price.getText());
		
		driver.quit();
	}
	
	
	
	
	
	
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		Object[][] objArr = new Object[3][2];
//		objArr[0][0] = "iphone";
//		objArr[0][1] = "iPhone 14 (128 GB) - Starlight";
//		
//		objArr[1][0] = "iphone";
//		objArr[1][1] = "iPhone 15 Plus (128 GB) - Black";
//		
//		objArr[2][0] = "iphone";
//		objArr[2][1] = "iPhone 15 (128 GB) - Green";
//		
//		return objArr;
//	}
	
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility eLib = new ExcelUtility();
		int rowCount = eLib.getRowCount("product");
		
		Object[][] objArr = new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++)
		{
		objArr[i][0] = eLib.getDataFromExcel("product", i+1, 0);
		objArr[i][1] = eLib.getDataFromExcel("product", i+1, 1);
		}
		
		return objArr;
	}
}

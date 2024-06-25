package TasksForPractice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.irctcpage;

public class irctcApplication 
{
	@Test
	public void irctc() throws AWTException, InterruptedException, EncryptedDocumentException, IOException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.irctc.co.in/nget/train-search");
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);	
				
				
		WebElement fromTF = driver.findElement(By.xpath("//input[@aria-controls='pr_id_1_list']"));
		fromTF.sendKeys("KSR BENGALURU - SBC");
		fromTF.click();
		
		WebElement loginBtn = driver.findElement(By.xpath("//a[@class='fa fa-window-close pull-right loginCloseBtn ng-tns-c19-13']"));
		if(loginBtn.isDisplayed())
		{
			loginBtn.click();
		}
		else
		{
			System.out.println("Not present");
		}

		
		WebElement toTextF = driver.findElement(By.xpath("//span[@class='ng-tns-c57-9 ui-autocomplete ui-widget']/input[@aria-controls='pr_id_2_list']"));
		toTextF.sendKeys(" HYDERABAD DECAN - HYB ", Keys.ENTER);
		 
		Thread.sleep(2000);
		
		WebElement search = driver.findElement(By.xpath("//button[@type='submit']"));
		search.click();
		
		irctcpage i=new irctcpage(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(7000, 0)");
		Thread.sleep(3000);
		
		System.out.println(i.getTrain().getText());
		WebElement A_time=driver.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]/ancestor::div[@class='form-group no-pad col-xs-12 bull-back border-all']/descendant::div[@class='white-back no-pad col-xs-12']/descendant::span[@class='time']/strong[text()='13:50 | ']"));
		System.out.println(A_time.getText());
		
		WebElement A_TrainName=driver.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]/ancestor::div[@class='form-group no-pad col-xs-12 bull-back border-all']/descendant::div[@class='white-back no-pad col-xs-12']/descendant::div[@class='col-xs-5 hidden-xs' and contains(text(),' YESVANTPUR JN | Sun, 16 Jun ')]"));
		System.out.println(A_TrainName.getText());
		
		WebElement D_Time=driver.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]/ancestor::div[@class='form-group no-pad col-xs-12 bull-back border-all']/descendant::div[@class='white-back no-pad col-xs-12']/div[@class='col-xs-7 hidden-xs']/span[@class='pull-right']/strong[text()='08:10 | ']"));
		System.out.println(D_Time.getText());
		
		WebElement D_TrainName=driver.findElement(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong[contains(text(),' SAMPARK KRANTI (12649)')]/ancestor::div[@class='form-group no-pad col-xs-12 bull-back border-all']/descendant::div[@class='white-back no-pad col-xs-12']/div[@class='col-xs-7 hidden-xs']/span[text()=' KACHEGUDA | Mon, 17 Jun']"));
		System.out.println(D_TrainName.getText());
		
		FileInputStream fis = new FileInputStream("C:\\Users\\shwet\\OneDrive\\Desktop\\tekPyramid\\irctc.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("irctc");
		Row row = sh.getRow(0);
		Cell c= row.createCell(1);
		c.setCellValue(i.getTrain().getText());
		sh.getRow(1).createCell(1).setCellValue(A_time.getText());
		sh.getRow(2).createCell(1).setCellValue(A_TrainName.getText());
		sh.getRow(3).createCell(1).setCellValue(D_Time.getText());
		sh.getRow(4).createCell(1).setCellValue(D_TrainName.getText());
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\shwet\\OneDrive\\Desktop\\tekPyramid\\irctc.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("Done");		
				
		driver.quit();
	}
}

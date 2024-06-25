package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToBrowser(WebDriver driver, String partialURL)
	{
		Set<String> set = driver.getWindowHandles();//it will store data in random order not sequence
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String win = it.next();
			driver.switchTo().window(win);
			
			String curl = driver.getCurrentUrl();
			if(curl.contains(partialURL))
				break;
		}
	}
	
	public void switchToTabOnTitle(WebDriver driver, String partialURL)
	{
		Set<String> set = driver.getWindowHandles();//it will store data in random order not sequence
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String win = it.next();
			driver.switchTo().window(win);
			
			String curl = driver.getCurrentUrl();
			if(curl.contains(partialURL))
				break;
		}
	}
	
	//MethodOverLoaded example in selenium with respect to java
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String nameID)
	{
		driver.switchTo().frame(nameID);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	//alert methods
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();;
	}
	public void switchToAlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	//DropDown
	public void select(WebElement element, String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element, int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	//action class
	public void mouseMoveOnElement(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver, WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
}

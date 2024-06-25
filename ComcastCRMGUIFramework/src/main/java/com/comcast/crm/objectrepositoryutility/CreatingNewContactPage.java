package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage 
{
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name = "lastname")
	private WebElement LastName;
	
	@FindBy(name = "button")
	private WebElement saveBtn;
	
	@FindBy(name = "support_start_date")
	private WebElement startDatetxt;
	
	@FindBy(name = "support_end_date")
	private WebElement endDatetxt;
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement lookUpBtn;
	
	
	
	
	public WebElement getLastName()
	{
		return LastName;
	}
	public WebElement getsaveBtn()
	{
		return saveBtn;
	}
	public WebElement getStartDatetxt()
	{
		return startDatetxt;
	}
	public WebElement getEndDatetxt()
	{
		return endDatetxt;
	}
	public WebElement getLookUpBtn()
	{
		return lookUpBtn;
	}
	
	
	
	public void createLastName(String lastName)
	{
		LastName.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createLastName(String lastName, String startDate, String endDate)
	{
		LastName.sendKeys(lastName);
		startDatetxt.clear();
		startDatetxt.sendKeys(startDate);
		
		endDatetxt.clear();
		endDatetxt.sendKeys(endDate);
		saveBtn.click();
	}

}
